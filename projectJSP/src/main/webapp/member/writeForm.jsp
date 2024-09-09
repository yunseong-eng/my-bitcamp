<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
</head>
<body class="join-background">
<h1>
	<img src="../image/9.jpg" width="100" height="100" alt="홈" 
		 onclick="location.href='../index.jsp'" style="cursor: pointer;"> 회원가입
</h1>
<!-- 회원가입 폼 -->
<form name="writeForm" method="post" action="write.jsp" class="join-form">
	<table border="1">
		<tr>
	        <th width="100">이름</th>
	        <td>
	        	<input type="text" name="name" id="name" class="join-input" placeholder="이름 입력">
	        	<div id="nameDiv" class="error-message"></div>
	        </td>
	    </tr>
	    
	    <tr>
	        <th>아이디</th>
	        <td>
	        	<input type="text" name="id" id="id" size="30" class="join-input" placeholder="아이디 입력">
	        	<button type="button" onclick="checkId()">중복체크</button>
	        	<input type="hidden" id="check" value="">
	        	<div id="idDiv" class="error-message"></div>
	        </td>
	    </tr>
	    
	    <tr>
	        <th>비밀번호</th>
	        <td>
	        	<input type="password" name="pwd" id="pwd" size="40" class="join-input" placeholder="비밀번호 입력">
	        	<div id="pwdDiv" class="error-message"></div>
	        </td>
	    </tr>
	    
	    <tr>
	        <th>재확인</th>
	        <td>
	        	<input type="password" id="repwd" size="40" class="join-input" placeholder="비밀번호 입력">
	        </td>
	    </tr>
	    
	    <tr>
	        <th>성별</th>
	        <td class="join-input">
	        	<input type="radio" name="gender" value="0" checked="checked" />남자
	            <input type="radio" name="gender" value="1" />여자
	        </td>
	    </tr>
	    
	    <tr>
	        <th>이메일</th>
	        <td class="join-input">
	        	<input type="email" name="email1" class="join-input">
	        	@
	        	<input type="text" name="email2" id="email2" class="join-input">
	        	
	        	<input type="email" name="email3" id="email3" list="email3_list" oninput="change()" class="join-input">        
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
	        <td class="join-input">
	            <select name="tel1" class="join-input">
	                <optgroup label="hp">
	                    <option value="010">010</option>
	                    <option value="011">011</option>
	                    <option value="019">019</option>
	                </optgroup>
	            </select>
		         -
		         <input type="text" name="tel2" size="4" maxlength="4" class="join-input">
		         -
		         <input type="text" name="tel3" size="4" maxlength="4" class="join-input">
			</td>
	    </tr>
	    
	    <tr>
	    	<th>주소</th>
	    	<td class="join-input">
	    		<input type="text" name="zipcode" id="zipcode" size="6" readonly class="join-input" placeholder="우편번호">
	    		<button type="button" onclick="checkPost(); return false;">우편번호 검색</button><br/>
	    		<input type="text" name="addr1" id="addr1" size="60" readonly class="join-input" placeholder="주소"><br/>
	    		<input type="text" name="addr2" id="addr2" size="60" class="join-input" placeholder="상세주소">
	    	</td>
	    </tr>
	    
	    <tr>
	    	<td colspan="2" align="center" class="button-group">
	    		<input type="button" value="회원가입" class="btn-submit" onclick="checkWrite()"/>
	    		<input type="reset" value="다시작성" class="btn-reset" />
	    	</td>
	    </tr>
	</table>
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/member.js"></script>
</body>
</html>
