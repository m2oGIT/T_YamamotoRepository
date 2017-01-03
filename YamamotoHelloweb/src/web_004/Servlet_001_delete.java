
package web_004;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * ���KWeb_004�̖�P�̉𓚗� <br />
 * DB���K�ŃT�[�o�[�ɍ쐬�����e�[�u���̃f�[�^�����ƌ��ʕ\�����s�� <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */

@WebServlet(name = "/Servlet_010", urlPatterns = {
    "/web_004/Servlet_010"
})
public class Servlet_001_delete extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_001_delete() {
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

    // �Z�b�V��������p�����[�^�[���擾
    WorkTimeEntity wtEntity = new WorkTimeEntity();
    wtEntity = ( WorkTimeEntity ) session.getAttribute( "inputEntity" );

    // �G���[MSG�i�[�p�����񃊃X�g
    ArrayList<String> errMsg = new ArrayList<String>();

    try {
      // DB�����̎��s
      DBAccessor dbac = new DBAccessor( this.getServletContext() );
      int result = dbac.delete( wtEntity );

      // �폜���ʂ�0���ł���΃G���[�ƌ��Ȃ��B
      if ( result == 0 ) {
        errMsg.add( "�f�[�^�̍폜�Ɏ��s���܂����B" );
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
      // SQL���s���G���[(�A����Ӑ���G���[�͌����Ŕ��������Ȃ�)�B
      errMsg.add( "SQL���s���G���[���������܂����B" );
      e.printStackTrace();
    } catch ( Exception e ) {
      errMsg.add( "�z��O�̃G���[���������܂����B" );
      e.printStackTrace();
    }


    // �G���[���b�Z�[�W�����N�G�X�g�Ɋi�[
    request.setAttribute( "errMsg", errMsg );
    // �Z�b�V������j�����A���ʉ�ʂ֑J��
    session.invalidate();
    RequestDispatcher rd = request.getRequestDispatcher( "/web_004/View_001_Delete.jsp" );
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

}
