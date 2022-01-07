package com.example.excel;

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

import com.example.excel.controller.CSVController;

public class ExcelRead {

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVController.class);

	public static List<BD> getExcelData() {
		List<BD> courses = new ArrayList<>();

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
					ExcelRead e = new ExcelRead();
					BD course = e.new BD();
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

	public static void main(String[] args) throws IOException {
		String bankName = System.getProperty("bankName");
		String type = System.getProperty("type");
		String city = System.getProperty("city");
		String state = System.getProperty("state");
		String zipCode = System.getProperty("zipCode");

		getData(bankName, type, city, state, zipCode);
	}

	private static List<BD> getData(String bankName, String type, String city, String state, String zipCode) {
		List<BD> details = getExcelData();
		List<BD> filDate = filterData(details, bankName, type, city, state, zipCode);
		System.out.println("Filtered Data " + filDate);
		return filDate;
	}

	private static List<BD> filterData(List<BD> details, String bankName, String type, String city, String state, String zipCode) {
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

	class BD {
		private String id;
		private String bankName;
		private String type;
		private String city;
		private String state;
		private String zipCode;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		@Override
		public String toString() {
			return "Course [id=" + id + ", bankName=" + bankName + ", type=" + type + ", city=" + city + ", state="
					+ state + ", zipCode=" + zipCode + "]";
		}
	}

}
