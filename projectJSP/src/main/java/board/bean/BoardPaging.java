package board.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPaging {
	private int currentPage; //현재페이지
	private int pageBlock; //[이전][1][2][3][다음] ,1page는 [이전]이 안나옴, 마지막은 [다음]이 안나옴
	private int pageSize;//1페이지당 5개씩
	private int totalA; //총글수
	private StringBuffer pagingHTML;
	
	//페이지 번호를 만들기위한 함수
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA + pageSize-1) / pageSize;
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1;  //(현재페이지-1) / 3 * 3 + 1;
		int endPage = startPage + pageBlock - 1; 
		if(endPage > totalP) endPage = totalP; //endpage가 9면 9까지 못갔으니 if문을 걸어준다.
		
		if(startPage != 1)
			pagingHTML.append("<span id='paging' onclick='boardPaging(" + (startPage-1) + ")'>이전</span>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPage)
				pagingHTML.append("<span id='currentpaging' onclick='boardPaging(" + i + ")'>" + i + "</span>");
			else
				pagingHTML.append("<span id='paging' onclick='boardPaging(" + i + ")'>" + i + "</span>");
		}//for
		
		if(endPage < totalP)
			pagingHTML.append("<span id='paging' onclick='boardPaging(" + (endPage+1) + ")'>다음</span>");
	}
}






















