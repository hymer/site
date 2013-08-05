package com.hymer.core.security.controller;

import java.util.Enumeration;

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

import com.hymer.core.BaseContoller;
import com.hymer.core.ServiceException;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.security.entity.Preferences;
import com.hymer.core.security.service.ConfigurationService;
import com.hymer.core.util.JsonUtils;

@Controller
public class ConfigurationController extends BaseContoller {

	@Autowired
	private ConfigurationService coreService;
	
	@RequestMapping(value = "/core/config/query.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON queryResource(HttpServletRequest request,
			HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			json = coreService.query(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/core/config/{id}.html", method = RequestMethod.GET)
	public @ResponseBody
	Preferences getPreferences(@PathVariable Long id) throws ServiceException {
		Preferences preference = null;
		preference = coreService.getPreferenceById(id);
		return preference;
	}

	@RequestMapping(value = "/core/config/edit.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON saveOrUpdateResource(HttpServletRequest request,
			HttpServletResponse response, Preferences preferences)
			throws ServiceException {
		response.setContentType("text/html; charset=utf-8");
		ResponseJSON json = new ResponseJSON();
		Long id = preferences.getId();
		if (id == null) {
			coreService.save(preferences);
		} else {
			Preferences entity = coreService.getPreferenceById(id);
			entity.setDisabled(preferences.isDisabled());
			entity.setKey(preferences.getKey());
			entity.setValue(preferences.getValue());
			coreService.update(entity);
		}
		json.setMsg("保存成功！");
		return json;
	}
	
	@Deprecated
//	@RequestMapping(value = "/core/config/delete.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON deleteConfig(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseJSON json = new ResponseJSON();
		String idsString = request.getParameter("id");
		if (StringUtils.hasText(idsString)) {
			Long id = Long.parseLong(idsString);
			try {
				coreService.deletePreferenceById(id);
				json.setMsg("删除成功!");
			} catch (Exception e) {
				json.setResult(false);
				json.setMsg("资源正在使用，无法删除，请先将资源从相应权限中移除!");
			}
		} else {
			json.setMsg("没有删除任何资源.");
		}
		return json;
	}

	@Deprecated
//	@RequestMapping(value = "/core/preferences.html", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Enumeration<String> attrs = request.getParameterNames();
		while (attrs.hasMoreElements()) {
			String attr = attrs.nextElement();
			if (attr.startsWith("key")) {
				String key = request.getParameter(attr);
				String index = attr.substring(3);
				String value = request.getParameter("value" + index);
				String removed = request.getParameter("remove" + index);
				coreService.saveOrUpdatePreferences(key, value, removed);
			}
		}
		coreService.refreshPreferences();
		ModelAndView mv = getSuccessMV();
		mv.addObject(BaseContoller.COMMON_URL_KEY, "admin/preferences.jsp");
		mv.addObject(BaseContoller.COMMON_URL_DISPLAY_KEY, "系统配置");
		return mv;
	}

}
