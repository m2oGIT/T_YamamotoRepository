/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �����񃆁[�e�B���e�B�N���X <br />
 * �����񑀍�Ɋւ��郆�[�e�B���e�B�N���X�ł��B <br />
 * �X�V���� 2015/10/28 �R�{ ���u�F�V�K�쐬 <br />
 */
public class StringUtil {

  /**
   * �K�{�`�F�b�N<br />
   * �󕶎��܂���Null�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isNecessary( String value ) {

    if ( value == null || "".equals( value ) || value.length() == 0 ) {
      return false;
    }
    return true;
  }

  /**
   * ���p���l�����`�F�b�N<br />
   * ���p���l�����ȊO�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isNumChar( char value ) {

    // �S�p�����e�������ꍇ�́A�S�p�����͈̔̓`�F�b�N��or�����Œǉ�����B
    if ( value >= '0' && value <= '9' ) {
      return true;
    }
    return false;
  }

  /**
   * ���l������`�F�b�N�i�����R�[�h�l�ł̎����j<br />
   * ���l������ȊO�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isNumString( String value ) {

    for ( int i = 0; i < value.length() - 1; i++ ) {
      // �擪����ꕶ�������lchar�����`�F�b�N����B
      if ( !isNumChar( value.substring( i, i + 1 ).charAt( 0 ) ) ) {
        // substring()�͖����Ă��ǂ��B
        // if ( !isNumChar( value.charAt( i ) ) ) {
        return false;
      }
    }
    return true;
  }

  /**
   * ���l������`�F�b�N�i���K�\���ł̎����j<br />
   * ���l������ȊO�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isNumber( String value ) {

    // ���K�\����Java�ň����ꍇ��Pattern/Matcher�N���X��import����B
    String regex = "\\A[-]?[0-9]+\\z";
    Pattern p = Pattern.compile( regex );
    Matcher m1 = p.matcher( value );
    return m1.find();
  }

}
