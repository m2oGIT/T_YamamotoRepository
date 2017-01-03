<%@page import="java.util.ArrayList"%>
<%@page import="entity.WorkTimeEntity"%>
<%@page import="java.util.Date"%>
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
  // セッション情報を取得（初回アクセス時はentityを初期化）
  WorkTimeEntity inputEntity = ( WorkTimeEntity ) session.getAttribute( "inputEntity" );
  if( inputEntity == null ) {
    inputEntity = new WorkTimeEntity();
  }
%>

<body>
  <form action="./Servlet_011" method="POST">
    <table border="1">
    <tr>
      <td bgcolor="lightskyblue">社員番号</td>
      <td><input type="text" name="staffNo" value="<%=inputEntity.getStaffNo()%>" size="20" maxlength="11" /></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">事業所コード</td>
      <td><input type="text" name="officeCd" value="<%=inputEntity.getOfficeCd()%>" size="20"  maxlength="3" /></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">社員名</td>
      <td><input type="text" name="staffName" value="<%=inputEntity.getStaffName()%>" size="20"  maxlength="20" /></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">稼働時間</td>
      <td><input type="text" name="workTime" value="<%=inputEntity.getWorkTime()%>" size="20"  maxlength="5" /></td>
    </tr>
    </table>
    <br> <input type="submit" value="送信" />
  </form>

</body>

</body>
</html>
