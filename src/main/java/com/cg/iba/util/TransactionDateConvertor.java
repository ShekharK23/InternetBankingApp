package com.cg.iba.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionDateConvertor {

	public static String convertCurrentDateToString(String dateFormatPattern) {
		LocalDate currentDate = LocalDate.now(); // Get the current date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
		return currentDate.format(formatter);
	}

	public static void main(String[] args) {
		String dateFormatPattern = "yyyy-MM-dd"; // The format pattern for the date

		String currentDateAsString = convertCurrentDateToString(dateFormatPattern);

		System.out.println("Current date as a string: " + currentDateAsString);
	}
}