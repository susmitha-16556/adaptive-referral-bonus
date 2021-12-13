package com.hcl.Referral.Service;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hcl.Referral.model.UserModel;

@Service
public class ExcelService {
	public static UserModel findByUsername(String username2) {
		try {
			FileInputStream filename = new FileInputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(filename);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row1 = itr.next();
				if (row1.getCell(1).getStringCellValue().equals(username2))
					return new UserModel((int) row1.getCell(0).getNumericCellValue(),
							row1.getCell(1).getStringCellValue(), row1.getCell(3).getStringCellValue());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
