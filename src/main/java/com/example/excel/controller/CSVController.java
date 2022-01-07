package com.example.excel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.commonutils.CommonUtils;
import com.example.dto.BankDetails;

@Controller
@RequestMapping("/getData")
public class CSVController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVController.class);

	@GetMapping
	public @ResponseBody List<BankDetails> readCSV(@RequestParam(name = "bankName", required = false) String bankName,
			@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "zipCode", required = false) String zipCode) {
		LOGGER.info("Read CSV Method is Called");
		List<BankDetails> details = CommonUtils.getExcelData();
		return CommonUtils.filterData(details, bankName, type, city, state, zipCode);
	}

}
