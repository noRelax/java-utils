package com.itstar.erp.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	
	public static void showExcel(Map countMap, String[] str,String table ){
		//String str = "file"
		try{
			OutputStream os = new FileOutputStream(table+".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(table);
			//int i = 1;
			HSSFRow row = sheet.createRow(0);
			for(int j = 0;j<str.length;j++){
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(str[j]);
			}
			Set<String> set= countMap.keySet();
		/*	for(int k = 0;k<countMap.size();k++){
				HSSFRow row1 = sheet.createRow(k);
				for(int i = 0;i<2;i++ ){
					HSSFCell cell = row1.createCell(i);
					cell.setCellValue();
				}
			}*/
			int i = 1;
			for(String str1:set){
				HSSFRow row1 = sheet.createRow(i);
				HSSFCellStyle style = workbook.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				HSSFCell cell1 = row1.createCell(0);
				cell1.setCellValue(str1);
				cell1.setCellStyle(style);
				HSSFCell cell2 = row1.createCell(1);
				cell2.setCellValue(countMap.get(str1)+"");
				cell2.setCellStyle(style);
				i++;
			}
			workbook.write(os);
			if(os != null){
				os.close();
				os = null;
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
