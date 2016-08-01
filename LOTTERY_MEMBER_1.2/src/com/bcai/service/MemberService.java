package com.bcai.service;

import java.util.List;

import com.bcai.domain.Member;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;

public interface MemberService extends BaseService<Member, Long> {
	
		//新方法
		public List<Member> findMembersByCommdName(String commdName);
		
		public boolean isSubaccount(String mbName,String subMbName);
		
		public boolean isTrSubaccount(String mbName,String subMbName);
		
		public int register(Member newMember);
		
		public List<Member> findAll();
		
		public void clear();
		
		public boolean countHonor();
		
		public List<Member> findMemberByEmail(String email);
		
		//新方法
		public int dispatch(String dispMbName,String mbName,int dep);
		
}
