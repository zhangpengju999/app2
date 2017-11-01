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
import app.entity.SellerSettle;
import app.entity.SellerStatistic;
import app.entity.SubTask;
import app.entity.ValueItem;
import app.entity.ValueWay;
import app.repository.ChannelStatisticRepository;
import app.repository.SellerStatisticRepository;
import app.repository.*;
import app.service.ChannelStatisticService;
import app.service.SellerSettleService;
import app.service.SellerStatisticService;
import app.service.SubTaskService;
import app.service.ValueItemService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SellerSettleServiceImpl implements SellerSettleService {

	@Inject
	SellerSettleRepository sellerSettleRepository;

	@Inject
	SubTaskService subTaskService;

	@Inject
	ValueItemService valueItemService;

	@Override
	public SellerSettle findById(Long id) {
		return sellerSettleRepository.findById(id);
	}

	@Override
	@Transactional
	public SellerSettle create(SellerSettle sellerSettle) {
		Long year = sellerSettle.getYear();
		Long month = sellerSettle.getMonth();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> exist = sellerSettleRepository.findBySubTaskIdAndDate(sellerSettle.getSubTask().getId(),
				year,month);
		if (exist != null && exist.size() > 0) {

		} else {
			sellerSettleRepository.save(sellerSettle);
		}
		return sellerSettle;
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
		return sellerSettleRepository.findByUsername(id);
	}

	public List<Double> findTotalIncomeByUsername(Long id) {
		return sellerSettleRepository.findTotalIncomeByUsername(id);
	}

	public List<SellerSettle> findALl() {
		return sellerSettleRepository.findAll();
	}

	private void getValueItemFromWorkBook(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);

		SellerSettle sellerSettle= new SellerSettle();
		Row row = sheet.getRow(1);

		Cell subTaskNameCell = row.getCell(5);
		if (subTaskNameCell != null && subTaskNameCell.getStringCellValue() != null) {
			String subTaskName = subTaskNameCell.getStringCellValue();
			List<SubTask> subTasks = subTaskService.findByName(subTaskName);
			if (subTasks != null && subTasks.size() > 0) {
				SubTask subTask = subTasks.get(0);
				sellerSettle.setSubTask(subTask);
				
				Cell yearCell = row.getCell(3);
				long year =  Math.round(yearCell.getNumericCellValue());
				sellerSettle.setYear(year);
				
				Cell monthCell = row.getCell(4);
				long month =  Math.round(monthCell.getNumericCellValue());
				sellerSettle.setMonth(month);
				

				ValueWay valueWay = subTask.getParent().getValueWayId();
				if (valueWay.getValueWayName().equals("cps")) {
					Cell cpsDataCell = row.getCell(2);
					double cpsData = cpsDataCell.getNumericCellValue();

					double income = cpsData * (1 - sellerSettle.getCheckReduceRate());
					sellerSettle.setIncome(income);
				} else {

					Cell showCountCell = row.getCell(0);
					long showCount = Math.round(showCountCell.getNumericCellValue());
					sellerSettle.setShowCount(showCount);

					Cell clickCountCell = row.getCell(1);
					long clicCount = Math.round(clickCountCell.getNumericCellValue());
					sellerSettle.setClickCount(clicCount);

					double clickRate = 0;
					if (showCount != 0)
						clickRate = (double) clicCount / showCount;
					sellerSettle.setClickRate(clickRate);

					double purchasePrice = subTask.getParent().getUnitPrice();
					double cpm = purchasePrice * 1000;
					sellerSettle.setCPM(cpm);

					double income = purchasePrice * clicCount * (1 - sellerSettle.getCheckReduceRate());
					sellerSettle.setIncome(income);

					create(sellerSettle);

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
