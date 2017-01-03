/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字列ユーティリティクラス <br />
 * 文字列操作に関するユーティリティクラスです。 <br />
 * 更新履歴 2015/10/28 山本 高志：新規作成 <br />
 */
public class StringUtil {

  /**
   * 必須チェック<br />
   * 空文字またはNullの場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isNecessary( String value ) {

    if ( value == null || "".equals( value ) || value.length() == 0 ) {
      return false;
    }
    return true;
  }

  /**
   * 半角数値文字チェック<br />
   * 半角数値文字以外の場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isNumChar( char value ) {

    // 全角も許容したい場合は、全角数字の範囲チェックをor条件で追加する。
    if ( value >= '0' && value <= '9' ) {
      return true;
    }
    return false;
  }

  /**
   * 数値文字列チェック（文字コード値での実装）<br />
   * 数値文字列以外の場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isNumString( String value ) {

    for ( int i = 0; i < value.length() - 1; i++ ) {
      // 先頭から一文字ずつ数値charかをチェックする。
      if ( !isNumChar( value.substring( i, i + 1 ).charAt( 0 ) ) ) {
        // substring()は無くても良い。
        // if ( !isNumChar( value.charAt( i ) ) ) {
        return false;
      }
    }
    return true;
  }

  /**
   * 数値文字列チェック（正規表現での実装）<br />
   * 数値文字列以外の場合はfalseを返します。 <br />
   *
   * @param value 対象の文字列
   * @return チェック結果
   */
  public static boolean isNumber( String value ) {

    // 正規表現をJavaで扱う場合はPattern/Matcherクラスをimportする。
    String regex = "\\A[-]?[0-9]+\\z";
    Pattern p = Pattern.compile( regex );
    Matcher m1 = p.matcher( value );
    return m1.find();
  }

}
