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

import com.hcl.Referral.model.PaymentsModel;

@RestController
public class PaymentsController {

	@PostMapping("/pay")
	public PaymentsModel add(@RequestBody PaymentsModel paymentsmodel) {
		PaymentsModel item = new PaymentsModel(paymentsmodel.getUserId(), paymentsmodel.getPaymentType(),
				paymentsmodel.getPaymentAmount());
		System.out.println(item);

		try {

			FileInputStream filename = new FileInputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(filename);

			System.out.println("Enter userid, payment type and payment amount.");
			int userId = paymentsmodel.getUserId();
			String paymentType = paymentsmodel.getPaymentType();
			double paymentAmount = paymentsmodel.getPaymentAmount();

			int type = 0;
			double total = 0;

			switch (paymentType) {
			case "MOB_RECHARGE":
				type = 4;
				break;
			case "WATER_BILL":
				type = 4;
				break;
			case "RENT":
				type = 5;
				break;
			case "INSURANCE":
				type = 10;
				break;
			case "SHOPPING":
				type = 12;
				break;
			default:
				System.out.println(
						"Choose only one of these payments: \nMOB_RECHARGE\nWATER_BILL\nRENT\nINSURANCE\nSHOPPING");

			}

			int profitMargin = (int) ((type * paymentAmount) / 100);

			XSSFSheet sheet1 = workbook.getSheetAt(1);

			int newRow = getEmptyRowNumber(sheet1);

			XSSFRow row1 = sheet1.createRow((short) newRow);
			row1.createCell(0).setCellValue(userId);
			row1.createCell(1).setCellValue(paymentType);
			row1.createCell(2).setCellValue(paymentAmount);

			XSSFSheet sheet2 = workbook.getSheetAt(2);

			XSSFRow row2 = sheet2.getRow(0);
			total = row2.getCell(0).getNumericCellValue() + profitMargin;
			row2.getCell(0).setCellValue(total);

			FileOutputStream fileOut1 = new FileOutputStream(
					"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");

			workbook.write(fileOut1);
			fileOut1.close();
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
			Row row = itr.next();
			count++;
		}
		return count;
	}
}