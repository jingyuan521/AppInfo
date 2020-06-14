package com.example.app.dao;

import com.example.app.entity.DevUser;
import org.apache.ibatis.annotations.Param;


public interface DevUserMapper {
	//????????
	public DevUser DevLogin(@Param("devCode") String devCode, @Param("devPassword") String devPassword);

}
