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

import app.entity.ChannelSettle;
import app.entity.ChannelStatistic;
import app.entity.DivideRate;
import app.entity.SubTask;
import app.entity.ValueItem;
import app.entity.ValueWay;
import app.repository.ChannelSettleRepository;
import app.repository.ChannelStatisticRepository;
import app.service.ChannelSettleService;
import app.service.ChannelStatisticService;
import app.service.SubTaskService;
import app.service.ValueItemService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChannelSettleServiceImpl implements ChannelSettleService {

	@Inject
	ChannelSettleRepository channelSettleRepository;

	@Inject
	SubTaskService subTaskService;

	@Inject
	ValueItemService valueItemService;

	@Override
	public ChannelSettle findById(Long id) {
		return channelSettleRepository.findById(id);
	}

	@Override
	@Transactional
	public ChannelSettle create(ChannelSettle channelSettle) {
		Long year = channelSettle.getYear();
		Long month = channelSettle.getMonth();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> exist = channelSettleRepository.findByvalueItemIdAndDate(channelSettle.getValueItem().getId(),
				year,month);
		if (exist != null && exist.size() > 0) {

		} else {
			channelSettleRepository.save(channelSettle);
		}
		return channelSettle;
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
		return channelSettleRepository.findByUsername(id);
	}

	public List<Double> findTotalIncomeByUsername(Long id) {
		return channelSettleRepository.findTotalIncomeByUsername(id);
	}

	public List<ChannelSettle> findALl() {
		Iterable<ValueItem> allValueItem  = valueItemService.findAll();
		return channelSettleRepository.findAll();
	}

	private void getValueItemFromWorkBook(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);

		ChannelSettle channelSettle = new ChannelSettle();
		Row row = sheet.getRow(1);

		Cell subTaskNameCell = row.getCell(5);
		if (subTaskNameCell != null && subTaskNameCell.getStringCellValue() != null) {
			String subTaskName = subTaskNameCell.getStringCellValue();
			List<SubTask> subTasks = subTaskService.findByName(subTaskName);
			if (subTasks != null && subTasks.size() > 0) {
				SubTask subTask = subTasks.get(0);
				
				Cell channelCell = row.getCell(6);
				String channelName = channelCell.getStringCellValue();
				ValueItem currentValueItem = valueItemService.findCurrentValueItemBySubTaskIdAndChannelName(subTask.getId(), channelName);
				if (currentValueItem != null) {
					channelSettle.setValueItem(currentValueItem);
					DivideRate dr = currentValueItem.getDivideRate();
					
					Cell yearCell = row.getCell(3);
					long year =  Math.round(yearCell.getNumericCellValue());
					channelSettle.setYear(year);
					
					Cell monthCell = row.getCell(4);
					long month =  Math.round(monthCell.getNumericCellValue());
					channelSettle.setMonth(month);
					
					ValueWay valueWay = currentValueItem.getValueWay();
					Cell clickCountCell = row.getCell(1);
					long clicCount = Math.round(clickCountCell.getNumericCellValue());
					channelSettle.setClickCount(clicCount);
					if((valueWay.getValueWayName().equals("cps")||valueWay.getValueWayName().equals("cpa"))&&null!=dr){
						Cell cpsDataCell = row.getCell(2);
						long resultCount = Math.round(cpsDataCell.getNumericCellValue());
						channelSettle.setResultCount(resultCount);

						channelSettle.setResultRate(clicCount==0?0:resultCount/clicCount);
						
						double income = resultCount*currentValueItem.getPurchasePrice()*dr.getRate();
						channelSettle.setIncome(income);
					}else{

					Cell showCountCell = row.getCell(0);
					long showCount = Math.round(showCountCell.getNumericCellValue());
					channelSettle.setShowCount(showCount);
					
					double clickRate = 0;
					if(showCount!=0)
						clickRate = (double)clicCount/showCount;
					channelSettle.setClickRate(clickRate);

					double purchasePrice = currentValueItem.getPurchasePrice();
					double cpm = purchasePrice*1000;
					channelSettle.setCPM(cpm);
					
					double income = purchasePrice*clicCount*(1-channelSettle.getDeductRate());
					channelSettle.setIncome(income);
					}
					create(channelSettle);
				}
			}
		}
	}

}
