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
 * ���t���[�e�B���e�B�N���X <br />
 * ���t����Ɋւ��郆�[�e�B���e�B�N���X�ł��B <br />
 * �X�V���� 2015/10/28 �R�{ ���u�F�V�K�쐬 <br />
 */
public class DateUtil {

  /** ���P�[�����w�肷��B */
  private static Locale locale = new Locale( "ja", "JP", "JP" );

  /** ���e����a����t�̃t�H�[�}�b�g���w�肷��B */
  private static DateFormat japaseseFormat = new SimpleDateFormat( "GGGGy�NMM��dd��", locale );

  /** ���e���鐼����t�̃t�H�[�}�b�g���w�肷��B */
  private static DateFormat defaultFormat = new SimpleDateFormat( "yyyyMMdd" );

  /** ���e���鐼����t�̃t�H�[�}�b�g���w�肷��B */
  private static DateFormat annoDominiformat = new SimpleDateFormat( "yyyy/MM/dd" );

  /** ���e���鐼����t�̃t�H�[�}�b�g���w�肷��B */
  private static DateFormat mysqlDominiformat = new SimpleDateFormat( "yyyy-MM-dd" );

  /** TimeStamp�ϊ����̃t�H�[�}�b�g���w�肷��B */
  private static DateFormat annoDominiformatLD = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );


  /**
   * ���t�`�F�b�N("yyyy/MM/dd")<br />
   * �s�����t�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isDate( String value ) {

    try {
      // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
      annoDominiformat.setLenient( false );
      annoDominiformat.parse( value );
    } catch ( ParseException e ) {
      // �ϊ����s���͕s�����t�B
      return false;
    }
    // �ϊ��ɐ�������ΐ��������t�Ƃ݂Ȃ��B
    return true;
  }

  /**
   * ���t�`�F�b�N("yyyyMMdd")<br />
   * �s�����t�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isDefaultDate( String value ) {

    try {
      // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
      defaultFormat.setLenient( false );
      defaultFormat.parse( value );
    } catch ( ParseException e ) {
      // �ϊ����s���͕s�����t�B
      return false;
    }
    // �ϊ��ɐ�������ΐ��������t�Ƃ݂Ȃ��B
    return true;
  }

  /**
   * ���t�`�F�b�N("yyyy-MM-dd")<br />
   * �s�����t�̏ꍇ��false��Ԃ��܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return �`�F�b�N����
   */
  public static boolean isMysqlDate( String value ) {

    try {
      // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
      mysqlDominiformat.setLenient( false );
      mysqlDominiformat.parse( value );
    } catch ( ParseException e ) {
      // �ϊ����s���͕s�����t�B
      return false;
    }
    // �ϊ��ɐ�������ΐ��������t�Ƃ݂Ȃ��B
    return true;
  }

  /**
   * �a��ϊ�<br />
   * ����\�L("yyyy/MM/dd")��a��\�L�ɕϊ����܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static String formatJP( String value ) {

    // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
    annoDominiformat.setLenient( false );
    Date date = null;

    try {
      // �����񂩂���t�^�֕ϊ��B
      date = annoDominiformat.parse( value );
    } catch ( ParseException e ) {
      // �s�����t
      return null;
    }

    // �t�H�[�}�b�g��K�p��������Ƃ��ĕԋp�B
    return japaseseFormat.format( date.getTime() );

  }

  /**
   * �a��ϊ�<br />
   * ����\�L("yyyyMMdd")��a��\�L�ɕϊ����܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static String formatDefaultToJP( String value ) {

    // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
    defaultFormat.setLenient( false );
    Date date = null;

    try {
      // �����񂩂���t�^�֕ϊ��B
      date = defaultFormat.parse( value );
    } catch ( ParseException e ) {
      // �s�����t
      return null;
    }

    // �t�H�[�}�b�g��K�p��������Ƃ��ĕԋp�B
    return japaseseFormat.format( date.getTime() );

  }

  /**
   * ����ϊ�<br />
   * �a��\�L�𐼗�\�L("yyyy/MM/dd")�ɕϊ����܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static String formatAD( String value ) {

    // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
    japaseseFormat.setLenient( false );
    Date date = null;

    try {
      // �����񂩂���t�^�֕ϊ��B
      date = japaseseFormat.parse( value );
    } catch ( ParseException e ) {
      // �s�����t
      return null;
    }

    // �t�H�[�}�b�g��K�p��������Ƃ��ĕԋp�B
    return annoDominiformat.format( date );

  }

  /**
   * ����ϊ�<br />
   * �a��\�L�𐼗�\�L("yyyyMMdd")�ɕϊ����܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static String formatDD( String value ) {

    // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
    japaseseFormat.setLenient( false );
    Date date = null;

    try {
      // �����񂩂���t�^�֕ϊ��B
      date = japaseseFormat.parse( value );
    } catch ( ParseException e ) {
      // �s�����t
      return null;
    }

    // �t�H�[�}�b�g��K�p��������Ƃ��ĕԋp�B
    return defaultFormat.format( date );

  }

  /**
   * ����ϊ�<br />
   * ����\�L("yyyy/MM/dd")�𐼗�\�L("yyyyMMdd")�̑��ݕϊ����s���܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static String formatDDtoAD( String value ) {

    // �ϊ��p�̓��t�^�ϐ��ƃ`�F�b�N�̐��x���w��
    Date date = null;
    defaultFormat.setLenient( false );
    annoDominiformat.setLenient( false );

    // ���̗�ł�parse�ň�xdate�^�̕b�P�ʂɕϊ����Aformat�ōĕϊ����|���Ă���B
    // �P���ɕ����񑀍��"/"��t���͂������Ă��ǂ����A���̏ꍇ�͓��t�`���̃`�F�b�N�̎������K�v�B
    try {
      // ����\�L("yyyyMMdd")�̏ꍇ
      if ( isDefaultDate( value ) ) {
        return annoDominiformat.format( defaultFormat.parse( value ).getTime() );
        // ����\�L("yyyy/MM/dd")�̏ꍇ
      } else if ( isDate( value ) ) {
        return defaultFormat.format( annoDominiformat.parse( value ).getTime() );
        // ��L�ȊO��null��ԋp
      } else {
        return null;
      }
    // �ϊ��ł��Ȃ������ꍇ�́Anull��ԋp
    } catch ( ParseException e ) {
      return null;
    }
  }

  /**
   * ����ϊ�<br />
   * ����\�L("yyyy/MM/dd")�𐼗�\�L("yyyy-MM-dd")�̑��ݕϊ����s���܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static String formatDDtoMD( String value ) {

    // �ϊ��p�̓��t�^�ϐ��ƃ`�F�b�N�̐��x���w��
    annoDominiformat.setLenient( false );
    mysqlDominiformat.setLenient( false );

    // ���̗�ł�parse�ň�xdate�^�̕b�P�ʂɕϊ����Aformat�ōĕϊ����|���Ă���B
    // �P���ɕ����񑀍��"/"��t���͂������Ă��ǂ����A���̏ꍇ�͓��t�`���̃`�F�b�N�̎������K�v�B
    try {
      // ����\�L("yyyyMMdd")�̏ꍇ
      if ( isDefaultDate( value ) ) {
        return mysqlDominiformat.format( annoDominiformat.parse( value ).getTime() );
        // ����\�L("yyyy-MM-dd")�̏ꍇ
      } else if ( isMysqlDate( value ) ) {
        return annoDominiformat.format( mysqlDominiformat.parse( value ).getTime() );
        // ��L�ȊO��null��ԋp
      } else {
        return null;
      }
    // �ϊ��ł��Ȃ������ꍇ�́Anull��ԋp
    } catch ( ParseException e ) {
      return null;
    }
  }

  /**
   * TimeStamp�^�ւ̕ϊ�<br />
   * ������^����TimeStamp�^�֕ϊ����܂��B <br />
   *
   * @param value �Ώۂ̕�����
   * @return ���������t�F�ϊ����� �s�����t�Fnull��ԋp
   */
  public static Timestamp formatTS( String value ) {

    // ���t�̑Ó����`�F�b�N�̐��x��"����"���w��B
    annoDominiformatLD.setLenient( false );
    Long dateTimeLong = null;

    try {
      // �����񂩂�TimeStamp�^�֕ϊ��B
      dateTimeLong = annoDominiformatLD.parse( value ).getTime();
      return new Timestamp( dateTimeLong );
    } catch ( ParseException e ) {
      // �s�����t
      return null;
    }
  }

}
