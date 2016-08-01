package com.bcai.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BankDao;
import com.bcai.dao.MessageDao;
import com.bcai.domain.Bank;
import com.bcai.domain.Message;
import com.bcai.service.BankService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class BankServiceImpl extends AbstractServiceImpl<Bank,Long> implements BankService{

	@Autowired
	private BankDao bankDao;
	
	@Override
	public GenericDaoImpl<Bank, Long> getDao() {
		// TODO Auto-generated method stub
		return bankDao;
	}
	
	public List<Bank> getList(String username){
		
		String rmSQL = "from Bank where mbName = ?";
		List<Bank> banks = bankDao.findList(rmSQL,
				new Object[] { username});
		return banks;
		
	}
	
	public void add(Bank bank){
		
		bankDao.save(bank);
		
	}

	@Override
	public List<Bank> getBankByType(String username, String type) {
		String rmSQL = "";
		if(StringUtils.equals("1", type)){
			rmSQL = "from Bank where mbName = ? and bankName != ?";
		}else if(StringUtils.equals("2", type)){
			rmSQL = "from Bank where mbName = ? and bankName = ?";
		}else{
			return null;
		}
		
		List<Bank> banks = bankDao.findList(rmSQL,
				new Object[] { username,"btc"});
		return banks;
	}

}
