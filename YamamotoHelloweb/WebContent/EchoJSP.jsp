<%@page import="entity.EmployeeEntity"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>はじめてのJSP</title>
</head>
<body>
    <h1><font color="blue">はじめてのJSPです。</font></h1>
    <form action="./EchoJSP.jsp" method="POST">
      <input type="text" name="param">
      <input type="submit" value="次ページに送信！">
    </form>

<!--  HTMLのコメントは画面のソースに出力される。-->
<%-- JSPのコメントは出力されない。--%>

<%
  // 文字コードの指定
  request.setCharacterEncoding( "Shift_JIS" );
  // 次ページで入力した値を出力
  String param = request.getParameter( "param" );
  // コンソール出力
  System.out.println( param );
%>

<%= param %>

<hr>

  ループによる列の出力
  <table border = "1" bordercolor = "black">
    <tr>
      <% for ( int i = 0; i < 10; i++) { %>
        <td> <%= i %></td>
      <% } %>
    </tr>
  </table>

<hr>

  現在日時を表示<br>
<%
  SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日 HH時mm分ss秒" );
  Date now = new Date();
%>

<%= sdf.format( now ) %>


<hr>

  FowardServletから取得した値<br >

<%
  // FowardServletから取得した値を設定
  String name = ( String )request.getAttribute( "name" );
  Integer age = ( Integer )request.getAttribute( "age" );

%>

<%= name %><br >
<%= age %>


<hr>

  FowardServletから取得した値(エンティティ)<br >

<%
  // FowardServletから取得した値を設定
  EmployeeEntity entity = ( EmployeeEntity )request.getAttribute( "entity" );

%>

<%= entity.getNo( ) %><br >
<%= entity.getName( ) %><br >
<%= entity.getAge( ) %>

</body>
</html>
