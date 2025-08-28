package com.Utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String filename) {
		File xlsxFile = new File(System.getProperty("user.dir") + "//testData//" + filename);
		XSSFWorkbook xssfworkbook = null;
		XSSFSheet xssfsheet;
		Iterator<Row> rowIterator;
		List<User> userList = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		User user;
		try {
			xssfworkbook = new XSSFWorkbook(xlsxFile);
			xssfsheet = xssfworkbook.getSheet("LoginTestData");
			rowIterator = xssfsheet.iterator();
			userList = new ArrayList<User>();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userList.add(user);
				xssfworkbook.close();
			}
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();

	}
}