/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL006;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DB演習問題（MySQL006） <br />
 * 引数で渡されたSQLを実行するプログラムの実装例（DBアクセッサの実装） <br />
 * 更新履歴 2015/10/28 山本 高志：新規作成 <br />
 */
public class ExecuteArgsSQLbyDBAccessor {

  /** MySQLの一意制約エラーコード */
  private static final String SQL_ERR_CODE = "1062";

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public ExecuteArgsSQLbyDBAccessor() {
    // 行うべき処理なし。
  }

  /**
   * メインメソッド<br />
   * 処理を起動します。 <br />
   *
   * @param args 実行時引数
   */
  public static void main( String[] args ) {

    // DB処理用インスタンスを生成。
    PreparedStatement pstmt = null;
    Connection conn = null;
    DBAccessor dba = new DBAccessor();

    // 起動時引数チェック。
    if ( args == null || args.length == 0 ) {
      System.out.println( "起動引数を入力して下さい。" );
      return;
    }

    // 起動時引数からSQL文を生成する。
    String sqlStr = dba.createQuery( args );

    try {
      // コネクションを生成。
      conn = dba.createConnection( conn );
      // コネクションからステートメントを生成。
      pstmt = conn.prepareStatement( sqlStr );
      // パラメータオブジェクトをセット。
      dba.setParamObject( pstmt, args );
      // SQLを実行。
      dba.execStatement( pstmt, sqlStr );
      // ステートメントをクローズ。
      pstmt.close();

      // エラー発生時はエラーの内容を表示。
    } catch ( ClassNotFoundException e ) {
      System.out.println( "ClassNotFoundException:" + e.getMessage() );

    } catch ( SQLException e ) {
      System.out.println( "SQLException:" + e.getMessage() );

      // 一意制約エラーの判定
      if ( SQL_ERR_CODE.equals( String.valueOf( e.getErrorCode() ) ) ) {
        System.out.println( "一意制約エラーが発生しました。" );
        e.printStackTrace();
      }

    } catch ( Exception e ) {
      System.out.println( "Exception:" + e.getMessage() );

    } finally {
      try {
        // コネクションをクローズ。
        if ( conn != null ) {
          conn.close();
          System.out.println( "コネクションを解放しました。" );
        }
      } catch ( SQLException e ) {
        System.out.println( "SQLException:" + e.getMessage() );
      }
    }
  }

}
