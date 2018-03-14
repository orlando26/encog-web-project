<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ENCOG</title>
</head>
<body>
	<form>
		<input type="button" class="btn" value="Train" id="train" name="train"/>
		<input type="button" class="btn" value="Evaluate" id="evaluate" name="evaluate"/>
		<div id="plot" style="width: 100%"></div>
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/scripts.js"></script>
	<script type="text/javascript" src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</body>
</html>