package com.bcai.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberExtractListDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.domain.MemberExtractList;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.service.MemberExtractListService;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.WebUtils;

@Service
public class MemberExtractListServiceImpl extends AbstractServiceImpl<MemberExtractList, Long>  implements MemberExtractListService {

	@Autowired
	private MemberExtractListDao memberExtractListDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;
	
	@Override
	public GenericDaoImpl<MemberExtractList, Long> getDao() {		
		return memberExtractListDao;
	}

	@Override
	public Message processWithdrawals(MemberExtractList memberExtractList) {	
		MemberWallet memberWallet = memberWalletDao.findByField("mbName", memberExtractList.getMbName());
		if(memberExtractList.getExtractState() == 1){
			memberExtractListDao.update(memberExtractList);			
		}else if(memberExtractList.getExtractState() == 2){
			
			memberWallet.setWallet(memberWallet.getWallet() + memberExtractList.getExtractAmount());
			memberWalletDao.update(memberWallet);
		}
		memberExtractList.setExtractAmountFact(memberExtractList.getExtractAmountFact());
		memberExtractList.setExtractTime(new Date());
		memberExtractListDao.update(memberExtractList);	
		
	
		MemberWalletList w1 =  new MemberWalletList();
		w1.setMbName(memberExtractList.getMbName());		
		w1.setType(4);	
		w1.setAmoney(memberExtractList.getExtractAmount());
		w1.setBalance(memberWallet.getWallet());
		w1.setCreateTime(new Date());
		w1.setDescription("提现，金额："+memberExtractList.getExtractAmount());
		memberWalletListDao.save(w1);
		
		return new Message(1, WebUtils.getMesString("bm.service.a5"));
	}

	

}