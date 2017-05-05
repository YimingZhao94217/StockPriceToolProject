<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table.calendarTable {
	border-collapse: collapse;
	border-style: solid;
	width: 900px;
	border-color: LightGrey;
	border-width: 2px;
}

th.titleTD {
	border-style: solid;
	border-color: LightGrey;
	border-width: 2px;
}
td{
border-width: 1px;
}
td.dateTD {
	border-style: solid;
	background-color: Black;
	color: white;
	width: 40px;
	border-color: LightGrey;
}

td.topLineTD {
	border-style: solid none none none;
	width: 40px;
	border-color: LightGrey;
}

td.cellLeftTD {
	border-style: none none none solid;
	border-color: LightGrey;
}

td.cellRightTD {
	border-style: none solid none none;
	border-color: LightGrey;
}

td.blankTD {
	
}

td.stockNameTD {
	border-style: solid;
	background-color: #00BFFF;
	color: White;
	border-color: LightGrey;
	text-align: center;
}

tr.calendarRowTR {
	height: 20px;
}

a.openRatioA {
	color: white;
}

div.selectMonthDiv {
	float: right;
}
</style>
</head>
<body>
	<p>Earning date canlendar</p>
	<div class="selectMonthDiv">
		<c:set var="ban">${ban}</c:set>
		<c:choose>
			<c:when test="${ban == 'banLast'}">${currentTimeFormat}<a
					href="earningDate.htm?currentMonth=${currentMonth}&currentYear=${currentYear}&action=next">Next</a>
			</c:when>
			<c:when test="${ban == 'banNext'}">
				<a
					href="earningDate.htm?currentMonth=${currentMonth}&currentYear=${currentYear}&action=last">Last</a>${currentTimeFormat}</c:when>
			<c:otherwise>
				<a
					href="earningDate.htm?currentMonth=${currentMonth}&currentYear=${currentYear}&action=last">Last</a>
	${currentTimeFormat}
				<a
					href="earningDate.htm?currentMonth=${currentMonth}&currentYear=${currentYear}&action=next">Next</a>
			</c:otherwise>
		</c:choose>

	</div>
	<div>
		<table class="calendarTable">
			<tr class="calendarRowTR">
				<th colspan="3" class="titleTD">Sun</th>
				<th colspan="3" class="titleTD">Mon</th>
				<th colspan="3" class="titleTD">Tue</th>
				<th colspan="3" class="titleTD">Wed</th>
				<th colspan="3" class="titleTD">The</th>
				<th colspan="3" class="titleTD">Fri</th>
				<th colspan="3" class="titleTD">Sat</th>
			</tr>
			<c:set var="startBlock">${startBlock}</c:set>
			<c:forEach var="line" items="${calendar}">
				<tr class="calendarRowTR">
					<c:forEach var="cell" items="${line}">
						<c:set var="type">${cell.type}</c:set>
						<c:if test="${type == 'date'}">
							<td class="dateTD">${cell.value}</td>
						</c:if>
						<c:if test="${type == 'basicTop'}">
							<td class="topLineTD"></td>
						</c:if>
						<c:if test="${type == 'basicLeft'}">
							<td class="cellLeftTD"></td>
						</c:if>
						<c:if test="${type == 'basicRight'}">
							<td class="cellRightTD"></td>
						</c:if>
						<c:if test="${type == 'basic'}">
							<td class="blankTD"></td>
						</c:if>
						<c:if test="${type == 'stock'}">
							<td colspan="${cell.duration}" class="stockNameTD"><a
								class="openRatioA" href="openRatio.htm?ticker=${cell.value}">${cell.value}</a></td>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>