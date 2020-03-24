package com.briup.demo.bean.ex;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.demo.bean.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	@Transactional
	@Modifying
	@Query("select * from customer where username=?1")
	List<Customer> findByUsername(String username);
	
	
}
