/**
 * Copyright 2016 EIS Co., Ltd. All rights reserved.
 */

package entity;


/**
 * ���KWeb_001�̖�S�̉𓚗�G���e�B�e�B <br />
 * moeiwast_eiwatest_DB01.test01_yamamoto�ւ̓o�^�f�[�^���Ǘ� <br />
 * �X�V���� 2016/01/01 �R�{ ���u�F�V�K�쐬 <br />
 */
public class WorkTimeEntity {

  private String staffNo = "";
  private String officeCd = "";
  private String staffName = "";
  private String workTime = "";
  private String createDate = "";

  /**
   * �R���X�g���N�^ <br />
   * �f�t�H���g�R���X�g���N�^ <br />
   */
  public WorkTimeEntity() {
    // �s���ׂ������Ȃ�
    super();
  }

  /**
   * @return staffNo �Ј��ԍ�
   */
  public String getStaffNo() {
    return staffNo;
  }

  /**
   * @param staffNo �Z�b�g���� �Ј��ԍ�
   */
  public void setStaffNo( String staffNo ) {
    this.staffNo = staffNo;
  }

  /**
   * @return officeCd ���Ə��R�[�h
   */
  public String getOfficeCd() {
    return officeCd;
  }

  /**
   * @param officeCd �Z�b�g���� ���Ə��R�[�h
   */
  public void setOfficeCd( String officeCd ) {
    this.officeCd = officeCd;
  }

  /**
   * @return staffName �Ј���
   */
  public String getStaffName() {
    return staffName;
  }

  /**
   * @param staffName �Z�b�g���� �Ј���
   */
  public void setStaffName( String staffName ) {
    this.staffName = staffName;
  }

  /**
   * @return workTime �ғ�����
   */
  public String getWorkTime() {
    return workTime;
  }

  /**
   * @param workTime �Z�b�g���� �ғ�����
   */
  public void setWorkTime( String workTime ) {
    this.workTime = workTime;
  }

  /**
   * @return createDate �쐬��
   */
  public String getCreateDate() {
    return createDate;
  }

  /**
   * @param createDate �Z�b�g���� �쐬��
   */
  public void setCreateDate( String createDate ) {
    this.createDate = createDate;
  }

}
