package com.mnt.dao;

import com.mnt.domain.RxDetail;

public interface RxDetailDao extends BaseDao<RxDetail> {
	public void deleteOldRecords(String year, String month, String provider);
}
