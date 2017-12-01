package app.service.impl;

import static org.mockito.Matchers.doubleThat;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import app.entity.DivideRate;
import app.entity.SubTask;
import app.entity.Task;
import app.entity.ValueItem;
import app.entity.ValueWay;
import app.repository.ChannelStatisticRepository;
import app.service.ChannelStatisticService;
import app.service.SubTaskService;
import app.service.ValueItemService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChannelStatisticServiceImpl implements ChannelStatisticService {

	@Inject
	ChannelStatisticRepository channelStatisticRepository;

	@Inject
	SubTaskService subTaskService;

	@Inject
	ValueItemService valueItemService;

	@Override
	public ChannelStatistic findById(Long id) {
		return channelStatisticRepository.findById(id);
	}

	@Override
	@Transactional
	public ChannelStatistic create(ChannelStatistic statistic) {
		Date date = statistic.getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> exist = channelStatisticRepository.findByvalueItemIdAndDate(statistic.getValueItem().getId(),
				df.format(date));
		if (exist != null && exist.size() > 0) {

		} else {
			channelStatisticRepository.save(statistic);
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
		return channelStatisticRepository.findByUsername(id);
	}

	public List<Double> findTotalIncomeByUsername(Long id) {
		return channelStatisticRepository.findTotalIncomeByUsername(id);
	}

	public List<Object[]> findAllTaskStatistics() {
		return channelStatisticRepository.findAllTaskStatistics();
	}

	public List<ChannelStatistic> findALl() {
		Iterable<ValueItem> allValueItem  = valueItemService.findAll();
		return channelStatisticRepository.findAll();
	}

	public List<Object[]> findAllValueItemStatistics() {
		return channelStatisticRepository.findAllValueItemStatistics();
	}
	
	@Override
	public void delete(Long id) {
		ChannelStatistic channelStatistic = findById(id);
		if (channelStatistic != null) {
			channelStatisticRepository.delete(id);
		}
	}

	private void getValueItemFromWorkBook(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);

		ChannelStatistic statistic = new ChannelStatistic();
		Row row = sheet.getRow(1);

		Cell subTaskNameCell = row.getCell(5);
		if (subTaskNameCell != null && subTaskNameCell.getStringCellValue() != null) {
			String subTaskName = subTaskNameCell.getStringCellValue();
			List<SubTask> subTasks = subTaskService.findByName(subTaskName);
			if (subTasks != null && subTasks.size() > 0) {
				SubTask subTask = subTasks.get(0);
				Cell dateCell = row.getCell(4);
				Date date = dateCell.getDateCellValue();
				ValueItem currentValueItem = valueItemService.findCurrentValueItemBySubTaskIdAndDate(subTask.getId(), date);
				if (currentValueItem != null) {
					statistic.setDate(date);
					statistic.setValueItem(currentValueItem);
					DivideRate dr = currentValueItem.getDivideRate();
					
					ValueWay valueWay = currentValueItem.getValueWay();
					Cell clickCountCell = row.getCell(1);
					long clicCount = Math.round(clickCountCell.getNumericCellValue());
					statistic.setClickCount(clicCount);
					
					if((valueWay.getValueWayName().equals("cps")||valueWay.getValueWayName().equals("cpa"))&&null!=dr){
						Cell cpsDataCell = row.getCell(3);
						long resultCount = Math.round(cpsDataCell.getNumericCellValue());
						statistic.setResultCount(resultCount);

						double resultRate = 0;
						if(clicCount>0){
							resultRate = (double)resultCount/(double)clicCount;
						}
						statistic.setResultRate(resultRate);
						
						
						
						double income = resultCount*currentValueItem.getPurchasePrice()*dr.getRate();
						statistic.setIncome(income);
						statistic.setPurchasePrice(currentValueItem.getPurchasePrice());
					}else{
					DecimalFormat df = new DecimalFormat("0.00");


					Cell showCountCell = row.getCell(0);
					long showCount = Math.round(showCountCell.getNumericCellValue());
					statistic.setShowCount(showCount);
					
					double price = currentValueItem.getPurchasePrice();
					statistic.setPurchasePrice(price);

					double clickRate = 0.00;
					if(showCount!=0)
					{
						clickRate = (double)100*clicCount/showCount;
						String clickRateStr = df.format(clickRate);
						clickRate = Double.valueOf(clickRateStr);
						
					}
					
					statistic.setClickRate(clickRate);

					double cpm = price*1000;
					statistic.setCPM(cpm);
					
					Cell incomeCell = row.getCell(3);
					double income = incomeCell.getNumericCellValue();
					statistic.setIncome(income);
					
					Cell appCell = row.getCell(6);
					appCell.setCellType(Cell.CELL_TYPE_STRING);
					if(null!=appCell&&null!=appCell.getStringCellValue()){
						statistic.setAppName(appCell.getStringCellValue());
					}
					
					Cell adverCell = row.getCell(7);
					adverCell.setCellType(Cell.CELL_TYPE_STRING);
					if(null!=adverCell&&null!=adverCell.getStringCellValue()){
						statistic.setAdverName(adverCell.getStringCellValue());
					}
					
					double fillRate = 0.00;
					Cell fillRateCell = row.getCell(8);
					if(null!=fillRateCell){
						double rate = 100*fillRateCell.getNumericCellValue();
						String fillRateStr = df.format(rate);
						fillRate = Double.valueOf(fillRateStr);
					}
					statistic.setFillRate(fillRate);
					
					Cell ecmpCell = row.getCell(2);
					if(null!=ecmpCell){
						statistic.setEcmp(ecmpCell.getNumericCellValue());
					}
					}
					create(statistic);
				}
			}
		}
	}

}
