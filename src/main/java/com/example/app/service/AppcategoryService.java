package com.example.app.service;

import java.util.List;

import com.example.app.entity.AppCategory;

public interface AppcategoryService {
	//三级菜单查询、
	public List<AppCategory> getListByid(Integer id);

}
