<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
  pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�͂��߂Ă�JSP</title>
</head>
<body>
  <h1>
    <font color="blue"> �͂��߂Ă�JSP�ł��B </font>
  </h1>

  <form action="./EchoJSP11111.jsp" method="POST">
    <input type="text" name="param" /> <input type="submit"
      value="���y�[�W�ɑ��M">
  </form>

  <%
    request.setCharacterEncoding( "Windows-31J" );
    // ���y�[�W�œ��͂����l���擾
    String param = request.getParameter( "param" );

    System.out.println( param );
  %>

  <%=param%>

  <hr>
 </hr>
  <table border="1" bordercolor="black" >
    <tr><!-- �s -->
      <%
        for ( int i = 0; i < 10; i++ ) {
      %>
      <td><%=i%></td><!-- �� -->
      <%
        }
      %>
    </tr>

    <tr><!-- �s -->
      <%
        for ( int i = 0; i < 10; i++ ) {
      %>
      <td><%=i%></td><!-- �� -->
      <%
        }
      %>
    </tr>

  </table>

<br>
<br>
  <table border="1" >
    <tr><!-- �s -->
      <td>c</td><!-- �� -->
    </tr>
    <tr><!-- �s -->
      <td>a</td><!-- �� -->
    </tr>
  </table>

<hr/>
���݂̓�����\��
<%
SimpleDateFormat fmt = new SimpleDateFormat("yyyy�NMM��dd�� HH��mm��ss�b");
Date date= new Date();
%>

<%=fmt.format( date ) %>


  <!-- HTML�R�����g -->
  <%-- JSP�R�����g--%>
</body>
</html>