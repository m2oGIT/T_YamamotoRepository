<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.WorkTimeEntity"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
  pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>演習Web_004の問２の解答例</title>
</head>
<!-- 画面の装飾（スタイルシート等）に関しては、各自自由に実装してください。 -->
<body>
<%
  // 文字コードの指定
  request.setCharacterEncoding( "Shift_JIS" );
  // 前ページで生成した検索結果とメッセージを取得
  List<WorkTimeEntity> resultEntityList = ( ArrayList<WorkTimeEntity> ) session.getAttribute( "resultEntityList" );
  ArrayList<String> errMsg = ( ArrayList<String> ) session.getAttribute( "errMsg" );
%>

<!-- エラーmsgがあれば表示、なければデータを表示する -->
<% if ( errMsg.size() != 0 ) {
     for( String msg: errMsg){ %>
       <%= msg %> <br><br>
<%
     }
   }else {
%>
DBへの登録が正常に終了しました。
<hr>
    <table border="1">
    <tr>
      <td width="150" bgcolor="lightskyblue">社員番号</td>
      <td width="100" bgcolor="lightskyblue">事業所コード</td>
      <td width="200" bgcolor="lightskyblue">社員名</td>
      <td width="100" bgcolor="lightskyblue">稼働時間</td>
      <td width="100" bgcolor="lightskyblue">作成日</td>
    </tr>
    <%
    // resultEntityリストの末尾までループ
    for (WorkTimeEntity resultEntity: resultEntityList){
    %>
    <tr>
      <!-- データを1件ずつ表示する -->
      <td width="150"><%= resultEntity.getStaffNo() %></td>
      <td width="100"><%= resultEntity.getOfficeCd() %></td>
      <td width="200"><%= resultEntity.getStaffName() %></td>
      <td width="100"><%= resultEntity.getWorkTime() %></td>
      <td width="100"><%= resultEntity.getCreateDate() %></td>
    </tr>
    <%
    }
    %>
    </table>
<% } %>

  <!--  戻るボタンを表示する -->
   <form action="./Servlet_012" method="POST">
    <br> <input type="submit" value="入力画面に戻る" />
   </form>

</body>
</html>
