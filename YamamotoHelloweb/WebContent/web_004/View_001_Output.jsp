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
  WorkTimeEntity resultEntity = ( WorkTimeEntity ) session.getAttribute( "resultEntity" );
  ArrayList<String> errMsg = ( ArrayList<String> ) session.getAttribute( "errMsg" );
%>

<!--  �G���[msg������Ε\���A�Ȃ���΃f�[�^��\������ -->
<% if ( errMsg.size() != 0 ) {
     for( String msg: errMsg){ %>
       <%= msg %> <br><br>
<%
     }
%>
<!--  �G���[�������́A�߂�{�^���̂ݕ\������ -->
   <form action="./Servlet_009" method="POST">
    <br> <input type="submit" value="���͉�ʂɖ߂�" />
   </form>
<%
   }else {
%>
    <table border="1">
    <tr>
      <td bgcolor="lightskyblue">�Ј��ԍ�</td>
      <td width="200"><%= resultEntity.getStaffNo() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">���Ə��R�[�h</td>
      <td width="200" bgcolor="lightcyan"><%= resultEntity.getOfficeCd() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">�Ј���</td>
      <td width="200"><%= resultEntity.getStaffName() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">�ғ�����</td>
      <td width="200" bgcolor="lightcyan"><%= resultEntity.getWorkTime() %></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">�쐬��</td>
      <td width="200"><%= resultEntity.getCreateDate() %></td>
    </tr>
    </table>

    <!--  ����I�����́A�߂�ƍ폜�{�^����\������ -->
    <form  method="POST">
      <br> <input formaction="./Servlet_009" type="submit" value="���͉�ʂɖ߂�" />
           <input formaction="./Servlet_010" type="submit" value="�폜" />
    </form>

<% } %>


</body>
</html>
