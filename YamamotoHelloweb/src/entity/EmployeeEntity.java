/**
 * Copyright 2016 EIS Co., Ltd. All rights reserved.
 */
package entity;

/**
 * �G���e�B�e�B�̃T���v�� <br />
 * Servlet�v���O���~���O.pdf�@��{�I�ȃG���e�B�e�B�N���X <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
public class EmployeeEntity {

  private String no;
  private String name;
  private String age;

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public EmployeeEntity() {
    // �s���ׂ������Ȃ�
    super();

  }

  /**
   * @return no
   */
  public String getNo() {
    return no;
  }

  /**
   * @param no �Z�b�g���� no
   */
  public void setNo( String no ) {
    this.no = no;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name �Z�b�g���� name
   */
  public void setName( String name ) {
    this.name = name;
  }

  /**
   * @return age
   */
  public String getAge() {
    return age;
  }

  /**
   * @param age �Z�b�g���� age
   */
  public void setAge( String age ) {
    this.age = age;
  }

}
