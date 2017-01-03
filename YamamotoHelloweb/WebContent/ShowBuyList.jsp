<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entity.EmployeeEntity"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>買い物かごの中身を表示</title>
</head>

<hr>

<%
  // SessionServletから取得した値を設定
  ArrayList<String> buyList = (ArrayList<String>)session.getAttribute( "buyList" );

%>

  <table border = "1" bordercolor = "black">
    <tr>
      <% for ( int i = 0; i < buyList.size(); i++) {
         String buyObj = buyList.get(i); %>
        <td> <%= buyObj %></td>
      <% } %>
    </tr>
  </table>

</body>
</html>
