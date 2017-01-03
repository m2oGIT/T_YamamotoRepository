
package web_001;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���KWeb_001�̖�Q�̉𓚗� <br />
 * �����l�̐g����ǂݍ��݁A�W���̏d��\������ <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
@WebServlet(name = "/Servlet_002", urlPatterns = {"/web_001/Servlet_002"})
public class Servlet_002 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_002() {
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

    // �p�����[�^�[���擾
    String param = request.getParameter( "param" );

    // ��؂蕶���ŕ������A�z��Ɋi�[
    String[] inputStr = param.split( "," );

    // �����͂̓G���[�Ƃ���
    if ( inputStr.length == 0 ) {
      pw.println( "�����l�̐g�����J���}��؂�œ��͂��Ă��������B" );
    }

    // �g���i�[�p�ϐ�
    int height = 0;

    for ( int i = 0; i < inputStr.length; i++ ) {

      try {
        // ������^���琔�l�^�ɕϊ�����
        height = Integer.parseInt( inputStr[i] );

        // �g����100�ȉ��̏ꍇ�͏����𒆎~����
        if ( height < 100 ) {
          pw.println( "�g����100�ȏ�œ��͂��Ă��������B" );
          break; // break�������Ζ����܂ŏ��������s����
        }

      } catch ( NumberFormatException e ) {
        // �񐔒l���܂ޏꍇ�͏����𒆎~����
        pw.println( "�g���͐��l�œ��͂��Ă��������B" );
        break; // break�������Ζ����܂ŏ��������s����
      }

      // ����ɕϊ��ł����ꍇ�͕��ϑ̏d���Z�o�A�g���Ƃ��킹�ĕ\��
      pw.println( "�g���F" + height + "�@" );
      pw.println( "���ϑ̏d�F" + ( ( height - 100 ) * 0.9 ) + "<br>" );
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
