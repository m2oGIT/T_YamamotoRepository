/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL005;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import MySQL004.PropertyLoader;

/**
 * DB���K���iMySQL005�j <br />
 * �����œn���ꂽSQL�����s����v���O�����̎�����iPreparedStatement�ƃv���p�e�B�̎����j <br />
 * �X�V���� 2015/10/28 �R�{ ���u�F�V�K�쐬 <br />
 */
public class ExecuteArgsSQLbyPreparedProperty {

  /** SELECT����Prefix */
  private static final String SELECT = "SELECT";

  /** INSERT����Prefix */
  private static final String INSERT = "INSERT";

  /** UPDATE����Prefix */
  private static final String UPDATE = "UPDATE";

  /** DELETE����Prefix */
  private static final String DELETE = "DELETE";

  /** ���[�_�[�̃C���X�^���X */
  private static PropertyLoader loader = null;

  /** DB�ڑ��v���p�e�B�̃p�X */
  private static final String DB_CONNECT_PROP = "run/properties/dbConnect.properties";

  /** SQL�v���p�e�B�̃p�X */
  private static final String SQL_PROP = "run/properties/sql.properties";

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public ExecuteArgsSQLbyPreparedProperty() {
    // �s���ׂ������Ȃ��B
  }

  /**
   * ���C�����\�b�h<br />
   * �������N�����܂��B <br />
   *
   * @param args ���s������
   */
  public static void main( String[] args ) {

    // DB�����p�C���X�^���X�𐶐��B
    PreparedStatement pstmt = null;
    Connection conn = null;

    // �N���������`�F�b�N�B
    if ( args == null || args.length == 0 ) {
      System.out.println( "�N����������͂��ĉ������B" );
      return;
    }

    // �N������������SQL���𐶐�����B
    String sqlStr = createQuery( args );

    try {
      // �R�l�N�V�����𐶐��B
      conn = createConnection( conn );
      // �R�l�N�V��������X�e�[�g�����g�𐶐��B
      pstmt = conn.prepareStatement( sqlStr );
      // �p�����[�^�I�u�W�F�N�g���Z�b�g�B
      setParamObject( pstmt, args );
      // SQL�����s�B
      execStatement( pstmt, sqlStr );
      // �X�e�[�g�����g���N���[�Y�B
      pstmt.close();

      // �G���[�������̓G���[�̓��e��\���B
    } catch ( ClassNotFoundException e ) {
      System.out.println( "ClassNotFoundException:" + e.getMessage() );
    } catch ( SQLException e ) {
      System.out.println( "SQLException:" + e.getMessage() );
    } catch ( Exception e ) {
      System.out.println( "Exception:" + e.getMessage() );

    } finally {
      try {
        // �R�l�N�V�������N���[�Y�B
        if ( conn != null ) {
          conn.close();
          System.out.println( "�R�l�N�V������������܂����B" );
        }
      } catch ( SQLException e ) {
        System.out.println( "SQLException:" + e.getMessage() );
      }
    }
  }


  /**
   * DB�R�l�N�V�����������\�b�h<br />
   * �w�肳�ꂽ�ڑ�����DB�ɐڑ����܂��B <br />
   *
   * @param conn �ڑ��O�R�l�N�V����
   * @throws ClassNotFoundException �h���C�o���[�h��O
   * @throws IllegalAccessException �z��O�A�N�Z�X��O
   * @throws InstantiationException �h���C�o�C���X�^���X������O
   * @throws SQLException SQL��O
   * @return Connection �ڑ��ς݃R�l�N�V����
   */
  private static Connection createConnection( Connection conn )
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

    try {
      // �v���p�e�B�t�@�C����ǂݍ��ށB
      loader = new PropertyLoader( DB_CONNECT_PROP );
    } catch ( IOException e ) {
      e.printStackTrace();
    }

    // �ڑ���DB�̎w�蕶����
    String url = loader.getValue( "url-info" );

    // �h���C�o�����[�h�B
    Class.forName( loader.getValue( "driver-name" ) ).newInstance();

    // DB�ڑ������Z�b�g�B
    Properties props = new Properties();
    props.put( "user", loader.getValue( "user-id" ) );
    props.put( "password", loader.getValue( "pass-word" ) );
    props.put( "useUnicode", loader.getValue( "use-unicode" ) );
    props.put( "characterEncoding", loader.getValue( "char-code" ) );

    // �ڑ��������ɁA�R�l�N�V�����𐶐��B
    conn = DriverManager.getConnection( url, props );
    System.out.println( "�R�l�N�V�����𐶐����܂����B" );

