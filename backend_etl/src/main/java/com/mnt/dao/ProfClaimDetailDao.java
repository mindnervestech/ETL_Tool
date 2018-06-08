package com.mnt.dao;

import com.mnt.domain.ProfClaimDetail;

public interface ProfClaimDetailDao extends BaseDao<ProfClaimDetail> {
	public void deleteOldRecords(String year, String month, String provider);
}
