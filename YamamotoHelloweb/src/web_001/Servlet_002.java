
package web_001;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演習Web_001の問２の解答例 <br />
 * 複数人の身長を読み込み、標準体重を表示する <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
@WebServlet(name = "/Servlet_002", urlPatterns = {"/web_001/Servlet_002"})
public class Servlet_002 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_002() {
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
    String param = request.getParameter( "param" );

    // 区切り文字で分割し、配列に格納
    String[] inputStr = param.split( "," );

    // 未入力はエラーとする
    if ( inputStr.length == 0 ) {
      pw.println( "複数人の身長をカンマ区切りで入力してください。" );
    }

    // 身長格納用変数
    int height = 0;

    for ( int i = 0; i < inputStr.length; i++ ) {

      try {
        // 文字列型から数値型に変換する
        height = Integer.parseInt( inputStr[i] );

        // 身長が100以下の場合は処理を中止する
        if ( height < 100 ) {
          pw.println( "身長は100以上で入力してください。" );
          break; // breakを消せば末尾まで処理を実行する
        }

      } catch ( NumberFormatException e ) {
        // 非数値を含む場合は処理を中止する
        pw.println( "身長は数値で入力してください。" );
        break; // breakを消せば末尾まで処理を実行する
      }

      // 正常に変換できた場合は平均体重を算出、身長とあわせて表示
      pw.println( "身長：" + height + "　" );
      pw.println( "平均体重：" + ( ( height - 100 ) * 0.9 ) + "<br>" );
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
