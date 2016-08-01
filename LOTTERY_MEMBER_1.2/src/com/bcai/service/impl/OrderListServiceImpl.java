package com.bcai.service.impl;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.dao.OrderListDao;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.domain.OrderList;
import com.bcai.service.OrderListService;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class OrderListServiceImpl extends AbstractServiceImpl<OrderList, Long> implements OrderListService  {

	@Autowired
	private OrderListDao orderListDao;
	
	@Autowired
	private MemberWalletDao memberWalletDao;
	
	@Autowired
	private MemberWalletListDao memberWalletListDao;
	
	@Override
	public GenericDaoImpl<OrderList, Long> getDao() {		
		return orderListDao;
	}
	
	
	@Override
	public String procRecharge(String amount, String billno,
			String succ,String result) {	
		OrderList orderByBillno = orderListDao.findByField("orderNumber", billno);
		if(orderByBillno == null){
			return "fail";
		}
		if(succ.equals("88")){
			 System.out.println("3");
			if(orderByBillno.getState() == 1 || orderByBillno.getState() == 2){
				return "ok";
			}
			DecimalFormat currentNumberFormat=new DecimalFormat("#0.0"); 
			String damount = currentNumberFormat.format(orderByBillno.getReMoney());
			 System.out.println("5damount: "+damount+" amount "+amount);
			if(damount.equals(amount)){				
				orderByBillno.setState(1);
				MemberWallet orWallet = memberWalletDao.findByField("mbName", orderByBillno.getMbName());
				orWallet.setWallet(orWallet.getWallet()+orderByBillno.getReMoney());
				memberWalletDao.update(orWallet);
				
				MemberWalletList walletList = new MemberWalletList();
				
				walletList.setAmoney(orderByBillno.getReMoney());
				walletList.setCreateTime(new Date());
				walletList.setBalance(orWallet.getWallet()+orderByBillno.getReMoney());
				walletList.setMbName(orderByBillno.getMbName());
				walletList.setDescription("充值成功");
				walletList.setType(1);
				
				memberWalletListDao.save(walletList);
				
				
			}else{			
				 System.out.println("4");
				orderByBillno.setState(2);
				orderByBillno.setExtractSpare("实际到账金额与填写充值金额不符，需核实。");
				return "ok";
			}
			
			orderListDao.save(orderByBillno);			
			return "ok";
			
		}else{
			orderByBillno.setState(2);
			orderByBillno.setExtractSpare(result);
			return "ok";
		}
		
		
	}

}
