
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EchoServlet
 */
@WebServlet("/EchoServlet")
public class EchoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public EchoServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {

    // �͂��߂Ƀ��N�G�X�g�̃G���R�[�h�����{
    request.setCharacterEncoding( "Windows-31J" );
    //request.setCharacterEncoding( "Shift_JIS" );
    response.setContentType( "text/html; charset=Windows-31J" );
    // ContentType���w��
    //response.setContentType( "text/html; charset=Shift_JIS" );
    // HTTP���X�|���X�Ƃ��ĕ�������o�͂��郉�C�^�[
    PrintWriter pw = response.getWriter();


    // ��������o�͂���B
    // pw.println( "Hello EchoServlet!!" );


    pw.println( "<html>" );
    pw.println( "<head>" );
    pw.println( "<title>EchoServlet</title>" );
    pw.println( "</head>" );
    pw.println( "<body>" );
    pw.println( "<h1><font color=\"red\">Hello EchoServlet</font></h1>" );
    pw.println( "<br>" );

    // index.html����̒l���擾���ĕ\������
    String param = request.getParameter( "param" );
    pw.println( param );

    // ���͒l��W���o�͂���B
    System.out.println( param );

    pw.println( "</body>" );
    pw.println( "</html>" );

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    // doGET���\�b�h�ɈϏ�����B
    this.doGet( request, response );
  }

}
