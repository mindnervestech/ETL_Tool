package com.mnt.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.dao.RxDetailDao;
import com.mnt.domain.RxDetail;

@Repository
public class RxDetailDaoJpa extends BaseDaoJpa<RxDetail> implements RxDetailDao {

	public RxDetailDaoJpa() {
		super(RxDetail.class, "RxDetail");
	}
	
	@Override
	@Transactional
	public void deleteOldRecords(String year, String month, String provider) {
		Query query = getEntityManager().createQuery("DELETE FROM RxDetail rd where rd.year=:year and rd.month=:month and rd.provider=:provider");
		query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("provider", provider);
        query.executeUpdate();
	}
}
