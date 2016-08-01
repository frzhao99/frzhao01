package com.bcai.service;

import com.bcai.domain.MemberStoken;
import com.bcai.web.vo.MarketInfo;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

public interface MemberStokenService extends BaseService<MemberStoken, Long>  {
	public Message resolveMills(Integer millNum,String mbName,Long mid);
	
	public Message synthetiseMills(Integer millNum,String mbName);
	
	public MarketInfo countMills();
	
	public boolean split(double count,String date);
	
	public boolean sellStoen(String startDate,String endDate,double sellPrice);
	
	public double countMillGross(String mbName);
	
	public boolean synMill();
	/**
	 * 新的产矿机器
	 * @return
	 */
	public boolean newMill();
	
	public boolean revoDebris();
	
	public boolean splitfo();

	
}
