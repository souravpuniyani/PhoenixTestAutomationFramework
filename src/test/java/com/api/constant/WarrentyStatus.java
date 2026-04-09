package com.api.constant;

public enum WarrentyStatus {

	IN_WARRENTY(1),
	OUT_WARRENTY(2);
	
	int code;
	private WarrentyStatus(int code) {
		this.code=code;
	}
	
	public int getCode() {
		return this.code;
	}
}
