<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock</title>
<style type="text/css">
body{
margin-left: 150px;
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

table.titleTable{
	text-align: center;
}
td.titleTD {
	text-align: center;
}
#searchInput {
	width: 200px;
}

table.revTable {
	border-collapse: collapse;
	border-style: solid;
	border-color: LightGrey;
	border-width: 1px;
	width: 205px;
}

tr.revRow {
	border-style: solid none none none;
	border-width: 1px;
	border-color: LightGrey;
}

tr.revRow:hover {
	background-color: #00BFFF;
	color: #fff;
}

td.symbol {
	font-style: italic;
	font-weight: bold;
	font-size: 15px;
	width: 100px;
}

td.market {
	font-size: 15px;
	float: right;
	width: 100px;
	text-align: right;
}

font {
	font-style: normal;
	color: DarkGray;
	font-size: 10px;
}

a.resultLink {
	text-decoration: none;
	outline: none;
	color: #000000;
}

#resultDiv {
	position: absolute;
	top: 86px;
	left: 150px;
	z-index: 2;
	background: #fff;
}

#list {
	height: 100%;
	position: relative;
	z-index: 1;
}

#allStockTable {
	margin: 10px 0 0 0;
	border-collapse: collapse;
	color: #000000;
	font-size: 16px;
}

tr.stockListRow {
	border-style: solid none;
	border-width: 1px;
	border-color: LightGrey;
}

#symbolTitle {
	width: 100px;
}

#nameTitle {
	width: 400px;
}

a.stockListSymbol {
	color: #000000;
}

#showMoreU {
	position: relative;
	left: 50%;
	cursor: pointer;
}
</style>
<script lanuage="javascript">
	function searchBegin() {
		var resultDiv = document.getElementById("resultDiv");
		resultDiv.innerHTML = "";
		var userInput = document.getElementById("searchInput").value;
		if (userInput == "") {
			return false;
		}
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
				var respText = xmlhttp.responseText;
				var jsonT = JSON.parse(respText);
				var count = Number(jsonT.count);
				var revTable = document.createElement("table");
				revTable.className = "revTable";
				for (var i = 0; i < count; i++) {
					//row = revTable.insertRow();
					var link = document.createElement("a");
					var url = "openRatio.htm?ticker=" + jsonT.stocks[i].Symbol;
					link.setAttribute("href", url);
					link.className = "resultLink";
					revTable.appendChild(link);
					var row = document.createElement("tr");
					row.className = "revRow";
					link.appendChild(row);
					/* row.onclick = function() {
						selectStock(this);
					}; */

					var symbolCell = row.insertCell();
					symbolCell.innerHTML = jsonT.stocks[i].Symbol;
					symbolCell.appendChild(document.createElement("br"));
					var name = document.createElement("font");
					name.innerHTML = jsonT.stocks[i].Name;
					symbolCell.appendChild(name);
					symbolCell.className = "symbol";

					var marketCell = row.insertCell();
					marketCell.innerHTML = jsonT.stocks[i].Market;
					marketCell.className = "market";
				}
				resultDiv.appendChild(revTable);
			}
		}

		var url = "search.htm";
		var sendStr = "userInput=" + userInput;
		xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(sendStr);
		/* var stockListJson = JSON.parse(response); */
		/* alert(stockList.stocks[0].Symbol); */

		return true;
	}

	function selectStock(row) {
		var symbol = row.cells[0].innerHTML;
		alert(symbol);
	}

	function clearSearch() {
		var resultDiv = document.getElementById("resultDiv");
		setTimeout("resultDiv.innerHTML=''", 10);
	}

	function showMoreClicked() {
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
					return false;
				}
			}
		}
		var table = document.getElementById("allStockTable");
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var respText = xmlhttp.responseText;
				var jsonT = JSON.parse(respText);
				var count = Number(jsonT.count);

				for (var i = 0; i < count; i++) {
					var row = table.insertRow();
					row.className = "stockListRow";

					var symbol = row.insertCell();
					var symbolLink = document.createElement("a");
					symbolLink.className = "stockListSymbol";
					symbolLink.innerText = jsonT.stocks[i].Symbol;
					var url = "openRatio.htm?ticker=" + jsonT.stocks[i].Symbol;
					symbolLink.setAttribute("href", url)
					symbol.appendChild(symbolLink);

					var name = row.insertCell();
					name.innerHTML = jsonT.stocks[i].Name;

					var market = row.insertCell();
					market.innerHTML = jsonT.stocks[i].Market;
				}
			}
		}

		var url = "showMore.htm";
		var sendStr = "showed=" + (table.rows.length - 1);
		xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(sendStr);
		return true;
	}

	function test() {
		alert("test");
	}
</script>
</head>
<body>
	<table class="titleTable" width="100%" height="100%" cellspacing="0">
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
	<div id="searchDiv">
		<input id="searchInput" type="text" name="search"
			placeholder="Please input ticker" onkeyup="searchBegin()"
			onclick="searchBegin()" onblur="clearSearch()" />
		<div id="list">
			<table id="allStockTable">
				<tr>
					<th id="symbolTitle">Ticker</th>
					<th id="nameTitle">Company Name</th>
					<th>Market</th>
				</tr>
				<c:forEach var="stock" items="${stockList}">
					<tr class="stockListRow">
						<td><a class="stockListSymbol"
							href="openRatio.htm?ticker=${stock.symbol}">${stock.symbol}</a></td>
						<td>${stock.companyName}</td>
						<td>${stock.market}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<u id="showMoreU" onclick="showMoreClicked()">Show more</u>
		</div>
		<div id="resultDiv"></div>
	</div>
</body>
</html>