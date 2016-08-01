package com.bcai.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BonusByDateDao;
import com.bcai.dao.MemAchListDao;
import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberDao;
import com.bcai.dao.MemberStokenDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.domain.BonusByDate;
import com.bcai.domain.Member;
import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.service.MemberService;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;

@Service("memberServiceImpl")
public class MemberServiceImpl extends AbstractServiceImpl<Member, Long> implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemAchListDao memberAchListDao;

	@Autowired
	private MemberWalletDao memberWalletDao;

	@Autowired
	private MemberStokenDao memberStokenDao;

	@Autowired
	private MemberWalletListDao memberWalletListDao;

	@Autowired
	private MemberBonusListDao memberBonusListDao;

	@Autowired
	private BonusByDateDao bonusByDateDao;

	@Override
	public GenericDaoImpl<Member, Long> getDao() {

		return memberDao;
	}

	@Override
	public List<Member> findMembersByCommdName(String commdName) {
		String rmSQL = "from Member where parentUsername = ?";
		List<Member> members = memberDao.findList(rmSQL, new Object[] { commdName });
		return members;
	}

	@Override
	public boolean isSubaccount(String mbName, String subMbName) {
		if (mbName.equals(subMbName)) {
			return true;
		}
		boolean isC = true;
		String sMbName = subMbName;
		do {
			Member member = memberDao.findByField("mbName", sMbName);
			if (member == null) {
				return false;
			}
			Member pMember = memberDao.findByField("mbName", member.getParentUsername());
			if (pMember == null || pMember.getParentUsername().equals("BMsystem")) {
				isC = false;
			}
			if (pMember.getMbName().equals(mbName)) {
				return true;
			}
			sMbName = pMember.getMbName();
		} while (isC);

		return isC;
	}

	@Override
	public int register(Member newMember) {

		// 得到接点人信息
		// Member parentMemberRegisterInfo = memberDao.findByField("mbName",
		// newMember.getParentUsername());
		// String leftMbName = parentMemberRegisterInfo.getLeftMbName();
		// if (newMember.getIsAc() == 0) {
		// if (StringUtils.isNotBlank(leftMbName)) {
		// newMember.setParentUsername(leftMbName);
		// parentMemberRegisterInfo = memberDao.findByField("mbName",
		// leftMbName);
		// parentMemberRegisterInfo.setLeftMbName(newMember.getMbName());
		// memberDao.updateHQL(parentMemberRegisterInfo.getMbName(),
		// newMember.getMbName());
		// } else {
		// parentMemberRegisterInfo.setLeftMbName(newMember.getMbName());
		// memberDao.updateHQL(parentMemberRegisterInfo.getMbName(),
		// newMember.getMbName());
		// }
		// } else {
		// if (StringUtils.isBlank(leftMbName)) {
		// parentMemberRegisterInfo.setLeftMbName(newMember.getMbName());
		// memberDao.updateHQL(parentMemberRegisterInfo.getMbName(),
		// newMember.getMbName());
		// }
		// }

		newMember.setLocationId(0);
		newMember.setRegisterLevel("0");

		newMember.setIsAc(1);
		memberDao.save(newMember);
		memberDao.flush();

		MemberWallet memberWallet = new MemberWallet();
		memberWallet.setMbName(newMember.getMbName());
		memberWallet.setRewallet(0);
		memberWallet.setWallet(0);
		memberWallet.setHtcAmount(0);
		memberWalletDao.save(memberWallet);
		return 0;
	}

	@Override
	public boolean isTrSubaccount(String mbName, String subMbName) {
		Member tmember = memberDao.findByField("mbName", mbName);
		Member hmember = memberDao.findByField("mbName", subMbName);

		subMbName = tmember.getMbName();
		mbName = hmember.getMbName();

		if (mbName.equals(subMbName)) {
			return true;
		}
		boolean isC = true;
		String sMbName = subMbName;
		do {
			Member member = memberDao.findByField("mbName", sMbName);
			if (member == null) {
				return false;
			}
			Member pMember = memberDao.findByField("mbName", member.getRecommendMbName());
			if (pMember == null || pMember.getRecommendMbName().equals("BMsystem")) {
				isC = false;
			}
			if (pMember.getMbName().equals(mbName)) {
				return true;
			}
			sMbName = pMember.getMbName();
		} while (isC);

		return isC;
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return memberDao.findAll();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		//memberDao.getHibernateTemplate().clear();
		memberDao.getHibernateTemplate().flush();
	}

	@Override
	public boolean countHonor() {
		List<Member> members = memberDao.countHonor();
		if (members.size() == 0) {
			return false;
		}
		Member member = members.get(0);
		member = memberDao.getSyn(member.getId());
		String rmSQL = "from Member where parentUsername = ?";
		List<Member> parentMembers = memberDao.findList(rmSQL, new Object[] { member.getMbName() });
		if (parentMembers.size() >= 2) {
			double aac = parentMembers.get(0).getAchievement();
			double bac = parentMembers.get(1).getAchievement();
			double taac = 0;
			if (aac > bac) {
				taac = bac;
			} else {
				taac = aac;
			}
			double bonnus = 0;
			if (taac >= 30000 && taac < 80000) {
				bonnus = taac * 0.05;
			} else if (taac >= 80000 && taac < 200000) {
				bonnus = taac * 0.06;
			} else if (taac >= 200000 && taac < 500000) {
				bonnus = taac * 0.07;
			} else if (taac >= 500000) {
				bonnus = taac * 0.08;
			}
			if (bonnus >= 40000) {
				bonnus = 40000;
			}
			MemberWallet memberWallet = memberWalletDao.findMemberWalletById(member.getMbName());
			memberWallet.setWallet(memberWallet.getWallet() + bonnus);
			memberWalletDao.update(memberWallet);
			memberWalletDao.flush();
			MemberWalletList memberWalletList = new MemberWalletList();
			memberWalletList.setAmoney(bonnus);
			memberWalletList.setBalance(memberWallet.getWallet());
			memberWalletList.setCreateTime(new Date());
			memberWalletList.setCurrencyType(0);
			memberWalletList.setDescription("荣誉奖金");
			memberWalletList.setMbName(member.getMbName());
			memberWalletList.setType(0);
			memberWalletListDao.save(memberWalletList);
			memberWalletListDao.flush();
			MemberBonusList memberBonusList = new MemberBonusList();
			memberBonusList.setBonusBringMoney(bonnus);
			memberBonusList.setBonusBringTime(new Date());
			memberBonusList.setBonusRemark("荣誉奖金");
			memberBonusList.setBonusSort(5);
			memberBonusList.setBonusSpare("荣誉奖金");
			memberBonusList.setMbName(member.getMbName());
			memberBonusListDao.save(memberBonusList);
			memberBonusListDao.flush();

			String sDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
			String byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
			BonusByDate bonusByDate = bonusByDateDao.getBonusByDate(member.getMbName(), sDate);
			if (bonusByDate == null) {
				bonusByDate = new BonusByDate();
				bonusByDate.setBringDate(new Date());
				bonusByDate.setMbName(member.getMbName());
				bonusByDate.setStbringDate(sDate);
				bonusByDate.setCountBonus(0);
				bonusByDate.setFuliBonus(0);
				bonusByDate.setLiderBonus(0);
				bonusByDate.setOrgBonus(0);
				bonusByDate.setSaleBonus(0);
				bonusByDateDao.save(bonusByDate);
				bonusByDateDao.flush();
				bonusByDate = bonusByDateDao.getBonusByDate(member.getMbName(), sDate);
			}

			bonusByDate.setOrgBonus(bonusByDate.getOrgBonus() + bonnus);
			bonusByDate.setCountBonus(bonusByDate.getCountBonus() + bonnus);
			bonusByDateDao.update(bonusByDate);
			bonusByDateDao.flush();

		}

		for (Member pm : parentMembers) {
			pm.setAchievement(0);
			memberDao.update(pm);
			memberDao.flush();
		}
		member.setIsBus(1);
		memberDao.update(member);
		return true;

		// exeds();
		// return false;

	}

	private void exeds() {
		List<MemberWalletList> memberWalletLists = memberWalletListDao.findMemberWalletListByCe();
		for (MemberWalletList memberWall : memberWalletLists) {
			MemberWalletList newWall = memberWalletListDao.getSyn(memberWall.getId());
			List<MemberWalletList> mwList = memberWalletListDao.findMemberWalletListByMbName(newWall.getMbName());

			if (mwList.size() == 2) {
				MemberWalletList aMwallet = mwList.get(0);
				MemberWalletList bMwallet = mwList.get(1);
				if (aMwallet.getRemarks().equals("no") || bMwallet.getRemarks().equals("no")) {
					break;
				}
				aMwallet.setRemarks("no");
				bMwallet.setRemarks("no");
				memberWalletListDao.update(aMwallet);
				memberWalletListDao.update(bMwallet);
				memberWalletListDao.flush();
				MemberWallet memberWallet = memberWalletDao.findMemberWalletById(newWall.getMbName());
				memberWallet.setWallet(memberWallet.getWallet() - newWall.getAmoney());
				MemberWalletList oldMemberWall = new MemberWalletList();
				oldMemberWall.setAmoney(newWall.getAmoney());
				oldMemberWall.setBalance(memberWallet.getWallet());
				oldMemberWall.setCreateTime(new Date());
				oldMemberWall.setCurrencyType(0);
				oldMemberWall.setDescription("社区撤销多发荣誉奖金");
				oldMemberWall.setMbName(newWall.getMbName());
				oldMemberWall.setType(2);
				memberWalletListDao.save(oldMemberWall);
				memberWalletListDao.flush();
				memberWalletDao.update(memberWallet);
				memberWalletDao.flush();
			}
		}
	}

	@Override
	public List<Member> findMemberByEmail(String email) {
		String rmSQL = "from Member where email = ?";
		List<Member> members = memberDao.findList(rmSQL, new Object[] { email });
		return members;

	}

	@Override
	public int dispatch(String dispMbName,String mbName, int dep) {
		String sql = "from Member where mbName = '"+dispMbName+"'";
		Member member = memberDao.findByField("mbName", mbName);
		Member dispMember = memberDao.findByFieldSyn(sql);
		if(!StringUtils.equals(mbName, dispMember.getRecommendMbName())){
			return 1;
		}
		if(dispMember.getIsBus() != 0){
			return 2;
		}
		if(dep != 1 && dep != 2){
			return 1;
		}
		Member parentMemberRegisterInfo = memberDao.findByField("mbName",
				dispMember.getParentUsername());
		if(dep == 1){
			String leftMbName = parentMemberRegisterInfo.getLeftMbName();
			
			if (StringUtils.isNotBlank(leftMbName)) {
				dispMember.setParentUsername(leftMbName);
				parentMemberRegisterInfo = memberDao.findByField("mbName", leftMbName);
				parentMemberRegisterInfo.setLeftMbName(dispMbName);
				memberDao.updateHQL(parentMemberRegisterInfo.getMbName(),dispMbName);
			} else {
				parentMemberRegisterInfo.setLeftMbName(dispMbName);
				memberDao.updateHQL(parentMemberRegisterInfo.getMbName(), dispMbName);
			}
			dispMember.setRegisterLocation(dep);
		}else if(dep == 2){
			String rightMbName = parentMemberRegisterInfo.getRightMbName();
			if (StringUtils.isNotBlank(rightMbName)) {
				dispMember.setParentUsername(rightMbName);
				parentMemberRegisterInfo = memberDao.findByField("mbName", rightMbName);
				parentMemberRegisterInfo.setLeftMbName(dispMbName);
				memberDao.updateHQL(parentMemberRegisterInfo.getMbName(),dispMbName);
			} else {
				parentMemberRegisterInfo.setRightMbName(dispMbName);
				memberDao.update(parentMemberRegisterInfo);
			}
			
			dispMember.setRegisterLocation(dep);
		}
		
	
		dispMember.setIsBus(1);
		memberDao.update(dispMember);
		memberDao.flush();
		return 0;
	}

}
