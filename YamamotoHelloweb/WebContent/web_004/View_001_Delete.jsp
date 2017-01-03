<%@page import="java.util.ArrayList"%>
<%@page import="entity.WorkTimeEntity"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
  pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>演習Web_004の問１の解答例</title>
</head>
<body>
<%
  // 文字コードの指定
  request.setCharacterEncoding( "Shift_JIS" );
  // 前ページで生成した検索結果とメッセージを取得
  ArrayList<String> errMsg = ( ArrayList<String> ) request.getAttribute( "errMsg" );
%>

<!--  削除結果メッセージを表示する-->
<h1>レコード削除</h1>
<!--  エラーmsgがあれば表示、なければ削除完了メッセージを表示する-->
<% if ( errMsg.size() != 0 ) {
     for( String msg: errMsg){ %>
       <%= msg %> <br><br>
<%
     }
   }
%>

   削除しました。 <br><br>
   <form action="./Servlet_009" method="POST">
    <br> <input type="submit" value="入力画面に戻る" />
   </form>

</body>
</html>
