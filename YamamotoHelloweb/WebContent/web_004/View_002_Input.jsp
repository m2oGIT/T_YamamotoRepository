<%@page import="java.util.ArrayList"%>
<%@page import="entity.WorkTimeEntity"%>
<%@page import="java.util.Date"%>
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
  // �Z�b�V���������擾�i����A�N�Z�X����entity���������j
  WorkTimeEntity inputEntity = ( WorkTimeEntity ) session.getAttribute( "inputEntity" );
  if( inputEntity == null ) {
    inputEntity = new WorkTimeEntity();
  }
%>

<body>
  <form action="./Servlet_011" method="POST">
    <table border="1">
    <tr>
      <td bgcolor="lightskyblue">�Ј��ԍ�</td>
      <td><input type="text" name="staffNo" value="<%=inputEntity.getStaffNo()%>" size="20" maxlength="11" /></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">���Ə��R�[�h</td>
      <td><input type="text" name="officeCd" value="<%=inputEntity.getOfficeCd()%>" size="20"  maxlength="3" /></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">�Ј���</td>
      <td><input type="text" name="staffName" value="<%=inputEntity.getStaffName()%>" size="20"  maxlength="20" /></td>
    </tr>
    <tr>
      <td bgcolor="lightskyblue">�ғ�����</td>
      <td><input type="text" name="workTime" value="<%=inputEntity.getWorkTime()%>" size="20"  maxlength="5" /></td>
    </tr>
    </table>
    <br> <input type="submit" value="���M" />
  </form>

</body>

</body>
</html>
