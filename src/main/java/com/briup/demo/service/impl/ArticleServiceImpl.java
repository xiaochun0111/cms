package com.briup.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 实现文章管理相关的逻辑类
 * 
 * @author 19576
 *
 */
@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public void saveOrUpdateArticle(Article article) throws CustomerException {
		if (article == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if (article.getId() == null) {
			//	需要额外添加两条数据
			article.setPublishdate(new Date());
			article.setClicktimes(0);
			articleMapper.insert(article);
		} else {
//			article.setPublishdate(new Date()); 如果作者修改文章，则修改日期
			articleMapper.updateByPrimaryKey(article);
		}
	}

	@Override
	public void deleteArticleById(int id) throws CustomerException {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Article> findArticleByCondition(String keyStr, String condition) throws CustomerException {
		/*
		 * 分三种情况 1.没有添加任何条件。则查询所有文章 2.没有指定栏目仅指定关键字，则根据关键字查询满足条件的所有文章
		 * 3.指定栏目，没有指定关键字，则根据栏目查询满足条件的所有文章 4.指定栏目同时指定查询的关键字，则根据栏目和关键字查询满足条件的文章
		 */
		keyStr = keyStr == null ? "" : keyStr.trim();
		condition = condition == null ? "" : condition.trim();
		ArticleExample example = new ArticleExample();
		if ("".equals(keyStr) && "".equals(condition)) {
			// 情况1
			return articleMapper.selectByExample(example);
		} else if (!"".equals(keyStr) && "".equals(condition)) {
			// 情况2
			example.createCriteria().andTitleLike("%" + keyStr + "%");
			return articleMapper.selectByExample(example);
		} else if (!"".equals(condition) && "".equals(keyStr)) {
			// 情况3
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> categories = categoryMapper.selectByExample(categoryExample);
			if (categories.size() > 0) {
				// 根据栏目信息，找到里面所有的文章
				example.createCriteria().andCategoryIdEqualTo(categories.get(0).getId());
			} else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有指定的搜索栏目");
			}
			return articleMapper.selectByExample(example);
		} else {
			// 情况4
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> categories = categoryMapper.selectByExample(categoryExample);

			// and的方式拼接条件
			example.createCriteria().andCategoryIdEqualTo(categories.get(0).getId()).andTitleLike("%" + keyStr + "%");
			// or的方式拼接条件
			// example.or().andTitleLike(keyStr);

			return articleMapper.selectByExample(example);
		}
	}

	@Override
	public Article findArticleById(int id) throws CustomerException {
		Article article = articleMapper.selectByPrimaryKey(id);

		//	添加点击次数
		Integer clickTime = article.getClicktimes() == null ? 0 : article.getClicktimes();
		article.setClicktimes(clickTime+1);
		
		this.saveOrUpdateArticle(article);
		return article;
	}

}
