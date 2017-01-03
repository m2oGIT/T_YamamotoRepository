
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.EmployeeEntity;

/**
 * フォワードサーブレットのサンプル <br />
 * ServletとJSPの連携.pdf　基本的なサーブレットクラス <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
// @WebServlet("/ForwardServlet")
@WebServlet(name = "ForwardServlet", urlPatterns = {
  "/ForwardServlet"
})
public class ForwardServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public ForwardServlet() {
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

    // 処理の結果として名前と年齢を取得したとする
    request.setAttribute( "name", "山田太郎" );
    request.setAttribute( "age", new Integer( 20 ) );

    // エンティティ作成
    EmployeeEntity entity = new EmployeeEntity();
    entity.setNo( "0001" );
    entity.setName( "山田次郎" );
    entity.setAge( "30" );

    // リクエストにエンティティをセット
    request.setAttribute( "entity", entity );

    // // EchoJSP.jspに遷移（WebContentがコンテキストルートとなる）
    // RequestDispatcher rd = request.getRequestDispatcher( "/EchoJSP.jsp" );
    // EchoServlet.javaに遷移（WebContentがコンテキストルートとなる）
    RequestDispatcher rd = request.getRequestDispatcher( "/EchoServlet" );
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
