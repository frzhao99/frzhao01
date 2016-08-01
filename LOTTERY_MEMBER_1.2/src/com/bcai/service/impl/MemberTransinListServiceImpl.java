package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberTransinListDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.domain.MemberTransinList;
import com.bcai.domain.MemberWallet;
import com.bcai.service.MemberTransinListService;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.WebUtils;

@Service
public class MemberTransinListServiceImpl extends AbstractServiceImpl<MemberTransinList, Long> implements MemberTransinListService{

	@Autowired
	private MemberTransinListDao memberTransinListDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Override
	public GenericDaoImpl<MemberTransinList, Long> getDao() {
		
		return memberTransinListDao;
	}

	@Override
	public Message processTransferCoins(MemberTransinList memberTransinList,MemberTransinList memberTransoutList) {		
		
		//转入钱包账户
		MemberWallet memberInWallet = memberWalletDao.findByField("mbName", memberTransinList.getUserName());
		//转出用户钱包账户
		MemberWallet memberOutWallet = memberWalletDao.findByField("mbName", memberTransoutList.getUserName());
		memberInWallet.setWallet(memberInWallet.getWallet() + memberTransinList.getTransinAmount());
		memberOutWallet.setWallet(memberOutWallet.getWallet() - memberTransoutList.getTransinAmount());
		
				
		memberWalletDao.update(memberInWallet);
		memberWalletDao.update(memberOutWallet);
		
		memberTransinListDao.save(memberTransinList);
		memberTransinListDao.save(memberTransoutList);
		
		return new Message(5, WebUtils.getMesString("bm.service.a16"));
	}
	
	

}
