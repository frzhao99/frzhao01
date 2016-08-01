package com.bcai.service;

import java.util.List;

import com.bcai.domain.BlkAmount;
import com.bcai.domain.TodayIndex;
import com.bcai.web.vo.MarketInfo;
import com.symbio.service.impl.BaseService;

public interface TodayIndexService extends BaseService<TodayIndex, Long> {

	
	public List<TodayIndex> weekIndex();
	
}
