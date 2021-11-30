package com.susmitha.referral;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> itr = sheet.iterator();
				while (itr.hasNext()) {
					Row row = itr.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							System.out.print(cell.getStringCellValue() + "\t\t\t");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							System.out.print(cell.getNumericCellValue() + "\t\t\t");
							break;
						default:
						}
					}
					System.out.println("");
				}
				System.out.println("\n");
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}