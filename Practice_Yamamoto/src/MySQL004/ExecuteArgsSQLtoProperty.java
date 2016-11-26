/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL004;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB���K���iMySQL004�j <br />
 * �����œn���ꂽSQL�����s����v���O�����̎�����i�v���p�e�B���g�p�j <br />
 * �X�V���� 2015/10/28 �R�{ ���u�F�V�K�쐬 <br />
 */
public class ExecuteArgsSQLtoProperty {

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

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public ExecuteArgsSQLtoProperty() {
  }

  /**
   * ���C�����\�b�h<br />
   * �������N�����܂��B <br />
   *
   * @param args ���s������
   */
  public static void main( String[] args ) {

    // DB�����p�C���X�^���X�𐶐��B
    Statement stmt = null;
    Connection conn = null;

    // �N���������`�F�b�N�B
    if ( args == null || args.length == 0 ) {
      System.out.println( "�N����������͂��ĉ������B" );
      return;
    }
    // �N����������SQL���Ƃ��Ċi�[����B
    String sqlStr = args[0];

    try {
      // �R�l�N�V�����𐶐��B
      conn = createConnection( conn );
      // �R�l�N�V��������X�e�[�g�����g�𐶐��B
      stmt = conn.createStatement();
      // SQL�����s�B
      execStatement( stmt, sqlStr );
      // �X�e�[�g�����g���N���[�Y�B
      stmt.close();

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
   * SQL���s���\�b�h<br />
   * �w�肳�ꂽSQL�����s���܂��B <br />
   *
   * @param stmt �쐬�ς݂̃X�e�[�g�����g
   * @param sqlStr �N�������Ŏw�肳�ꂽSQL��
   * @throws SQLException SQL��O
   */
  private static void execStatement( Statement stmt, String sqlStr ) throws SQLException {

    // ���ʊi�[�p�̕ϐ�
    ResultSet rs = null;
    int count = 0;

    // �Q�ƌn�̏ꍇ
    if ( sqlStr.toUpperCase().startsWith( SELECT ) ) { // �������L�q���l�����A�啶���ɕϊ�

      // SQL�����s
      rs = stmt.executeQuery( sqlStr );
      System.out.println( "���sSQL : " + sqlStr + ";" );
      // ���ʂ�\��
      dispResult( rs );

      // �X�V�n�̏ꍇ
    } else if ( sqlStr.toUpperCase().startsWith( INSERT )
        || sqlStr.toUpperCase().startsWith( UPDATE )
        || sqlStr.toUpperCase().startsWith( DELETE ) ) {

      // SQL�����s
      count = stmt.executeUpdate( sqlStr );
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
