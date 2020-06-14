package com.example.app.service;

import java.util.List;

import com.example.app.entity.AppInfo;
import org.apache.ibatis.annotations.Param;




public interface AppInfoService {
	/*��ѯ����app��Ϣ*/
	public List<AppInfo> getAppInfoList(@Param(value = "softwareName") String querySoftwareName,
                                        @Param(value = "categoryLevel1") Integer queryCategoryLevel1,
                                        @Param(value = "categoryLevel2") Integer queryCategoryLevel2,
                                        @Param(value = "categoryLevel3") Integer queryCategoryLevel3,
                                        @Param(value = "flatformId") Integer queryFlatformId,
                                        @Param(value = "from") Integer currentPageNo,
                                        @Param(value = "pageSize") Integer pageSize, int i, int size)throws Exception;
     //��ѯ����app����
	public int getAppInfoCount(@Param(value = "softwareName") String querySoftwareName,
                               @Param(value = "categoryLevel1") Integer queryCategoryLevel1,
                               @Param(value = "categoryLevel2") Integer queryCategoryLevel2,
                               @Param(value = "categoryLevel3") Integer queryCategoryLevel3,
                               @Param(value = "flatformId") Integer queryFlatformId)throws Exception;
	//��ѯ����app
	public List<AppInfo> getAll(@Param(value = "softwareName") String querySoftwareName,
                                @Param(value = "status") Integer queryStatus,
                                @Param(value = "categoryLevel1") Integer queryCategoryLevel1,
                                @Param(value = "categoryLevel2") Integer queryCategoryLevel2,
                                @Param(value = "categoryLevel3") Integer queryCategoryLevel3,
                                @Param(value = "flatformId") Integer queryFlatformId,
                                @Param(value = "from") Integer currentPageNo,
                                @Param(value = "devId") Integer devId,
                                @Param(value = "pageSize") Integer pageSize) throws Throwable;
	
	//����id��ѯҪ�޸ĵ���Ʒ��Ϣ
    public AppInfo getinfoByid(@Param("id") Integer id);
    
    //����id�޸ĵ�ǰ����Ʒ��Ϣ
    public int UpdateAppinfo(@Param("status") Integer status, @Param("aid") Integer aid);
    
    //��ѯ��Ʒ����
    public AppInfo getApkname(String ApkName);
	
}
