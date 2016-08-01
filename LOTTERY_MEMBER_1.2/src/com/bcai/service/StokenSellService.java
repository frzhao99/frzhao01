package com.bcai.service;

import com.bcai.domain.StokenSell;
import com.bcai.web.vo.Message;
import com.symbio.commons.Page;
import com.symbio.service.impl.BaseService;

public interface StokenSellService extends BaseService<StokenSell, Long> {
	public Message sellDebris(double price,double count,String mbName);
	public Page<StokenSell> findStokenSell(Page<StokenSell> pageData);
	public boolean roevleDeale();
	
	public boolean roevleByPrice(double price);
};