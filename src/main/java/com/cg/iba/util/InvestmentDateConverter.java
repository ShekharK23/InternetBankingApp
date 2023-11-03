package com.cg.iba.util;

import java.time.LocalDate;

public class InvestmentDateConverter {
	public static LocalDate getDateFromString(String str) {

		String arr[] = str.split("-");

		if (arr.length == 3) {
			int year = Integer.parseInt(arr[2]);
			int month = Integer.parseInt(arr[1]);
			int date = Integer.parseInt(arr[0]);

			LocalDate d1 = LocalDate.of(year, month, date);
			return d1;
		}
		else return null;
	}
}
