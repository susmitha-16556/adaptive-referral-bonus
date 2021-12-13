package com.hcl.Referral.Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.Referral.model.UserModel;

@RestController
public class ResetController {

	@PostMapping("/reset")
	public UserModel add(@RequestBody UserModel usermodel) {
		UserModel item = new UserModel(usermodel.getUserName(), usermodel.getPassword());

		String name = usermodel.getUserName();

		try {
			FileInputStream filename = new FileInputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(filename);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Row row1 = sheet.getRow(0);

			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				if (row1.getCell(1).getStringCellValue().equals(name))
					row1.getCell(3).setCellValue(usermodel.getPassword());
				row1 = itr.next();
			}

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
}