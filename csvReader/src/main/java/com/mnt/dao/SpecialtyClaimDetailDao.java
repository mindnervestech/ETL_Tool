package com.mnt.dao;

import com.mnt.domain.SpecialtyClaimDetail;

public interface SpecialtyClaimDetailDao extends BaseDao<SpecialtyClaimDetail> {
	public void deleteOldRecords(String year, String month, String provider);
}
