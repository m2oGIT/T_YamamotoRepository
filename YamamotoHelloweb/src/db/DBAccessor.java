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
 * ���KWeb_001�̖�S�̉𓚗� <br />
 * �����œn���ꂽSQL�����s����v���O�����̎�����iDB�A�N�Z�b�T�̎����j <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
public class DBAccessor {

  /** DB�����p�̃C���X�^���X */
  private PreparedStatement pstmt = null;
  private Connection conn = null;

  /** DB���[�_�[�̃C���X�^���X */
  private static PropertyLoader dbLoader = null;

  /** SQL���[�_�[�̃C���X�^���X */
  private static PropertyLoader sqlLoader = null;

  /** DB�ڑ��v���p�e�B�̃p�X */
  private static final String DB_CONNECT_PROP = "/run/properties/dbConnect.properties";

  /** SQL�v���p�e�B�̃p�X */
  private static final String SQL_PROP = "/run/properties/sql.properties";

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   *
   * @param context http�R���e�L�X�g
   * @throws SQLException SQL���s����O
   * @throws InstantiationException �C���X�^���X������O
   * @throws IllegalAccessException �N���X�A�N�Z�X��O
   * @throws ClassNotFoundException �N���X�p�X��O
   */
  public DBAccessor( ServletContext context ) throws ClassNotFoundException,
      IllegalAccessException, InstantiationException, SQLException {
    try {

      // �p�X���΃p�X�ŋL�q���Ă���ꍇ�́Acontext.getRealPath�͕s�v�B

      // DB���[�_�[�̐����B
      dbLoader = new PropertyLoader( context.getRealPath( DB_CONNECT_PROP ) );
      // SQL���[�_�[�̐����B
      sqlLoader = new PropertyLoader( context.getRealPath( SQL_PROP ) );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    // �R�l�N�V��������
    createConnection();
  }

  /**
   * DB�R�l�N�V�����������\�b�h<br />
   * �w�肳�ꂽ�ڑ�����DB�ɐڑ����܂��B <br />
   *
   * @throws ClassNotFoundException �h���C�o���[�h��O
   * @throws IllegalAccessException �z��O�A�N�Z�X��O
   * @throws InstantiationException �h���C�o�C���X�^���X������O
   * @throws SQLException SQL��O
   * @return Connection �ڑ��ς݃R�l�N�V����
   */
  private Connection createConnection()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

    // �ڑ���DB�̎w�蕶����
    String url = dbLoader.getValue( "url-info" );
    // �h���C�o�����[�h�B
    Class.forName( dbLoader.getValue( "driver-name" ) ).newInstance();

    // DB�ڑ������Z�b�g�B
    Properties props = new Properties();
    props.put( "user", dbLoader.getValue( "user-id" ) );
    props.put( "password", dbLoader.getValue( "pass-word" ) );
    props.put( "useUnicode", dbLoader.getValue( "use-unicode" ) );
    props.put( "characterEncoding", dbLoader.getValue( "char-code" ) );

    // �ڑ��������ɁA�R�l�N�V�����𐶐��B
    conn = DriverManager.getConnection( url, props );
    System.out.println( "�R�l�N�V�����𐶐����܂����B" );

    // �R�l�N�V������ԋp�B
    return conn;
  }

  /**
   * insert���s���\�b�h<br />
   * �G���e�B�e�B�̓��e���e�[�u���ɓo�^���܂��B <br />
   *
   * @param wtEntity �G���e�B�e�B
   * @return rs �o�^����
   * @throws SQLException SQL���s��O
   */
  public int insert( WorkTimeEntity wtEntity ) throws SQLException {

    // �v���p�e�B����SQL���𐶐�����B
    String sqlStr = sqlLoader.getValue( "INSERT-SINGLE" );
    // �R�l�N�V��������X�e�[�g�����g�𐶐��B
    pstmt = conn.prepareStatement( sqlStr );

    // �p�����[�^�I�u�W�F�N�g���Z�b�g�B
    pstmt.setObject( 1, wtEntity.getStaffNo() );
    pstmt.setObject( 2, wtEntity.getOfficeCd() );
    pstmt.setObject( 3, wtEntity.getStaffName() );
    pstmt.setObject( 4, wtEntity.getWorkTime() );
    pstmt.setObject( 5, wtEntity.getCreateDate() );

    // SQL�����s�B
    int rs = pstmt.executeUpdate();

    // �o�^�������R���\�[���ɏo��
    System.out.println( "DB�o�^���������܂����B" );

    // �R�~�b�g�����s(autocommit=true���̓G���[)
    // conn.commit();
    // �X�e�[�g�����g���N���[�Y�B
    pstmt.close();

    return rs;
  }

