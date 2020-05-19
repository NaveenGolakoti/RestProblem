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
 <style>
 
   </style>
</head>
<body>
<div id="mainForm"class="myclass" style="margin-left:4px;margin-top:100px;margin-right:40px;border:2px solid black"">
<h2 style="text-align:center;">Problems List</h2>

 <a style="margin:500px; font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;" class="button" href="<c:url value='/addnewProblem'/>">Add New Problem</a>

 <hr style="height:1px;border:none;color:#333;background-color:#333;">
<table id="myTable" border="2" cellpadding="10" bordercolor="solid black">
<tr>
<th>PID</th>
<th>Content</th>
<th>Delete Problem</th>
<th>Edit Problem</th>
</tr>

    <c:forEach items="${list}" var="problem">
        <tr id='<c:out value="${problem.pid}"></c:out>'>

                    <td>${problem.pid }</td>
                    <td id='<c:out value="${ problem.pid}${problem.content}"></c:out>'>${problem.content}</td>
                     <td><input onclick="javascript:deleteProblem('${problem.pid}')" type="button" name="problem" id="delete" value="Delete" /></td>
                    <td><a style="font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;"class="button" href="<c:url value='/editProblem/${problem.pid}'/>">Edit</a></td>
        </tr>
    </c:forEach>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function addProblem() {
       console.log("inside add problem method");
    }
    function deleteProblem(problem) {
    	document.getElementById(problem).remove();
        console.log("inside delete problem method",problem);
        var dataJson = {
				"pid" : problem,
		};
        $.ajax({
        	  url: "/RestProblem/deleteProblem",
              type: "POST",
              data: JSON.stringify(dataJson),
              contentType: "application/json",
              async : false,
              dataType: "json",
            	  success: function(result) {
            		  console.log("success");
            		  window.location.href = "/RestProblem/getAllProblems"
            	    }
		});
		
     }
    function editProblem(pid) {
        console.log("inside edit problem method",pid);
     }
</script>

</body>
</html>