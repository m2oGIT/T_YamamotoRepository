
package web_001;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccessor;
import entity.WorkTimeEntity;

/**
 * 演習Web_001の問４の解答例 <br />
 * DB演習でサーバーに作成したテーブルへのデータ登録を行う <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */

@WebServlet(name = "/Servlet_004", urlPatterns = {
    "/web_001/Servlet_004"
})
public class Servlet_004 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_004() {
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

    // 初期処理を実行
    PrintWriter pw = initCode( request, response );

    // パラメーターを取得
    WorkTimeEntity wtEntity = new WorkTimeEntity();
    wtEntity = setEntity( wtEntity, request );

    // 入力チェック
    if ( !checkInput( wtEntity ) ) {
      pw.println( "入力値エラーが発生しました。" );
      return;
    }

    try {
      // DB処理の実行
      DBAccessor dbac = new DBAccessor( this.getServletContext() );
      dbac.insert( wtEntity );

    } catch ( ClassNotFoundException e ) {
      pw.println( "クラスパスエラーが発生しました。" );
      e.printStackTrace();
      return;
    } catch ( IllegalAccessException e ) {
      pw.println( "クラスアクセスエラーが発生しました。" );
      e.printStackTrace();
      return;
    } catch ( InstantiationException e ) {
      pw.println( "インスタンス生成エラーが発生しました。" );
      e.printStackTrace();
      return;
    } catch ( SQLException e ) {
      // 一意制約エラーのエラーコードはDB製品ごとに異なる。Oracleは00001、等。
      if ( e.getErrorCode() == 1062 ) {
        pw.println( "一意制約エラーが発生しました。" );
      } else {
        pw.println( "SQL実行時エラーが発生しました。" );
      }
      e.printStackTrace();
      return;
    } catch ( Exception e ) {
      pw.println( "想定外のエラーが発生しました。" );
      e.printStackTrace();
      return;
    }

    // 結果画面への遷移
    pw.println( "DBへの登録が正常に終了しました。" );

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
   * 共通初期処理メソッド<br />
   * 初期処理を実行します。 <br />
   *
   * @param request HTTPリクエスト
   * @param response HTTPレスポンス
   * @return pw セット済みPrintWriter
   * @throws IOException 入出力例外
   */
  private PrintWriter initCode( HttpServletRequest request, HttpServletResponse response )
      throws IOException {
    // エンコードを実施
    request.setCharacterEncoding( "Shift_JIS" );
    // ContentTypeを指定
    response.setContentType( "text/html; charset=Shift_JIS" );
    // ライターの生成
    PrintWriter pw = response.getWriter();

    return pw;

  }

  /**
   * 入力チパラメーター取得メソッド<br />
   * 入力内容をエンティティにセットします。 <br />
   *
   * @param wtEntity 入力内容エンティティ
   * @param request HTTPリクエスト
   * @return wtEntity セット済みエンティティ
   */
  private WorkTimeEntity setEntity( WorkTimeEntity wtEntity, HttpServletRequest request ) {

    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );

    // こういった処理メソッドはMVCにおけるmodelに該当し、別クラスに切り分ける場合が多い。
    // 当解答例は、servletがmodelの役割も兼ねた形になっている。
    wtEntity.setStaffNo( request.getParameter( "staffNo" ) );
    wtEntity.setOfficeCd( request.getParameter( "officeCd" ) );
    wtEntity.setStaffName( request.getParameter( "staffName" ) );
    wtEntity.setWorkTime( request.getParameter( "workTime" ) );
    wtEntity.setCreateDate( sdf.format( new Date() ) );

    // セット済みエンティティ。
    return wtEntity;
  }

  /**
   * 入力チェックメソッド<br />
   * 入力内容をチェックします。 <br />
   *
   * @param wtEntity 入力内容エンティティ
   * @return boolean チェック結果
   */
  private boolean checkInput( WorkTimeEntity wtEntity ) {
    // html側で maxlength指定を記述している場合、桁数オーバーは発生し得ないが、
    // 当解答例ではロジック側でも明示的にチェックを実施。
    // また、チェックをどこまで作りこむかも任意だが、最低でも未入力チェックと桁数チェックはあった方が良い。

    // 未入力チェック
    if ( "".equals( wtEntity.getStaffNo() ) || wtEntity.getStaffNo().length() == 0 ) {
      return false;
    }
    // 桁数チェック
    if ( wtEntity.getStaffNo().length() != 11 ) {
      return false;
    }

    // 未入力チェック
    if ( "".equals( wtEntity.getOfficeCd() ) || wtEntity.getOfficeCd().length() == 0 ) {
      return false;
    }
    // 桁数チェック
    if ( wtEntity.getOfficeCd().length() != 3 ) {
      return false;
    }

    // 未入力チェック
    if ( "".equals( wtEntity.getStaffName() ) || wtEntity.getStaffName().length() == 0 ) {
      return false;
    }
    // 桁数チェック
    if ( wtEntity.getStaffName().length() > 20 ) {
      return false;
    }

    // 未入力チェック
    if ( "".equals( wtEntity.getWorkTime() ) || wtEntity.getWorkTime().length() == 0 ) {
      return false;
    }
    // 桁数チェック
    if ( wtEntity.getWorkTime().length() > 5 ) {
      return false;
    }
    try {
      // 数値型に変換できない場合はエラー
      Integer.parseInt( wtEntity.getWorkTime() );
    } catch ( NumberFormatException e ) {
      return false;
    }

    // 全てのチェックをパスすればtrueとする。
    return true;
  }

}
