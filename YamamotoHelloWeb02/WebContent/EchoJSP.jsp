<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
  pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>はじめてのJSP</title>
</head>
<body>
  <h1>
    <font color="blue"> はじめてのJSPです。 </font>
  </h1>

  <form action="./EchoJSP11111.jsp" method="POST">
    <input type="text" name="param" /> <input type="submit"
      value="自ページに送信">
  </form>

  <%
    request.setCharacterEncoding( "Windows-31J" );
    // 自ページで入力した値を取得
    String param = request.getParameter( "param" );

    System.out.println( param );
  %>

  <%=param%>

  <hr>
 </hr>
  <table border="1" bordercolor="black" >
    <tr><!-- 行 -->
      <%
        for ( int i = 0; i < 10; i++ ) {
      %>
      <td><%=i%></td><!-- 列 -->
      <%
        }
      %>
    </tr>

    <tr><!-- 行 -->
      <%
        for ( int i = 0; i < 10; i++ ) {
      %>
      <td><%=i%></td><!-- 列 -->
      <%
        }
      %>
    </tr>

  </table>

<br>
<br>
  <table border="1" >
    <tr><!-- 行 -->
      <td>c</td><!-- 列 -->
    </tr>
    <tr><!-- 行 -->
      <td>a</td><!-- 列 -->
    </tr>
  </table>

<hr/>
現在の日時を表示
<%
SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
Date date= new Date();
%>

<%=fmt.format( date ) %>


  <!-- HTMLコメント -->
  <%-- JSPコメント--%>
</body>
</html>