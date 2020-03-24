package com.briup.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILingkService;
import com.briup.demo.utils.CustomerException;

@Service
public class indexResultServiceImpl implements IIndexResultService {

	//	关联超链接的service接口
	@Autowired
	private ILingkService linkService;
	@Autowired
	private ICategoryService categoryService;
	
	@Override
	public IndexResult findAllResult() throws CustomerException {
		
		IndexResult indexResult = new IndexResult();
		//	设置所有的超链接信息
		indexResult.setLinks(linkService.fingAllLinks());
		//	设置所有的栏目及其包含的所有文章信息
		indexResult.setCategoryExs(categoryService.findAllCategoryEx());
		
		return indexResult;
	}

}
