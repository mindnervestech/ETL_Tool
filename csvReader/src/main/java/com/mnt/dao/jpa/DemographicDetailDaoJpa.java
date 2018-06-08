package com.mnt.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.dao.DemographicDetailDao;
import com.mnt.domain.DemographicDetail;

@Repository
public class DemographicDetailDaoJpa extends BaseDaoJpa<DemographicDetail> implements DemographicDetailDao{

	public DemographicDetailDaoJpa() {
		super(DemographicDetail.class, "DemographicDetail");
	}
	
	@Override
	@Transactional
	public void deleteOldRecords(String year, String month, String provider) {
		Query query = getEntityManager().createQuery("DELETE FROM DemographicDetail icd where icd.year=:year and icd.month=:month and icd.provider=:provider");
		query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("provider", provider);
        query.executeUpdate();
	}
}
