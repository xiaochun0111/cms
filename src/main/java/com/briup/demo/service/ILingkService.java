package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;
import com.briup.demo.utils.CustomerException;

/**
 * 关于链接的相关操作
 * @author 19576
 *
 */
public interface ILingkService {
	/**
	 * 保存或修改链接信息
	 * @param link
	 * @throws CustomerException
	 */
	void saveOrUpdateLink(Link link) throws CustomerException;
	
	/**
	 * 查询所有链接信息
	 * @param null
	 * @throws CustomerException
	 */
	List<Link> fingAllLinks() throws CustomerException;
	
	/**
	 * 根据id删除对应链接
	 * @param id
	 * @throws CustomerException
	 */
	void deleteLinkById(int id) throws CustomerException;
	
	/**
	 * 根据链接名查询链接
	 * @param name
	 * @throws CustomerException
	 */
	List<Link> finLinksByName(String name) throws CustomerException;
}
