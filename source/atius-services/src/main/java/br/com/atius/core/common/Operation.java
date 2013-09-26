package br.com.atius.core.common;

public enum Operation {

	COPY(0), MOVE(1);

	private int code;

	private Operation(int c) {
		code = c;
	}

	public int getCode() {
		return code;
	}

}
