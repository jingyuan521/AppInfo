package com.example.app.service.impl;

import java.util.List;

import com.example.app.dao.AppVersionMapper;
import com.example.app.entity.AppVersion;
import com.example.app.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AppVersionServiceimpl")
public class AppVersionServiceimpl implements AppVersionService {
	@Autowired
	@Qualifier("appVersionMapper")
	private AppVersionMapper appVersionMapper;

	@Override
	public List<AppVersion> getAppVersionList(Integer vid) {
		// TODO Auto-generated method stub
		return appVersionMapper.getAppVersionList(vid);
	}

	@Override
	public AppVersion getAppVersionById(Integer id) {
		// TODO Auto-generated method stub
		return appVersionMapper.getAppVersionById(id);
	}

}
