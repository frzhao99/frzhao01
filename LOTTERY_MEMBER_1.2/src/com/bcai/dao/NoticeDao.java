package com.bcai.dao;

import org.springframework.stereotype.Repository;

import com.bcai.domain.Notice;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class NoticeDao extends GenericDaoImpl<Notice, Long> {
	
}
