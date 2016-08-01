package com.bcai.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.service.MemberWalletListService;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class MemberWalletListServiceImpl extends AbstractServiceImpl<MemberWalletList,Long> implements MemberWalletListService{

	@Autowired
	private MemberWalletListDao memberWalletListDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Autowired
	private MemberBonusListDao memberBonusListDao;
	
	private static byte[] lock = new byte[0]; // 特殊的instance变量
	
	@Override
	public GenericDaoImpl<MemberWalletList, Long> getDao() {		
		return memberWalletListDao;
	}

	@Override
	public Page<MemberWalletList> find(Page<MemberWalletList> pageData, String mbName) {
		return memberWalletListDao.find(pageData,mbName);
	}

//	@Override
//	public void modifyMemberWalletList(MemberWalletList mwalletList,
//			int bonusType,MemberBonusList memberBonusList) {
//		double banlance = 0.0;
//	   
//	    	   MemberWallet memberWallet = memberWalletDao.findMemberWalletById(mwalletList.getMbName());
//			   
//				if(bonusType == -2){
//					banlance = memberWallet.getWallet()-mwalletList.getAmoney();
//				}else{
//					banlance = mwalletList.getAmoney()+memberWallet.getWallet();
//				}
//				memberWallet.setWallet(banlance);
//				memberWalletDao.update(memberWallet);
//				memberWalletDao.flush();
//				mwalletList.setCreateTime(new Date());
//				mwalletList.setBalance(banlance);
//				memberWalletListDao.save(mwalletList);
//				memberWalletListDao.flush();
//		
//		
//		
//		
//	}

}
