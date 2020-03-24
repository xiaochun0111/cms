package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *	与栏目相关的 和前端交互的web层
 * @author 19576
 *
 */
@RestController
@Api(description = "栏目相关接口")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@PostMapping("/addCategory")
	@ApiOperation("新增栏目")
	public Message<String> addCategory(Category category) {
		try {
			categoryService.savaOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误" + e.getMessage());
		}
	}

	@PostMapping("/updateCategory")
	@ApiOperation("修改栏目")
	public Message<String> updateCategory(Category category) {
		categoryService.savaOrUpdateCategory(category);
		return MessageUtil.success();
	}

	@GetMapping("/deleteCategoryById")
	@ApiOperation("根据id删除栏目")
	public Message<String> deleteById(int id) {
		categoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/findAllCategorys")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> selectAllCategorys() {
		return MessageUtil.success(categoryService.findAllCategories());
	}
	
	@GetMapping("/findCategoryById")
	@ApiOperation("根据id查询栏目")
	public Message<Category> selectCategoryById(int id){
		return MessageUtil.success(categoryService.findCategoryById(id));
	}
	
	@GetMapping("/findCategoryExExById")
	@ApiOperation("根据id查询栏目及其包含的查询文章信息")
	public Message<CategoryEx> selectAllCategoryEx(int id){
		return MessageUtil.success(categoryService.findCategoryExById(id));
	}
}
