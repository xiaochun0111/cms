package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 关于栏目的service
 * 
 * @author 19576
 *
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryExMapper categoryExMapper;

	@Override
	public List<Category> findAllCategories() throws CustomerException {
		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	public void savaOrUpdateCategory(Category category) throws CustomerException {

		//	参数为引用类型，要做判空处理
		if (category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//	判断category对象的id是否为空，如果为空则新增链接，如果不为空则修改链接
		if (category.getId() == null) {
			//	判断栏目是否已经存在
			CategoryExample example = new CategoryExample();
			example.createCriteria().andCodeEqualTo(category.getCode());
			example.or().andNameEqualTo(category.getName());
			List<Category> list = categoryMapper.selectByExample(example);
			if (!list.isEmpty()) {
				throw new CustomerException(StatusCodeUtil.REPEAT_CODE, "栏目已存在");
			}
			categoryMapper.insert(category);
		} else {
			categoryMapper.updateByPrimaryKey(category);
		}

	}

	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		//	删除栏目信息的同时删除article信息
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);

		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {	
		return categoryExMapper.findAllCategoryExs();
	}

	@Override
	public CategoryEx findCategoryExById(int id) throws CustomerException {
		return categoryExMapper.findCategoryExById(id);
	}
}
