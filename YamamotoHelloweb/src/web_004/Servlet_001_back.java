
package web_004;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演習Web_003の問２の解答例 <br />
 * DB演習でサーバーに作成したテーブルのデータ検索と結果表示を行う <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */

@WebServlet(name = "/Servlet_009", urlPatterns = {
    "/web_004/Servlet_009"
})
public class Servlet_001_back extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_001_back() {
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

    // 戻り時に何か処理を行う場合はここに記述する。
    // 今回は題意に従い特に何もせず、そのまま入力画面へリダイレクトする。
    RequestDispatcher rd = request.getRequestDispatcher( "/web_004/View_001_Input.jsp" );
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

}
