package com.hcl.Referral.Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevenueController {
	@GetMapping("/get-referral-bonus")
	public int add(@RequestParam String userId) {
		int referralBonus = 0;
		try {

			FileInputStream filename = new FileInputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(filename);

			int find = Integer.parseInt(userId);
			int fmargin = 0;
			XSSFSheet sheet3 = workbook.getSheetAt(1);

			Iterator<Row> itr = sheet3.iterator();
			while (itr.hasNext()) {
				Row rows = itr.next();
				if (rows.getCell(0).getNumericCellValue() == find) {
					int type1 = 0;
					switch (rows.getCell(1).toString()) {
					case "MOB_RECHARGE":
						type1 = 4;
						break;
					case "WATER_BILL":
						type1 = 4;
						break;
					case "RENT":
						type1 = 5;
						break;
					case "INSURANCE":
						type1 = 10;
						break;
					case "SHOPPING":
						type1 = 12;
						break;
					}
					fmargin = (int) (fmargin + type1 * rows.getCell(2).getNumericCellValue() / 100);
				}
			}

			XSSFSheet sheet4 = workbook.getSheetAt(2);

			int referralPercentage = (fmargin * 100) / (int) sheet4.getRow(0).getCell(0).getNumericCellValue();

			if (referralPercentage <= 5)
				referralBonus = 51;
			else if (referralPercentage < 8)
				referralBonus = 126;
			else
				referralBonus = 251;

			XSSFSheet sheet5 = workbook.getSheetAt(0);

			XSSFRow row3 = sheet5.getRow(getRowFromUserId(sheet5, find));
			row3.getCell(2).setCellValue(referralBonus);

			FileOutputStream fileOut2 = new FileOutputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");

			workbook.write(fileOut2);
			fileOut2.close();
			workbook.close();

			System.out.println("Referral Bonus of the user " + find + " is : " + referralBonus);
		} catch (Exception e) {

		}

		return referralBonus;
	}

	public static int getRowFromUserId(XSSFSheet sheet, int userid) {
		int rownumber = 0;
		Iterator<Row> itr = sheet.iterator();
		while (itr.hasNext()) {
			Row row = itr.next();
			if (row.getCell(0).getNumericCellValue() == userid)
				break;
			rownumber++;
		}
		return rownumber;
	}
}