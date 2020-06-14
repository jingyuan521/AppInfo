package com.example.app.dao;

import java.util.List;

import com.example.app.entity.AppCategory;

import org.apache.ibatis.annotations.Param;

public interface AppcategoryMapper {
	//三级菜单查询、
	public List<AppCategory> getListByid(@Param("id") Integer id);


}
