
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
 * 演習Web_001の問３の解答例 <br />
 * 入力値を３つ取り、四則演算を行う <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */

@WebServlet(name = "/Servlet_003", urlPatterns = {
    "/web_001/Servlet_003"
})
public class Servlet_003 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_003() {
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

    // HTTPレスポンスとして文字列を出力するライター
    PrintWriter pw = response.getWriter();

    // パラメーターを取得
    String firstParam = request.getParameter( "firstParam" );
    String secondParam = request.getParameter( "secondParam" );
    String thirdParam = request.getParameter( "thirdParam" );

    // 未入力はエラーとする
    if ( "".equals( firstParam ) || "".equals( secondParam ) || "".equals( thirdParam ) ){
      pw.println( "不正な値が入力されました。" );
      return;
    }

    // 演算値格納用変数
    BigDecimal firstNumber = null;
    BigDecimal secondNumber = null;
    BigDecimal resultNumber = null;

    try {
      // スケールを設定し入力値を格納
      firstNumber = new BigDecimal( firstParam ).setScale( 5, BigDecimal.ROUND_HALF_UP );
      secondNumber = new BigDecimal( thirdParam ).setScale( 5, BigDecimal.ROUND_HALF_UP );

    } catch ( NumberFormatException e ) {
      // 非数値の入力はエラーとする
      pw.println( "不正な値が入力されました。" );
      return;
    }

    // 演算記号に応じて処理を分岐
    switch ( secondParam ) {

      // 加算
      case "+":
        resultNumber = firstNumber.add( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        pw.println( firstNumber + "　" + secondParam + "　" + secondNumber + "　＝　" + resultNumber );
        break;

      // 減算
      case "-":
        resultNumber = firstNumber.subtract( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        pw.println( firstNumber + "　" + secondParam + "　" + secondNumber + "　＝　" + resultNumber );
        break;

      // 乗算
      case "*":
        resultNumber = firstNumber.multiply( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        pw.println( firstNumber + "　" + secondParam + "　" + secondNumber + "　＝　" + resultNumber );
        break;

      // 除算
      case "/":
        try {
          resultNumber = firstNumber.divide( secondNumber ).setScale( 5, BigDecimal.ROUND_HALF_UP );
        } catch ( ArithmeticException e ) {
          // ゼロ除算はエラーとする
          pw.println( "不正な値が入力されました。" );
          break;
        }
        pw.println( firstNumber + "　" + secondParam + "　" + secondNumber + "　＝　" + resultNumber );
        break;

      // 演算記号以外
      default:
        pw.println( "不正な値が入力されました。" );
        break;
    }

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

}
