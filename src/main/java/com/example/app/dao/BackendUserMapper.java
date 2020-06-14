package com.example.app.dao;

import com.example.app.entity.BackendUser;



public interface BackendUserMapper {
	//后台管理人员登录
	public BackendUser adminLogin(BackendUser backendUser);
}
