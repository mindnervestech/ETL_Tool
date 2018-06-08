package com.mnt.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.dao.InstClaimDetailDao;
import com.mnt.domain.InstClaimDetail;

@Repository
public class InstClaimDetailDaoJpa extends BaseDaoJpa<InstClaimDetail> implements InstClaimDetailDao {

	public InstClaimDetailDaoJpa() {
		super(InstClaimDetail.class, "InstClaimDetail");
	}
	
	@Override
	@Transactional
	public void deleteOldRecords(String year, String month, String provider) {
		Query query = getEntityManager().createQuery("DELETE FROM InstClaimDetail icd where icd.year=:year and icd.month=:month and icd.provider=:provider");
		query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("provider", provider);
        query.executeUpdate();
	}
}
