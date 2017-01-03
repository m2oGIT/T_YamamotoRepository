
package web_004;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * 演習Web_004の問２の解答例 <br />
 * DB演習でサーバーに作成したテーブルのデータ登録と結果表示を行う <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */

@WebServlet(name = "/Servlet_011", urlPatterns = {
    "/web_004/Servlet_011"
})
public class Servlet_002_main extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public Servlet_002_main() {
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

    // パラメーターを取得
    WorkTimeEntity wtEntity = new WorkTimeEntity();
    wtEntity = setEntity( wtEntity, request );

    // 検索結果格納用にentityのリストを用意しておく
    // WorkTimeEntity resultEntity = new WorkTimeEntity();
    List<WorkTimeEntity> resultEntityList = new ArrayList<WorkTimeEntity>();

    // エラーMSG格納用文字列リスト
    ArrayList<String> errMsg = new ArrayList<String>();
    errMsg = checkInput( wtEntity, errMsg );

    // 入力チェックをパスした場合のみ、DB処理を実行
    if ( errMsg.size() == 0 ) {

      try {
        // DB処理の実行
        // DB処理の実行
        DBAccessor dbac = new DBAccessor( this.getServletContext() );
        dbac.insert( wtEntity );

        resultEntityList = dbac.selectAll();

        // 検索結果が無しであればエラーと見なす。
        // 検索結果0件の検知は、DBアクセッサでnullで仮置きで初期化する方法もある。
        if ( "".equals( resultEntityList.get( 0 ).getStaffNo() ) ) {
          errMsg.add( "DBに未登録のデータです。" );
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
        // 一意制約エラー
        if ( 1022 == e.getErrorCode() || 1062 == e.getErrorCode() ) {
          errMsg.add( "一意制約エラーが発生しました。" );
        } else {
          // SQL実行時エラー
          errMsg.add( "SQL実行時エラーが発生しました。" );
        }
        e.printStackTrace();
      } catch ( Exception e ) {
        errMsg.add( "想定外のエラーが発生しました。" );
        e.printStackTrace();
      }
    }
    // 結果画面への遷移
    session.setAttribute( "inputEntity", wtEntity );
    session.setAttribute( "resultEntityList", resultEntityList );
    session.setAttribute( "errMsg", errMsg );
    RequestDispatcher rd = request.getRequestDispatcher( "/web_004/View_002_Output.jsp" );
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
   * 入力チパラメーター取得メソッド<br />
   * 入力内容をエンティティにセットします。 <br />
   *
   * @param wtEntity 入力内容エンティティ
   * @param request HTTPリクエスト
   * @return wtEntity セット済みエンティティ
   */
  private WorkTimeEntity setEntity( WorkTimeEntity wtEntity, HttpServletRequest request ) {

    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );

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
   * @param errMsg エラーメッセージ格納リスト
   * @return ArrayList<String> チェック結果
   */
  private ArrayList<String> checkInput( WorkTimeEntity wtEntity, ArrayList<String> errMsg ) {
    // html側で maxlength指定を記述している場合、桁数オーバーは発生し得ないが、
    // 当解答例ではロジック側でも明示的にチェックを実施。
    // また、チェックをどこまで作りこむかも任意だが、最低でも未入力チェックと桁数チェックはあった方が良い。

    // 未入力チェック
    if ( "".equals( wtEntity.getStaffNo() ) || wtEntity.getStaffNo().length() == 0 ) {
      errMsg.add( "社員番号が未入力です。" );
      // 桁数チェック
    } else if ( wtEntity.getStaffNo().length() != 11 ) {
      errMsg.add( "社員番号の桁数が不正です。" );
    }

    // 未入力チェック
    if ( "".equals( wtEntity.getOfficeCd() ) || wtEntity.getOfficeCd().length() == 0 ) {
      errMsg.add( "事業所コードが未入力です。" );
      // 桁数チェック
    } else if ( wtEntity.getOfficeCd().length() != 3 ) {
      errMsg.add( "事業所コードの桁数が不正です。" );
    }

    // 未入力チェック
    if ( "".equals( wtEntity.getStaffName() ) || wtEntity.getStaffName().length() == 0 ) {
      errMsg.add( "社員名が未入力です。" );
      // 桁数チェック
    } else if ( wtEntity.getStaffName().length() > 20 ) {
      errMsg.add( "社員名の桁数が不正です。" );
    }

    // 未入力チェック
    if ( "".equals( wtEntity.getWorkTime() ) || wtEntity.getWorkTime().length() == 0 ) {
      errMsg.add( "稼働時間が未入力です。" );
      // 桁数チェック
    } else if ( wtEntity.getWorkTime().length() > 8 ) {
      errMsg.add( "稼働時間の桁数が不正です。" );
    }
    // 検出したエラーメッセージを返却。
    return errMsg;
  }

}
