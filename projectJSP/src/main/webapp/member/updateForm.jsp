<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.bean.MemberDTO"%>
<%@ page import="member.dao.MemberDAO"%>

<%
	String id = (String)session.getAttribute("memId");

	//DB
	MemberDAO memberDAO = MemberDAO.getInstance();
	MemberDTO memberDTO = memberDAO.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<style type="text/css">
table {
	border-collapse: collapse;
}
th, td {
	padding: 5px;
}
div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#updatebutton').click(function(){
        $('#nameDiv').empty();
        $('#pwdDiv').empty();
        
        if($('#name').val() == '') {
            $('#nameDiv').html('이름을 입력하세요.');
            return false;
        } else if($('#pwd').val() == '') {
            $('#pwdDiv').html('비밀번호를 입력하세요.');
            return false;
        } else if($('#pwd').val() != $('#repwd').val()) {
            $('#pwdDiv').html('비밀번호가 일치하지 않습니다.');
            return false;
        } else {
            // AJAX로 폼 데이터 전송
            $.ajax({
                type: 'POST',
                url: 'update.jsp',
                data: $('form[name="updateForm"]').serialize(), // 폼 데이터를 시리얼라이즈해서 전송
                success: function(response) {
                    alert('회원정보 수정이 완료되었습니다.');
                    window.location.href = '../index.jsp'; // 수정 완료 후 index.jsp로 이동
                },
                error: function(xhr, status, error) {
                    console.error(error);
                }
            });
        }
    });
});

function checkPost() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수
            if (data.userSelectedType === 'R') { 
                addr = data.roadAddress;
            } else { 
                addr = data.jibunAddress;
            }
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("addr1").value = addr;
            document.getElementById("addr2").focus();
        }
    }).open();
}
</script>
</head>
<body>
<h1>
	<img src="../image/4.jpg" width="100" height="100" alt="홈" 
		 onclick="location.href='../index.jsp'" style="cursor: pointer;"> 회원정보수정
</h1>
<form name="updateForm" method="post">
	<table border="1">
		<tr>
	        <th width="100">이름</th>
	        <td>
	        	<input type="text" name="name" id="name" value="<%=memberDTO.getName() %>">
	        	<div id="nameDiv"></div>
	        </td>
	    </tr>
	    
	    <tr>
	        <th>아이디</th>
	        <td>
	        	<input type="text" name="id" id="id" size="30" value="<%=memberDTO.getId() %>" readonly>     
	        </td>
	    </tr>
	    
	    <tr>
	        <th>비밀번호</th>
	        <td>
	        	<input type="password" name="pwd" id="pwd" size="40" placeholder="비밀번호 입력">
	        	<div id="pwdDiv"></div>
	        </td>
	    </tr>
	    
	    <tr>
	        <th>재확인</th>
	        <td>
	        	<input type="password" id="repwd" size="40" placeholder="비밀번호 입력">
	        </td>
	    </tr>
	    
	    <tr>
	        <th>성별</th>
	        <td>
	        	<input type="radio" name="gender" value="0" <%= memberDTO.getGender().equals("0") ? "checked" : "" %>>남자
	            <input type="radio" name="gender" value="1" <%= memberDTO.getGender().equals("1") ? "checked" : "" %>>여자
	        </td>
	    </tr>
	    
	    <tr>
	        <th>이메일</th>
	        <td>
	        	<input type="email" name="email1" value="<%=memberDTO.getEmail1() %>">
	        	@
	        	<input type="text" name="email2" id="email2" value="<%=memberDTO.getEmail2() %>">
	        	
	        	<input type="email" name="email3" id="email3" list="email3_list" oninput="change()">        
	        	<datalist id="email3_list">
	        		<option value="직접입력"></option>
	                <option value="naver.com"/>
	                <option value="gmail.com"/>
	                <option value="daum.net"/>
	        	</datalist>
	        </td>
	    </tr>
	    
	    <tr>
	        <th>휴대전화</th>
	        <td>
	            <select name="tel1">
	                <optgroup label="hp">
	                    <option value="010" <%= memberDTO.getTel1().equals("010") ? "selected" : "" %>>010</option>
	                    <option value="011" <%= memberDTO.getTel1().equals("011") ? "selected" : "" %>>011</option>
	                    <option value="019" <%= memberDTO.getTel1().equals("019") ? "selected" : "" %>>019</option>
	                </optgroup>
	            </select>
		         -
		         <input type="text" name="tel2" size="4" maxlength="4" value="<%=memberDTO.getTel2() %>">
		         -
		         <input type="text" name="tel3" size="4" maxlength="4" value="<%=memberDTO.getTel3() %>">
			</td>
	    </tr>
	    
	    <tr>
	    	<th>주소</th>
	    	<td>
	    		<input type="text" name="zipcode" id="zipcode" size="6" readonly value="<%=memberDTO.getZipcode() %>">
	    		<button type="button" onclick="checkPost(); return false;">우편번호 검색</button><br/>
	    		<input type="text" name="addr1" id="addr1" size="60" readonly value="<%=memberDTO.getAddr1() %>"><br/>
	    		<input type="text" name="addr2" id="addr2" size="60" value="<%=memberDTO.getAddr2() %>">
	    	</td>
	    </tr>
	    
	    <tr>
	    	<td colspan="2" align="center">
	    		<input type="button" value="회원정보수정" id="updatebutton"/>
	    		<input type="reset" value="다시작성" onclick="location.reload()" />
	    	</td>
	    </tr>
	</table>
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>
