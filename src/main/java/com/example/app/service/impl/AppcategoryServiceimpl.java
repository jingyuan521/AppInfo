package com.example.app.service.impl;

import java.util.List;

import com.example.app.dao.AppcategoryMapper;
import com.example.app.entity.AppCategory;
import com.example.app.service.AppcategoryService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AppcategoryServiceimpl")
public class AppcategoryServiceimpl implements AppcategoryService {
	@Autowired
	@Qualifier("appcategoryMapper")
	private AppcategoryMapper appcategoryMapper;

	@Override
	public List<AppCategory> getListByid(Integer id) {
		// TODO Auto-generated method stub
		return appcategoryMapper.getListByid(id);
	}

}
