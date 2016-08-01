package com.bcai.service;

import com.bcai.domain.MillRecord;
import com.bcai.web.vo.Message;
import com.symbio.service.impl.BaseService;


public interface MillRecordService extends BaseService<MillRecord, Long> {
	public int countMill(String mbName);
	public boolean sellBonus();
	public boolean mill();	
	public boolean countDayBonus();
	public Message recoveryBonus();
	
	public void checkMill();
	
	public boolean splitfo();
	
	
	
}
