package com.briup.demo.mapper.ex;

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

/**
 * 	处理  查询栏目及其包含的文章信息
 * @author 19576
 *
 */
public interface CategoryExMapper {

	/**
	 * 实现查询所有栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryExs();
	
	/**
	 * 通过id查询对应的栏目及其包含的文章信息
	 * @return
	 */
	CategoryEx findCategoryExById(int id);
}
