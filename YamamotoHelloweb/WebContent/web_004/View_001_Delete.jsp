<%@page import="java.util.ArrayList"%>
<%@page import="entity.WorkTimeEntity"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
  pageEncoding="Shift_JIS"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>���KWeb_004�̖�P�̉𓚗�</title>
</head>
<body>
<%
  // �����R�[�h�̎w��
  request.setCharacterEncoding( "Shift_JIS" );
  // �O�y�[�W�Ő��������������ʂƃ��b�Z�[�W���擾
  ArrayList<String> errMsg = ( ArrayList<String> ) request.getAttribute( "errMsg" );
%>

<!--  �폜���ʃ��b�Z�[�W��\������-->
<h1>���R�[�h�폜</h1>
<!--  �G���[msg������Ε\���A�Ȃ���΍폜�������b�Z�[�W��\������-->
<% if ( errMsg.size() != 0 ) {
     for( String msg: errMsg){ %>
       <%= msg %> <br><br>
<%
     }
   }
%>

   �폜���܂����B <br><br>
   <form action="./Servlet_009" method="POST">
    <br> <input type="submit" value="���͉�ʂɖ߂�" />
   </form>

</body>
</html>
