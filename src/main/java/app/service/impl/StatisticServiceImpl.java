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

import app.entity.Statistic;
import app.repository.StatisticRepository;
import app.service.StatisticService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatisticServiceImpl implements StatisticService{
	
	@Inject
	StatisticRepository statisticRepository;
	
	
	@Override
	public Statistic findById(Long id){
		return statisticRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Statistic create(Statistic statistic){
		Date date = statistic.getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> exist = statisticRepository.findByvalueItemIdAndDate(statistic.getValueItemId(), df.format(date));
		if(exist!=null &&exist.size()>0){
			
		}else{
			statisticRepository.save(statistic);
		}
		return statistic;
	}
	
	@Override
	public void store(Long id, MultipartFile file) throws Exception{
		Path path = Paths.get("/tmp/guanggao/"+System.currentTimeMillis());
		File f = path.toFile();
		if(!f.exists()){
			f.mkdir();
		}
		try{
			if(file.isEmpty() ){
				throw new Exception("The file is empty or not a excel file");
			}else{
				path = path.resolve(file.getOriginalFilename());
				Files.copy(file.getInputStream(), path);
			}
			
			String fileName = file.getOriginalFilename();
			if(fileName.contains(".xlsx")){
				handeleXlsxFile(id, path);
			}else{
				handleXlsFile(id, path);
			}
		}catch (Exception e) {
			throw e;
		}

	}
	
	void handeleXlsxFile(Long valueItemId, Path path){
		try{
			//FileInputStream file =new FileInputStream(path.toFile());
			Workbook workbook = new XSSFWorkbook(path.toFile());
			getValueItemFromWorkBook(valueItemId, workbook);
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	
	void handleXlsFile(Long valueItemId, Path path){
		try{
			FileInputStream file =new FileInputStream(path.toFile());
			Workbook workbook = new HSSFWorkbook(file);
			getValueItemFromWorkBook(valueItemId, workbook);
		}catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	public List<Object[]> findByUsername(Long id){
		return statisticRepository.findByUsername(id);
	}
	
	public List<Double> findTotalIncomeByUsername(Long id){
		return statisticRepository.findTotalIncomeByUsername(id);
	}
	
	public List<Object[]> findAllTaskStatistics(){
		return statisticRepository.findAllTaskStatistics();
	}
	
	public List<Object[]> findAllValueItemStatistics(){
		return statisticRepository.findAllValueItemStatistics();
	}

	private void getValueItemFromWorkBook(Long valueItemId, Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		
		Statistic statistic = new Statistic();
		statistic.setValueItemId(valueItemId);
		Row row = sheet.getRow(1);
		
		Cell showCountCell = row.getCell(0);
		statistic.setShowCount(Math.round(showCountCell.getNumericCellValue()));
//		switch (showCountCell.getCellTypeEnum()){
//			case STRING: statistic.setShowCount(Long.valueOf(showCountCell.getRichStringCellValue().getString()));
//			case NUMERIC:statistic.setShowCount(Math.round(showCountCell.getNumericCellValue()));
//			default: statistic.setShowCount(new Long(0));
//		}
			 
		
		Cell clickCountCell = row.getCell(1);
		statistic.setClickCount(Math.round(clickCountCell.getNumericCellValue()));
//		switch (clickCountCell.getCellTypeEnum()){
//			case STRING: statistic.setClickCount(Long.valueOf(clickCountCell.getRichStringCellValue().getString()));
//			case NUMERIC:statistic.setClickCount(Math.round(clickCountCell.getNumericCellValue()));
//			default: statistic.setClickCount(new Long(0));
//		}
		
		Cell clickRateCell = row.getCell(2);
		statistic.setClickRate(clickRateCell.getNumericCellValue());
//		switch (clickRateCell.getCellTypeEnum()){
//			case STRING: statistic.setClickRate(Double.valueOf(clickRateCell.getRichStringCellValue().getString()));
//			case NUMERIC:statistic.setClickRate(clickRateCell.getNumericCellValue());
//			default: statistic.setClickRate(new Double(0));
//		}
		
		Cell cpmCell = row.getCell(3);
		statistic.setCPM(cpmCell.getNumericCellValue());
//		switch (cpmCell.getCellTypeEnum()){
//			case STRING: statistic.setCPM(Double.valueOf(cpmCell.getRichStringCellValue().getString()));
//			case NUMERIC:statistic.setCPM(cpmCell.getNumericCellValue());
//			default: statistic.setCPM(new Double(0));
//		}
		
		
		Cell incomeCell = row.getCell(4);
		statistic.setIncome(incomeCell.getNumericCellValue());
//		switch (incomeCell.getCellTypeEnum()){
//			case STRING: statistic.setIncome(Double.valueOf(incomeCell.getRichStringCellValue().getString()));
//			case NUMERIC:statistic.setIncome(incomeCell.getNumericCellValue());
//			default: statistic.setIncome(new Double(0));
//		}
//		switch (dateCell.getCellTypeEnum()){
//		case NUMERIC:statistic.setDate(dateCell.getDateCellValue());
//		default: statistic.setDate(new Date());
//	}		
		
		Cell dateCell = row.getCell(5);
		statistic.setDate(dateCell.getDateCellValue());	
		create(statistic);
	}

}
