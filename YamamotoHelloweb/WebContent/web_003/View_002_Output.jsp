<%@page import="java.util.ArrayList"%>
<%@page import="entity.WorkTimeEntity"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
  pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>演習Web_003の問２の解答例</title>
</head>
<body>
<%
  // 文字コードの指定
  request.setCharacterEncoding( "Shift_JIS" );
  // 前ページで生成した検索結果とメッセージを取得
  WorkTimeEntity resultEntity = ( WorkTimeEntity ) request.getAttribute( "resultEntity" );
  ArrayList<String> errMsg = ( ArrayList<String> ) request.getAttribute( "errMsg" );
%>

<!-- エラーmsgがあれば表示、なければデータを表示する -->
<% if ( errMsg.size() != 0 ) {
     for( String msg: errMsg){ %>
       <%= msg %> <br><br>
<%
     }
   }else {
%>
    <table border="1">
    <tr>
      <td bgcolor="lightskyblue">社員番号</td>
      <td width="200"><%= resultEntity.getStaffNo() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">事業所コード</td>
      <td width="200" bgcolor="lightcyan"><%= resultEntity.getOfficeCd() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">社員名</td>
      <td width="200"><%= resultEntity.getStaffName() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">稼働時間</td>
      <td width="200" bgcolor="lightcyan"><%= resultEntity.getWorkTime() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">作成日</td>
      <td width="200"><%= resultEntity.getCreateDate() %></td>
    </tr>
    </table>
<% } %>

</body>
</html>
