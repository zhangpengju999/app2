package app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.entity.ChannelStatistic;
import app.entity.SellerStatistic;
import app.entity.SubTask;
import app.entity.ValueItem;
import app.entity.ValueWay;
import app.repository.ChannelStatisticRepository;
import app.repository.SellerStatisticRepository;
import app.service.ChannelStatisticService;
import app.service.SellerStatisticService;
import app.service.SubTaskService;
import app.service.ValueItemService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SellerStatisticServiceImpl implements SellerStatisticService {

	@Inject
	SellerStatisticRepository sellerStatisticRepository;

	@Inject
	SubTaskService subTaskService;

	@Inject
	ValueItemService valueItemService;

	@Override
	public SellerStatistic findById(Long id) {
		return sellerStatisticRepository.findById(id);
	}

	@Override
	@Transactional
	public SellerStatistic create(SellerStatistic statistic) {
		Date date = statistic.getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> exist = sellerStatisticRepository.findBySubTaskIdAndDate(statistic.getSubTask().getId(),
				df.format(date));
		if (exist != null && exist.size() > 0) {

		} else {
			sellerStatisticRepository.save(statistic);
		}
		return statistic;
	}

	@Override
	public void store(MultipartFile file) throws Exception {
		Path path = Paths.get("/tmp/guanggao/" + System.currentTimeMillis());
		File f = path.toFile();
		if (!f.exists()) {
			f.mkdir();
		}
		try {
			if (file.isEmpty()) {
				throw new Exception("The file is empty or not a excel file");
			} else {
				path = path.resolve(file.getOriginalFilename());
				Files.copy(file.getInputStream(), path);
			}

			String fileName = file.getOriginalFilename();
			if (fileName.contains(".xlsx")) {
				handeleXlsxFile(path);
			} else {
				handleXlsFile(path);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	void handeleXlsxFile(Path path) {
		try {
			// FileInputStream file =new FileInputStream(path.toFile());
			Workbook workbook = new XSSFWorkbook(path.toFile());
			getValueItemFromWorkBook(workbook);
		} catch (Exception e) {
			// TODO: handle exception

		}

	}

	void handleXlsFile(Path path) {
		try {
			FileInputStream file = new FileInputStream(path.toFile());
			Workbook workbook = new HSSFWorkbook(file);
			getValueItemFromWorkBook(workbook);
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	public List<Object[]> findByUsername(Long id) {
		return sellerStatisticRepository.findByUsername(id);
	}

	public List<Double> findTotalIncomeByUsername(Long id) {
		return sellerStatisticRepository.findTotalIncomeByUsername(id);
	}

	public List<SellerStatistic> findALl() {
		return sellerStatisticRepository.findAll();
	}

	private void getValueItemFromWorkBook(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);

		SellerStatistic statistic = new SellerStatistic();
		Row row = sheet.getRow(1);

		Cell subTaskNameCell = row.getCell(4);
		if (subTaskNameCell != null && subTaskNameCell.getStringCellValue() != null) {
			String subTaskName = subTaskNameCell.getStringCellValue();
			List<SubTask> subTasks = subTaskService.findByName(subTaskName);
			if (subTasks != null && subTasks.size() > 0) {
				SubTask subTask = subTasks.get(0);
				statistic.setSubTask(subTask);
				
				Cell dateCell = row.getCell(3);
				Date date = dateCell.getDateCellValue();
				statistic.setDate(date);
				

				ValueWay valueWay = subTask.getParent().getValueWayId();
				if (valueWay.getValueWayName().equals("cps")) {
					Cell cpsDataCell = row.getCell(2);
					double cpsData = cpsDataCell.getNumericCellValue();

					double income = cpsData * (1 - statistic.getCheckReduceRate());
					statistic.setIncome(income);
				} else {

					Cell showCountCell = row.getCell(0);
					long showCount = Math.round(showCountCell.getNumericCellValue());
					statistic.setShowCount(showCount);

					Cell clickCountCell = row.getCell(1);
					long clicCount = Math.round(clickCountCell.getNumericCellValue());
					statistic.setClickCount(clicCount);

					double clickRate = 0;
					if (showCount != 0)
						clickRate = (double) clicCount / showCount;
					statistic.setClickRate(clickRate);

					double purchasePrice = subTask.getParent().getUnitPrice();
					double cpm = purchasePrice * 1000;
					statistic.setCPM(cpm);

					double income = purchasePrice * clicCount * (1 - statistic.getCheckReduceRate());
					statistic.setIncome(income);

					create(statistic);

					// switch (showCountCell.getCellTypeEnum()){
					// case STRING:
					// statistic.setShowCount(Long.valueOf(showCountCell.getRichStringCellValue().getString()));
					// case
					// NUMERIC:statistic.setShowCount(Math.round(showCountCell.getNumericCellValue()));
					// default: statistic.setShowCount(new Long(0));
					// }

					// switch (incomeCell.getCellTypeEnum()){
					// case STRING:
					// statistic.setIncome(Double.valueOf(incomeCell.getRichStringCellValue().getString()));
					// case
					// NUMERIC:statistic.setIncome(incomeCell.getNumericCellValue());
					// default: statistic.setIncome(new Double(0));
					// }
					// switch (dateCell.getCellTypeEnum()){
					// case
					// NUMERIC:statistic.setDate(dateCell.getDateCellValue());
					// default: statistic.setDate(new Date());
					// }

				}
			}
		}
	}

}
