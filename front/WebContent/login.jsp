<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id = request.getParameter("id");
   String pwd = request.getParameter("pwd");
   if(id.equals(pwd)){
%>  
{
	"status" : "success"
}
<% }else{
%>  
{
	"status" : "fail"
}
<%}%>