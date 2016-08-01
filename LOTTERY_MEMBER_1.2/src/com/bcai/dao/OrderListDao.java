package com.bcai.dao;

import org.springframework.stereotype.Repository;

import com.bcai.domain.OrderList;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class OrderListDao extends GenericDaoImpl<OrderList, Long> {
	
}
