package com.bcai.dao;

import org.springframework.stereotype.Repository;

import com.bcai.domain.Notice;
import com.bcai.domain.RechargeBank;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class RechargeBankDao extends GenericDaoImpl<RechargeBank, Long> {
	
}
