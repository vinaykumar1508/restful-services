package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;





import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static void readExcel(){
		
		try{
				FileInputStream fileInputStream = new FileInputStream("D://others//learning//WebServices//src//data//dataDetails.xlsx");
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				System.out.println("ExcelUtils:sheetName:"+xssfSheet.toString());
				
				Iterator<Row> iterator= xssfSheet.iterator();
				System.out.println("no of rows:"+ xssfSheet.getPhysicalNumberOfRows());
				while(iterator.hasNext()){
					System.out.println("inside row iterator");
					Row row = iterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while(cellIterator.hasNext()){
						System.out.println("inside column iterator");
						Cell cell = cellIterator.next();
						switch(cell.getCellType()){
						
						case Cell.CELL_TYPE_NUMERIC:
							System.out.println("value:"+cell.getNumericCellValue());
						case Cell.CELL_TYPE_STRING:
							System.out.println("value:"+cell.getStringCellValue());
							
						}
					}
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeToExcel(String attr1, String attr2){
		
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("D://others//learning//WebServices//src//data//dataDetails.xlsx");
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			int existedRow=xssfSheet.getPhysicalNumberOfRows();
			
			//Row row= xssfSheet.getRow(existedRow+1);
			
			Map<String , String> dataMap = new HashMap<String, String>();
			dataMap.put(attr1, attr1);
			dataMap.put(attr2, attr2);
			
			 Set<String> keyset = dataMap.keySet();
			 Row row = xssfSheet.createRow(existedRow+1);
			 int cellnum = 0;
		        for (String key : keyset)
		        {	System.out.println("key:"+key);
		           
		            String objArr = dataMap.get(key);
		            
		            
		               Cell cell = row.createCell(cellnum++);
		               if(objArr instanceof String)
		                    cell.setCellValue((String)objArr);
		                
		              
			
		        }
		        FileOutputStream out = new FileOutputStream(new File("D://others//learning//WebServices//src//data//dataDetails.xlsx"));
		        xssfWorkbook.write(out);
		        out.close();
		        
	            System.out.println("dataDetails.xlsx written successfully on disk.");
		       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	
	public static void main(String[] args) {
		//writeToExcel("vinay","kumar");
		//readExcel();
		ExcelUtils excelUtils= new ExcelUtils();
		excelUtils = null;
		if(excelUtils!=null)
		System.out.println("excelUtils:"+excelUtils);
		
	}
	
}
