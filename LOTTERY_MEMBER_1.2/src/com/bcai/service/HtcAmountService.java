package com.bcai.service;

import java.util.List;

import com.bcai.domain.HtcAmount;
import com.bcai.web.vo.MarketInfo;
import com.symbio.service.impl.BaseService;

public interface HtcAmountService extends BaseService<HtcAmount, Long> {
	public MarketInfo countMarket();
	public List<MarketInfo> weekMarket();
}
