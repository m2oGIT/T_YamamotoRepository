
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.EmployeeEntity;

/**
 * �t�H���[�h�T�[�u���b�g�̃T���v�� <br />
 * Servlet��JSP�̘A�g.pdf�@��{�I�ȃT�[�u���b�g�N���X <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
// @WebServlet("/ForwardServlet")
@WebServlet(name = "ForwardServlet", urlPatterns = {
  "/ForwardServlet"
})
public class ForwardServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public ForwardServlet() {
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

    // �����̌��ʂƂ��Ė��O�ƔN����擾�����Ƃ���
    request.setAttribute( "name", "�R�c���Y" );
    request.setAttribute( "age", new Integer( 20 ) );

    // �G���e�B�e�B�쐬
    EmployeeEntity entity = new EmployeeEntity();
    entity.setNo( "0001" );
    entity.setName( "�R�c���Y" );
    entity.setAge( "30" );

    // ���N�G�X�g�ɃG���e�B�e�B���Z�b�g
    request.setAttribute( "entity", entity );

    // // EchoJSP.jsp�ɑJ�ځiWebContent���R���e�L�X�g���[�g�ƂȂ�j
    // RequestDispatcher rd = request.getRequestDispatcher( "/EchoJSP.jsp" );
    // EchoServlet.java�ɑJ�ځiWebContent���R���e�L�X�g���[�g�ƂȂ�j
    RequestDispatcher rd = request.getRequestDispatcher( "/EchoServlet" );
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
