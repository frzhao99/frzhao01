package com.bcai.dao;

import org.springframework.stereotype.Repository;

import com.bcai.domain.LoginStatus;
import com.symbio.dao.hibernate.GenericDaoImpl;

@Repository
public class LoginStatusDao extends GenericDaoImpl<LoginStatus,Long>{

}
