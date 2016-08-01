package com.bcai.service;

import java.util.List;

import com.bcai.domain.RechargeBank;
import com.symbio.service.impl.BaseService;

public interface RechargeBankService extends BaseService<RechargeBank, Long> {
	public List<RechargeBank> findRechargeBanks(String bankName);
}
