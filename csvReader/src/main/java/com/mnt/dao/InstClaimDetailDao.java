package com.mnt.dao;

import com.mnt.domain.InstClaimDetail;

public interface InstClaimDetailDao extends BaseDao<InstClaimDetail> {
	public void deleteOldRecords(String year, String month, String provider);
}
