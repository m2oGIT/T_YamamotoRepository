/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package Utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日付ユーティリティクラス <br />
 * 日付操作に関するユーティリティクラスです。 <br />
 * 更新履歴 2015/10/28 山本 高志：新規作成 <br />
 */
public class DateUtil {

  /** ロケールを指定する。 */
  private static Locale locale = new Locale( "ja", "JP", "JP" );

  /** 許容する和暦日付のフォーマットを指定する。 */
  private static DateFormat japaseseFormat = new SimpleDateFormat( "GGGGy年MM月dd日", locale );

  /** 許容する西暦日付のフォーマットを指定する。 */
  private static DateFormat defaultFormat = new SimpleDateFormat( "yyyyMMdd" );

  /** 許容する西暦日付のフォーマットを指定する。 */
  private static DateFormat annoDominiformat = new SimpleDateFormat( "yyyy/MM/dd" );

  /** 許容する西暦日付のフォーマットを指定する。 */
  private static DateFormat mysqlDominiformat = new SimpleDateFormat( "yyyy-MM-dd" );

  /** TimeStamp変換元のフォーマットを指定する。 */
  private static DateFormat annoDominiformatLD = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );


  /**
   * 日付チェック("yyyy/MM/dd")<br />
   * 不正日付の場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isDate( String value ) {

    try {
      // 日付の妥当性チェックの精度は"厳密"を指定。
      annoDominiformat.setLenient( false );
      annoDominiformat.parse( value );
    } catch ( ParseException e ) {
      // 変換失敗時は不正日付。
      return false;
    }
    // 変換に成功すれば正しい日付とみなす。
    return true;
  }

  /**
   * 日付チェック("yyyyMMdd")<br />
   * 不正日付の場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isDefaultDate( String value ) {

    try {
      // 日付の妥当性チェックの精度は"厳密"を指定。
      defaultFormat.setLenient( false );
      defaultFormat.parse( value );
    } catch ( ParseException e ) {
      // 変換失敗時は不正日付。
      return false;
    }
    // 変換に成功すれば正しい日付とみなす。
    return true;
  }

  /**
   * 日付チェック("yyyy-MM-dd")<br />
   * 不正日付の場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isMysqlDate( String value ) {

    try {
      // 日付の妥当性チェックの精度は"厳密"を指定。
      mysqlDominiformat.setLenient( false );
      mysqlDominiformat.parse( value );
    } catch ( ParseException e ) {
      // 変換失敗時は不正日付。
      return false;
    }
    // 変換に成功すれば正しい日付とみなす。
    return true;
  }

  /**
   * 和暦変換<br />
   * 西暦表記("yyyy/MM/dd")を和暦表記に変換します。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static String formatJP( String value ) {

    // 日付の妥当性チェックの精度は"厳密"を指定。
    annoDominiformat.setLenient( false );
    Date date = null;

    try {
      // 文字列から日付型へ変換。
      date = annoDominiformat.parse( value );
    } catch ( ParseException e ) {
      // 不正日付
      return null;
    }

    // フォーマットを適用し文字列として返却。
    return japaseseFormat.format( date.getTime() );

  }

  /**
   * 和暦変換<br />
   * 西暦表記("yyyyMMdd")を和暦表記に変換します。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static String formatDefaultToJP( String value ) {

    // 日付の妥当性チェックの精度は"厳密"を指定。
    defaultFormat.setLenient( false );
    Date date = null;

    try {
      // 文字列から日付型へ変換。
      date = defaultFormat.parse( value );
    } catch ( ParseException e ) {
      // 不正日付
      return null;
    }

    // フォーマットを適用し文字列として返却。
    return japaseseFormat.format( date.getTime() );

  }

  /**
   * 西暦変換<br />
   * 和暦表記を西暦表記("yyyy/MM/dd")に変換します。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static String formatAD( String value ) {

    // 日付の妥当性チェックの精度は"厳密"を指定。
    japaseseFormat.setLenient( false );
    Date date = null;

    try {
      // 文字列から日付型へ変換。
      date = japaseseFormat.parse( value );
    } catch ( ParseException e ) {
      // 不正日付
      return null;
    }

    // フォーマットを適用し文字列として返却。
    return annoDominiformat.format( date );

  }

  /**
   * 西暦変換<br />
   * 和暦表記を西暦表記("yyyyMMdd")に変換します。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static String formatDD( String value ) {

    // 日付の妥当性チェックの精度は"厳密"を指定。
    japaseseFormat.setLenient( false );
    Date date = null;

    try {
      // 文字列から日付型へ変換。
      date = japaseseFormat.parse( value );
    } catch ( ParseException e ) {
      // 不正日付
      return null;
    }

    // フォーマットを適用し文字列として返却。
    return defaultFormat.format( date );

  }

  /**
   * 西暦変換<br />
   * 西暦表記("yyyy/MM/dd")を西暦表記("yyyyMMdd")の相互変換を行います。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static String formatDDtoAD( String value ) {

    // 変換用の日付型変数とチェックの精度を指定
    Date date = null;
    defaultFormat.setLenient( false );
    annoDominiformat.setLenient( false );

    // この例ではparseで一度date型の秒単位に変換し、formatで再変換を掛けている。
    // 単純に文字列操作で"/"を付けはずししても良いが、その場合は日付形式のチェックの実装が必要。
    try {
      // 西暦表記("yyyyMMdd")の場合
      if ( isDefaultDate( value ) ) {
        return annoDominiformat.format( defaultFormat.parse( value ).getTime() );
        // 西暦表記("yyyy/MM/dd")の場合
      } else if ( isDate( value ) ) {
        return defaultFormat.format( annoDominiformat.parse( value ).getTime() );
        // 上記以外はnullを返却
      } else {
        return null;
      }
    // 変換できなかった場合は、nullを返却
    } catch ( ParseException e ) {
      return null;
    }
  }

  /**
   * 西暦変換<br />
   * 西暦表記("yyyy/MM/dd")を西暦表記("yyyy-MM-dd")の相互変換を行います。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static String formatDDtoMD( String value ) {

    // 変換用の日付型変数とチェックの精度を指定
    annoDominiformat.setLenient( false );
    mysqlDominiformat.setLenient( false );

    // この例ではparseで一度date型の秒単位に変換し、formatで再変換を掛けている。
    // 単純に文字列操作で"/"を付けはずししても良いが、その場合は日付形式のチェックの実装が必要。
    try {
      // 西暦表記("yyyyMMdd")の場合
      if ( isDefaultDate( value ) ) {
        return mysqlDominiformat.format( annoDominiformat.parse( value ).getTime() );
        // 西暦表記("yyyy-MM-dd")の場合
      } else if ( isMysqlDate( value ) ) {
        return annoDominiformat.format( mysqlDominiformat.parse( value ).getTime() );
        // 上記以外はnullを返却
      } else {
        return null;
      }
    // 変換できなかった場合は、nullを返却
    } catch ( ParseException e ) {
      return null;
    }
  }

  /**
   * TimeStamp型への変換<br />
   * 文字列型からTimeStamp型へ変換します。 <br />
   *
   * @param value 対象の文字列
   * @return 正しい日付：変換結果 不正日付：nullを返却
   */
  public static Timestamp formatTS( String value ) {

    // 日付の妥当性チェックの精度は"厳密"を指定。
    annoDominiformatLD.setLenient( false );
    Long dateTimeLong = null;

    try {
      // 文字列からTimeStamp型へ変換。
      dateTimeLong = annoDominiformatLD.parse( value ).getTime();
      return new Timestamp( dateTimeLong );
    } catch ( ParseException e ) {
      // 不正日付
      return null;
    }
  }

}
