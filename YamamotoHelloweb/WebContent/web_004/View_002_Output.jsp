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
<title>���KWeb_004�̖�Q�̉𓚗�</title>
</head>
<!-- ��ʂ̑����i�X�^�C���V�[�g���j�Ɋւ��ẮA�e�����R�Ɏ������Ă��������B -->
<body>
<%
  // �����R�[�h�̎w��
  request.setCharacterEncoding( "Shift_JIS" );
  // �O�y�[�W�Ő��������������ʂƃ��b�Z�[�W���擾
  List<WorkTimeEntity> resultEntityList = ( ArrayList<WorkTimeEntity> ) session.getAttribute( "resultEntityList" );
  ArrayList<String> errMsg = ( ArrayList<String> ) session.getAttribute( "errMsg" );
%>

<!-- �G���[msg������Ε\���A�Ȃ���΃f�[�^��\������ -->
<% if ( errMsg.size() != 0 ) {
     for( String msg: errMsg){ %>
       <%= msg %> <br><br>
<%
     }
   }else {
%>
DB�ւ̓o�^������ɏI�����܂����B
<hr>
    <table border="1">
    <tr>
      <td width="150" bgcolor="lightskyblue">�Ј��ԍ�</td>
      <td width="100" bgcolor="lightskyblue">���Ə��R�[�h</td>
      <td width="200" bgcolor="lightskyblue">�Ј���</td>
      <td width="100" bgcolor="lightskyblue">�ғ�����</td>
      <td width="100" bgcolor="lightskyblue">�쐬��</td>
    </tr>
    <%
    // resultEntity���X�g�̖����܂Ń��[�v
    for (WorkTimeEntity resultEntity: resultEntityList){
    %>
    <tr>
      <!-- �f�[�^��1�����\������ -->
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

  <!--  �߂�{�^����\������ -->
   <form action="./Servlet_012" method="POST">
    <br> <input type="submit" value="���͉�ʂɖ߂�" />
   </form>

</body>
</html>
