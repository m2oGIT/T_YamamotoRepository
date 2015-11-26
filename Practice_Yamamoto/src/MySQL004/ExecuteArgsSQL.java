/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB演習問題（MySQL004） <br />
 * 引数で渡されたSQLを実行するプログラムの実装例 <br />
 * 更新履歴 2015/10/28 山本 高志：新規作成 <br />
 */
public class ExecuteArgsSQL {

  /** SELECT文のPrefix */
  private static final String SELECT = "SELECT";

  /** INSERT文のPrefix */
  private static final String INSERT = "INSERT";

  /** UPDATE文のPrefix */
  private static final String UPDATE = "UPDATE";

  /** DELETE文のPrefix */
  private static final String DELETE = "DELETE";

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public ExecuteArgsSQL() {
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
    Statement stmt = null;
    Connection conn = null;

    // 起動時引数チェック。
    if ( args == null || args.length == 0 ) {
      System.out.println( "起動引数を入力して下さい。" );
      return;
    }
    // 起動時引数をSQL文として格納する。
    String sqlStr = args[0];

    try {
      // コネクションを生成。
      conn = createConnection( conn );
      // コネクションからステートメントを生成。
      stmt = conn.createStatement();
      // SQLを実行。
      execStatement( stmt, sqlStr );
      // ステートメントをクローズ。
      stmt.close();

      // エラー発生時はエラーの内容を表示。
    } catch ( ClassNotFoundException e ) {
      System.out.println( "ClassNotFoundException:" + e.getMessage() );
    } catch ( SQLException e ) {
      System.out.println( "SQLException:" + e.getMessage() );
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


  /**
   * DBコネクション生成メソッド<br />
   * 指定された接続情報でDBに接続します。 <br />
   *
   * @param conn 接続前コネクション
   * @throws ClassNotFoundException ドライバロード例外
   * @throws IllegalAccessException 想定外アクセス例外
   * @throws InstantiationException ドライバインスタンス生成例外
   * @throws SQLException SQL例外
   * @return Connection 接続済みコネクション
   */
  private static Connection createConnection( Connection conn )
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

    // 接続先DBの指定文字列
    String url = "jdbc:mysql://m2o-eiwa.1strentalserver.info:3306/moeiwast_eiwatest_DB01";

    // ドライバをロード。
    Class.forName( "com.mysql.jdbc.Driver" ).newInstance();

    // DB接続情報をセット。
    Properties props = new Properties();
    props.put( "user", "moeiwast_user02" );
    props.put( "password", "zaq12wsx_user02" );
    props.put( "useUnicode", "true" );
    props.put( "characterEncoding", "SJIS" );

    // 接続情報を元に、コネクションを生成。
    conn = DriverManager.getConnection( url, props );
    System.out.println( "コネクションを生成しました。" );

    // コネクションを返却。
    return conn;
  }

  /**
   * SQL実行メソッド<br />
   * 指定されたSQLを実行します。 <br />
   *
   * @param stmt 作成済みのステートメント
   * @param sqlStr 起動引数で指定されたSQL文
   * @throws SQLException SQL例外
   */
  private static void execStatement( Statement stmt, String sqlStr ) throws SQLException {

    // 結果格納用の変数
    ResultSet rs = null;
    int count = 0;

    // 参照系の場合
    if ( sqlStr.toUpperCase().startsWith( SELECT ) ) { // 小文字記述を考慮し、大文字に変換

      // SQLを実行
      rs = stmt.executeQuery( sqlStr );
      System.out.println( "実行SQL : " + sqlStr + ";" );
      // 結果を表示
      dispResult( rs );

      // 更新系の場合
    } else if ( sqlStr.toUpperCase().startsWith( INSERT )
        || sqlStr.toUpperCase().startsWith( UPDATE )
        || sqlStr.toUpperCase().startsWith( DELETE ) ) {

      // SQLを実行
      count = stmt.executeUpdate( sqlStr );
      System.out.println( "実行SQL : " + sqlStr + ";" );
      // 結果を表示
      System.out.println( "更新が完了しました。" + count + " 件を更新しました。" );

      // 上記以外はSQLを実行しない
    } else {
      System.out.println( "SQLは SELECT or INSERT or UPDATE or DELETE を指定してください。" );
    }

    // 検索結果のクローズ。
    if ( rs != null ) {
      rs.close();
    }
  }

  /**
   * 検索結果表示メソッド<br />
   * ResultSetを展開してコンソールに表示します。 <br />
   *
   * @param rs 検索結果
   * @throws SQLException SQL例外
   */
  private static void dispResult( ResultSet rs ) throws SQLException {

    // カウント用変数
    int i = 1;
    // 検索結果を末尾まで表示する。
    if ( rs != null ) {
      System.out.println( "SQL実行結果" );
      while ( rs.next() ) {
        System.out.println( "[" + i + "件目]" );
        // カラムごとに値を取り出す。
        System.out.println( "STAFF_NO    : " + rs.getString( "STAFF_NO" ) );
        System.out.println( "OFFICE_CD   : " + rs.getString( "OFFICE_CD" ) );
        System.out.println( "STAFF_NAME  : " + rs.getString( "STAFF_NAME" ) );
        System.out.println( "WORK_TIME   : " + rs.getDouble( "WORK_TIME" ) );
        System.out.println( "CREATE_DATE : " + rs.getString( "CREATE_DATE" ) );
        System.out.println( "" );
        i++;
      }
    }
  }

}
