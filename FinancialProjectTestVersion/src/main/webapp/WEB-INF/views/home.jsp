<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<style type="text/css">
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
</head>
<body>
	<table width="100%" height="100%" cellspacing="0">
		<tr height="10%">
			<td class="titleTD"><header>
					<nav class="titleNav">
						<ul class="titleUL">
							<li class="titleLI"><a class="titleA" href="singleStockPage.htm" title="stockPage"
								target="main">Stock</a></li>
							<li class="titleLI"><a class="titleA" href="earningDate.htm" title="earningDateCalendar"
								target="main">Earning Date Calendar</a></li>
							<li class="titleLI"><a class="titleA" href="stockCompare.htm" title="stockCompare"
								target="main">Stock Comparison</a></li>
							<li class="titleLI"><a class="titleA" href="stockFilter.htm" title="stockFilter"
								target="main">Stock Filter</a></li>
						</ul>
					</nav>
				</header></td>
		</tr>
		<tr height="90%">
			<td class="titleTD"><iframe name="main" src="singleStockPage.htm"></td>
		</tr>
	</table>
</body>
</html>
