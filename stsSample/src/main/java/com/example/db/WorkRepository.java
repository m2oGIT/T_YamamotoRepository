/**
 * Copyright 2016 EIS Co., Ltd. All rights reserved.
 */

package com.example.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yamamoto-t <br />
 *         サンプル用リポジトリ <br />
 *         DB接続検証用に作成 <br />
 *         更新履歴 2016/11/01 yamamoto-t：新規作成 <br />
 */
public interface WorkRepository extends JpaRepository<WorkEntity, String> {

  // JPAリポジトリにプリセットされている全件検索を呼び出し。
  /*
   * (非 Javadoc)
   * @see org.springframework.data.repository.
   * PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
   */
  Page<WorkEntity> findAll( Pageable pageable );

}
