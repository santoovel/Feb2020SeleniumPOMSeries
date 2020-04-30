package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	//C:\Santhoshbackup\selenium\Feb2020POMSeries\src\main\java\com\qa\hubspot\testdata\HubspotTestdata.xlsx
	
	public static String TEST_DATA_SHEET_PATH = "C:/Santhoshbackup/selenium/Feb2020POMSeries/src/main/java/com/qa/hubspot/testdata/HubspotTestdata.xlsx";
	
	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;

		try {
			 System.out.println("TEST_DATA_SHEET_PATH is ==> "+ TEST_DATA_SHEET_PATH);
			 
		//	FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			 
			 FileInputStream ip = new FileInputStream(Constants.PATH_TEST_DATA);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			//data = new Object[5][4];
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}
	
	/*
	 * public static Object[][] getTestData(String sheetName) {
	 * 
	 * //Object[][] data = null; try {
	 * System.out.println("TESTDATA_SHEET_PATH is ==> "+ TESTDATA_SHEET_PATH);
	 * FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH); book =
	 * WorkbookFactory.create(ip); sheet = book.getSheet(sheetName);
	 * 
	 * Object data[][] = new
	 * Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	 * 
	 * for (int i = 0; i < sheet.getLastRowNum(); i++) {
	 * 
	 * for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
	 * 
	 * data[i][k] = sheet.getRow(i + 1).getCell(k).toString(); } } return data;
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (InvalidFormatException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * 
	 * return null;
	 * 
	 * }
	 */
	

}
