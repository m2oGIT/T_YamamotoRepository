/**
 * Copyright 2016 EIS Co., Ltd. All rights reserved.
 */

package entity;


/**
 * 演習Web_001の問４の解答例エンティティ <br />
 * moeiwast_eiwatest_DB01.test01_yamamotoへの登録データを管理 <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
public class WorkTimeEntity {

  private String staffNo = "";
  private String officeCd = "";
  private String staffName = "";
  private String workTime = "";
  private String createDate = "";

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public WorkTimeEntity() {
    // 行うべき処理なし
    super();
  }

  /**
   * @return staffNo 社員番号
   */
  public String getStaffNo() {
    return staffNo;
  }

  /**
   * @param staffNo セットする 社員番号
   */
  public void setStaffNo( String staffNo ) {
    this.staffNo = staffNo;
  }

  /**
   * @return officeCd 事業所コード
   */
  public String getOfficeCd() {
    return officeCd;
  }

  /**
   * @param officeCd セットする 事業所コード
   */
  public void setOfficeCd( String officeCd ) {
    this.officeCd = officeCd;
  }

  /**
   * @return staffName 社員名
   */
  public String getStaffName() {
    return staffName;
  }

  /**
   * @param staffName セットする 社員名
   */
  public void setStaffName( String staffName ) {
    this.staffName = staffName;
  }

  /**
   * @return workTime 稼働時間
   */
  public String getWorkTime() {
    return workTime;
  }

  /**
   * @param workTime セットする 稼働時間
   */
  public void setWorkTime( String workTime ) {
    this.workTime = workTime;
  }

  /**
   * @return createDate 作成日
   */
  public String getCreateDate() {
    return createDate;
  }

  /**
   * @param createDate セットする 作成日
   */
  public void setCreateDate( String createDate ) {
    this.createDate = createDate;
  }

}
