package com.mnt.dao;

import com.mnt.domain.DemographicDetail;

public interface DemographicDetailDao extends BaseDao<DemographicDetail>{
	public void deleteOldRecords(String year, String month, String provider);
}
