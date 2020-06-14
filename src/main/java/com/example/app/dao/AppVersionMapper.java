package com.example.app.dao;

import java.util.List;

import com.example.app.entity.AppVersion;

import org.apache.ibatis.annotations.Param;


public interface AppVersionMapper {
	//��ȡ�汾��Ϣ�ļ���
	public List<AppVersion> getAppVersionList(@Param("vid") Integer vid);
	//����id��ȡ�汾��Ϣ
	public AppVersion getAppVersionById(@Param("id") Integer id);
}
