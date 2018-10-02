<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ENCOG</title>
</head>
<body>
	<form method="post" action="UploadServlet" enctype="multipart/form-data">
		<input type="button" class="btn" value="Train" id="train" name="train"/>
		<input type="button" class="btn" value="Evaluate" id="evaluate" name="evaluate"/>
		<input type="file" name="dataFile" id="fileChooser"/>
		<input type="submit" value="Upload" />
		<div id="plotError" style="width: 100%"></div>
		<div id="plotExperiments" style="width: 100%"></div>
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/scripts.js"></script>
	<script type="text/javascript" src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</body>
</html>