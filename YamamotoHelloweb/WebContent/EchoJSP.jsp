<%@page import="entity.EmployeeEntity"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>�͂��߂Ă�JSP</title>
</head>
<body>
    <h1><font color="blue">�͂��߂Ă�JSP�ł��B</font></h1>
    <form action="./EchoJSP.jsp" method="POST">
      <input type="text" name="param">
      <input type="submit" value="���y�[�W�ɑ��M�I">
    </form>

<!--  HTML�̃R�����g�͉�ʂ̃\�[�X�ɏo�͂����B-->
<%-- JSP�̃R�����g�͏o�͂���Ȃ��B--%>

<%
  // �����R�[�h�̎w��
  request.setCharacterEncoding( "Shift_JIS" );
  // ���y�[�W�œ��͂����l���o��
  String param = request.getParameter( "param" );
  // �R���\�[���o��
  System.out.println( param );
%>

<%= param %>

<hr>

  ���[�v�ɂ���̏o��
  <table border = "1" bordercolor = "black">
    <tr>
      <% for ( int i = 0; i < 10; i++) { %>
        <td> <%= i %></td>
      <% } %>
    </tr>
  </table>

<hr>

  ���ݓ�����\��<br>
<%
  SimpleDateFormat sdf = new SimpleDateFormat( "yyyy�NMM��dd�� HH��mm��ss�b" );
  Date now = new Date();
%>

<%= sdf.format( now ) %>


<hr>

  FowardServlet����擾�����l<br >

<%
  // FowardServlet����擾�����l��ݒ�
  String name = ( String )request.getAttribute( "name" );
  Integer age = ( Integer )request.getAttribute( "age" );

%>

<%= name %><br >
<%= age %>


<hr>

  FowardServlet����擾�����l(�G���e�B�e�B)<br >

<%
  // FowardServlet����擾�����l��ݒ�
  EmployeeEntity entity = ( EmployeeEntity )request.getAttribute( "entity" );

%>

<%= entity.getNo( ) %><br >
<%= entity.getName( ) %><br >
<%= entity.getAge( ) %>

</body>
</html>
