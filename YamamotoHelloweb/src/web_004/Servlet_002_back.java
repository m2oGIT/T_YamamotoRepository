
package web_004;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ���KWeb_004�̖�Q�̉𓚗� <br />
 * DB���K�ŃT�[�o�[�ɍ쐬�����e�[�u���̃f�[�^�����ƌ��ʕ\�����s�� <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */

@WebServlet(name = "/Servlet_012", urlPatterns = {
    "/web_004/Servlet_012"
})
public class Servlet_002_back extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_002_back() {
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

    // �߂莞�ɉ����������s���ꍇ�͂����ɋL�q����B
    ArrayList<String> errMsg = ( ArrayList<String> ) session.getAttribute( "errMsg" );

    // �����ʂ���̖߂莞�̓Z�b�V������j������B
    if ( errMsg.size() == 0 ) {
        session.invalidate();
      }

    RequestDispatcher rd = request.getRequestDispatcher( "/web_004/View_002_Input.jsp" );
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
