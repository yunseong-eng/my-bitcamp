<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style>
table {
    width: 90%;
    border-collapse: collapse;
}
th, td {
    padding: 10px;
    border: 1px solid #ccc;
}
input, textarea {
    width: 87%;
    padding: 5px;
}
button {
    padding: 10px 20px;
    border: none;
}
div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
    margin-top: 5px;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script  type="text/javascript">
$(document).ready(function() {
    $('#submitBtn').click(function() {
        //유효성 검사
        var subject = $('#subject').val().trim();
        var content = $('#content').val().trim();

        //초기화
        $('#subjectDiv').html('');
        $('#contentDiv').html('');

        if (subject == '') {
            $('#subjectDiv').html('제목을 입력하세요.');
            $('#subject').focus();
            return false;
        }
        if (content == '') {
            $('#contentDiv').html('내용을 입력하세요.');
            $('#content').focus();
            return false;
        }

        //폼 전송
        $.ajax({
            type: 'POST',
            url: 'boardWrite.jsp',
            data: {
                subject: subject,
                content: content
            },
            success: function(response) {
                alert('게시글이 작성되었습니다.');
                window.location.href = 'boardList.jsp?pg=1';
            },
        });

        return false;
    });
});
</script>
</head>
<body>
<h1>
	<img src="../image/10.jpg" width="100" height="100" alt="홈"
		onclick="location.href='../index.jsp'" style="cursor: pointer;"> 글 등록
</h1>
<form name="writeForm" method="post">
    <table>
        <tr>
            <th width="100">제목</th>
            <td>
                <input type="text" name="subject" id="subject" size="50" placeholder="제목을 입력하세요">
                <div id="subjectDiv"></div> <!-- 제목에 대한 유효성 검사 메시지 -->
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
                <textarea name="content" id="content" rows="10" placeholder="내용을 입력하세요"></textarea>
                <div id="contentDiv"></div> <!-- 내용에 대한 유효성 검사 메시지 -->
            </td>
        </tr>
    </table>
    <button type="button" id="submitBtn">글쓰기</button>
    <button type="reset">다시작성</button>
</form>
</body>
</html>
