<%@page import="entity.EmployeeEntity"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>演習Web_002の問１の解答例</title>
</head>

<%
  // 文字コードの指定
  request.setCharacterEncoding( "Shift_JIS" );
  // 前ページで入力した値を出力
  String param = request.getParameter( "param" );
%>
JSPの基礎　問１
<hr>
こんにちは　<%= param %>　さん
<hr>

</body>
</html>
