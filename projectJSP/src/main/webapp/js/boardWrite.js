/*$(function(){
	$('#submitBtn').click(function(){
		$('#subjectDiv').empty();
		$('#contentDiv').empty();
		
		if($('#subject').val() == '')
			$('#subjectDiv').html('제목 입력');
		
		else if($('#content').val() == '')
			$('#contentDiv').html('내용 입력');
		
		else		
			$.ajax({
				type: 'post',
				url: 'boardWrite.jsp',
				data: {
					'subject': $('#subject').val(),
					'content': $('#content').val()
				},
				success: function(){},
				error: function(e){
					console.log(e);
							}
			});
	});
});*/