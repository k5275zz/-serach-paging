package org.jht.dto;

public class PageDTO {
	//처음 페이지(startPage 1,11,21,31)
	private int startPage;
	//끝 페이지(endPage 10,20,30,40)
	private int endPage;
	//이전버튼(prev true/false)
	private boolean prev;
	//다음버튼(next true/false)
	private boolean next;
	//tbl_board테이블에 전체건수
	private int total;
	// PageConditionDTO 필통 추가
	private PageConditionDTO pcdto;
	
	public PageDTO(PageConditionDTO pcdto,int total) {
		this.pcdto = pcdto;
		this.total = total;
		
		//endPage 계산식
		this.endPage=(int)Math.ceil(pcdto.getPageNum()/10.0)*10; //1~10까지의 숫자가 들어왔다면 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1 올림 -> 1,1,1,1,1,1,1,1,1,1
		//startPage 계산식
		this.startPage=this.endPage-9; // 10 - 9=1, 20-9=11, 30-9=21
		
		// realendpage 계산식
		int realEnd = (int)(Math.ceil((total*1.0)/pcdto.getAmount()));
		if(realEnd < endPage) {
			this.endPage=realEnd;
		}
		//prev 조건
		this.prev =this.startPage >1;
		//next 조건
		this.next =this.endPage< realEnd;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public PageConditionDTO getPcdto() {
		return pcdto;
	}
	public void setPcdto(PageConditionDTO pcdto) {
		this.pcdto = pcdto;
	}
	@Override
	public String toString() {
		return "PageDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", pcdto=" + pcdto + "]";
	}
	
	
}
