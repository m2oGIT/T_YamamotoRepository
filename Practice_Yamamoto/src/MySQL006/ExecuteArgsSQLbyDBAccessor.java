/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL006;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DB���K���iMySQL006�j <br />
 * �����œn���ꂽSQL�����s����v���O�����̎�����iDB�A�N�Z�b�T�̎����j <br />
 * �X�V���� 2015/10/28 �R�{ ���u�F�V�K�쐬 <br />
 */
public class ExecuteArgsSQLbyDBAccessor {

  /** MySQL�̈�Ӑ���G���[�R�[�h */
  private static final String SQL_ERR_CODE = "1062";

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public ExecuteArgsSQLbyDBAccessor() {
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
    DBAccessor dba = new DBAccessor();

    // �N���������`�F�b�N�B
    if ( args == null || args.length == 0 ) {
      System.out.println( "�N����������͂��ĉ������B" );
      return;
    }

    // �N������������SQL���𐶐�����B
    String sqlStr = dba.createQuery( args );

    try {
      // �R�l�N�V�����𐶐��B
      conn = dba.createConnection( conn );
      // �R�l�N�V��������X�e�[�g�����g�𐶐��B
      pstmt = conn.prepareStatement( sqlStr );
      // �p�����[�^�I�u�W�F�N�g���Z�b�g�B
      dba.setParamObject( pstmt, args );
      // SQL�����s�B
      dba.execStatement( pstmt, sqlStr );
      // �X�e�[�g�����g���N���[�Y�B
      pstmt.close();

      // �G���[�������̓G���[�̓��e��\���B
    } catch ( ClassNotFoundException e ) {
      System.out.println( "ClassNotFoundException:" + e.getMessage() );

    } catch ( SQLException e ) {
      System.out.println( "SQLException:" + e.getMessage() );

      // ��Ӑ���G���[�̔���
      if ( SQL_ERR_CODE.equals( String.valueOf( e.getErrorCode() ) ) ) {
        System.out.println( "��Ӑ���G���[���������܂����B" );
        e.printStackTrace();
      }

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

}
