package com.bcai.service;

import java.util.List;

import com.bcai.domain.EpSell;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

public interface EpSellService  extends BaseService<EpSell, Long>  {
	public void updateEpSell(EpSell epSell);
	public boolean checkEp();
	public boolean checkConfPayEp();
	public void updateCheckEp();
	
	public Message sellEp(EpSell epSell);
	
	
	public Message delteOrderIdSyn(Long ovderID);
	
	public List<EpSell> findByEpsByMbName(String mbName);
	
	public List<EpSell> findByEPsByBuyMbName(String mbName);
	
	public boolean revokeEpSell(String epMbname);
	
	public boolean cckeEpSell();
}
