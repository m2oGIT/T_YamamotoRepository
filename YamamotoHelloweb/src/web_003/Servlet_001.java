
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
 * 演習Web_003の問１の解答例 <br />
 * 西暦年月日を入力し、和暦日付に変換しJSPページ中に表示する <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
@WebServlet(name = "/Servlet_005", urlPatterns = {
    "/web_003/Servlet_005"
})
public class Servlet_001 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_001() {
    // 行うべき処理なし
    super();
  }

  /**
   * doGetメソッド<br />
   * doGetを実行します。 <br />
   *
   * @param request HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws ServletException サーブレット例外
   * @throws IOException 入出力例外
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {

    // はじめにエンコードを実施
    request.setCharacterEncoding( "Shift_JIS" );
    // ContentTypeを指定
    response.setContentType( "text/html; charset=Shift_JIS" );

    // パラメーターを取得し、出力用メッセージを生成
    String outputMsg = this.createMsg( request.getParameter( "inputDateParam" ) );

    // リクエストにセット
    request.setAttribute( "outputMsg", outputMsg );

    // JSPに遷移
    RequestDispatcher rd = request.getRequestDispatcher( "/web_003/View_001_Output.jsp" );

    // 遷移（フォワード）実施
    rd.forward( request, response );

  }

  /**
   * doPostメソッド<br />
   * doPostを実行します。 <br />
   *
   * @param request HTTPリクエスト
   * @param response HTTPレスポンス
   * @throws ServletException サーブレット例外
   * @throws IOException 入出力例外
   */
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    // doGetメソッドに委譲
    this.doGet( request, response );
  }

  /**
   * 出力用メッセージ生成メソッド<br />
   * 入力値を元に出力用メッセージを生成します。 <br />
   *
   * @param inputDateParam 画面入力文字列
   * @return sb 編集済みメッセージ
   */
  private String createMsg( String inputDateParam ) {

    // 出力メッセージ格納文字列
    StringBuilder sb = new StringBuilder();

    // 未入力チェック
    if ( inputDateParam == null || "".equals( inputDateParam ) || inputDateParam.length() == 0 ) {
      sb.append( "未入力エラーです。値を入力して下さい。" );
      // 桁数チェック
    } else if ( inputDateParam.length() != 8 ) {
      sb.append( "桁数エラーです。8桁の数値を入力して下さい。" );
      // 数値チェック
    } else if ( !StringUtil.isNumber( inputDateParam ) ) {
      sb.append( "数値エラーです。8桁の数値を入力して下さい。" );
      // 日付チェック
    } else if ( !DateUtil.isDefaultDate( inputDateParam ) ) {
      sb.append( "日付エラーです。正しい数値を入力して下さい。" );
      // 上記のチェックをパスした場合
    } else {
      // 和暦へ変換し、メッセージを生成
      sb.append( "[" + DateUtil.formatDDtoAD( inputDateParam ) + "}" );
      sb.append( "の和暦日付は<br>" );
      sb.append( "[" + DateUtil.formatDefaultToJP( inputDateParam ) + "}" );
      sb.append( "です。" );
    }

    // 編集済みメッセージを返却
    return sb.toString();
  }

}
