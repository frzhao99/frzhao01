package com.bcai.service;

import com.bcai.domain.Notice;
import com.symbio.commons.Page;
import com.symbio.service.impl.BaseService;

public interface NoticeService extends BaseService<Notice, Long>{
	public Page<Notice> find(Page<Notice> pageData);
}
