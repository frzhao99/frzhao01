package com.bcai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcai.dao.NoticeDao;
import com.bcai.domain.Notice;
import com.bcai.service.NoticeService;
import com.symbio.commons.Page;
import com.symbio.dao.hibernate.GenericDaoImpl;
import com.symbio.service.impl.AbstractServiceImpl;

@Service
public class NoticeServiceImpl extends AbstractServiceImpl<Notice, Long> implements NoticeService{
	
    @Autowired
	private NoticeDao noticeDao;

	@Override
	public Page<Notice> find(Page<Notice> pageData) {
		return noticeDao.find(pageData);
	}

	@Override
	public GenericDaoImpl<Notice, Long> getDao() {
		return noticeDao;
	}
    


}
