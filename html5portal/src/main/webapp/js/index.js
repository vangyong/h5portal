function sendAjaxReq(){
			
			var UserLoginVo = {
					user_login_name : $("#username").val(),
					user_password : $("#password").val()
			};
			$.ajax({
				url:  "portalTest/sendAjaxReq",
				type: "POST",
				async: true,
				dataType : "json",
				contentType: "application/json",
				data: JSON.stringify(UserLoginVo),
				//beforeSend : function(req) {
				       // req.setRequestHeader('Authorization', make_basic_auth('lxiao','password'));
				//},
			    success : function(result) {
			    	
			    	var returValue = result;
			    	alert("SUCCESS");
			    	
			    	//document.getElementById("div1").innerHTML=result.userVo.user_name;
			    }
			});
} 