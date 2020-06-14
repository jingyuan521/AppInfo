package com.example.app.service;

import java.util.List;

import com.example.app.entity.DataDictionary;
import org.apache.ibatis.annotations.Param;


public interface DatadictonService {
	//查询所属平台
	public List<DataDictionary> getlistByid(String typeCode);
	//查询所有状态
	public List<DataDictionary> getStatus(String typecode);

}
