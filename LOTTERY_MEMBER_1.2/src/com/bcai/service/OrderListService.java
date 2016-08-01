package com.bcai.service;

import com.bcai.domain.OrderList;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;


public interface OrderListService extends BaseService<OrderList, Long> {
	public String procRecharge(String amount,  String billno,
			String succ,String result);
}
