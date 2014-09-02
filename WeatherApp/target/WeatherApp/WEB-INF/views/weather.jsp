<%@page import="weather.entity.Weather"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Main JSP to show weather details -->

<html>
<head>
<title>United States Weather</title>

<style type="text/css">

#divWeatherDetails{
	font-weight: bold;
	width: 350px;
}

body {
    background-color: #66C2FF;
}	

</style>

	<script type="text/javascript">
		/* JS Function to check zipCode exists or not based on API response [error:querynotfound] */
		function zipCodeNotFoundCheck() {
			if (document.getElementById("zipCodeNotFoundDiv").innerText == "true"){
				alert("ZipCode NOT Found !!");
			}
		}
		
	</script>
</head>

<body onload="zipCodeNotFoundCheck();">
	<h2 align="center">United States Weather</h2>
	<form:form action="" method="POST" commandName="weather">
	<table align="center" width="750px" border="0">
		<tr><td>
			<table border="2" style="border-color: blue;" width="750px" height="100px">
				<tr>
					<td width="375px" align="center" style="font-family: inherit; font-size: large;"> Enter US ZipCode: (ex. 10001) </td>
					<td align="left">	
						<form:input path="zipCode" /> 
			        	<form:errors style="color: red" path="zipCode"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"	align="center">
						<input type="submit" value="SUBMIT">
					</td>
				</tr>
			</table>
		</td></tr>
		<tr><td><br><br></td></tr>
		
		<% //validation to display weather details below form
			boolean queryNotFound = ((Weather) request.getAttribute("weather")).isQueryNotFound(); 
			String city = ((Weather) request.getAttribute("weather")).getCity(); 
		%>
		
		<% if ((!queryNotFound) && city != "" & city !=null) { %>
		
		<tr><td>
			<table border="2" style="border-color: blue;" width="750px" id="tableresult" align="left">
				<tr>
					<td> Weather Details for ZipCode: </td>
					<td id="divWeatherDetails">  ${zipCode} </td>
				</tr>
				<tr>
					<td> City: </td>
					<td id="divWeatherDetails"> ${ weather.city } </td>
				</tr>
				<tr>
					<td> State: </td>
					<td id="divWeatherDetails"> ${ weather.state } </td>
				</tr>		
				<tr>
					<td> Temperature (°F): </td>
					<td id="divWeatherDetails" style="font-family: sans-serif;"> ${ weather.temp_f } </td>
				</tr>
			</table>
		</td></tr>
		<% } %>
		
		<% //validation to display any exception
		   	String  error = (String) request.getAttribute("error"); 
			if (error != null && error != ""){
		%>
		<tr><td>
			<table border="8" width="750px" id="tableresult" align="left">
				<tr>
					<td colspan=""> Error : </td>
					<td id="divWeatherDetails">  ${error} </td>
				</tr>
			</table>
		</td></tr>
		<% } %>
		
		<!-- hidden variable to store queryNotFound values -->
		<tr><td><div id="zipCodeNotFoundDiv" style="display: none;">${ weather.queryNotFound }</div></td></tr>
		</table>
	</form:form>
</body>
</html>
