package com.example.app.service;

import java.util.List;

import com.example.app.entity.AppVersion;
import org.apache.ibatis.annotations.Param;


public interface AppVersionService {

	//��ȡ�汾��Ϣ�ļ���
	public List<AppVersion> getAppVersionList(Integer vid);
	//����id��ȡ�汾��Ϣ
	public AppVersion getAppVersionById(@Param("id") Integer id);
}
