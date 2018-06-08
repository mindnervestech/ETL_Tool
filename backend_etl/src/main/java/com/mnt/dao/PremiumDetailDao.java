package com.mnt.dao;

import com.mnt.domain.PremiumDetail;

public interface PremiumDetailDao extends BaseDao<PremiumDetail> {
	public void deleteOldRecords(String year, String month, String provider);
}
