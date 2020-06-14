package com.example.app.dao;

import java.util.List;

import com.example.app.entity.DataDictionary;
import org.apache.ibatis.annotations.Param;

public interface Data_dictionaryMapper {
	//查询所属平台
	public List<DataDictionary> getlistByid(@Param("typeCode") String typeCode);
	//查询所有状态
	public List<DataDictionary> getStatus(@Param("typecode") String typecode);
	

}
