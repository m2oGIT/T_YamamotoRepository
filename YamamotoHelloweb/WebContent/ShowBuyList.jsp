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
<title>�����������̒��g��\��</title>
</head>

<hr>

<%
  // SessionServlet����擾�����l��ݒ�
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
