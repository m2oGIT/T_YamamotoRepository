/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL004;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * プロパティファイルローダー <br />
 * Propertiesクラスを使用しプロパティから値を取得します。 <br />
 * 更新履歴 2015/10/28 山本 高志：新規作成 <br />
 */
public class PropertyLoader {

  /** プロパティオブジェクト */
  private Properties conf = null;

  /**
   * コンストラクタ <br />
   * プロパティファイルを読み込みます。 <br />
   *
   * @param filePath ファイルパス
   * @throws IOException 入出力例外
   */
  public PropertyLoader( String filePath ) throws IOException {

    // インスタンスを生成。
    conf = new Properties();

    // プロパティファイルを読み込む。
    InputStream inputStream = new FileInputStream( filePath );
    conf.load( inputStream );
    inputStream.close();

  }

  /**
   * 格納値の取得（String型）<br />
   * 指定されたkeyに紐づく値を返却します。 <br />
   *
   * @param key キー値
   * @return 取得した値
   */
  public String getValue( String key ) {
    // keyに紐づく値を取得。
    return conf.getProperty( key );
  }

}