  /**
   * select���s���\�b�h<br />
   * �G���e�B�e�B�̓��e�Ńe�[�u�����������܂��B <br />
   *
   * @param wtEntity �G���e�B�e�B
   * @return resultEntity �������ʃG���e�B�e�B
   * @throws SQLException SQL���s��O
   */
  public WorkTimeEntity select( WorkTimeEntity wtEntity ) throws SQLException {

    // ���ʊi�[�p�G���e�B�e�B
    WorkTimeEntity resultEntity = new WorkTimeEntity();

    // �v���p�e�B����SQL���𐶐�����B
    String sqlStr = sqlLoader.getValue( "SELECT-SINGLE" );
    // �R�l�N�V��������X�e�[�g�����g�𐶐��B
    pstmt = conn.prepareStatement( sqlStr );

    // �p�����[�^�I�u�W�F�N�g���Z�b�g�B
    pstmt.setObject( 1, wtEntity.getStaffNo() );
    pstmt.setObject( 2, wtEntity.getOfficeCd() );
    pstmt.setObject( 3, wtEntity.getStaffName() );

    // SQL�����s�B
    ResultSet rs = pstmt.executeQuery();

    if ( rs != null ) {
      while ( rs.next() ) {

        // �J�����̌^�ɍ��킹��getString�Œl�����o���B
        resultEntity.setStaffNo( rs.getString( "STAFF_NO" ) );
        resultEntity.setOfficeCd( rs.getString( "OFFICE_CD" ) );
        resultEntity.setStaffName( rs.getString( "STAFF_NAME" ) );
        resultEntity.setWorkTime( rs.getString( "WORK_TIME" ) );
        // ���t��"/"��؂�ɕϊ����Ċi�[�B
        // �{����Util�N���X�̎d�l�ύX�ɒ��ډe������Ȃ��悤�ɁA
        // entity�ւ̃Z�b�g�������ʃN���X/�ʃ��\�b�h�ɐ؂蕪���������ǂ��B
        resultEntity.setCreateDate( DateUtil.formatDDtoMD( rs.getString( "CREATE_DATE" )) );
      }
    }

    // �X�e�[�g�����g���N���[�Y�B
    pstmt.close();

    return resultEntity;
  }

  /**
   * selectAll���s���\�b�h<br />
   * �e�[�u����  �S���������܂��B <br />
   *
   * @return resultEntityList �������ʂ�EntityList
   * @throws SQLException SQL���s��O
   */
  public List<WorkTimeEntity> selectAll() throws SQLException {

    // ���ʊi�[�p�G���e�B�e�B
    WorkTimeEntity resultEntity = new WorkTimeEntity();
    // ���ʊi�[�p�G���e�B�e�B���X�g
    List<WorkTimeEntity> resultEntityList = new ArrayList<WorkTimeEntity>();

    // �v���p�e�B����SQL���𐶐�����B
    String sqlStr = sqlLoader.getValue( "SELECT-ALL" );
    // �R�l�N�V��������X�e�[�g�����g�𐶐��B
    pstmt = conn.prepareStatement( sqlStr );

    // SQL�����s�B
    ResultSet rs = pstmt.executeQuery();

    if ( rs != null ) {
      while ( rs.next() ) {

        // �O�̂��ߏ�����
        resultEntity = new  WorkTimeEntity();
        // �J�����̌^�ɍ��킹��getString�Œl�����o���B
        resultEntity.setStaffNo( rs.getString( "STAFF_NO" ) );
        resultEntity.setOfficeCd( rs.getString( "OFFICE_CD" ) );
        resultEntity.setStaffName( rs.getString( "STAFF_NAME" ) );
        resultEntity.setWorkTime( rs.getString( "WORK_TIME" ) );
        // ���t��"/"��؂�ɕϊ����Ċi�[�B
        // �{����Util�N���X�̎d�l�ύX�ɒ��ډe������Ȃ��悤�ɁA
        // entity�ւ̃Z�b�g�������ʃN���X/�ʃ��\�b�h�ɐ؂蕪���������ǂ��B
        resultEntity.setCreateDate( DateUtil.formatDDtoMD( rs.getString( "CREATE_DATE" )) );

        // List��entity���Z�b�g����B
        resultEntityList.add( resultEntity );
      }
    }

    // �X�e�[�g�����g���N���[�Y�B
    pstmt.close();

    return resultEntityList;
  }

  /**
   * delete���s���\�b�h<br />
   * �G���e�B�e�B�̓��e���e�[�u������폜���܂��B <br />
   *
   * @param wtEntity �G���e�B�e�B
   * @return rs �폜����
   * @throws SQLException SQL���s��O
   */
  public int delete( WorkTimeEntity wtEntity ) throws SQLException {

    // �v���p�e�B����SQL���𐶐�����B
    String sqlStr = sqlLoader.getValue( "DELETE-SINGLE" );
    // �R�l�N�V��������X�e�[�g�����g�𐶐��B
    pstmt = conn.prepareStatement( sqlStr );

    // �p�����[�^�I�u�W�F�N�g���Z�b�g�B
    pstmt.setObject( 1, wtEntity.getStaffNo() );
    pstmt.setObject( 2, wtEntity.getOfficeCd() );
    pstmt.setObject( 3, wtEntity.getStaffName() );

    // SQL�����s�B
    int rs = pstmt.executeUpdate();

    // �o�^�������R���\�[���ɏo��
    System.out.println( "DB�폜���������܂����B" );

    // �R�~�b�g�����s(autocommit=true���̓G���[)
    //conn.commit();

    // �X�e�[�g�����g���N���[�Y�B
    pstmt.close();

    return rs;
  }

}
