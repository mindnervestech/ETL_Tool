package com.mnt.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.dao.ProfClaimDetailDao;
import com.mnt.domain.ProfClaimDetail;

@Repository
public class ProfClaimDetailDaoJpa extends BaseDaoJpa<ProfClaimDetail> implements ProfClaimDetailDao {

	public ProfClaimDetailDaoJpa() {
		super(ProfClaimDetail.class, "ProfClaimDetail");
	}
	
	@Override
	@Transactional
	public void deleteOldRecords(String year, String month, String provider) {
		Query query = getEntityManager().createQuery("DELETE FROM ProfClaimDetail pcd where pcd.year=:year and pcd.month=:month and pcd.provider=:provider");
		query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("provider", provider);
        query.executeUpdate();
	}
}
