<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
#stockTitleTd {
	font-size: 20px;
	padding-right: 5px;
}

#nameP {
	color: DarkGray;
	font-size: 16px;
}

#stockPriceTd {
	font-size: 40px;
}

#upFont {
	color: Green;
	font-size: 30px;
}

#downFont {
	color: Crimson;
	font-size: 30px;
}

#keepFont {
	font-size: 30px;
}

#selectionTable {
	float: right;
}

#selectYearTD {
	border: solid LightGrey;
	border-width: 0px 2px 0px 0px;
	padding-right: 5px;
	color: #00BFFF;
	cursor: pointer;
}

.endDateFont {
	font-size: 14px;
	color: DimGray;
}

#selectQuarterTD {
	color: DarkGrey;
	cursor: pointer;
}

div.selectionDiv {
	float: right;
}

#quarterSelection {
	display: none
}

#ratioTable {
	border-collapse: collapse;
	border-style: solid none;
	border-width: 1px;
	border-color: LightGrey;
	margin-top: 20px;
}

tr.ratioTR {
	border-style: solid none solid none;
	border-width: 1px;
	border-color: LightGrey;
}
</style>

<script lanuage="javascript">
	function test() {
		alert("test");
	}
	function viewYearClicked(self) {
		var quarterSelect = document.getElementById("quarterSelection");
		quarterSelect.style.display = "none";
		var quarterFont = document.getElementById("selectQuarterTD");
		quarterFont.style.color = "DarkGrey";
		var yearSelect = document.getElementById("yearSelection");
		yearSelect.style.display = "inline";
		self.style.color = "#00BFFF";

	}
	function viewQuarterClicked(self) {
		var yearSelect = document.getElementById("yearSelection");
		yearSelect.style.display = "none";
		var yearFont = document.getElementById("selectYearTD");
		yearFont.style.color = "DarkGrey";
		yearSelect.style.color = "DarkGrey";
		var quarterSelect = document.getElementById("quarterSelection");
		quarterSelect.style.display = "inline";
		self.style.color = "#00BFFF";

	}
	function selectYearFun(targ, selObj, restore) {
		var selection = selObj.options[selObj.selectedIndex].value;
		var yearSelect = document.getElementById("yearSelection");
		var xmlhttp;
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlhttp = new ActivateXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlhttp = new ActivateXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("does not support ajax");
					retur
					false;
				}
			}
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			}
		}
		var url = "retriveRatios.htm";
		var ticker = document.getElementById("ticker").value;
		var sendStr
		if (yearSelect.style.display == "none") {
			sendStr = "selection=" + selection + "&ticker=" + ticker + "&period=quarter";
		}else{
			sendStr = "selection=" + selection + "&ticker=" + ticker + "&period=year";
		}
		xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(sendStr);
		return true;
	}
	function selectQuarterFun(targ, selObj, restore) {
		alert(selObj.options[selObj.selectedIndex].value);
	}
</script>
</head>
<body>
<table width="100%" height="100%" cellspacing="0">
		<tr height="10%">
			<td class="titleTD">
					<nav class="titleNav">
						<ul class="titleUL">
							<li class="titleLI"><a class="titleA" href="singleStockPage.htm" title="stockPage">Stock</a></li>
							<li class="titleLI"><a class="titleA" href="earningDate.htm" title="earningDateCalendar">Earning Date Calendar</a></li>
							<li class="titleLI"><a class="titleA" href="stockCompare.htm" title="stockCompare">Stock Comparison</a></li>
							<li class="titleLI"><a class="titleA" href="stockFilter.htm" title="stockFilter">Stock Filter</a></li>
						</ul>
					</nav>
				</td>
		</tr>
		</table>
	<div id="title">
		<table>
			<tr>
				<td id="stockTitleTd">${stock.market}:${stock.symbol}
					<p id="nameP">${stock.companyName}</p>
				</td>
				<td id="stockPriceTd">${currentPrice}<c:set var="price">${currentPrice}</c:set>
					<c:if test="${price != '0.00'}">
						<c:set var="status">${status}</c:set>
						<c:choose>
							<c:when test="${status == 'up'}">
								<font id="upFont">${deducAbs} (${deducRel}%)</font>
							</c:when>
							<c:when test="${status == 'down'}">
								<font id="downFont">${deducAbs} (${deducRel}%)</font>
							</c:when>
							<c:otherwise>
								<font id="keepFont">${deducAbs} (${deducRel}%)</font>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="ticker" value="${stock.symbol}" />
	<table id="selectionTable">
		<tr id="selectionTR">
			<td id="selectYearTD" onclick="viewYearClicked(this)">View by
				Year</td>
			<td id="selectQuarterTD" onclick="viewQuarterClicked(this)">View
				by Quarter</td>
		</tr>
	</table>
	<br />
	<br />
	<div class="selectionDiv">
		<font class="endDateFont">End Date: </font> <select
			name="yearSelection" id="yearSelection"
			onchange="selectYearFun('parent',this,0)">
			<c:forEach var="year" items="${yearList}">
				<option value="${year}">${year}</option>
			</c:forEach>
		</select> <select name="quarterSelection" id="quarterSelection"
			onchange="selectQuarterFun('parent',this,0)">
			<c:forEach var="quarter" items="${quarterList}">
				<option value="${quarter}">${quarter}</option>
			</c:forEach>
		</select>
	</div>
	<table id="ratioTable">
		<tr class="ratioTR">
			<td>ratios</td>
		</tr>
		<tr>
			<td>ratios</td>
		</tr>
	</table>
</body>
</html>