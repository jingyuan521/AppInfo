package com.example.app.service.impl;

import com.example.app.dao.DevUserMapper;
import com.example.app.entity.DevUser;
import com.example.app.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service("DevUserServiceimpl")
public class DevUserServiceimpl implements DevUserService {
	@Autowired
	@Qualifier("devUserMapper")
	private DevUserMapper devUserMapper;

	@Override
	public DevUser DevLogin(String devCode, String devPassword) {
		// TODO Auto-generated method stub
		return devUserMapper.DevLogin(devCode, devPassword);
	}



}
