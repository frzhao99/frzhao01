package com.bcai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemAchListDao;
import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.domain.MemAchList;
import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.service.MemberBonusListService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;

@Service
public class MemberBonusListServiceImpl extends AbstractServiceImpl<MemberBonusList, Long> implements MemberBonusListService{

	@Autowired
	private MemberBonusListDao memberBonusListDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;
	
	@Autowired
	private MemAchListDao memAchListDao;
	
	@Override
	public GenericDaoImpl<MemberBonusList, Long> getDao() {		
		return memberBonusListDao;
	}

	@Override
	public Message countLittleBonus(String mbName) {
		
		return null;
	}

	@Override
	public double getDynamicBonus(String mbName) {
		
		return memberBonusListDao.getDynamicBonus(mbName);
	}

	@Override
	public double getShareBonus(String mbName) {
		 MemAchList memAchList = memAchListDao.findByField("mbName",mbName);
		 return memAchList.getTotalDividend();
		 //return memberBonusListDao.getShareBonus(mbName);
	}

	
}
