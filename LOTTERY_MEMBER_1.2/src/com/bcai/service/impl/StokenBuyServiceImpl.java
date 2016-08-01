package com.bcai.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.MemberStokenDao;
import com.bcai.dao.MemberWalletDao;
import com.bcai.dao.MemberWalletListDao;
import com.bcai.dao.StokenBuyDao;
import com.bcai.dao.StokenSellDao;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.MemberWalletList;
import com.bcai.domain.StokenBuy;
import com.bcai.service.MemberWalletListService;
import com.bcai.service.StokenBuyService;
import com.bcai.web.vo.Message;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;
import com.symbio.utils.WebUtils;

@Service
public class StokenBuyServiceImpl extends AbstractServiceImpl<StokenBuy, Long>
		implements StokenBuyService {

	@Autowired
	private StokenBuyDao stokenBuyDao;

	@Autowired
	private MemberWalletDao memberWalletDao;

	@Autowired
	private MemberWalletListDao memberWalletListDao;

	@Autowired
	private MemberWalletListService memberWalletListService;

	@Autowired
	private MemberStokenDao memberStokenDao;

	@Autowired
	private StokenSellDao stokenSellDao;

	private static byte[] buyLock = new byte[0]; // 特殊的instance变量

	@Override
	public GenericDaoImpl<StokenBuy, Long> getDao() {
		return stokenBuyDao;
	}

	@Override
	public Message buyDebris(String coinType, int buyType, double price,
			double count, String mbName) {
		DecimalFormat df1 = new DecimalFormat("###.000");
		Message message = buyDe(coinType, buyType,
				Double.valueOf(df1.format(price)), count, mbName);
		return message;
	}

	private synchronized Message buyDe(String coinType, int buyType,
			double price, double count, String mbName) {
		double payAmount = price * count;
		MemberWallet memberWallet = memberWalletDao
				.findMemberWalletById(mbName);
		if (StringUtils.equals(coinType, "0")) {
			if (memberWallet.getWallet() < payAmount) {
				return new Message(0, "BM币余额不足");
			}
		} else if (StringUtils.equals(coinType, "2")) {
			if (memberWallet.getHtcAmount() < payAmount) {
				return new Message(0, "矿机币币余额不足");
			}
		} else {
			return new Message(0, "非法访问");
		}

		StokenBuy stokenBuy = new StokenBuy();
		stokenBuy.setMbName(mbName);
		stokenBuy.setTokenBuyCount(count);
		stokenBuy.setDealCount(0.0);
		stokenBuy.setState(0);
		stokenBuy.setDealAmount(0.0);

		stokenBuy.setTokenBuyPrice(price);
		stokenBuy.setTokenBuyTime(new Date());
		stokenBuy.setBuyType(buyType);
		stokenBuy.setCoinType(coinType);
		stokenBuyDao.save(stokenBuy);
		stokenBuyDao.flush();

		if (StringUtils.equals(coinType, "0")) {
			memberWallet.setWallet(memberWallet.getWallet() - payAmount);

		} else if (StringUtils.equals(coinType, "2")) {
			memberWallet.setHtcAmount(memberWallet.getHtcAmount() - payAmount);

		}

		MemberWalletList memberWalletList = new MemberWalletList();
		memberWalletList.setMbName(mbName);
		memberWalletList.setAmoney(payAmount);
		if (StringUtils.equals(coinType, "0")) {
			memberWalletList.setCurrencyType(0);
			memberWalletList.setBalance(memberWallet.getWallet());

		} else if (StringUtils.equals(coinType, "2")) {
			memberWalletList.setCurrencyType(2);
			memberWalletList.setBalance(memberWallet.getHtcAmount());

		}

		memberWalletList.setCreateTime(new Date());

		memberWalletList.setDescription("购买碎片：" + count + "个");
		memberWalletList.setType(5);

		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();
		memberWalletListDao.save(memberWalletList);
		memberWalletListDao.flush();

		return new Message(1, WebUtils.getMesString("bm.service.a22"));
	}

	@Override
	public Page<StokenBuy> findStokenBuy(Page<StokenBuy> pageData) {

		return stokenBuyDao.findGroupBuy(pageData);
	}

	@Override
	public List<StokenBuy> findStokenBuyState() {
		// TODO Auto-generated method stub
		String rmSQL = "from StokenBuy where state = ?";
		List<StokenBuy> stokenBuys = stokenBuyDao.findList(rmSQL,
				new Object[] { 1 });
		return stokenBuys;
	}

	@Override
	public Message revokebuy(Long id) {

		StokenBuy stokenBuy = stokenBuyDao.getSyn(id);
		if (stokenBuy.getState() == 0 || stokenBuy.getState() == 1) {
			stokenBuy.setState(3);
			double nuDealNumber = stokenBuy.getTokenBuyCount()
					- stokenBuy.getDealCount();
			double price = stokenBuy.getTokenBuyPrice() * nuDealNumber;
			
			MemberWallet memberWallet = memberWalletDao
					.findMemberWalletById(stokenBuy.getMbName());
			
			MemberWalletList memberWalletList = new MemberWalletList();
            if(stokenBuy.getCoinType().equals("2")){
            	memberWallet.setHtcAmount(memberWallet.getHtcAmount() + price);
            	memberWalletList.setCurrencyType(2);
            	memberWalletList.setBalance(memberWallet.getHtcAmount());
			}else{
				memberWallet.setWallet(memberWallet.getWallet() + price);
				memberWalletList.setCurrencyType(0);
				memberWalletList.setBalance(memberWallet.getWallet());
			}
			
			memberWalletList.setAmoney(price);
			
			memberWalletList.setCreateTime(new Date());
			
			memberWalletList.setDescription("撤销购买碎片买单");
			memberWalletList.setMbName(stokenBuy.getMbName());
			memberWalletList.setType(7);
			memberWalletDao.update(memberWallet);
			memberWalletDao.flush();
			memberWalletListDao.save(memberWalletList);
			memberWalletListDao.flush();
			// memberWalletListService.modifyMemberWalletList(
			// memberWalletList, -1, null);
			stokenBuyDao.update(stokenBuy);

			return new Message(1, WebUtils.getMesString("bm.service.a23"));

		} else {

			return new Message(0, WebUtils.getMesString("bm.service.a24"));
		}

	}

	@Override
	public boolean novo(double price) {

		StokenBuy stokenBuy = stokenBuyDao.vodoBuyDel(price);
		if (stokenBuy == null) {
			return false;
		}
		double orgPrice = stokenBuy.getTokenBuyPrice();
		double orgCount = stokenBuy.getTokenBuyCount();
		stokenBuy.setTokenBuyPrice(price);
		stokenBuy.setTokenBuyCount(orgCount * (orgPrice / price));
		stokenBuyDao.update(stokenBuy);
		return true;
	}

	@Override
	public boolean splituh() {
		List<StokenBuy> stokenBuys = stokenBuyDao.splituh();
		if (stokenBuys == null || stokenBuys.get(0) == null) {
			return false;
		}
		StokenBuy stokenBuy = stokenBuys.get(0);
		stokenBuy.setState(5);
		MemberWallet memberWallet = memberWalletDao
				.findByFieldSyn("from MemberWallet where mbName = '"
						+ stokenBuy.getMbName() + "'");
		memberWallet.setSapphire(memberWallet.getSapphire()
				+ (stokenBuy.getTokenBuyCount() - stokenBuy.getDealCount())
				* stokenBuy.getTokenBuyPrice());
		memberWalletDao.update(memberWallet);
		memberWalletDao.flush();
		stokenBuyDao.update(stokenBuy);
		stokenBuyDao.flush();
		return true;
	}

}
