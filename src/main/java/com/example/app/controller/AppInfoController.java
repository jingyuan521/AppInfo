package com.example.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.example.app.entity.AppCategory;
import com.example.app.entity.AppInfo;
import com.example.app.entity.AppVersion;
import com.example.app.entity.DTO.AppinfoDto;
import com.example.app.entity.DataDictionary;
import com.example.app.service.AppInfoService;
import com.example.app.service.AppVersionService;
import com.example.app.service.AppcategoryService;
import com.example.app.service.DatadictonService;
import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONArray;
import tools.PageSupport;

import static tools.Constants.pageSize;

//后台业务
@Controller
@RequestMapping("appinfo")
public class AppInfoController {
	@Resource
    private AppInfoService appInfoService;
	@Resource
	private DatadictonService datadictonService;
	@Resource
	private AppcategoryService appcategoryService;
	@Resource
	private AppVersionService appVersionService;
	//查询所有不在审核通过状态的app
	@RequestMapping(value="/list")
	public String getAppInfoList(Model model,HttpSession session,
			@RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
			@RequestParam(value="queryStatus",required=false) Integer _queryStatus,
			@RequestParam(value="queryCategoryLevel1",required=false) Integer _queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false) Integer _queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false) Integer _queryCategoryLevel3,
			@RequestParam(value="queryFlatformId",required=false)Integer _queryFlatformId,
			@RequestParam(value="pageIndex",required=false) Integer pageIndex){
		System.out.println("我进入到查询所有app信息的方法里面");
		List<AppInfo> appInfoList = null;
		List<DataDictionary> flatFormList = null;//所属平台的分类列表
		List<AppCategory> categoryLevel1List = null;//列出一级分类列表，注：二级和三级分类列表通过异步ajax获取
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;
		//页面容量
		int pageSize = 5;
		//当前页码
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
		
		//总数量（表）
		int totalCount = 0;
		try {
			totalCount = appInfoService.getAppInfoCount(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			appInfoList = appInfoService.getAppInfoList(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, _queryFlatformId, currentPageNo, pageSize, (currentPageNo - 1) * pageSize, pageSize);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取app分类
		categoryLevel1List = appcategoryService.getListByid(null);
        System.out.println("app分类"+categoryLevel1List);
		//app所属平台
		flatFormList = datadictonService.getlistByid("APP_FLATFORM");
        System.out.println("获取app所属平台"+flatFormList);
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("pages", pages);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);
		//二级分类列表和三级分类列表---回显
		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}

		return "backend/applist";
	}
	public List<AppCategory> getCategoryList(String pid){
		List<AppCategory> appList = null;
		appList = appcategoryService.getListByid(Integer.parseInt(pid));
		return appList;
	}
	//将获取的到的数据以json形式返回给前端
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getAppCategoryList(@RequestParam String pid){
		if(pid.equals("")) pid = null;
		String json = JSONArray.toJSONString(getCategoryList(pid));
		return json;
	}
	

	//根据id查询
	@RequestMapping("check")
	public String getAppinfoByid(Model m,@RequestParam("aid") Integer aid,@RequestParam("vid") Integer vid){
		System.out.println("我现在进入得是通过id查找app信息");
		AppInfo appInfo = appInfoService.getinfoByid(aid);
		AppVersion appVersion = null;
		appVersion = appVersionService.getAppVersionById(vid);
		System.out.println("++++++++++"+appVersion.getApkFileName());
		if (appInfo!=null) {
			m.addAttribute("appInfo", appInfo);
			m.addAttribute("appVersion", appVersion);
		}
		return "backend/appcheck";
	}
	//根据id修改app状态信息并取消掉最新版本号
	@RequestMapping(value="checksave",method=RequestMethod.POST)
	public String UpdateAppinfostatus(@RequestParam("status") Integer status,@RequestParam("id") Integer aid){
		System.out.println("=========================修改app状态==============================");
		int num = appInfoService.UpdateAppinfo(status, aid);
		if (num>0) {
			return "redirect:/appinfo/list";
		}
		
		return "backend/appcheck";
	}




	
	
}
