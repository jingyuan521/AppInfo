package com.example.app.service;

import com.example.app.entity.DevUser;
import org.apache.ibatis.annotations.Param;


public interface DevUserService {
	//�����ߵ�¼
	public DevUser DevLogin(String devCode, String devPassword);


}
