<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
  pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>���KWeb_003�̖�P�̉𓚗�</title>
</head>
<body>
<%
  // �����R�[�h�̎w��
  request.setCharacterEncoding( "Shift_JIS" );
  // �O�y�[�W�Ő����������b�Z�[�W���o��
  String outputMsg = ( String ) request.getAttribute( "outputMsg" );
%>

<hr>
<%=outputMsg%>
<hr>

</body>
</html>
