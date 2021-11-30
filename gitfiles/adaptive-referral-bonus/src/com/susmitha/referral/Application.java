package com.susmitha.referral;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		FileInputStream filename = null;
		XSSFWorkbook workbook = null;

		try {
			while (option != 4) {
				System.out.println(
						"1. Add a new user.\n2. Add a new payment transaction.\n3. Get a referral bonus for a user.\n4. Quit");
				option = sc.nextInt();

				filename = new FileInputStream(
						"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");
				workbook = new XSSFWorkbook(filename);

				switch (option) {
				case 1:

					System.out.println("Enter user name: ");
					String name = sc.next();

					XSSFSheet sheet = workbook.getSheetAt(0);

					int newRowIndex = getEmptyRowNumber(sheet);

					XSSFRow row = sheet.createRow((short) newRowIndex);
					row.createCell(0).setCellValue(newRowIndex + 1);
					row.createCell(1).setCellValue(name);
					row.createCell(2).setCellValue("0");
					filename.close();

					FileOutputStream fileOut = new FileOutputStream(
							"C:\\Users\\susmitha.kothamasu\\eclipse-workspace\\ReferralBonus\\ReferralBonus.xlsx");

					workbook.write(fileOut);
					fileOut.close();
					workbook.close();

					System.out.println("Successfully added new user !");

					break;

				case 2:
					System.out.println("Enter userid, payment type and payment amount.");
					int userId = sc.nextInt();
					String paymentType = sc.next();
					double paymentAmount = sc.nextDouble();
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

					break;

				case 3:
					System.out.println("Enter the userId: ");
					int find = sc.nextInt();
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
					int referralBonus = 0;
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

					break;
				case 4:
					break;
				default:
					System.out.println("Please provide valid input");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sc.close();
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