package com.example.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.example.app.entity.AppCategory;
import com.example.app.entity.AppInfo;
import com.example.app.entity.AppVersion;
import com.example.app.entity.DataDictionary;
import com.example.app.service.AppInfoService;
import com.example.app.service.AppVersionService;
import com.example.app.service.AppcategoryService;
import com.example.app.service.DatadictonService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;


import tools.PageSupport;
//开发者业务
@Controller
@RequestMapping("dev")
public class DevAppInfoController {

	@Resource
    private AppInfoService appInfoService;
	@Resource
	private DatadictonService datadictonService;
	@Resource
	private AppcategoryService appcategoryService;
	@Resource
	private AppVersionService appVersionService;
	@RequestMapping(value="/list")
	public String getAppInfoList(Model model,HttpSession session,
			@RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
			@RequestParam(value="queryStatus",required=false) Integer _queryStatus,
			@RequestParam(value="queryCategoryLevel1",required=false) Integer _queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false) Integer _queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false) Integer _queryCategoryLevel3,
			@RequestParam(value="queryFlatformId",required=false)Integer _queryFlatformId,
			@RequestParam(value="pageIndex",required=false) Integer pageIndex){
		System.out.println("进入app开发者的查询方法");
		Integer devId = 1;
		List<AppInfo> appInfoList = null;
		List<DataDictionary> statusList = null;//����ƽ̨�ķ����б�
		List<AppCategory> categoryLevel1List = null;//�г�һ�������б�ע�����������������б�ͨ���첽ajax��ȡ
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;
		List<DataDictionary> flatFormList = null;//����ƽ̨�ķ����б�
		//ҳ������
		int pageSize = 5;
		//��ǰҳ��
		Integer currentPageNo = 1;
		
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		Integer queryStatus = null;
		if(_queryStatus != null && !_queryStatus.equals("")){
			queryStatus = _queryStatus;
		}
		Integer queryCategoryLevel1 = null;
		if(_queryCategoryLevel1 != null && !_queryCategoryLevel1.equals("")){
			queryCategoryLevel1 = _queryCategoryLevel1;
		}
		Integer queryCategoryLevel2 = null;
		if(_queryCategoryLevel2 != null && !_queryCategoryLevel2.equals("")){
			queryCategoryLevel2 = _queryCategoryLevel2;
		}
		Integer queryCategoryLevel3 = null;
		if(_queryCategoryLevel3 != null && !_queryCategoryLevel3.equals("")){
			queryCategoryLevel3 = _queryCategoryLevel3;
		}
		Integer queryFlatformId = null;
		if(_queryFlatformId != null && !_queryFlatformId.equals("")){
			queryFlatformId = _queryFlatformId;
		}
		
		//����������
		int totalCount = 0;
		try {
			totalCount = appInfoService.getAppInfoCount(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ҳ��
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//������ҳ��βҳ
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			try {
				appInfoList = appInfoService.getAll(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, _queryFlatformId,devId, currentPageNo, pageSize);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		categoryLevel1List = appcategoryService.getListByid(null);
		statusList = datadictonService.getStatus("APP_STATUS");
		flatFormList = datadictonService.getlistByid("APP_FLATFORM");
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("pages", pages);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);
		//���������б�����������б�---����
		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}

		return "developer/appinfolist";
	}
	public List<AppCategory> getCategoryList(String pid){
		List<AppCategory> appList = null;
		appList = appcategoryService.getListByid(Integer.parseInt(pid));
		return appList;
	}
	//����ȡ�ĵ���������json��ʽ���ظ�ǰ��
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getAppCategoryList(@RequestParam String pid){
		System.out.println(pid);
		if(pid.equals("")) pid = null;
		String json = JSONArray.toJSONString(getCategoryList(pid));
		return json;
	}
	//��ת����ҳ��
	@RequestMapping("appinfoadd")
	public String addinfo(){
		
		return "developer/appinfoadd";
	}
	
	//����ҳ�淵������ƽ̨
	@RequestMapping(value="datadictionarylist.json",method=RequestMethod.GET,produces="application/json; charset=utf-8")
	public List<DataDictionary> dataDictionaries(@RequestParam String TyoeCode){
		List<DataDictionary> dataDictionaries = null;

		return dataDictionaries = datadictonService.getlistByid(TyoeCode);
	}

	//��֤�Ƿ�����

	@RequestMapping("apkexist.json")
	public String getApkName(@RequestParam String ApkName){
		String json = null;
		AppInfo appInfo = appInfoService.getApkname(ApkName);
		if (ApkName==null) {
			json=JSONArray.toJSONString("empty");
		}else if(appInfo!=null){
			json=JSONArray.toJSONString("exist");
		}else if (appInfo==null) {
			json=JSONArray.toJSONString("noexist");
		}
		return json;
	}
	
	//ִ������ҵ��
	public String AddAppinfo(@RequestParam AppInfo appInfo){
		
		return null;
	}
	
	
	//��ת�����汾ҳ��
	@RequestMapping("appversionadd")
	public ModelAndView Addversion(ModelAndView md, @RequestParam Integer id){
		List<AppVersion> aList = appVersionService.getAppVersionList(id);
		
			md.addObject("appVersionList", aList);
			md.setViewName("developer/appversionadd");

		
		return md;
		
		
	}

	
}
