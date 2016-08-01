package com.bcai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.dao.PeepListDao;
import com.bcai.dao.PrizeDao;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.domain.PeepList;
import com.bcai.domain.Prize;
import com.bcai.service.PrizeService;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class PrizeServiceImpl extends AbstractServiceImpl<Prize, Long> implements PrizeService  {

	@Autowired
	private PrizeDao prizeDao;
	
	@Autowired
	private PeepListDao peepListDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;
	
	@Autowired
	private PeepListDao peePListDao;
	
	@Override
	public GenericDaoImpl<Prize, Long> getDao() {
		
		return prizeDao;
	}

	@Override
	public List<Prize> findAll() {
		// TODO Auto-generated method stub
		return prizeDao.findAll();
	}

	@Override
	public List<PeepList> findPeeListByMbnameAndPrId(String mbName, String prId) {
		
		return peepListDao.findPeeListByMbnameAndPrId(mbName,prId);
	}

	@Override
	public Message peep(String prId, String mbName) {
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = '"+mbName+"'");
		Prize prize = prizeDao.findByFieldSyn("from Prize where prId = '"+prId+"'");
		if(prize.getRemainPeep() <= 0){
			return new Message(0, "竞拍结束");
		}
		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setMbName(mbName);
		memberWalletList.setAmoney(prize.getPeepCost());
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setDescription("查看竞拍商品："+prId);
		memberWalletList.setType(11);
		
		if(memberWallet.getRewallet() < prize.getPeepCost()){
			if(memberWallet.getWallet() < prize.getPeepCost()){
				return new Message(0, "余额不足");
				
			}
			memberWallet.setWallet(memberWallet.getWallet()-prize.getPeepCost());
			memberWalletList.setBalance(memberWallet.getWallet());
			memberWalletList.setCurrencyType(0);
		}else{
			
			memberWallet.setRewallet(memberWallet.getRewallet()-prize.getPeepCost());
			memberWalletList.setBalance(memberWallet.getRewallet());
			memberWalletList.setCurrencyType(1);
		}
		
		
		prize.setRemainPeep(prize.getRemainPeep()-prize.getPeepCost()/10);
		
		PeepList peepList = new PeepList();
		peepList.setMbName(mbName);
		peepList.setPrId(prId);
		peepList.setPrTime(new Date());
		peepList.setPrName(prize.getPrName());
		peepList.setRemainPeep(prize.getRemainPeep());
		
		peePListDao.save(peepList);
		peePListDao.flush();
		
		prizeDao.update(prize);
		prizeDao.flush();
		memberWalletDao.update(memberWallet);
        memberWalletDao.flush();
        memberWalletListDao.save(memberWalletList);
        memberWalletListDao.flush();
		
		return new Message(1, "偷窥成功");
	}

}
