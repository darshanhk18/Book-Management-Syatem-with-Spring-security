package com.hcl.gradedassignment.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMessage {

	private String message;
	private int errorCode;
}
