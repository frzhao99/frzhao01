package com.bcai.service;

import java.util.List;

import com.bcai.domain.Bank;
import com.symbio.service.impl.BaseService;

public interface BankService extends BaseService<Bank,Long>{

	public List<Bank> getList(String username);
	
	public void add(Bank bank);
	
	public List<Bank> getBankByType(String username,String type);
	
}
