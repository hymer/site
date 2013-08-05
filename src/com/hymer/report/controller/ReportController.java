package com.hymer.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hymer.core.BaseContoller;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.util.JsonUtils;
import com.hymer.report.service.ReportService;

@Controller
public class ReportController extends BaseContoller {
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/report/{year}/{month}.ajax", method = RequestMethod.GET)
	public @ResponseBody
	ResponseJSON monthReport(@PathVariable int year, @PathVariable int month) {
		ResponseJSON json = null;
		try {
			json = reportService.month(year, month);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/report/{year}.ajax", method = RequestMethod.GET)
	public @ResponseBody
	ResponseJSON yearReport(@PathVariable int year) {
		ResponseJSON json = null;
		try {
			json = reportService.year(year);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/report/lastoneyear.ajax", method = RequestMethod.GET)
	public @ResponseBody
	ResponseJSON lastOneYearReport() {
		ResponseJSON json = null;
		try {
			json = reportService.lastOneYear();
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/report/last15daysmoney.ajax", method = RequestMethod.GET)
	public @ResponseBody
	ResponseJSON last15DaysMoney() {
		ResponseJSON json = null;
		try {
			json = reportService.last15DaysMoney();
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/report/last15dayscount.ajax", method = RequestMethod.GET)
	public @ResponseBody
	ResponseJSON last15DaysCount() {
		ResponseJSON json = null;
		try {
			json = reportService.last15DaysCount();
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
