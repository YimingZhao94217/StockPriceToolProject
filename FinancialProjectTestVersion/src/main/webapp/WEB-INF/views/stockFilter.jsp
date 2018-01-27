<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body{
margin-left: 150px;
margin-right: 200px;
}
nav.titleNav {
	width: 950px;
	height: 40px;
	margin: 10 auto;
	background-color: #00BFFF;
	padding: 0;
}

nav ul.titleUL {
	float: left;
	margin: 10px;
	padding: 0 0 0 0;
	width: 920px;
	list-style: none;
}

nav ul li.titleLI {
	display: inline;
}

nav ul li a.titleA {
	width: 150px;
	float: left;
	padding: 11px 30px;
	font-size: 14px;
	text-align: center;
	text-decoration: none; 
	center right no-repeat;
	color: #fff;
	outline: none;
}

nav li a.titleA:hover {
	color: #1E90FF;
}

td.titleTD {
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock - Filter</title>
</head>
<body>
<table width="100%" height="100%" cellspacing="0">
		<tr height="10%">
			<td class="titleTD">
					<nav class="titleNav">
						<ul class="titleUL">
							<li class="titleLI"><a class="titleA" href="singleStockPage.htm" >Stock</a></li>
							<li class="titleLI"><a class="titleA" href="earningDate.htm" title="earningDateCalendar">Earning Date Calendar</a></li>
							<li class="titleLI"><a class="titleA" href="stockCompare.htm" title="stockCompare">Stock Comparison</a></li>
							<li class="titleLI"><a class="titleA" href="stockFilter.htm" title="stockFilter">Stock Filter</a></li>
						</ul>
					</nav>
				</td>
		</tr>
</table>
<div id="filterSelection">
	<select id="ratioSelection">
		<c:forEach var="ratio" items="${ratios}">
			<option></option>
		</c:forEach>
	</select>
</div>
</body>
</html>