<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
       <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<body>
<div class="myclass" style="margin-left:100px;margin-top:100px;margin-right:30px;border:2px solid black"">
<h2 style="text-align:center;">Edit Problem</h2>
 <hr style="height:1px;border:none;color:#333;background-color:#333;">
 <form:form modelAttribute="problemDto" action="updateProblem" method="post" id="editForm" style="margin-left:500px;margin-bottom:10px;">
<form:textarea rows="4" cols="50" path="content" id="text"></form:textarea>
<form:input type="text" path="pid" style="display:none;"/>
 <input type="submit" value ="Update" style="margin: 20px;width: 200px;height: 30px;">
</form:form>
</div>
</body>
</html>