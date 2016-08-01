package com.bcai.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bcai.domain.MillRecord;
import com.bcai.domain.TodayIndex;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class TodayIndexDao extends GenericDaoImpl<TodayIndex,Long>   {

	
	public int countIndex(){
		String rmSQL = "from TodayIndex";
		List<MillRecord> indexRecords = this.findList(rmSQL);
		return indexRecords.size();
	}
	
	public List<TodayIndex> indexList(){	
		String rmSQL = "from TodayIndex order by id desc";
		Query query = this.getSession().createQuery(rmSQL);
		query.setMaxResults(30);
		return query.list();
	}
	
}
