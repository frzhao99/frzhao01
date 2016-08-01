package com.bcai.service;


import com.bcai.domain.MemberTransinList;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

/**
 * 会员转入数据层
 * @author Fred
 *
 */
public interface MemberTransinListService extends BaseService<MemberTransinList, Long> {
	/**
	 * 处理转账
	 * @param memberTransinList
	 * @return
	 */
	public Message processTransferCoins(MemberTransinList memberTransinList,MemberTransinList memberTransoutList);
}
