package com.hymer.contract.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hymer.contract.entity.Item;
import com.hymer.contract.service.ContractService;
import com.hymer.contract.service.ItemService;
import com.hymer.core.BaseContoller;
import com.hymer.core.ServiceException;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.util.JsonUtils;

@Controller
public class ItemController extends BaseContoller {

	@Autowired
	private ContractService contractService;
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/item/query.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON query(HttpServletRequest request, HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			json = itemService.query(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/item/{id}.html", method = RequestMethod.GET)
	public @ResponseBody
	Item getItem(@PathVariable Long id) throws ServiceException {
		Item item = itemService.getById(id);
		return item;
	}

	@RequestMapping(value = "/item/edit.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON saveOrUpdateItem(HttpServletRequest request,
			HttpServletResponse response, Item item)
			throws ServiceException {
		response.setContentType("text/html; charset=utf-8");
		ResponseJSON json = new ResponseJSON();
		Long id = item.getId();
		if (id == null) {
			itemService.save(item);
		} else {
			Item entity = itemService.getById(id);
			entity.setItemCode(item.getItemCode());
			entity.setItemName(item.getItemName());
			entity.setItemPrice(item.getItemPrice());
			entity.setItemRemark(item.getItemRemark());
			entity.setItemStandard(item.getItemStandard());
			entity.setItemUnit(item.getItemUnit());
			itemService.update(entity);
		}
		json.setMsg("保存成功！");
		return json;
	}
	
	@RequestMapping(value = "/item/delete.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON deleteItem(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseJSON json = new ResponseJSON();
		String idsString = request.getParameter("ids");
		if (StringUtils.hasText(idsString)) {
			Set<Long> ids = new HashSet<Long>();
			for (String id : idsString.split(",")) {
				ids.add(Long.parseLong(id));
			}
			try {
				itemService.deleteByIds(ids);
				json.setMsg("删除成功!");
			} catch (Exception e) {
				json.setResult(false);
				json.setMsg("删除失败!");
			}
		} else {
			json.setMsg("没有删除任何数据.");
		}
		return json;
	}

	@RequestMapping(value = "/item/queryInContract.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON queryInContract(HttpServletRequest request, HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			json = contractService.queryItem(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
