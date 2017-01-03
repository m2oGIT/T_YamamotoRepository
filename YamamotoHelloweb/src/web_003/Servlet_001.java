
package web_003;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utility.DateUtil;
import Utility.StringUtil;

/**
 * ���KWeb_003�̖�P�̉𓚗� <br />
 * ����N��������͂��A�a����t�ɕϊ���JSP�y�[�W���ɕ\������ <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
@WebServlet(name = "/Servlet_005", urlPatterns = {
    "/web_003/Servlet_005"
})
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

    // �p�����[�^�[���擾���A�o�͗p���b�Z�[�W�𐶐�
    String outputMsg = this.createMsg( request.getParameter( "inputDateParam" ) );

    // ���N�G�X�g�ɃZ�b�g
    request.setAttribute( "outputMsg", outputMsg );

    // JSP�ɑJ��
    RequestDispatcher rd = request.getRequestDispatcher( "/web_003/View_001_Output.jsp" );

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

  /**
   * �o�͗p���b�Z�[�W�������\�b�h<br />
   * ���͒l�����ɏo�͗p���b�Z�[�W�𐶐����܂��B <br />
   *
   * @param inputDateParam ��ʓ��͕�����
   * @return sb �ҏW�ς݃��b�Z�[�W
   */
  private String createMsg( String inputDateParam ) {

    // �o�̓��b�Z�[�W�i�[������
    StringBuilder sb = new StringBuilder();

    // �����̓`�F�b�N
    if ( inputDateParam == null || "".equals( inputDateParam ) || inputDateParam.length() == 0 ) {
      sb.append( "�����̓G���[�ł��B�l����͂��ĉ������B" );
      // �����`�F�b�N
    } else if ( inputDateParam.length() != 8 ) {
      sb.append( "�����G���[�ł��B8���̐��l����͂��ĉ������B" );
      // ���l�`�F�b�N
    } else if ( !StringUtil.isNumber( inputDateParam ) ) {
      sb.append( "���l�G���[�ł��B8���̐��l����͂��ĉ������B" );
      // ���t�`�F�b�N
    } else if ( !DateUtil.isDefaultDate( inputDateParam ) ) {
      sb.append( "���t�G���[�ł��B���������l����͂��ĉ������B" );
      // ��L�̃`�F�b�N���p�X�����ꍇ
    } else {
      // �a��֕ϊ����A���b�Z�[�W�𐶐�
      sb.append( "[" + DateUtil.formatDDtoAD( inputDateParam ) + "}" );
      sb.append( "�̘a����t��<br>" );
      sb.append( "[" + DateUtil.formatDefaultToJP( inputDateParam ) + "}" );
      sb.append( "�ł��B" );
    }

    // �ҏW�ς݃��b�Z�[�W��ԋp
    return sb.toString();
  }

}
