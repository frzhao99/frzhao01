package com.bcai.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcai.domain.Member;
import com.bcai.web.BcaiUtils;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.utils.HibernateUtils;

@Repository
public class MemberDao extends GenericDaoImpl<Member,Long>   {
	
	
	public void updateHQL(String leftMbName,String newLeftMbName){
		String hql = "update Member set leftMbName = ? where leftMbName = ?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, newLeftMbName);
		query.setString(1, leftMbName);
		query.executeUpdate();
	}
	
	public Member getSyn(Long id){
		return (Member) this.getSession().get(Member.class, id, LockOptions.UPGRADE);
	}

	public Page<Member> find(Page<Member> pageData, String mbName) {
		
		// TODO Auto-generated method stub
	    Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);		
	    criteria.add(Restrictions.eq("recommendMbName", mbName));
		HibernateUtils.setParameter(criteria, pageData);		
		pageData.setResult(criteria.list());		
		return pageData;	
	}
	
	public int getSumMemberByRecommendMbName(String mbName){
		String hsql = "select count(*) from Member where recommendMbName = ?";
		Query query = HibernateUtils.createQuery(this.getSession(), hsql, new Object[] {mbName});
		int amount = 0;
		if(!query.list().isEmpty()){  
	        amount = Integer.parseInt(query.list().get(0).toString());  
	    } 
		return amount;
	}
	
	public int getSumMember(String mbName){
		String hsql = "from Member where recommendMbName = ?";
		
		List<Member> members = this.findList(hsql,new Object[] {mbName});
		int conunt = 0;
		for(Member member:members){			
			int userClass = member.getUsClass();
		    if (userClass == 2) {
		    	conunt +=1;
			} else if (userClass == 3) {
				conunt +=2;
			} else if (userClass == 4) {
				conunt +=5;
			} else if (userClass == 5) {
				conunt +=10;
			}
		    if(conunt > 10){
		    	break;
		    }
		}
		return conunt;
	}
	
	public void updateBonus(){
		String hsql = "update MemAchList set bonus = ?";
		this.batchExecute(hsql, new Object[] {0.0});
	} 
	
	public List<Member> getTopReMember(List<String> mbNames){		
		StringBuilder hsql = new StringBuilder("from Member where ");		
		for(int i = 0; i < mbNames.size(); i++){			
			if(i == 0){
				hsql.append("mbName = ?");
			}else{
				hsql.append(" or mbName = ?");
			}
		}
		hsql.append("order by atime");		
		Query query = getSession().createQuery(hsql.toString());
        
		Object[] obj = mbNames.toArray();
		for (int i = 0; i < obj.length; i++)
		{
			query.setParameter(i, obj[i]);
		}
		query.setFirstResult(0);
		query.setMaxResults(10);
		//this.findList(hsql.toString(),mbNames.toArray())
	
		return query.list();	
	}
	
	public List<Member> getReMember(List<String> mbNames){		
		StringBuilder hsql = new StringBuilder("from Member where ");		
		for(int i = 0; i < mbNames.size(); i++){			
			if(i == 0){
				hsql.append("mbName = ?");
			}else{
				hsql.append(" or mbName = ?");
			}
		}
		hsql.append("order by atime");		
		Query query = getSession().createQuery(hsql.toString());
        
		Object[] obj = mbNames.toArray();
		for (int i = 0; i < obj.length; i++)
		{
			query.setParameter(i, obj[i]);
		}	
		return query.list();	
	}
	
	public void clearSession(){
		this.getSession().clear();
	}
	
	
	public List<Member> countHonor(){
		String rmSQL = "from Member where IsBus = :IsBus";
		Query query = this.getSession().createQuery(rmSQL).setInteger("IsBus",0);
		query.setMaxResults(1);
		return query.list();
	}
	

}
