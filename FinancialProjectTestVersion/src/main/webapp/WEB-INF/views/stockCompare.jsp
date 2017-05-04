<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>
<p>Stock comparison page</p>
<ul>
<c:forEach var="record" items="${srh.yearRatioRecords}">
<li>${record.timeSlot}</li>
</c:forEach>
</ul>

<ul>
<c:forEach var="record" items="${srh.quarterRatioRecords}">
<li>${record.timeSlot}</li>
</c:forEach>
</ul>

</body>
</html>