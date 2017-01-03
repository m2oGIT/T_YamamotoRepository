/**
 * Copyright 2016 EIS Co., Ltd. All rights reserved.
 */
package entity;

/**
 * エンティティのサンプル <br />
 * Servletプログラミング.pdf　基本的なエンティティクラス <br />
 * 更新履歴 2016/01/01 山本 高志：新規作成 <br />
 */
public class EmployeeEntity {

  private String no;
  private String name;
  private String age;

  /**
   * コンストラクタ <br />
   * デフォルトコンストラクタ <br />
   */
  public EmployeeEntity() {
    // 行うべき処理なし
    super();

  }

  /**
   * @return no
   */
  public String getNo() {
    return no;
  }

  /**
   * @param no セットする no
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
   * @param name セットする name
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
   * @param age セットする age
   */
  public void setAge( String age ) {
    this.age = age;
  }

}
