package com.bcai.service;

import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberWalletList;
import com.symbio.commons.Page;
import com.symbio.service.impl.BaseService;

public interface MemberWalletListService extends BaseService<MemberWalletList, Long> {

	Page<MemberWalletList> find(Page<MemberWalletList> pageData, String mbName);
	//public void modifyMemberWalletList(MemberWalletList mwalletList,int bonusType,MemberBonusList memberBonusList);
}
