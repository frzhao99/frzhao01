package com.bcai.service;


import java.util.List;

import com.bcai.domain.MemberLeaveMessage;
import com.symbio.service.impl.BaseService;

public interface MemberLeaveMessageService extends BaseService<MemberLeaveMessage, Long>{
	public List<MemberLeaveMessage> findLeaveMessages(String mbName); 
}
