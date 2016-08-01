package com.bcai.service;

import java.util.List;

import com.bcai.domain.BlkAmount;
import com.bcai.domain.Message;
import com.symbio.service.impl.BaseService;

public interface MessageService extends BaseService<Message, Long> {

	public List<com.bcai.domain.Message> getList(String mbName);

	public int getNewCount(String mbName);
	
	public void refreshMessage(String mbName,String id);
}
