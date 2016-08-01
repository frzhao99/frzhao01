package com.bcai.service;

import com.bcai.domain.MemberExtractList;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

/**
 * 会员提现明细表
 * @author Fred
 *
 */
public interface MemberExtractListService extends BaseService<MemberExtractList, Long>  {
	public Message processWithdrawals(MemberExtractList memberExtractList);	
}
