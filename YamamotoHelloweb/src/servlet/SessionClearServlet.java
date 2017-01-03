
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * セッション処理のサンプル <br />
 * セッション操作.pdf　基本的なセッションサーブレットクラス <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
@WebServlet("/SessionClearServlet")
public class SessionClearServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public SessionClearServlet() {
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

    // セッションオブジェクトを取得
    HttpSession session = request.getSession();

    // 買い物かごリストをクリア
    session.invalidate();

    // index.htmlに遷移（WebContentがコンテキストルートとなる）
    RequestDispatcher rd = request.getRequestDispatcher( "/index.html" );
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

}
