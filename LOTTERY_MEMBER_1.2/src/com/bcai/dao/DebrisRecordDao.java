package com.bcai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcai.domain.DebrisRecord;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class DebrisRecordDao extends GenericDaoImpl<DebrisRecord, Long> {
	public double findDebrisRecordSellDe(String startDate,String endDate,int type,String mbName){
		List topData = this.find("select sum(amoney) from debris_record where mb_name='"+mbName+"' and occ_date > '"+startDate+"' and occ_date <  '"+endDate+"' and type = "+type+"");
		if(topData == null || topData.get(0) == null){
			return 0.0;
		}
		double topCount = Double.valueOf(topData.get(0).toString());
		return topCount;		
	}
}
