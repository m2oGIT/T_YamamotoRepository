/**
 * Copyright 2015 EIS Co., Ltd. All rights reserved.
 */

package MySQL004;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * �v���p�e�B�t�@�C�����[�_�[ <br />
 * Properties�N���X���g�p���v���p�e�B����l���擾���܂��B <br />
 * �X�V���� 2015/10/28 �R�{ ���u�F�V�K�쐬 <br />
 */
public class PropertyLoader {

  /** �v���p�e�B�I�u�W�F�N�g */
  private Properties conf = null;

  /**
   * �R���X�g���N�^ <br />
   * �v���p�e�B�t�@�C����ǂݍ��݂܂��B <br />
   *
   * @param filePath �t�@�C���p�X
   * @throws IOException ���o�͗�O
   */
  public PropertyLoader( String filePath ) throws IOException {

    // �C���X�^���X�𐶐��B
    conf = new Properties();

    // �v���p�e�B�t�@�C����ǂݍ��ށB
    InputStream inputStream = new FileInputStream( filePath );
    conf.load( inputStream );
    inputStream.close();

  }

  /**
   * �i�[�l�̎擾�iString�^�j<br />
   * �w�肳�ꂽkey�ɕR�Â��l��ԋp���܂��B <br />
   *
   * @param key �L�[�l
   * @return �擾�����l
   */
  public String getValue( String key ) {
    // key�ɕR�Â��l���擾�B
    return conf.getProperty( key );
  }

}
