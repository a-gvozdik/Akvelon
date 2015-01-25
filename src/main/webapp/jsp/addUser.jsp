<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<c:url value="jsp/javascript.js" />"></script>
<script src="jsp/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
          $(document).ready(function(){
              $(".uname").change(function(){
                  var uname = $(this).val();
                  if(uname.length >= 3){
                      $.ajax({
                          type: "POST",
                          url: "/AkvelonServlet/checkLogin",
                          data: "uname="+ uname,
                          success: function(msg){

                              $(".status").ajaxComplete(function(event, request, settings){
                                   
                                  $(".status").html(msg);

                              });
                          }
                      }); 
                  }
                  else{
                       $(".status").html("<font color=red>Login should be min <b>3</b> character long.</font>");
                  }
                  
              });
          });
</script>
          

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
   <%@include file='style.css' %>
</style>

<title>Add new User</title>

</head>

<body>
<div class="newuser">
	<div class="newuserinner">
<p align="center"> Add new User </p>
<form name="newuserform" method="post" action="/AkvelonServlet/addUser">
<p> First Name* <input type="text" name="fname" onBlur="FieldsValid()"> </p>
<p> Last Name* <input type="text" name="lname" onBlur="FieldsValid()"> </p>
<p> Login* <input type="text" name="login" class="uname" onBlur="FieldsValid()"> <br><span style="font-size: 10px;" class="status"></span></p>
<p> Birthday* <input type="text" name="birthday" onBlur="isValidDate()"><br> <span style="font-size: 10px;">2000-01-01</span> </p>
<p> Balance <input type="text" name="balance" value="0.00" onKeyDown="FieldsValid()"> </p>
<p> <input class="button" name="submit" type="submit" value="Add" disabled> <input class="button" type="submit" value="Cancel" name="cancel"> </p>
</form>
	</div>
</div>

</body>
</html>
