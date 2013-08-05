package com.hymer.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hymer.core.BaseContoller;
import com.hymer.core.ServiceException;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.util.JsonUtils;
import com.hymer.customer.entity.Customer;
import com.hymer.customer.service.CustomerService;

@Controller
public class CustomerController extends BaseContoller {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customer/{id}.html", method = RequestMethod.GET)
	public @ResponseBody
	Customer get(@PathVariable Long id) throws ServiceException {
		Customer customer = null;
		customer = customerService.getById(id);
		return customer;
	}

	@RequestMapping(value = "/customer/edit.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Customer customer)
			throws ServiceException {
		response.setContentType("text/html; charset=utf-8");
		ResponseJSON json = new ResponseJSON();
		Long id = customer.getId();
		if (id == null) {
			customerService.addCustomer(customer);
		} else {
			customerService.updateCustomer(customer);
		}
		json.setMsg("保存成功！");
		return json;
	}

	@RequestMapping(value = "/customer/query.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON query(HttpServletRequest request, HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			json = customerService.query(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/customer/delete.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON delete(HttpServletRequest request, HttpServletResponse response) {
		ResponseJSON json = new ResponseJSON();
		String idString = request.getParameter("ids");
		if (StringUtils.hasText(idString)) {
			for (String str : idString.split(",")) {
				Long id = Long.parseLong(str);
				try {
					customerService.deleteById(id);
					json.setMsg("删除成功!");
				} catch (Exception e) {
					json.setResult(false);
					json.setMsg("该信息已被占用，无法删除!");
				}
			}
		} else {
			json.setMsg("没有删除任何数据.");
		}
		return json;
	}

}
