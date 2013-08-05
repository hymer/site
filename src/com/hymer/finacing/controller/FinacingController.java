package com.hymer.finacing.controller;

import java.util.List;

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

import com.hymer.contract.entity.Contract;
import com.hymer.core.BaseContoller;
import com.hymer.core.ServiceException;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.util.JsonUtils;
import com.hymer.finacing.dto.PaymentDTO;
import com.hymer.finacing.entity.Finacing;
import com.hymer.finacing.entity.Payment;
import com.hymer.finacing.service.FinacingService;

@Controller
public class FinacingController extends BaseContoller {

	@Autowired
	private FinacingService finacingService;

	@RequestMapping(value = "/finacing/query.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON query(HttpServletRequest request, HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			json = finacingService.query(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/finacing/manager/{id}.html", method = RequestMethod.GET)
	public ModelAndView manager(@PathVariable Long id) {
		ModelAndView mv = null;
		Finacing finacing = finacingService.viewFinacing(id);
		if (Contract.TYPE_PURCHASE.equals(finacing.getFinacingType())) {
			mv = new ModelAndView("finacing_purchase_manager", "finacing", finacing);
		} else if (Contract.TYPE_SALES.equals(finacing.getFinacingType())) {
			mv = new ModelAndView("finacing_sales_manager", "finacing", finacing);
		}
		List<Contract> contracts = finacingService.getContractByFinacing(finacing);
		mv.addObject("contracts", contracts);
		return mv;
	}
	
	@RequestMapping(value = "/finacing/payment/edit.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON saveOrUpdatePayment(HttpServletRequest request,
			HttpServletResponse response, PaymentDTO payment)
			throws ServiceException {
		response.setContentType("text/html; charset=utf-8");
		ResponseJSON json = new ResponseJSON();
		finacingService.saveOrUpdatePayment(payment);
		json.setMsg("保存成功！");
		return json;
	}

	@RequestMapping(value = "/finacing/payment/delete.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON deletePayment(HttpServletRequest request,
			HttpServletResponse response, Payment payment)
					throws ServiceException {
		ResponseJSON json = new ResponseJSON();
		String idString = request.getParameter("id");
		if (StringUtils.hasText(idString)) {
			Long id = Long.parseLong(idString);
			try {
				finacingService.deletePaymentById(id);
				json.setMsg("删除成功!");
			} catch (Exception e) {
				e.printStackTrace();
				json.setResult(false);
				json.setMsg("该资源已被占用，无法删除!");
			}
		} else {
			json.setMsg("没有删除任何数据.");
		}
		return json;
	}

	@RequestMapping(value = "/finacing/delete.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON delete(HttpServletRequest request, HttpServletResponse response) {
		ResponseJSON json = new ResponseJSON();
		String idString = request.getParameter("id");
		if (StringUtils.hasText(idString)) {
			Long id = Long.parseLong(idString);
			try {
				finacingService.deleteById(id);
				json.setMsg("删除成功!");
			} catch (Exception e) {
				json.setResult(false);
				json.setMsg("该资源已被占用，无法删除!");
			}
		} else {
			json.setMsg("没有删除任何数据.");
		}
		return json;
	}

}
