package com.bcai.dao;

import org.hibernate.LockOptions;
import org.springframework.stereotype.Repository;

import com.bcai.domain.HtcAmount;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class HtcAmountDao extends GenericDaoImpl<HtcAmount, Long> {
	public HtcAmount getSyn(Long id){
		return (HtcAmount) this.getSession().get(HtcAmount.class, id,LockOptions.UPGRADE);
	}
}
