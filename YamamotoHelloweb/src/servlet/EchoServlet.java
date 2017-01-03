
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.EmployeeEntity;

/**
 * サーブレットのサンプル <br />
 * Servletプログラミング.pdf　基本的なサーブレットクラス <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
// @WebServlet("/EchoServlet")
@WebServlet( name = "EchoServlet", urlPatterns = { "/EchoServlet" } )
// nameの/は省略可能、urlPatternsはフォルダ名が必要
public class EchoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public EchoServlet() {
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

    // 文字列を出力する
    pw.println( "Hello EchoServlet" );

    // HTML形式を指定して文字列を出力する
    pw.println( "<html>" );
    pw.println( "<head>" );
    pw.println( "<title>EchoServlet</title>" );
    pw.println( "</head>" );
    pw.println( "<body>" );
    pw.println( "<h1><font color = \"red\">Hello EchoServlet!!</font></h1>" );
    pw.println( "<html>" );

    // index.htmlの値を取得して表示する
    String param = request.getParameter( "param" );
    pw.println( param );

    // 入力値を標準出力に出力する
    System.out.println( param );


    // ForwardServletから受け取った値を表示
    pw.println( "<hr>" );
    EmployeeEntity entity = ( EmployeeEntity )request.getAttribute( "entity" );

    pw.println( "ForwardServletから受け取った値を表示" + "<br>" );

    pw.println( entity.getNo() + "<br>" );
    pw.println( entity.getName() + "<br>" );
    pw.println( entity.getAge() + "<br>" );

    pw.println( "</body>" );
    pw.println( "</html>" );

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
