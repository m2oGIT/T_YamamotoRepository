
package web_001;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccessor;
import entity.WorkTimeEntity;

/**
 * ���KWeb_001�̖�S�̉𓚗� <br />
 * DB���K�ŃT�[�o�[�ɍ쐬�����e�[�u���ւ̃f�[�^�o�^���s�� <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */

@WebServlet(name = "/Servlet_004", urlPatterns = {
    "/web_001/Servlet_004"
})
public class Servlet_004 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_004() {
    // �s���ׂ������Ȃ�
    super();
  }

  /**
   * doGet���\�b�h<br />
   * doGet�����s���܂��B <br />
   *
   * @param request HTTP���N�G�X�g
   * @param response HTTP���X�|���X
   * @throws ServletException �T�[�u���b�g��O
   * @throws IOException ���o�͗�O
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {

    // �������������s
    PrintWriter pw = initCode( request, response );

    // �p�����[�^�[���擾
    WorkTimeEntity wtEntity = new WorkTimeEntity();
    wtEntity = setEntity( wtEntity, request );

    // ���̓`�F�b�N
    if ( !checkInput( wtEntity ) ) {
      pw.println( "���͒l�G���[���������܂����B" );
      return;
    }

    try {
      // DB�����̎��s
      DBAccessor dbac = new DBAccessor( this.getServletContext() );
      dbac.insert( wtEntity );

    } catch ( ClassNotFoundException e ) {
      pw.println( "�N���X�p�X�G���[���������܂����B" );
      e.printStackTrace();
      return;
    } catch ( IllegalAccessException e ) {
      pw.println( "�N���X�A�N�Z�X�G���[���������܂����B" );
      e.printStackTrace();
      return;
    } catch ( InstantiationException e ) {
      pw.println( "�C���X�^���X�����G���[���������܂����B" );
      e.printStackTrace();
      return;
    } catch ( SQLException e ) {
      // ��Ӑ���G���[�̃G���[�R�[�h��DB���i���ƂɈقȂ�BOracle��00001�A���B
      if ( e.getErrorCode() == 1062 ) {
        pw.println( "��Ӑ���G���[���������܂����B" );
      } else {
        pw.println( "SQL���s���G���[���������܂����B" );
      }
      e.printStackTrace();
      return;
    } catch ( Exception e ) {
      pw.println( "�z��O�̃G���[���������܂����B" );
      e.printStackTrace();
      return;
    }

    // ���ʉ�ʂւ̑J��
    pw.println( "DB�ւ̓o�^������ɏI�����܂����B" );

  }

  /**
   * doPost���\�b�h<br />
   * doPost�����s���܂��B <br />
   *
   * @param request HTTP���N�G�X�g
   * @param response HTTP���X�|���X
   * @throws ServletException �T�[�u���b�g��O
   * @throws IOException ���o�͗�O
   */
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    // doGet���\�b�h�ɈϏ�
    this.doGet( request, response );
  }

  /**
   * ���ʏ����������\�b�h<br />
   * �������������s���܂��B <br />
   *
   * @param request HTTP���N�G�X�g
   * @param response HTTP���X�|���X
   * @return pw �Z�b�g�ς�PrintWriter
   * @throws IOException ���o�͗�O
   */
  private PrintWriter initCode( HttpServletRequest request, HttpServletResponse response )
      throws IOException {
    // �G���R�[�h�����{
    request.setCharacterEncoding( "Shift_JIS" );
    // ContentType���w��
    response.setContentType( "text/html; charset=Shift_JIS" );
    // ���C�^�[�̐���
    PrintWriter pw = response.getWriter();

    return pw;

  }

  /**
   * ���̓`�p�����[�^�[�擾���\�b�h<br />
   * ���͓��e���G���e�B�e�B�ɃZ�b�g���܂��B <br />
   *
   * @param wtEntity ���͓��e�G���e�B�e�B
   * @param request HTTP���N�G�X�g
   * @return wtEntity �Z�b�g�ς݃G���e�B�e�B
   */
  private WorkTimeEntity setEntity( WorkTimeEntity wtEntity, HttpServletRequest request ) {

    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );

    // �����������������\�b�h��MVC�ɂ�����model�ɊY�����A�ʃN���X�ɐ؂蕪����ꍇ�������B
    // ���𓚗�́Aservlet��model�̖��������˂��`�ɂȂ��Ă���B
    wtEntity.setStaffNo( request.getParameter( "staffNo" ) );
    wtEntity.setOfficeCd( request.getParameter( "officeCd" ) );
    wtEntity.setStaffName( request.getParameter( "staffName" ) );
    wtEntity.setWorkTime( request.getParameter( "workTime" ) );
    wtEntity.setCreateDate( sdf.format( new Date() ) );

    // �Z�b�g�ς݃G���e�B�e�B�B
    return wtEntity;
  }

  /**
   * ���̓`�F�b�N���\�b�h<br />
   * ���͓��e���`�F�b�N���܂��B <br />
   *
   * @param wtEntity ���͓��e�G���e�B�e�B
   * @return boolean �`�F�b�N����
   */
  private boolean checkInput( WorkTimeEntity wtEntity ) {
    // html���� maxlength�w����L�q���Ă���ꍇ�A�����I�[�o�[�͔��������Ȃ����A
    // ���𓚗�ł̓��W�b�N���ł������I�Ƀ`�F�b�N�����{�B
    // �܂��A�`�F�b�N���ǂ��܂ō�肱�ނ����C�ӂ����A�Œ�ł������̓`�F�b�N�ƌ����`�F�b�N�͂����������ǂ��B

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getStaffNo() ) || wtEntity.getStaffNo().length() == 0 ) {
      return false;
    }
    // �����`�F�b�N
    if ( wtEntity.getStaffNo().length() != 11 ) {
      return false;
    }

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getOfficeCd() ) || wtEntity.getOfficeCd().length() == 0 ) {
      return false;
    }
    // �����`�F�b�N
    if ( wtEntity.getOfficeCd().length() != 3 ) {
      return false;
    }

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getStaffName() ) || wtEntity.getStaffName().length() == 0 ) {
      return false;
    }
    // �����`�F�b�N
    if ( wtEntity.getStaffName().length() > 20 ) {
      return false;
    }

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getWorkTime() ) || wtEntity.getWorkTime().length() == 0 ) {
      return false;
    }
    // �����`�F�b�N
    if ( wtEntity.getWorkTime().length() > 5 ) {
      return false;
    }
    try {
      // ���l�^�ɕϊ��ł��Ȃ��ꍇ�̓G���[
      Integer.parseInt( wtEntity.getWorkTime() );
    } catch ( NumberFormatException e ) {
      return false;
    }

    // �S�Ẵ`�F�b�N���p�X�����true�Ƃ���B
    return true;
  }

}
