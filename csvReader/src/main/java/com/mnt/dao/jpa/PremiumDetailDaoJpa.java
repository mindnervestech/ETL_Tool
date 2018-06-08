package com.mnt.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.dao.PremiumDetailDao;
import com.mnt.domain.PremiumDetail;

@Repository
public class PremiumDetailDaoJpa  extends BaseDaoJpa<PremiumDetail> implements PremiumDetailDao {

	public PremiumDetailDaoJpa() {
		super(PremiumDetail.class, "PremiumDetail");
	}
	
	@Override
	@Transactional
	public void deleteOldRecords(String year, String month, String provider) {
		Query query = getEntityManager().createQuery("DELETE FROM PremiumDetail pd where pd.year=:year and pd.month=:month and pd.provider=:provider");
		query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("provider", provider);
        query.executeUpdate();
	}
}
