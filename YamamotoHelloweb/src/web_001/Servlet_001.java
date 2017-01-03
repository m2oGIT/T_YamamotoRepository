
package web_001;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演習Web_001の問１の解答例 <br />
 * 第１入力値の文字列を第２入力値の回数分繰り返し表示する <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
@WebServlet(name = "/Servlet_001", urlPatterns = {"/web_001/Servlet_001"})
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

    // HTTPレスポンスとして文字列を出力するライター
    PrintWriter pw = response.getWriter();

    // 第１パラメーターを取得
    String firstParam = request.getParameter( "firstParam" );

    // 第２パラメーターは数値のみ許容する
    try {
      int secondParam = Integer.parseInt( request.getParameter( "secondParam" ) );

      // 第２パラメータを上限値に適用
      for ( int i = 0; i < secondParam; i++ ) {
        // 第１パラメーターを表示
        pw.println( firstParam );
      }

    } catch ( NumberFormatException e ) {
      pw.println( "第２パラメーターには数値を入力して下さい" );
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
