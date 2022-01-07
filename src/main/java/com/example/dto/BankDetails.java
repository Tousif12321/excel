package com.example.dto;

/**
 * DTO class for Bank Details
 */
public class BankDetails {

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
		return "Course [id=" + id + ", bankName=" + bankName + ", type=" + type + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}

}
