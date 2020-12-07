package org.jht.dto;

public class PageConditionDTO {
	// 페이지수(1,2,3,4)
	private int pageNum;
	// 페이지 당 처리 되어야 할 데이터 수(10)
	private int amount;
	// 검색 기준
	private String type;
	// 검색 키워드
	private String keyword;
	
	public PageConditionDTO() {// 기본생성자
		this(1,5); // 다른 생성자 호출.
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PageConditionDTO [pageNum=" + pageNum + ", amount=" + amount + ", type=" + type + ", keyword=" + keyword
				+ "]";
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PageConditionDTO(int pageNum,int amount) { // 매개변수 두개인 생성자
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
