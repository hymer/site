package com.hymer.contract.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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
import org.springframework.web.servlet.ModelAndView;

import com.hymer.contract.dto.ContractDTO;
import com.hymer.contract.entity.Contract;
import com.hymer.contract.entity.ItemInContract;
import com.hymer.contract.service.ContractService;
import com.hymer.core.BaseContoller;
import com.hymer.core.ServiceException;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.util.JsonUtils;

@Controller
public class ContractController extends BaseContoller {

	@Autowired
	private ContractService contractService;
	
	@RequestMapping(value = "/contract/edit/{id}.html", method = RequestMethod.GET)
	public ModelAndView getEdit(@PathVariable Long id) throws ServiceException {
		Contract contract = null;
		contract = contractService.getById(id);
		ModelAndView mv = new ModelAndView("contract_" + contract.getContractType() + "_edit", "contract", contract);
		return mv;
	}
	
	@RequestMapping(value = "/contract/view/{id}.html", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable Long id) throws ServiceException {
		Contract contract = null;
		contract = contractService.getById(id);
		ModelAndView mv = new ModelAndView("contract_view", "contract", contract);
		return mv;
	}

	@RequestMapping(value = "/contract/add.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, ContractDTO dto)
			throws ServiceException {
		ModelAndView mv = null;
		
		Set<ItemInContract> items = new HashSet<ItemInContract>();
		Map<String, String[]> params = request.getParameterMap();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if (key.startsWith("item_name")) {
				String endfix = key.substring(9);
				ItemInContract item = new ItemInContract();
				item.setItemName(params.get(key)[0]);
				String itemId = request.getParameter("item_id" + endfix);
				String countString = request.getParameter("item_count" + endfix);
				String priceString = request.getParameter("item_price" + endfix);
				String totalPriceString = request.getParameter("item_total_price" + endfix);
				if (StringUtils.hasText(itemId)) {
					item.setId(Long.parseLong(itemId));
				}
				if (StringUtils.hasText(countString)) {
					item.setItemCount(Integer.parseInt(countString));
				}
				if (StringUtils.hasText(countString)) {
					item.setItemPrice(Double.parseDouble(priceString));
				}
				if (StringUtils.hasText(totalPriceString)) {
					item.setItemTotalPrice(Double.parseDouble(totalPriceString));
				}
				item.setItemCode(request.getParameter("item_code" + endfix));
				item.setItemRemark(request.getParameter("item_remark" + endfix));
				item.setItemStandard(request.getParameter("item_standard" + endfix));
				item.setItemUnit(request.getParameter("item_unit" + endfix));
				items.add(item);
			}
		}
		
		Long id = dto.getId();
		if (id == null) {
			contractService.addContract(dto, items);
		} else {
			contractService.updateContract(dto, items);
		}
		mv = getSuccessMV();
		return mv;
	}

	@RequestMapping(value = "/contract/query.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON query(HttpServletRequest request, HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			json = contractService.query(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/contract/delete.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON delete(HttpServletRequest request, HttpServletResponse response) {
		ResponseJSON json = new ResponseJSON();
		String idString = request.getParameter("ids");
		if (StringUtils.hasText(idString)) {
			for (String str : idString.split(",")) {
				Long id = Long.parseLong(str);
				try {
					contractService.deleteById(id);
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
