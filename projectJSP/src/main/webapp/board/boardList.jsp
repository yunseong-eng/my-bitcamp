<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="board.bean.BoardDTO"%>
<%@page import="board.bean.BoardPaging"%>
<%@ page import="board.dao.BoardDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
    //page번호를 받아오는
    String pgParam = request.getParameter("pg");
    int pg = 1; //기본값 1page 설정

    if (pgParam != null && !pgParam.trim().isEmpty()) {
        try {
            pg = Integer.parseInt(pgParam); //파라미터가 있으면 정수로 변환
        } catch (NumberFormatException e) {
            pg = 1; //변환에 실패하면 기본값 1페이지로 설정
        }
    }

    //한 페이지당 5개씩 글을 끊을 설정
    int endNum = pg * 5;
    int startNum = endNum - 4;

    //DB
    BoardDAO boardDAO = BoardDAO.getInstance();
    List<BoardDTO> list = boardDAO.boardList(startNum, endNum);
    
    //페이징 처리 , 총 글수 가져오기
    int totalA = boardDAO.getTotalA();
    
    BoardPaging boardPaging = new BoardPaging();
    boardPaging.setCurrentPage(pg);
    boardPaging.setPageBlock(3);
    boardPaging.setPageSize(5);
    boardPaging.setTotalA(totalA);
    
    boardPaging.makePagingHTML();
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style type="text/css">
table {
    border-collapse: collapse;
}
th, td {
    padding: 7px;
}
#currentpaging {
	border: 1px solid blue;
	color: red;
	font-size: 15pt;
	padding: 5px 8px;
	margin-right: 3px;
	margin-top: 5px;
}
#paging {
	color: black;
	font-size: 15pt;
	padding: 5px 8px;
	margin-right: 3px;
	margin-top: 5px;
}
span:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
    <h1>게시글 목록</h1>
    <table border="1">
        <tr>
            <th width="100">글번호</th>
            <th width="400">제목</th>
            <th width="150">작성자</th>
            <th width="150">작성일</th>
            <th width="100">조회수</th>
        </tr>
        <% if(list != null && !list.isEmpty()){ %>
            <% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); %>
            <% for(BoardDTO boardDTO : list) { %>
                <tr>
                    <td align="center"><%= totalA-- %></td> <!-- 글번호를 계산하여 표시 -->
                    <td><%= boardDTO.getSubject() %></td>
                    <td align="center"><%= boardDTO.getId() %></td>
                    <td align="center"><%= sdf.format(boardDTO.getLogtime()) %></td>
                    <td align="center"><%= boardDTO.getHit() %></td>
                </tr>         
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="5" align="center">게시글이 없습니다.</td>
            </tr>
        <% } %>
    </table>
    <div style="text-align: center; width: 973px; margin-top: 15px;">
    	<%=boardPaging.getPagingHTML() %>
    </div>
    
<script type="text/javascript">
function boardPaging(pg){
	location.href = "boardList.jsp?pg=" + pg;
}
</script>    
</body>
</html>















