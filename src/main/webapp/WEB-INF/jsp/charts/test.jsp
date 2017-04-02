<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function getCloudData() {
        $.ajax({
			type: 'GET',
            url: "/Chart.js-master/samples/get_line_data.php",
            async: false,
			dataType : "json",
            success: function (ret_values) {
				totalData = ret_values.data;
            },
            error: function (e) {
				alert(e.responseText);
            }
        });
	}

	$(document).ready(function(){
		getCloudData();
	})
</script>


</head>
<body>

</body>
</html>