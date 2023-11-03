package com.cg.iba.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsNotFoundException extends Exception {

	private String errorMsg;
	private String  srcResName;
	
}
