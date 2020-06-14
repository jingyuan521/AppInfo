package com.example.app.service.impl;

import java.util.List;

import com.example.app.dao.AppInfoMapper;
import com.example.app.entity.AppInfo;
import com.example.app.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AppInfoServiceImpl")
public class AppInfoServiceImpl implements AppInfoService {
	@Autowired
	@Qualifier("appInfoMapper")
   private AppInfoMapper appInfoMapper;

	@Override
	public List<AppInfo> getAppInfoList(String querySoftwareName, Integer queryCategoryLevel1,
										Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer queryFlatformId, Integer currentPageNo,
										Integer pageSize, int i, int size) throws Exception {
		// TODO Auto-generated method stub
		return appInfoMapper.getAppInfoList(querySoftwareName, 1, queryCategoryLevel1, queryCategoryLevel2, 
				                 queryCategoryLevel3, queryFlatformId, null, (currentPageNo-1)*pageSize, pageSize);
	}

	@Override
	public int getAppInfoCount(String querySoftwareName, Integer queryCategoryLevel1, Integer queryCategoryLevel2,
			Integer queryCategoryLevel3, Integer queryFlatformId) throws Exception {
		// TODO Auto-generated method stub
		return appInfoMapper.getAppInfoCount(querySoftwareName, 1, queryCategoryLevel1, queryCategoryLevel2, 
									queryCategoryLevel3, queryFlatformId, null);
	}

	@Override
	public AppInfo getinfoByid(Integer id) {
		// TODO Auto-generated method stub
		return appInfoMapper.getinfoByid(id);
	}

	@Override
	public int UpdateAppinfo(Integer status, Integer aid) {
		// TODO Auto-generated method stub
		return appInfoMapper.UpdateAppinfo(status, aid);
	}

	@Override
	public List<AppInfo> getAll(String querySoftwareName, Integer queryStatus, Integer queryCategoryLevel1,
			Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer queryFlatformId, Integer currentPageNo,
			Integer devId, Integer pageSize) throws Throwable {
		// TODO Auto-generated method stub
		return appInfoMapper.getAppInfoList(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, 1, currentPageNo, pageSize);
	}

	@Override
	public AppInfo getApkname(String ApkName) {
		// TODO Auto-generated method stub
		return appInfoMapper.getApkname(ApkName);
	}





	

}
