
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.EmployeeEntity;

/**
 * �T�[�u���b�g�̃T���v�� <br />
 * Servlet�v���O���~���O.pdf�@��{�I�ȃT�[�u���b�g�N���X <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
// @WebServlet("/EchoServlet")
@WebServlet( name = "EchoServlet", urlPatterns = { "/EchoServlet" } )
// name��/�͏ȗ��\�AurlPatterns�̓t�H���_�����K�v
public class EchoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public EchoServlet() {
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
    // ContentType���w��
    response.setContentType( "text/html; charset=Shift_JIS" );

    // HTTP���X�|���X�Ƃ��ĕ�������o�͂��郉�C�^�[
    PrintWriter pw = response.getWriter();

    // ��������o�͂���
    pw.println( "Hello EchoServlet" );

    // HTML�`�����w�肵�ĕ�������o�͂���
    pw.println( "<html>" );
    pw.println( "<head>" );
    pw.println( "<title>EchoServlet</title>" );
    pw.println( "</head>" );
    pw.println( "<body>" );
    pw.println( "<h1><font color = \"red\">Hello EchoServlet!!</font></h1>" );
    pw.println( "<html>" );

    // index.html�̒l���擾���ĕ\������
    String param = request.getParameter( "param" );
    pw.println( param );

    // ���͒l��W���o�͂ɏo�͂���
    System.out.println( param );


    // ForwardServlet����󂯎�����l��\��
    pw.println( "<hr>" );
    EmployeeEntity entity = ( EmployeeEntity )request.getAttribute( "entity" );

    pw.println( "ForwardServlet����󂯎�����l��\��" + "<br>" );

    pw.println( entity.getNo() + "<br>" );
    pw.println( entity.getName() + "<br>" );
    pw.println( entity.getAge() + "<br>" );

    pw.println( "</body>" );
    pw.println( "</html>" );

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
