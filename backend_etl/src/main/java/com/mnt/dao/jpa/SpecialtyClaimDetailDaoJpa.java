package com.mnt.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.dao.SpecialtyClaimDetailDao;
import com.mnt.domain.SpecialtyClaimDetail;

@Repository
public class SpecialtyClaimDetailDaoJpa extends BaseDaoJpa<SpecialtyClaimDetail> implements SpecialtyClaimDetailDao {

	public SpecialtyClaimDetailDaoJpa() {
		super(SpecialtyClaimDetail.class, "SpecialtyClaimDetail");
	}
	
	@Override
	@Transactional
	public void deleteOldRecords(String year, String month, String provider) {
		Query query = getEntityManager().createQuery("DELETE FROM SpecialtyClaimDetail scd where scd.year=:year and scd.month=:month and scd.provider=:provider");
		query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("provider", provider);
        query.executeUpdate();
	}
}
