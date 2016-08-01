package com.bcai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.BonusByDateDao;
import com.bcai.dao.EpSellDao;
import com.bcai.dao.MemAchListDao;
import com.bcai.dao.MemberBonusListDao;
import com.bcai.dao.MemberDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.domain.BonusByDate;
import com.bcai.domain.EpSell;
import com.bcai.domain.MemAchList;
import com.bcai.domain.Member;
import com.bcai.domain.MemberBonusList;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.service.MemberWalletListService;
import com.bcai.service.MemberWalletService;
import com.bcai.web.vo.Message;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.DateUtils;
import com.symbio.utils.WebUtils;

@Service("memberWalletServiceImpl")
public class MemberWalletServiceImpl extends
		AbstractServiceImpl<MemberWallet, Long> implements MemberWalletService {

	@Autowired
	private MemberWalletDao memberWalletDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberWalletListDao memberWalletListDao;

	@Autowired
	private MemberBonusListDao memberBonusListDao;

	@Autowired
	private BonusByDateDao bonusByDateDao;

	@Autowired
	private MemAchListDao memAchListDao;

	@Autowired
	private MemberWalletListService memberWalletListService;

	@Autowired
	private EpSellDao epSellDao;

	private static byte[] lock = new byte[0]; // 鐗规畩鐨刬nstance鍙橀噺

	@Override
	public GenericDaoImpl<MemberWallet, Long> getDao() {
		return memberWalletDao;
	}

	@Override
	public Message toWall(String selectType, double money, String mbName,
			String toMbName) {
		synchronized (lock) {
			MemberWallet memberWallet = memberWalletDao
					.findMemberWalletById(mbName);

			if (StringUtils.equals(selectType, "1")) {
				if (memberWallet.getWallet() < money) {
					return new Message(0,
							WebUtils.getMesString("bm.service.a17"));
				}
			} else if (StringUtils.equals(selectType, "2")) {
				if (memberWallet.getHtcAmount() < money) {
					return new Message(0,
							WebUtils.getMesString("bm.public.a53"));
				}
			} else {
				return new Message(0, "闈炴硶璁块棶");
			}

			MemberWallet toMbWallet = memberWalletDao
					.findMemberWalletById(toMbName);
			if (toMbWallet == null) {
				return new Message(0, WebUtils.getMesString("bm.service.a18"));
			}

			if (memberWallet.getRuby() < money * 0.4) {
				return new Message(0, WebUtils.getMesString("bm.public.a50"));
			} else {
				memberWallet.setRuby(memberWallet.getRuby() - money * 0.4);
			}
			MemberWalletList toMWalletList = new MemberWalletList();

			if (StringUtils.equals(selectType, "1")) {
				toMbWallet.setWallet(toMbWallet.getWallet() + money);
				toMWalletList.setBalance(toMbWallet.getWallet());
			} else if (StringUtils.equals(selectType, "2")) {
				toMbWallet.setHtcAmount(toMbWallet.getHtcAmount() + money);
				toMWalletList.setBalance(toMbWallet.getHtcAmount());
			}

			toMWalletList.setAmoney(money);

			toMWalletList.setCreateTime(new Date());

			if (StringUtils.equals(selectType, "1")) {
				toMWalletList.setCurrencyType(0);
			} else if (StringUtils.equals(selectType, "2")) {
				toMWalletList.setCurrencyType(2);
			}
			toMWalletList.setMbName(toMbName);
			toMWalletList.setDescription(mbName + "杞叆");
			toMWalletList.setRemarks(mbName);
			toMWalletList.setType(2);
			memberWalletListDao.save(toMWalletList);
			memberWalletListDao.flush();
			memberWalletDao.update(toMbWallet);
			memberWalletDao.flush();
			MemberWalletList mWalletList = new MemberWalletList();
			if (StringUtils.equals(selectType, "1")) {
				memberWallet.setWallet(memberWallet.getWallet() - money);
				mWalletList.setBalance(memberWallet.getWallet());
			} else if (StringUtils.equals(selectType, "2")) {
				memberWallet.setHtcAmount(memberWallet.getHtcAmount() - money);
				mWalletList.setBalance(memberWallet.getHtcAmount());
			}

			mWalletList.setAmoney(money);

			mWalletList.setMbName(mbName);
			mWalletList.setCreateTime(new Date());

			if (StringUtils.equals(selectType, "1")) {
				mWalletList.setCurrencyType(0);
			} else if (StringUtils.equals(selectType, "2")) {
				mWalletList.setCurrencyType(2);
			}
			mWalletList.setDescription("杞嚭鍒帮細" + toMbName);
			mWalletList.setRemarks(toMbName);
			mWalletList.setType(3);
			memberWalletListDao.save(mWalletList);
			memberWalletListDao.flush();
			memberWalletDao.update(memberWallet);
			memberWalletDao.flush();

			return new Message(1, WebUtils.getMesString("bm.service.a16"));
		}

	}

	@Override
	public Message shareBonus(double bonus) {

		String pHql = "from Member where usClass > ? and isDivd = ?";
		List<Member> members = memberDao.findList(pHql, new Object[] { 1, 0 });
		for (Member member : members) {
			double countBonuse = 0;
			if (member.getUsClass() == 2) {
				countBonuse = bonus * 1;
			} else if (member.getUsClass() == 3) {
				countBonuse = bonus * 2;
			} else if (member.getUsClass() == 4) {
				countBonuse = bonus * 5;
			} else if (member.getUsClass() == 5) {
				countBonuse = bonus * 10;
			}
			int code = bringShareBonus(member, countBonuse);
			if (code == 1) {
				continue;
			}
			Member commandMember = memberDao.findByField("mbName",
					member.getRecommendMbName());
			if (commandMember == null) {
				continue;
			}
			if (member.getIsComInt() == 0) {
				int count = 1;
				do {
					count = leadershipBons(commandMember, member.getMbName(),
							countBonuse * 0.1, count);
					commandMember = memberDao.findByField("mbName",
							commandMember.getRecommendMbName());
					if (commandMember == null) {
						count = 11;
					}
				} while (count < 10);
			}
		}
		updateBonuns();
		return new Message(1, WebUtils.getMesString("bm.service.a19"));
	}

	private void updateBonuns() {
		memberDao.updateBonus();
		memberDao.flush();
	}

	private int leadershipBons(Member member, String byMaName, double bonus,
			int count) {
		int amont = memberDao.getSumMember(member.getMbName());
		if (count > amont) {
			return count + 1;
		}
		if (member.getUsClass() < 2) {
			return count + 1;

		}
		MemberWallet memberWallet = memberWalletDao.findByField("mbName",
				member.getMbName());

		double epBonus = bonus * 0.60;
		double reBonus = bonus * 0.35;

		double epBalance = memberWallet.getWallet() + epBonus;
		double reBalance = memberWallet.getRewallet() + reBonus;
		memberWallet.setWallet(epBalance);
		memberWallet.setRewallet(reBalance);
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();

		MemberBonusList memberBonusList = new MemberBonusList();
		memberBonusList.setBonusBringMoney(bonus);
		memberBonusList.setBonusBringTime(new Date());
		memberBonusList.setBonusRemark("鍥犺处鎴凤細" + byMaName + "浜х敓");
		memberBonusList.setBonusSort(4);
		memberBonusList.setEpRemain(epBonus);
		memberBonusList.setReRemain(reBonus);
		memberBonusList.setMbName(memberWallet.getMbName());
		memberBonusListDao.save(memberBonusList);
		memberBonusListDao.flush();

		// MemberWalletList memberWalletList = new MemberWalletList();
		// memberWalletList.setAmoney(epBonus);
		// memberWalletList.setBalance(epBalance);
		// memberWalletList.setCreateTime(new Date());
		// memberWalletList.setCurrencyType(0);
		// memberWalletList.setDescription("棰嗗濂栵紝鍥犺处鎴凤細"+byMaName+"浜х敓");
		// memberWalletList.setMbName(memberWallet.getMbName());
		// memberWalletList.setType(0);
		// memberWalletListDao.save(memberWalletList);
		//
		// MemberWalletList reMemberWalletList = new MemberWalletList();
		// reMemberWalletList.setAmoney(reBonus);
		// reMemberWalletList.setBalance(reBalance);
		// reMemberWalletList.setCreateTime(new Date());
		// reMemberWalletList.setCurrencyType(1);
		// reMemberWalletList.setDescription("棰嗗濂栵紝鍥犺处鎴凤細"+byMaName+"浜х敓");
		// reMemberWalletList.setMbName(memberWallet.getMbName());
		// reMemberWalletList.setType(0);
		// memberWalletListDao.save(reMemberWalletList);
		// memberWalletListDao.flush();

		count = count + 1;
		return count;
	}

	private int bringShareBonus(Member member, double bonus) {

		MemAchList memAchList = memAchListDao.findByField("mbName",
				member.getMbName());
		double mbonus = 0;
		if ((memAchList.getMaxAvc() * 2 + 400) == memAchList.getTotalDividend()) {
			return 1;
		}
		if ((memAchList.getMaxAvc() * 2 + 400) < bonus
				+ memAchList.getTotalDividend()) {
			mbonus = memAchList.getMaxAvc() - memAchList.getTotalDividend();
			memAchList.setTotalDividend(memAchList.getMaxAvc());
			memAchListDao.update(memAchList);
			memAchListDao.flush();
			Member upMember = memberDao.findByField("mbName",
					member.getMbName());
			upMember.setIsDivd(1);
			memberDao.update(upMember);
			memberDao.flush();
		} else {
			memAchList.setTotalDividend(memAchList.getTotalDividend() + bonus);
			memAchListDao.update(memAchList);
			memAchListDao.flush();
		}

		if (mbonus > 0) {
			bonus = mbonus;
		}

		MemberWallet memberWallet = memberWalletDao.findByField("mbName",
				member.getMbName());

		double epBonus = bonus * 0.60;
		double reBonus = bonus * 0.35;

		double epBalance = memberWallet.getWallet() + epBonus;
		double reBalance = memberWallet.getRewallet() + reBonus;
		memberWallet.setWallet(epBalance);
		memberWallet.setRewallet(reBalance);
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();

		MemberBonusList memberBonusList = new MemberBonusList();
		memberBonusList.setBonusBringMoney(bonus);
		memberBonusList.setBonusBringTime(new Date());
		memberBonusList.setBonusRemark("鍒嗙孩濂栭噾");
		memberBonusList.setBonusSort(3);
		memberBonusList.setEpRemain(epBonus);
		memberBonusList.setReRemain(reBonus);
		memberBonusList.setMbName(memberWallet.getMbName());
		memberBonusListDao.save(memberBonusList);
		memberBonusListDao.flush();

		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setAmoney(epBonus);
		memberWalletList.setBalance(epBalance);
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setCurrencyType(0);
		memberWalletList.setDescription("鍒嗙孩濂栭噾");
		memberWalletList.setMbName(memberWallet.getMbName());
		memberWalletList.setType(0);
		memberWalletListDao.save(memberWalletList);

		MemberWalletList reMemberWalletList = new MemberWalletList();
		reMemberWalletList.setAmoney(reBonus);
		reMemberWalletList.setBalance(reBalance);
		reMemberWalletList.setCreateTime(new Date());
		reMemberWalletList.setCurrencyType(1);
		reMemberWalletList.setDescription("鍒嗙孩濂栭噾");
		reMemberWalletList.setMbName(memberWallet.getMbName());
		reMemberWalletList.setType(0);
		memberWalletListDao.save(reMemberWalletList);
		memberWalletListDao.flush();
		return 0;
	}

	@Override
	public Message countBonus(String bonus) {
		String startDate = DateUtils
				.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		String endDate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		List<String> dates = new ArrayList<String>();
		dates.add(bonus);
		for (String date : dates) {
			startDate = date;
			endDate = date;
			startDate += " 00:00:00";
			endDate += " 23:59:59";
			String pHql = "from MemberBonusList where bonusBringTime >= ? and bonusBringTime <= ?";
			// 鏌ヨ婵�娲绘湭婵�娲诲閲戠殑浜烘暟
			List<MemberBonusList> memberBonusLists = memberDao.findList(
					pHql,
					new Object[] {
							DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss,
									startDate),
							DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss,
									endDate) });
			String oneDate = date;
			for (MemberBonusList memberBonsList : memberBonusLists) {
				String byDSQL = "from BonusByDate where mbName = ? and stbringDate = ?";
				List<BonusByDate> bonusByDates = bonusByDateDao.findList(
						byDSQL, new Object[] { memberBonsList.getMbName(),
								oneDate });
				if (bonusByDates.size() == 0) {
					BonusByDate bonusByDate = new BonusByDate();
					bonusByDate.setBringDate(DateUtils.strToDate(
							DateUtils.yyyy_MM_dd, date));
					bonusByDate.setMbName(memberBonsList.getMbName());
					bonusByDate.setStbringDate(oneDate);
					bonusByDate.setCountBonus(0);
					bonusByDate.setFuliBonus(0);
					bonusByDate.setLiderBonus(0);
					bonusByDate.setOrgBonus(0);
					bonusByDate.setSaleBonus(0);
					bonusByDateDao.save(bonusByDate);
					bonusByDateDao.flush();
				}
				BonusByDate bonusByDate = bonusByDateDao.getBonusByDate(
						memberBonsList.getMbName(), oneDate);

				if (memberBonsList.getBonusSort() == 1) {// 鎺ㄨ崘濂�
					bonusByDate.setCountBonus(bonusByDate.getCountBonus()
							+ memberBonsList.getBonusBringMoney());
					bonusByDate.setSaleBonus(bonusByDate.getSaleBonus()
							+ memberBonsList.getEpRemain()
							+ memberBonsList.getReRemain());

				} else if (memberBonsList.getBonusSort() == 2) {// 缁勭粐濂�
					bonusByDate.setCountBonus(bonusByDate.getCountBonus()
							+ memberBonsList.getBonusBringMoney());
					bonusByDate.setOrgBonus(bonusByDate.getOrgBonus()
							+ memberBonsList.getEpRemain()
							+ memberBonsList.getReRemain());

				} else if (memberBonsList.getBonusSort() == 3) {// 绾㈠埄濂�
					bonusByDate.setCountBonus(bonusByDate.getCountBonus()
							+ memberBonsList.getBonusBringMoney());
					bonusByDate.setDividendBonus(bonusByDate.getDividendBonus()
							+ memberBonsList.getEpRemain()
							+ memberBonsList.getReRemain());

				} else if (memberBonsList.getBonusSort() == 4) {// 棰嗗濂�
					bonusByDate.setCountBonus(bonusByDate.getCountBonus()
							+ memberBonsList.getBonusBringMoney());
					bonusByDate.setLiderBonus(bonusByDate.getLiderBonus()
							+ memberBonsList.getEpRemain()
							+ memberBonsList.getReRemain());

				}
				bonusByDateDao.update(bonusByDate);
				bonusByDateDao.flush();
			}
		}

		return new Message(1, WebUtils.getMesString("bm.service.a20"));
	}

	@Override
	public Message sellBklc(double price, double count, String mbName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean honorBonus() {
		List<MemberWallet> memberWallets = memberWalletDao.findMemberWall();
		if (memberWallets == null || memberWallets.size() == 0) {
			return false;
		}
		MemberWallet memberWallet = memberWalletDao
				.findByFieldSyn("from MemberWallet where mbName = "
						+ memberWallets.get(0).getMbName());

		if (memberWallet.getDiamond() > 0) {
			Double money = memberWallet.getDiamond();
			List<EpSell> epSells = epSellDao.findEpSells(memberWallet
					.getMbName());
			if (epSells != null && epSells.size() > 0) {
				memberWallet.setWallet(memberWallet.getWallet() + (money / 12));
				MemberWalletList memberWalletList = new MemberWalletList();
				memberWalletList.setAmoney(money / 12);
				memberWalletList.setBalance(memberWallet.getWallet());
				memberWalletList.setCreateTime(new Date());
				memberWalletList.setCurrencyType(0);
				memberWalletList.setDescription("2015-12鑽ｈ獕濂栭噾锛屽垎12鏈堝彂鏀撅紝璇锋牳瀹�");
				memberWalletList.setMbName(memberWallet.getMbName());
				memberWalletList.setType(0);
				memberWalletListDao.save(memberWalletList);
				memberWalletListDao.flush();

			} else {
				memberWallet.setWallet(memberWallet.getWallet() + money);
				MemberWalletList memberWalletList = new MemberWalletList();
				memberWalletList.setAmoney(money);
				memberWalletList.setBalance(memberWallet.getWallet());
				memberWalletList.setCreateTime(new Date());
				memberWalletList.setCurrencyType(0);
				memberWalletList.setDescription("2015-12鑽ｈ獕濂栭噾");
				memberWalletList.setMbName(memberWallet.getMbName());
				memberWalletList.setType(0);
				memberWalletListDao.save(memberWalletList);
				memberWalletListDao.flush();

			}
			memberWallet.setRuby(0);
			memberWalletDao.update(memberWallet);
		} else {
			memberWallet.setRuby(0);
			memberWalletDao.update(memberWallet);
		}
		return true;
	}

	@Override
	public MemberWallet findMemberWalletSyn(String mbName) {
		// TODO Auto-generated method stub

		return memberWalletDao
				.findByFieldSyn("from MemberWallet where mbName = " + mbName);
	}

	@Override
	public boolean frozenBM() {
		List<MemberWallet> memberWallets = memberWalletDao.frozen();
		if (memberWallets.size() == 0 || memberWallets.get(0) == null) {
			return false;
		}
		MemberWallet memberWallet = memberWallets.get(0);
		memberWallet.setRuby(memberWallet.getSapphire()
				+ memberWallet.getWallet());
		memberWallet.setWallet(0);
		memberWallet.setRemarks("asd");
		memberWalletDao.update(memberWallet);

		return true;
	}

	@Override
	public void testA() {
		
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = 'frzhao88'");
		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setAmoney(10);
		memberWalletList.setBalance(memberWallet.getWallet()+10);
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setCurrencyType(14);
		memberWalletList.setDescription("增加");
		memberWalletList.setMbName(memberWallet.getMbName());
		memberWalletList.setType(0);
		memberWallet.setWallet(memberWallet.getWallet()+10);
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();
		memberWalletListDao.save(memberWalletList);
		memberWalletListDao.flush();
	}

	@Override
	public void testB() {
		MemberWallet memberWallet = memberWalletDao.findByFieldSyn("from MemberWallet where mbName = 'frzhao88'");
		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setAmoney(10);
		memberWalletList.setBalance(memberWallet.getWallet()-10);
		memberWalletList.setCreateTime(new Date());
		memberWalletList.setCurrencyType(15);
		memberWalletList.setDescription("减少");
		memberWalletList.setMbName(memberWallet.getMbName());
		memberWalletList.setType(1);
		memberWallet.setWallet(memberWallet.getWallet()-10);
		memberWalletDao.update(memberWallet);
		memberWalletListDao.save(memberWalletList);
		memberWalletDao.flush();
		memberWalletListDao.flush();

}
	
}
