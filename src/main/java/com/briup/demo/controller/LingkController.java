package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Link;
import com.briup.demo.service.ILingkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与链接相关的 和前端交互的web层
 * 
 * @author 19576
 *
 */
@RestController
@Api(description = "链接相关接口")
public class LingkController {
	@Autowired
	private ILingkService lingkService;

	@PostMapping("/addLink")
	@ApiOperation("新增链接")
	public Message<String> addLink(Link link) {
		try {
			lingkService.saveOrUpdateLink(link);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误" + e.getMessage());
		}
	}

	@PostMapping("/updateLink")
	@ApiOperation("修改链接")
	public Message<String> updateLink(Link link) {
		lingkService.saveOrUpdateLink(link);
		return MessageUtil.success();
	}

	@GetMapping("/fingLinks")
	@ApiOperation("查询所有链接")
	public Message<List<Link>> selectLinks() {
		//List<Link> list = lingkService.fingAllLinks();
		return MessageUtil.success(lingkService.fingAllLinks());
	}
	
	@GetMapping("/deleteLinkById")
	@ApiOperation("根据id删除链接")
	public Message<String> deleteLinkById(int id){
		lingkService.deleteLinkById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/findLinkByName")
	@ApiOperation("根据链接名查询链接")
	public Message<List<Link>> selectLingByName(String name){
		//List<Link> list = lingkService.finLinksByName(name);
		return MessageUtil.success(lingkService.finLinksByName(name));
	}
}
