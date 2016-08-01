package com.bcai.dao;

import org.springframework.stereotype.Repository;

import com.bcai.domain.Bank;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class BankDao extends GenericDaoImpl<Bank,Long>{

}
