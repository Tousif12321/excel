package com.example.commonutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dto.BankDetails;
import com.example.excel.controller.CSVController;

/**
 * Common utils file to perform read and contruct the excel data
 */
public class CommonUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVController.class);

	public static List<BankDetails> getExcelData() {
		List<BankDetails> courses = new ArrayList<>();

		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File("D:\\BankDetails.xls"));

			workbook.forEach(sheet -> {
				// Create a DataFormatter to format and get each cell's value as String
				DataFormatter dataFormatter = new DataFormatter();

				// loop through all rows and columns and create Course object
				int index = 0;
				for (Row row : sheet) {
					if (index++ == 0)
						continue;
					BankDetails course = new BankDetails();
					course.setId(dataFormatter.formatCellValue(row.getCell(0)));
					course.setBankName(dataFormatter.formatCellValue(row.getCell(1)));
					course.setType(dataFormatter.formatCellValue(row.getCell(2)));
					course.setCity(dataFormatter.formatCellValue(row.getCell(3)));
					course.setState(dataFormatter.formatCellValue(row.getCell(4)));
					course.setZipCode(dataFormatter.formatCellValue(row.getCell(5)));
					courses.add(course);
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return courses;
	}

	public static List<BankDetails> filterData(List<BankDetails> details, String bankName, String type, String city,
			String state, String zipCode) {
		if (bankName != null) {
			details = details.stream().filter(d -> bankName.equals(d.getBankName())).collect(Collectors.toList());
		}
		if (type != null) {
			details = details.stream().filter(d -> type.equals(d.getType())).collect(Collectors.toList());
		}
		if (city != null) {
			details = details.stream().filter(d -> city.equals(d.getCity())).collect(Collectors.toList());
		}
		if (state != null) {
			details = details.stream().filter(d -> state.equals(d.getState())).collect(Collectors.toList());
		}
		if (zipCode != null) {
			details = details.stream().filter(d -> zipCode.equals(d.getZipCode())).collect(Collectors.toList());
		}
		return details;
	}

}
