package com.bcai.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.jsoup.helper.DataUtil;
import org.springframework.stereotype.Repository;

import com.bcai.domain.MillRecord;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.DateUtils;

@Repository
public class MillRecordDao extends GenericDaoImpl<MillRecord, Long>  {
	public int countMill(String mbName){
		String rmSQL = "from MillRecord where modelNum = ? and mbName = ?";
		List<MillRecord> millRecords = this.findList(rmSQL,
				new Object[] {"yes",mbName});
		
		return millRecords.size();
	}
	
	public List<MillRecord> mills(){		
		
		String rmSQL = "from MillRecord where isCount = :isCount and id <= :id";
		
		Query query = this.getSession().createQuery(rmSQL).setString("isCount","yes").setInteger("id",6408306);
		
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<MillRecord> decomMills(String mbName,int decomNum){
		String rmSQL = "from MillRecord where mbName = :mbName and modelNum = :modelNum";
		Query query = this.getSession().createQuery(rmSQL).setString("mbName",mbName).setString("modelNum", "yes");
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setFirstResult(0);
		query.setMaxResults(decomNum);
		return query.list();
	}
	
	public int recoveryBonus(){
		String upHQL ="update MillRecord set isCount = ?,holdDay = holdDay+1";
		Query query = this.getSession().createQuery(upHQL);
	    query.setString(0, "yes");	
	    return query.executeUpdate();
	    
	}
	
	public int reupdate(MillRecord millRecord){
		String upHQL ="update MillRecord set isCount = ?,holdDay=? where id = ?";
		Query query = this.getSession().createQuery(upHQL);
	    query.setString(0, "no");
	    query.setInteger(1, millRecord.getHoldDay());
	    query.setLong(2, millRecord.getId());
	    return query.executeUpdate();
	}
	
	public int  checkMill(){
		String upHQL ="update MillRecord set modelNum = ? where holdDay >= ?";
		Query query = this.getSession().createQuery(upHQL);
	    query.setString(0, "yes");
	    query.setInteger(1, 30);
	    return query.executeUpdate();
	}
	
	public double countMillWorthSpit(String mbName,String date){
		List topData = this.find("select sum(buy_price) from mill_record where mb_name = '"+mbName+"' and synthesis_date >= '"+date+"'");
		if(topData.get(0) == null){
			return 0.0;
		}
		double topCount = Double.valueOf(topData.get(0).toString());
		return topCount;
	}
	
	public double countMillWorth(String mbName){
		List topData = this.find("select sum(buy_price) from mill_record where mb_name = '"+mbName+"'");
		if(topData.get(0) == null){
			return 0.0;
		}
		double topCount = Double.valueOf(topData.get(0).toString());
		return topCount;
	}
	
	public double countNoDecMillWorth(String mbName){
		String nowdate = DateUtils.DateToStr(DateUtils.yyyy_MM_dd, new Date());
		nowdate += " 00:00:00";
		
		List maxIDData = this.find("select max(id) from mill_record where synthesis_date <  '"+nowdate+"'");
		Long maxId =Long.valueOf(maxIDData.get(0).toString());
		
		List topData = this.find("select sum(buy_price) from mill_record where mb_name = '"+mbName+"' and hold_day <= 30 and is_count = 'yes' and id <= "+maxId+"");
		if(topData.get(0) == null){
			return 0.0;
		}
		double topCount = Double.valueOf(topData.get(0).toString());
		return topCount;
	}
	
	
	public List<MillRecord> splitfragment(){
		String rmSQL = "from MillRecord where holdDay > :holdDay";
		Query query = this.getSession().createQuery(rmSQL).setInteger("holdDay",34);
		query.setLockOptions(LockOptions.UPGRADE); 
		query.setMaxResults(1);
        return query.list();
		
	}
}
