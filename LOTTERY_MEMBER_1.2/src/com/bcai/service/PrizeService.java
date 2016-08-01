package com.bcai.service;

import java.util.List;



import com.bcai.domain.PeepList;
import com.bcai.domain.Prize;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

public interface PrizeService extends BaseService<Prize, Long> {
	public List<Prize> findAll();
	public List<PeepList> findPeeListByMbnameAndPrId(String mbName,String prId);
	public Message peep(String prId,String mbName);

}
