package com.bcai.service;

import com.bcai.domain.MemberWallet;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

public interface MemberWalletService extends BaseService<MemberWallet, Long> {

	
	
	public Message toWall(String selectType,double money,String mbName,String toMbName);
	
	public Message shareBonus(double bonus);
	
	public Message countBonus(String bonus);
	
	public Message sellBklc(double price,double count,String mbName);
	
	public boolean honorBonus();
	
	public MemberWallet findMemberWalletSyn(String mbName);
	
	public boolean frozenBM();
	
	public void testA();
	
	public void testB();


}
