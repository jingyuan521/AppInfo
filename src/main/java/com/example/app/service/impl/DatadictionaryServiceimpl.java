package com.example.app.service.impl;

import java.util.List;

import com.example.app.dao.Data_dictionaryMapper;
import com.example.app.entity.DataDictionary;
import com.example.app.service.DatadictonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service("DatadictionaryServiceimpl")
public class DatadictionaryServiceimpl implements DatadictonService {

	@Autowired
	@Qualifier("data_dictionaryMapper")
	private Data_dictionaryMapper data_dictionaryMapper;
	@Override
	public List<DataDictionary> getlistByid(String typeCode) {
		// TODO Auto-generated method stub
		return data_dictionaryMapper.getlistByid(typeCode);
	}
	@Override
	public List<DataDictionary> getStatus(String typecode) {
		// TODO Auto-generated method stub
		return data_dictionaryMapper.getStatus(typecode);
	}
	

}
