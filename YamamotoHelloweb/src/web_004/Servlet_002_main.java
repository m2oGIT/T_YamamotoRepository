
package web_004;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBAccessor;
import entity.WorkTimeEntity;

/**
 * ���KWeb_004�̖�Q�̉𓚗� <br />
 * DB���K�ŃT�[�o�[�ɍ쐬�����e�[�u���̃f�[�^�o�^�ƌ��ʕ\�����s�� <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */

@WebServlet(name = "/Servlet_011", urlPatterns = {
    "/web_004/Servlet_011"
})
public class Servlet_002_main extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_002_main() {
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

    // �Z�b�V�����I�u�W�F�N�g���擾
    HttpSession session = request.getSession();

    // �p�����[�^�[���擾
    WorkTimeEntity wtEntity = new WorkTimeEntity();
    wtEntity = setEntity( wtEntity, request );

    // �������ʊi�[�p��entity�̃��X�g��p�ӂ��Ă���
    // WorkTimeEntity resultEntity = new WorkTimeEntity();
    List<WorkTimeEntity> resultEntityList = new ArrayList<WorkTimeEntity>();

    // �G���[MSG�i�[�p�����񃊃X�g
    ArrayList<String> errMsg = new ArrayList<String>();
    errMsg = checkInput( wtEntity, errMsg );

    // ���̓`�F�b�N���p�X�����ꍇ�̂݁ADB���������s
    if ( errMsg.size() == 0 ) {

      try {
        // DB�����̎��s
        // DB�����̎��s
        DBAccessor dbac = new DBAccessor( this.getServletContext() );
        dbac.insert( wtEntity );

        resultEntityList = dbac.selectAll();

        // �������ʂ������ł���΃G���[�ƌ��Ȃ��B
        // ��������0���̌��m�́ADB�A�N�Z�b�T��null�ŉ��u���ŏ�����������@������B
        if ( "".equals( resultEntityList.get( 0 ).getStaffNo() ) ) {
          errMsg.add( "DB�ɖ��o�^�̃f�[�^�ł��B" );
        }

      } catch ( ClassNotFoundException e ) {
        errMsg.add( "�N���X�p�X�G���[���������܂����B" );
        e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        errMsg.add( "�N���X�A�N�Z�X�G���[���������܂����B" );
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        errMsg.add( "�C���X�^���X�����G���[���������܂����B" );
        e.printStackTrace();
      } catch ( SQLException e ) {
        // ��Ӑ���G���[
        if ( 1022 == e.getErrorCode() || 1062 == e.getErrorCode() ) {
          errMsg.add( "��Ӑ���G���[���������܂����B" );
        } else {
          // SQL���s���G���[
          errMsg.add( "SQL���s���G���[���������܂����B" );
        }
        e.printStackTrace();
      } catch ( Exception e ) {
        errMsg.add( "�z��O�̃G���[���������܂����B" );
        e.printStackTrace();
      }
    }
    // ���ʉ�ʂւ̑J��
    session.setAttribute( "inputEntity", wtEntity );
    session.setAttribute( "resultEntityList", resultEntityList );
    session.setAttribute( "errMsg", errMsg );
    RequestDispatcher rd = request.getRequestDispatcher( "/web_004/View_002_Output.jsp" );
    rd.forward( request, response );
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
   * ���̓`�p�����[�^�[�擾���\�b�h<br />
   * ���͓��e���G���e�B�e�B�ɃZ�b�g���܂��B <br />
   *
   * @param wtEntity ���͓��e�G���e�B�e�B
   * @param request HTTP���N�G�X�g
   * @return wtEntity �Z�b�g�ς݃G���e�B�e�B
   */
  private WorkTimeEntity setEntity( WorkTimeEntity wtEntity, HttpServletRequest request ) {

    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );

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
   * @param errMsg �G���[���b�Z�[�W�i�[���X�g
   * @return ArrayList<String> �`�F�b�N����
   */
  private ArrayList<String> checkInput( WorkTimeEntity wtEntity, ArrayList<String> errMsg ) {
    // html���� maxlength�w����L�q���Ă���ꍇ�A�����I�[�o�[�͔��������Ȃ����A
    // ���𓚗�ł̓��W�b�N���ł������I�Ƀ`�F�b�N�����{�B
    // �܂��A�`�F�b�N���ǂ��܂ō�肱�ނ����C�ӂ����A�Œ�ł������̓`�F�b�N�ƌ����`�F�b�N�͂����������ǂ��B

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getStaffNo() ) || wtEntity.getStaffNo().length() == 0 ) {
      errMsg.add( "�Ј��ԍ��������͂ł��B" );
      // �����`�F�b�N
    } else if ( wtEntity.getStaffNo().length() != 11 ) {
      errMsg.add( "�Ј��ԍ��̌������s���ł��B" );
    }

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getOfficeCd() ) || wtEntity.getOfficeCd().length() == 0 ) {
      errMsg.add( "���Ə��R�[�h�������͂ł��B" );
      // �����`�F�b�N
    } else if ( wtEntity.getOfficeCd().length() != 3 ) {
      errMsg.add( "���Ə��R�[�h�̌������s���ł��B" );
    }

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getStaffName() ) || wtEntity.getStaffName().length() == 0 ) {
      errMsg.add( "�Ј����������͂ł��B" );
      // �����`�F�b�N
    } else if ( wtEntity.getStaffName().length() > 20 ) {
      errMsg.add( "�Ј����̌������s���ł��B" );
    }

    // �����̓`�F�b�N
    if ( "".equals( wtEntity.getWorkTime() ) || wtEntity.getWorkTime().length() == 0 ) {
      errMsg.add( "�ғ����Ԃ������͂ł��B" );
      // �����`�F�b�N
    } else if ( wtEntity.getWorkTime().length() > 8 ) {
      errMsg.add( "�ғ����Ԃ̌������s���ł��B" );
    }
    // ���o�����G���[���b�Z�[�W��ԋp�B
    return errMsg;
  }

}
