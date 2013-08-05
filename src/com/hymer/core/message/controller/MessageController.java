package com.hymer.core.message.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hymer.core.BaseContoller;
import com.hymer.core.ServiceException;
import com.hymer.core.message.service.MessageService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.security.entity.User;
import com.hymer.core.util.JsonUtils;

@Controller
public class MessageController extends BaseContoller {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/message/query.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON queryResource(HttpServletRequest request,
			HttpServletResponse response) {
		String queryString = request.getParameter("query");
		ResponseJSON json = null;
		try {
			QueryObject queryObject = null;
			queryObject = JsonUtils.fromJson(queryString, QueryObject.class);
			User user = (User) request.getSession().getAttribute("user");
			queryObject.getConditions().add(
					new Condition("toUser.id", user.getId()));
			json = messageService.query(queryObject);
			log.info("json = " + JsonUtils.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/message/setreaded/{id}.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON setReaded(@PathVariable Long id) throws ServiceException {
		ResponseJSON json = null;
		messageService.updateMessage2Readed(id);
		json = new ResponseJSON();
		return json;
	}

	@RequestMapping(value = "/message/delete/{id}.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON deleteMessage(@PathVariable Long id) throws ServiceException {
		ResponseJSON json = null;
		messageService.deleteById(id);
		json = new ResponseJSON();
		return json;
	}

	@RequestMapping(value = "/message/setreadmore.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON setReadMore(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		ResponseJSON json = null;
		String idsString = request.getParameter("ids");
		List<Long> ids = new ArrayList<Long>();
		for (String s : idsString.split(",")) {
			ids.add(Long.parseLong(s));
		}
		messageService.updateMessages2Readed(ids);
		json = new ResponseJSON();
		return json;
	}

	@RequestMapping(value = "/message/deletemore.html", method = RequestMethod.POST)
	public @ResponseBody
	ResponseJSON deleteMore(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		ResponseJSON json = null;
		String idsString = request.getParameter("ids");
		List<Long> ids = new ArrayList<Long>();
		for (String s : idsString.split(",")) {
			ids.add(Long.parseLong(s));
		}
		messageService.deleteByIds(ids);
		json = new ResponseJSON();
		return json;
	}

	@RequestMapping(value = "/showajaxerror.ajax", method = RequestMethod.GET)
	public @ResponseBody
	ResponseJSON showAjaxError(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		ResponseJSON json = new ResponseJSON();
		json.setError(true);
		json.setResult(false);
		json.setMsg(request.getParameter("error_msg"));
		return json;
	}

}
