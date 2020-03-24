package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.utils.CustomerException;

/**
 * 文章相关内容的service接口
 * 
 * @author 19576
 *
 */
public interface IArticleService {

	/**
	 * 新增或修改文章
	 * @param article
	 * @throws CustomerException
	 */
	void saveOrUpdateArticle(Article article) throws CustomerException;

	/**
	 * 根据id删除文章
	 * @param id
	 * @throws CustomerException
	 */
	void deleteArticleById(int id) throws CustomerException;
	
	/**
	 * 查询文章
	 * @param keyStr  表示搜索框
	 * @param condition 表示栏目框
	 * @return
	 * @throws CustomerException
	 */
	List<Article> findArticleByCondition(String keyStr,String condition) 
			throws CustomerException;
	
	/**
	 * 根据id查询文章
	 */
	Article findArticleById(int id) throws CustomerException;
}
