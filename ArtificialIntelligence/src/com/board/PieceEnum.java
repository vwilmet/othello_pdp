package com.board;

public enum PieceEnum {
	
	EMPTYPIECE(0), WHITEPIECE(1), BLACKPIECE(2);

	private Integer value;
		
	private PieceEnum(int value){
		this.value = value;
		
	}

	public Integer getValue() {
		return value;
	}
	

}
