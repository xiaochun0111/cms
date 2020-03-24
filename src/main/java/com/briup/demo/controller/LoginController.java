package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.ex.CustomerDao;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;

/**
 * 	登录验证
 * @author 19576
 *
 */
@RestController
@Api(description = "登录模块")
public class LoginController {
	
	@Autowired
	private CustomerDao customerDao;

	public String login(String username,String password) {
		if (username == null || password == null) {
			throw new CustomerException(StatusCodeUtil.NOFOUND_CODE, "用户名或者密码为空");
		}
		List<Customer> list = customerDao.findByUsername(username);
		if (!password.equals(list.get(0).getPassword())) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "密码错误");
		}
		return "登录成功";
	}
}
