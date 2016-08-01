package com.bcai.service;

import java.util.List;

import com.bcai.domain.StokenBuy;
import com.bcai.web.vo.Message;
import com.symbio.commons.Page;
import com.symbio.service.impl.BaseService;

public interface StokenBuyService extends BaseService<StokenBuy, Long> {
	public Message buyDebris(String coinType,int buyType,double price,double count,String mbName);
	public Page<StokenBuy> findStokenBuy(Page<StokenBuy> pageData);
	public List<StokenBuy> findStokenBuyState();
	public Message revokebuy(Long id);
	public boolean novo(double price);
	
	public boolean splituh();
	
	
}
