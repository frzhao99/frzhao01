package com.bcai.service;

import java.util.List;

import com.bcai.domain.MemberLottery;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

public interface MemberLotteryService extends BaseService<MemberLottery, Long>{

	List<MemberLottery> findAll();

	public Message buyLottery(Integer amount,String mbName);
}
