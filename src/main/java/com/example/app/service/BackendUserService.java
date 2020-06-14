package com.example.app.service;

import com.example.app.entity.BackendUser;


public interface BackendUserService {
	//登录
	public BackendUser adminLogin(BackendUser backendUser);
}
