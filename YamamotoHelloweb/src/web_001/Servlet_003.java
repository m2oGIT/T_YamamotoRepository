
package web_001;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���KWeb_001�̖�R�̉𓚗� <br />
 * ���͒l���R���A�l�����Z���s�� <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */

@WebServlet(name = "/Servlet_003", urlPatterns = {
    "/web_001/Servlet_003"
})
public class Servlet_003 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public Servlet_003() {
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
    String firstParam = request.getParameter( "firstParam" );
    String secondParam = request.getParameter( "secondParam" );
    String thirdParam = request.getParameter( "thirdParam" );

    // �����͂̓G���[�Ƃ���
    if ( "".equals( firstParam ) || "".equals( secondParam ) || "".equals( thirdParam ) ){
      pw.println( "�s���Ȓl�����͂���܂����B" );
      return;
    }

    // ���Z�l�i�[�p�ϐ�
    BigDecimal firstNumber = null;
    BigDecimal secondNumber = null;
    BigDecimal resultNumber = null;

    try {
      // �X�P�[����ݒ肵���͒l���i�[
      firstNumber = new BigDecimal( firstParam ).setScale( 5, BigDecimal.ROUND_HALF_UP );
      secondNumber = new BigDecimal( thirdParam ).setScale( 5, BigDecimal.ROUND_HALF_UP );

    } catch ( NumberFormatException e ) {
      // �񐔒l�̓��͂̓G���[�Ƃ���
      pw.println( "�s���Ȓl�����͂���܂����B" );
      return;
    }

    // ���Z�L���ɉ����ď����𕪊�
    switch ( secondParam ) {

      // ���Z
      case "+":
        resultNumber = firstNumber.add( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        pw.println( firstNumber + "�@" + secondParam + "�@" + secondNumber + "�@���@" + resultNumber );
        break;

      // ���Z
      case "-":
        resultNumber = firstNumber.subtract( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        pw.println( firstNumber + "�@" + secondParam + "�@" + secondNumber + "�@���@" + resultNumber );
        break;

      // ��Z
      case "*":
        resultNumber = firstNumber.multiply( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        pw.println( firstNumber + "�@" + secondParam + "�@" + secondNumber + "�@���@" + resultNumber );
        break;

      // ���Z
      case "/":
        try {
          resultNumber = firstNumber.divide( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        } catch ( ArithmeticException e ) {
          // �[�����Z�̓G���[�Ƃ���
          pw.println( "�s���Ȓl�����͂���܂����B" );
          break;
        }
        pw.println( firstNumber + "�@" + secondParam + "�@" + secondNumber + "�@���@" + resultNumber );
        break;

      // ���Z�L���ȊO
      default:
        pw.println( "�s���Ȓl�����͂���܂����B" );
        break;
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
