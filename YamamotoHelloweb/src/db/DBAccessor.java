/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import Utility.DateUtil;
import entity.WorkTimeEntity;

/**
 * 演習Web_001の問４の解答例 <br />
 * 引数で渡されたSQLを実行するプログラムの実装例（DBアクセッサの実装） <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
public class DBAccessor {

  /** DB処理用のインスタンス */
  private PreparedStatement pstmt = null;
  private Connection conn = null;

  /** DBローダーのインスタンス */
  private static PropertyLoader dbLoader = null;

  /** SQLローダーのインスタンス */
  private static PropertyLoader sqlLoader = null;

  /** DB接続プロパティのパス */
  private static final String DB_CONNECT_PROP = "/run/properties/dbConnect.properties";

  /** SQLプロパティのパス */
  private static final String SQL_PROP = "/run/properties/sql.properties";

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   *
   * @param context httpコンテキスト
   * @throws SQLException SQL実行時例外
   * @throws InstantiationException インスタンス生成例外
   * @throws IllegalAccessException クラスアクセス例外
   * @throws ClassNotFoundException クラスパス例外
   */
  public DBAccessor( ServletContext context ) throws ClassNotFoundException,
      IllegalAccessException, InstantiationException, SQLException {
    try {

      // パスを絶対パスで記述している場合は、context.getRealPathは不要。

      // DBローダーの生成。
      dbLoader = new PropertyLoader( context.getRealPath( DB_CONNECT_PROP ) );
      // SQLローダーの生成。
      sqlLoader = new PropertyLoader( context.getRealPath( SQL_PROP ) );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    // コネクション生成
    createConnection();
  }

  /**
   * DBコネクション生成メソッド<br />
   * 指定された接続情報でDBに接続します。 <br />
   *
   * @throws ClassNotFoundException ドライバロード例外
   * @throws IllegalAccessException 想定外アクセス例外
   * @throws InstantiationException ドライバインスタンス生成例外
   * @throws SQLException SQL例外
   * @return Connection 接続済みコネクション
   */
  private Connection createConnection()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

    // 接続先DBの指定文字列
    String url = dbLoader.getValue( "url-info" );
    // ドライバをロード。
    Class.forName( dbLoader.getValue( "driver-name" ) ).newInstance();

    // DB接続情報をセット。
    Properties props = new Properties();
    props.put( "user", dbLoader.getValue( "user-id" ) );
    props.put( "password", dbLoader.getValue( "pass-word" ) );
    props.put( "useUnicode", dbLoader.getValue( "use-unicode" ) );
    props.put( "characterEncoding", dbLoader.getValue( "char-code" ) );

    // 接続情報を元に、コネクションを生成。
    conn = DriverManager.getConnection( url, props );
    System.out.println( "コネクションを生成しました。" );

    // コネクションを返却。
    return conn;
  }

  /**
   * insert実行メソッド<br />
   * エンティティの内容をテーブルに登録します。 <br />
   *
   * @param wtEntity エンティティ
   * @return rs 登録件数
   * @throws SQLException SQL実行例外
   */
  public int insert( WorkTimeEntity wtEntity ) throws SQLException {

    // プロパティからSQL文を生成する。
    String sqlStr = sqlLoader.getValue( "INSERT-SINGLE" );
    // コネクションからステートメントを生成。
    pstmt = conn.prepareStatement( sqlStr );

    // パラメータオブジェクトをセット。
    pstmt.setObject( 1, wtEntity.getStaffNo() );
    pstmt.setObject( 2, wtEntity.getOfficeCd() );
    pstmt.setObject( 3, wtEntity.getStaffName() );
    pstmt.setObject( 4, wtEntity.getWorkTime() );
    pstmt.setObject( 5, wtEntity.getCreateDate() );

    // SQLを実行。
    int rs = pstmt.executeUpdate();

    // 登録完了をコンソールに出力
    System.out.println( "DB登録が完了しました。" );

    // コミットを実行(autocommit=true時はエラー)
    // conn.commit();
    // ステートメントをクローズ。
    pstmt.close();

    return rs;
  }

  /**
   * select実行メソッド<br />
   * エンティティの内容でテーブルを検索します。 <br />
   *
   * @param wtEntity エンティティ
   * @return resultEntity 検索結果エンティティ
   * @throws SQLException SQL実行例外
   */
  public WorkTimeEntity select( WorkTimeEntity wtEntity ) throws SQLException {

    // 結果格納用エンティティ
    WorkTimeEntity resultEntity = new WorkTimeEntity();

    // プロパティからSQL文を生成する。
    String sqlStr = sqlLoader.getValue( "SELECT-SINGLE" );
    // コネクションからステートメントを生成。
    pstmt = conn.prepareStatement( sqlStr );

    // パラメータオブジェクトをセット。
    pstmt.setObject( 1, wtEntity.getStaffNo() );
    pstmt.setObject( 2, wtEntity.getOfficeCd() );
    pstmt.setObject( 3, wtEntity.getStaffName() );

    // SQLを実行。
    ResultSet rs = pstmt.executeQuery();

    if ( rs != null ) {
      while ( rs.next() ) {

        // カラムの型に合わせてgetStringで値を取り出す。
        resultEntity.setStaffNo( rs.getString( "STAFF_NO" ) );
        resultEntity.setOfficeCd( rs.getString( "OFFICE_CD" ) );
        resultEntity.setStaffName( rs.getString( "STAFF_NAME" ) );
        resultEntity.setWorkTime( rs.getString( "WORK_TIME" ) );
        // 日付は"/"区切りに変換して格納。
        // 本来はUtilクラスの仕様変更に直接影響されないように、
        // entityへのセット処理も別クラス/別メソッドに切り分けた方が良い。
        resultEntity.setCreateDate( DateUtil.formatDDtoMD( rs.getString( "CREATE_DATE" )) );
      }
    }

    // ステートメントをクローズ。
    pstmt.close();

    return resultEntity;
  }

  /**
   * selectAll実行メソッド<br />
   * テーブルを  全件検索します。 <br />
   *
   * @return resultEntityList 検索結果のEntityList
   * @throws SQLException SQL実行例外
   */
  public List<WorkTimeEntity> selectAll() throws SQLException {

    // 結果格納用エンティティ
    WorkTimeEntity resultEntity = new WorkTimeEntity();
    // 結果格納用エンティティリスト
    List<WorkTimeEntity> resultEntityList = new ArrayList<WorkTimeEntity>();

    // プロパティからSQL文を生成する。
    String sqlStr = sqlLoader.getValue( "SELECT-ALL" );
    // コネクションからステートメントを生成。
    pstmt = conn.prepareStatement( sqlStr );

    // SQLを実行。
    ResultSet rs = pstmt.executeQuery();

    if ( rs != null ) {
      while ( rs.next() ) {

        // 念のため初期化
        resultEntity = new  WorkTimeEntity();
        // カラムの型に合わせてgetStringで値を取り出す。
        resultEntity.setStaffNo( rs.getString( "STAFF_NO" ) );
        resultEntity.setOfficeCd( rs.getString( "OFFICE_CD" ) );
        resultEntity.setStaffName( rs.getString( "STAFF_NAME" ) );
        resultEntity.setWorkTime( rs.getString( "WORK_TIME" ) );
        // 日付は"/"区切りに変換して格納。
        // 本来はUtilクラスの仕様変更に直接影響されないように、
        // entityへのセット処理も別クラス/別メソッドに切り分けた方が良い。
        resultEntity.setCreateDate( DateUtil.formatDDtoMD( rs.getString( "CREATE_DATE" )) );

        // Listにentityをセットする。
        resultEntityList.add( resultEntity );
      }
    }

    // ステートメントをクローズ。
    pstmt.close();

    return resultEntityList;
  }

  /**
   * delete実行メソッド<br />
   * エンティティの内容をテーブルから削除します。 <br />
   *
   * @param wtEntity エンティティ
   * @return rs 削除件数
   * @throws SQLException SQL実行例外
   */
  public int delete( WorkTimeEntity wtEntity ) throws SQLException {

    // プロパティからSQL文を生成する。
    String sqlStr = sqlLoader.getValue( "DELETE-SINGLE" );
    // コネクションからステートメントを生成。
    pstmt = conn.prepareStatement( sqlStr );

    // パラメータオブジェクトをセット。
    pstmt.setObject( 1, wtEntity.getStaffNo() );
    pstmt.setObject( 2, wtEntity.getOfficeCd() );
    pstmt.setObject( 3, wtEntity.getStaffName() );

    // SQLを実行。
    int rs = pstmt.executeUpdate();

    // 登録完了をコンソールに出力
    System.out.println( "DB削除が完了しました。" );

    // コミットを実行(autocommit=true時はエラー)
    //conn.commit();

    // ステートメントをクローズ。
    pstmt.close();

    return rs;
  }

}
