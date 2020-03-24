package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 关于栏目的相关操作
 * @author 19576
 *
 */
public interface ICategoryService {
	
	/**
	 * 查询所有的栏目
	 */
	List<Category> findAllCategories() throws CustomerException;
	
	/**
	 * 添加或修改栏目信息
	 */
	void savaOrUpdateCategory(Category category) throws CustomerException;
	
	/**
	 * 根据id删除栏目信息
	 */
	void deleteCategoryById(int id) throws CustomerException;
	
	/**
	 * 根据id查找指定的栏目信息
	 */
	Category findCategoryById(int id) throws CustomerException;
	
	/**
	 *  查询栏目信息并级联查询包含的文章信息
	 * @return
	 * @throws CustomerException
	 */
	List<CategoryEx> findAllCategoryEx() throws CustomerException;
	
	/**
	 * 根据id查询栏目信息并级联查询包含的文章信息
	 * @param id
	 * @return 
	 * @throws CustomerException
	 */
	CategoryEx findCategoryExById(int id) throws CustomerException;
}