    // �R�l�N�V������ԋp�B
    return conn;
  }

  /**
   * SQL�������\�b�h<br />
   * ����������SQL�𐶐����܂��B <br />
   *
   * @param args �N��������
   * @return createdQuery �쐬�ς݂�SQL��
   */
  private static String createQuery( String[] args ) {

    try {
      // �v���p�e�B�t�@�C����ǂݍ��ށB
      loader = new PropertyLoader( SQL_PROP );
    } catch ( IOException e ) {
      e.printStackTrace();
    }

    // SQL�i�[�p�̕ϐ�
    String createdQuery = "";

    // ��P�����̒l�ŕ���
    switch ( args[0] ) {
      case SELECT:
        createdQuery = loader.getValue( "SELECT-ALL" );
        break;
      case INSERT:
        createdQuery = loader.getValue( "INSERT-SINGLE" );
        break;
      case UPDATE:
        createdQuery = loader.getValue( "UPDATE-SINGLE" );
        break;
      case DELETE:
        createdQuery = loader.getValue( "DELETE-SINGLE" );
        break;
      default:
        createdQuery = "arags was unexpected";
    }
    // ��������SQL��ԋp����
    return createdQuery;
  }

  /**
   * �p�����[�^�[�o�C���h���\�b�h<br />
   * ���������Ƀp�����[�^�[�o�C���h���s���܂��B <br />
   *
   * @param pstmt �v���y�A�[�h�X�e�[�g�����g
   * @param args �N��������
   * @throws SQLException SQL��O
   */
  private static void setParamObject( PreparedStatement pstmt, String[] args ) throws SQLException {

    // INSERT���̏ꍇ
    if ( args[0].toUpperCase().startsWith( INSERT ) ) { // �������L�q���l�����A�啶���ɕϊ�
      pstmt.setObject( 1, args[1] );
      pstmt.setObject( 2, args[2] );
      pstmt.setObject( 3, args[3] );
      pstmt.setObject( 4, args[4] );
      pstmt.setObject( 5, args[5] );
      System.out.println( "�p�����[�^���o�C���h���܂����B" );

      // UPDATE���̏ꍇ
    } else if ( args[0].toUpperCase().startsWith( UPDATE ) ) {
      pstmt.setObject( 1, args[4] );
      pstmt.setObject( 2, args[1] );
      pstmt.setObject( 3, args[2] );
      pstmt.setObject( 4, args[3] );
      System.out.println( "�p�����[�^���o�C���h���܂����B" );

      // DELETE���̏ꍇ
    } else if ( args[0].toUpperCase().startsWith( DELETE ) ) {
      pstmt.setObject( 1, args[1] );
      pstmt.setObject( 2, args[2] );
      pstmt.setObject( 3, args[3] );
      System.out.println( "�p�����[�^���o�C���h���܂����B" );

      // ��L�ȊO�̓o�C���h���Ȃ�
    } else {
      System.out.println( "�o�C���h�������X�L�b�v���܂����B" );
    }
  }

  /**
   * SQL���s���\�b�h<br />
   * �w�肳�ꂽSQL�����s���܂��B <br />
   *
   * @param pstmt �쐬�ς݂̃X�e�[�g�����g
   * @param sqlStr �N���������琶�����ꂽSQL��
   * @throws SQLException SQL��O
   */
  private static void execStatement( PreparedStatement pstmt, String sqlStr ) throws SQLException {

    // ���ʊi�[�p�̕ϐ�
    ResultSet rs = null;
    int count = 0;

    // �Q�ƌn�̏ꍇ
    if ( sqlStr.toUpperCase().startsWith( SELECT ) ) { // �啶���Œ肾���A�O�̂��ߕϊ�

      // SQL�����s
      rs = pstmt.executeQuery();
      System.out.println( "���sSQL : " + sqlStr + ";" );
      // ���ʂ�\��
      dispResult( rs );

      // �X�V�n�̏ꍇ
    } else if ( sqlStr.toUpperCase().startsWith( INSERT )
        || sqlStr.toUpperCase().startsWith( UPDATE )
        || sqlStr.toUpperCase().startsWith( DELETE ) ) {

      // SQL�����s
      count = pstmt.executeUpdate();
      System.out.println( "���sSQL : " + sqlStr + ";" );
      // ���ʂ�\��
      System.out.println( "�X�V���������܂����B" + count + " �����X�V���܂����B" );

      // ��L�ȊO��SQL�����s���Ȃ�
    } else {
      System.out.println( "SQL�� SELECT or INSERT or UPDATE or DELETE ���w�肵�Ă��������B" );
    }

    // �������ʂ̃N���[�Y�B
    if ( rs != null ) {
      rs.close();
    }
  }

  /**
   * �������ʕ\�����\�b�h<br />
   * ResultSet��W�J���ăR���\�[���ɕ\�����܂��B <br />
   *
   * @param rs ��������
   * @throws SQLException SQL��O
   */
  private static void dispResult( ResultSet rs ) throws SQLException {

    // �J�E���g�p�ϐ�
    int i = 1;
    // �������ʂ𖖔��܂ŕ\������B
    if ( rs != null ) {
      System.out.println( "SQL���s����" );
      while ( rs.next() ) {
        System.out.println( "[" + i + "����]" );
        // �J�������Ƃɒl�����o���B
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
