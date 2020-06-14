package com.example.app.service.impl;

import com.example.app.dao.BackendUserMapper;
import com.example.app.entity.BackendUser;
import com.example.app.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service("backendUserServiceImpl")
public class BackendUserServiceImpl implements BackendUserService {
	@Autowired
	@Qualifier("backendUserMapper")
	private BackendUserMapper backendUserMapper;
	
	@Override
	public BackendUser adminLogin(BackendUser backendUser) {
		// TODO Auto-generated method stub
		return backendUserMapper.adminLogin(backendUser);
	}


}
