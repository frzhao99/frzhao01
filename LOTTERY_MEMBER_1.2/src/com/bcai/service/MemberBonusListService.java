package com.bcai.service;

import com.bcai.domain.MemberBonusList;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

/**
 * 会员奖金明细服务层
 * @author Fred
 *
 */
public interface MemberBonusListService extends  BaseService<MemberBonusList, Long> {
	public Message countLittleBonus(String mbName);
	
	public double getDynamicBonus(String mbName);
	
	public double getShareBonus(String mbName);
	
	
}
