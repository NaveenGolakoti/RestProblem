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
<h2 style="text-align:center;">Problems List</h2>
 <hr style="height:1px;border:none;color:#333;background-color:#333;">
   <input type="button" onclick="javascript:addProblem();" value ="Add Problem"/ style="margin-left: 1100px;
    margin-bottom: 20px;width: 200px;height: 30px;">
<form:form modelAttribute="list" method="post" name="myform" target="_blank" action="selectedList" accept-charset="UTF-8">
<table border="2" cellpadding="10" bordercolor="solid black">
<tr>
<th>Delete Problem</th>
<th>Edit Problem</th>
<th>PID</th>
<th>Content</th>
</tr>
    <c:forEach items="${list}" var="problem">
        <tr>
                    <td><input type="button" name="problem" value="Delete" onclick="javascript:deleteProblem('${problem.pid}');"/></td>
                    <td><input type="button" name="problem" value="Edit" onclick="javascript:editProblem('${problem}');"/></td>
                    <td><p>${problem.pid }</p></td>
                    <td>${problem.content}</td>
        </tr>
    </c:forEach>
    </table>
</form:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function addProblem() {
       console.log("inside add problem method");
    }
    function deleteProblem(problem) {
        console.log("inside delete problem method",problem);
        $.ajax({
        	  url: "/tooms/getAllDealerForGroup",
              type: "POST",
              data: JSON.stringify(dataJson),
              contentType: "application/json",
              dataType: "json",
			success:function(data){
				
				$('#dealer-search-result').show();			
				$('#dealer-search-result').html(data);	
				$('.datatable tbody tr:even').addClass('tblsort-oddrow');	
				$('#search-loading').addClass('hide');
				
			},error : function(error){
				if(error.status==403){
					$('#dealer-search-result').show();			
					$('#dealer-search-result').html(error.responseText);	
					$('.datatable tbody tr:even').addClass('tblsort-oddrow');	
					$('#search-loading').addClass('hide');
				}
			}
			
		});
		
     }
    function editProblem(problem) {
        console.log("inside edit problem method",problem);
     }
</script>

</body>
</html>