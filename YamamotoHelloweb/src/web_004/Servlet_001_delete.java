
package web_004;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBAccessor;
import entity.WorkTimeEntity;

/**
 * 演習Web_004の問１の解答例 <br />
 * DB演習でサーバーに作成したテーブルのデータ検索と結果表示を行う <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */

@WebServlet(name = "/Servlet_010", urlPatterns = {
    "/web_004/Servlet_010"
})
public class Servlet_001_delete extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_001_delete() {
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

    // セッションからパラメーターを取得
    WorkTimeEntity wtEntity = new WorkTimeEntity();
    wtEntity = ( WorkTimeEntity ) session.getAttribute( "inputEntity" );

    // エラーMSG格納用文字列リスト
    ArrayList<String> errMsg = new ArrayList<String>();

    try {
      // DB処理の実行
      DBAccessor dbac = new DBAccessor( this.getServletContext() );
      int result = dbac.delete( wtEntity );

      // 削除結果が0件であればエラーと見なす。
      if ( result == 0 ) {
        errMsg.add( "データの削除に失敗しました。" );
      }

    } catch ( ClassNotFoundException e ) {
      errMsg.add( "クラスパスエラーが発生しました。" );
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      errMsg.add( "クラスアクセスエラーが発生しました。" );
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      errMsg.add( "インスタンス生成エラーが発生しました。" );
      e.printStackTrace();
    } catch ( SQLException e ) {
      // SQL実行時エラー(但し一意制約エラーは検索で発生し得ない)。
      errMsg.add( "SQL実行時エラーが発生しました。" );
      e.printStackTrace();
    } catch ( Exception e ) {
      errMsg.add( "想定外のエラーが発生しました。" );
      e.printStackTrace();
    }


    // エラーメッセージをリクエストに格納
    request.setAttribute( "errMsg", errMsg );
    // セッションを破棄し、結果画面へ遷移
    session.invalidate();
    RequestDispatcher rd = request.getRequestDispatcher( "/web_004/View_001_Delete.jsp" );
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
