package com.hcl.Referral.Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.Referral.model.UserModel;

@RestController
public class UserController {
	
	@PostMapping("/user")
	public UserModel add(@RequestBody UserModel usermodel) {
		UserModel item = new UserModel(usermodel.getUserName(), usermodel.getPassword());
		try {

			FileInputStream filename = new FileInputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(filename);

			String name = usermodel.getUserName();

			XSSFSheet sheet = workbook.getSheetAt(0);

			int newRowIndex = getEmptyRowNumber(sheet);

			XSSFRow row = sheet.createRow((short) newRowIndex);
			row.createCell(0).setCellValue(newRowIndex + 1);
			row.createCell(1).setCellValue(name);
			row.createCell(2).setCellValue("0");
			row.createCell(3).setCellValue(usermodel.getPassword());
			filename.close();

			FileOutputStream fileOut = new FileOutputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");

			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return item;
	}

	public static int getEmptyRowNumber(XSSFSheet sheet) {

		int count = 0;
		Iterator<Row> itr = sheet.iterator();
		while (itr.hasNext()) {
			@SuppressWarnings("unused")
			Row row1 = itr.next();
			count++;
		}
		return count;
	}
}