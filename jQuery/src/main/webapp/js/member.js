$(function(){
	$('#join').submit(function(){ //폼이 제출(submit)될 때 수행되는 이벤트 핸들러
		//아이디 입력 필드를 가져옴
		let user_id = $('input[name="user_id"]').val();/*val(value속성)*/
		//아이디가 비어있는지 확인
		//if(!user_id){
		if(user_id == ''){	
			alert("아이디를 입력하세요");
			$('input[name=user_id]').focus(); //아이디 입력 필드로 포커스 이동
			return false; //폼 제출 중단
		}
		
		let user_pw = $('input[name="user_pw"]').val();
		if(user_pw == ''){
			alert("비밀번호를 입력하세요");
			$('input[name="user_pw"]').focus();
			return false;
		}	
		
		if(!$('input[name="gender"]').is(':checked')) {
			alert("성별을 입력하세요");
			
			//남자 선택
			//radio는 배열로 받는다. 남자 : gender[0], 여자 gender[1]
			//document.form1.gender[0].checked = true;
			
			$('input[name="gender"]:eq(0)').attr('checked', true) /*attr 나중에 동적으로 속성을 부여*/
						
			return false;			
		}
		
		let email = $('input[name="email"]').val();
		if(!email){
			alert("이메일을 입력하세요");
			$('input[name="email"]').focus();			
			return false;
		}
		
		let url = $('input[name="url"]').val();
		if(!url){
			alert("URL을 입력하세요");
			$('input[name="url"]').focus();			
			return false;
		}
		
		let phone = $('input[name="phone"]').val();
		if(!phone){
			alert("핸드폰 번호를 입력하세요");
			$('input[name="phone"]').focus();			
			return false;
		}
		
		if(!$('input[name="hobby"]').is(':checked')){
		    alert("취미를 선택하세요");
		    $('input[name="hobby"]:eq(1)').attr('checked', true)
		    return false;
	    }
		        
		if($('select[name="job"] > option:selected').index() == 0){
		    alert("직업을 선택하세요");
		    $('select[name="job"] > option:eq(1)').attr('selected', true)
		    return false;
		}
		        
		 // 입력한 내용을 화면에 출력
		 let gender = $('input[name="gender"]:checked').val();
		 
		 let hobby = $('input[name="hobby"]:checked') //여러개 선택, 배열
		 let hobby_val = ''; //취미 값을 저장할 변수 초기화
		 hobby.each(function(){
			 hobby_val += $(this).val()+", " //선택된 취미들을 콤마로 구분
		 });
		 
		 let job = $('select[name="job"] > option:selected').val();
		 
		 //최종결과를 화면에 출력
		 let result = `
		 
		 	<ul>
			<li>아이디 : ` + user_id + `</li>
			<li>비밀번호 : ` + user_pw + `</li>
			<li>성별 : ` + gender + `</li>
			<li>이메일 : ` + email + `</li>
			<li>URL : ` + url + `</li>
			<li>핸드폰 : ` + phone + `</li>
			<li>취미 : ` + hobby_val + `</li>
			<li>직업 : ` + job + `</li>
			</ul>`;
			
		$('body').html(result)	//body태그 내부의 내용을 result로 대체
		 	
		 return false; //폼 제출 중단 (페이지 리로드 방지)
   });
});
