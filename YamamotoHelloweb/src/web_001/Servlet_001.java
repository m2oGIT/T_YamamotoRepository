
package web_001;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���KWeb_001�̖�P�̉𓚗� <br />
 * ��P���͒l�̕�������Q���͒l�̉񐔕��J��Ԃ��\������ <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
@WebServlet(name = "/Servlet_001", urlPatterns = {"/web_001/Servlet_001"})
public class Servlet_001 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_001() {
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

    // ��P�p�����[�^�[���擾
    String firstParam = request.getParameter( "firstParam" );

    // ��Q�p�����[�^�[�͐��l�̂݋��e����
    try {
      int secondParam = Integer.parseInt( request.getParameter( "secondParam" ) );

      // ��Q�p�����[�^������l�ɓK�p
      for ( int i = 0; i < secondParam; i++ ) {
        // ��P�p�����[�^�[��\��
        pw.println( firstParam );
      }

    } catch ( NumberFormatException e ) {
      pw.println( "��Q�p�����[�^�[�ɂ͐��l����͂��ĉ�����" );
    }
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
