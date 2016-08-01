package com.bcai.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.StokenBuy;
import com.bcai.domain.StokenDealRecord;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.DateUtils;

@Repository
public class StokenDealRecordDao extends GenericDaoImpl<StokenDealRecord, Long>{
	
	public StokenDealRecord todayHighRecord(Date mdate){
		String date = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, mdate);
		String sdate =date + " 00:00:00";
		String edate =date + " 23:59:59";
		String rmSQL = "from StokenDealRecord where dealTime >= :sdate and dealTime <= :edate order by price desc";
		Query query = this.getSession().createQuery(rmSQL);
		query.setTimestamp("sdate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, sdate));
		query.setTimestamp("edate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, edate));
		query.setMaxResults(1);
		List<StokenDealRecord> stokenDealRecord = query.list();
		if(stokenDealRecord.size() == 0){
			return null;
		}
		return stokenDealRecord.get(0);
	}
	
	public StokenDealRecord todayCloseRecord(Date mdate){
		String date = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, mdate);
		String sdate =date + " 09:30:00";
		String edate =date + " 22:00:00";
		String rmSQL = "from StokenDealRecord where dealTime >= :sdate and dealTime <= :edate order by dealTime desc";
		Query query = this.getSession().createQuery(rmSQL);
		query.setTimestamp("sdate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, sdate));
		query.setTimestamp("edate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, edate));
		query.setMaxResults(1);
		List<StokenDealRecord> stokenDealRecord = query.list();
		if(stokenDealRecord.size() == 0){
			return null;
		}
		return stokenDealRecord.get(0);
	}
	
	
	public StokenDealRecord todayBottomRecord(Date mdate){
		String date = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, mdate);
		String sdate =date + " 00:00:00";
		String edate =date + " 23:59:59";
		String rmSQL = "from StokenDealRecord where dealTime >= :sdate and dealTime <= :edate order by price asc";
		Query query = this.getSession().createQuery(rmSQL);
		query.setTimestamp("sdate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, sdate));
		query.setTimestamp("edate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, edate));
		query.setMaxResults(1);
		List<StokenDealRecord> stokenDealRecord = query.list();
		if(stokenDealRecord.size() == 0){
			return null;
		}
		return stokenDealRecord.get(0);
	}
	
	public double countDealNum(){
		String date = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		String sdate =date + " 00:00:00";
		String edate =date + " 23:59:59";
		String rmSQL = "select sum(dealNum) from StokenDealRecord where dealTime >= :sdate and dealTime <= :edate";
		Query query = this.getSession().createQuery(rmSQL);
		query.setTimestamp("sdate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, sdate));
		query.setTimestamp("edate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, edate));
		Double stokenDealRecord;
		if(query.uniqueResult() == null){
			stokenDealRecord = 0.0;
		}else{
			stokenDealRecord = (Double)query.uniqueResult();
		}		
		
		return stokenDealRecord;
	}
	
	public double countDealNumByDate(Date newDate){
		String date = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, newDate);
		String sdate =date + " 00:00:00";
		String edate =date + " 23:59:59";
		String rmSQL = "select sum(dealNum) from StokenDealRecord where dealTime >= :sdate and dealTime <= :edate";
		Query query = this.getSession().createQuery(rmSQL);
		query.setTimestamp("sdate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, sdate));
		query.setTimestamp("edate", DateUtils.strToDate(DateUtils.yyyy_MM_dd_HH_mm_ss, edate));
		Double stokenDealRecord;
		if(query.uniqueResult() == null){
			stokenDealRecord = 0.0;
		}else{
			stokenDealRecord = (Double)query.uniqueResult();
		}		
		
		return stokenDealRecord;
	}
	
	
}
