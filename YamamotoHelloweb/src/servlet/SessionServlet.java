
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �Z�b�V���������̃T���v�� <br />
 * �Z�b�V��������.pdf�@��{�I�ȃZ�b�V�����T�[�u���b�g�N���X <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public SessionServlet() {
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
    // �͂��߂ɃG���R�[�h�����{
    request.setCharacterEncoding( "Shift_JIS" );

    // �Z�b�V�����I�u�W�F�N�g���擾
    HttpSession session = request.getSession();

    // �������������X�g���擾
    ArrayList<String> buyList = ( ArrayList<String> ) session.getAttribute( "buyList" );

    String buyObj =request.getParameter( "param" );

    // ��������������Ȃ�V�K�ɍ쐬
    if (buyList == null){
      buyList = new ArrayList<String>();
    }

    // �����������ɒǉ�
    buyList.add( buyObj );

    // �Z�b�V�����ɃZ�b�g
    session.setAttribute( "buyList", buyList );

    // JSP�ɑJ�ځiWebContent���R���e�L�X�g���[�g�ƂȂ�j
    RequestDispatcher rd = request.getRequestDispatcher( "/ShowBuyList.jsp" );
    // �J�ځi�t�H���[�h�j���{
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
