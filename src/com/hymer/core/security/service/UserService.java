package com.hymer.core.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.QueryObject;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.security.MD5Utils;
import com.hymer.core.security.dao.CompanyInfoDAO;
import com.hymer.core.security.dao.RoleDAO;
import com.hymer.core.security.dao.UserDAO;
import com.hymer.core.security.dao.UserInfoDAO;
import com.hymer.core.security.dto.MenuDTO;
import com.hymer.core.security.dto.UserDTO;
import com.hymer.core.security.entity.CompanyInfo;
import com.hymer.core.security.entity.Menu;
import com.hymer.core.security.entity.Role;
import com.hymer.core.security.entity.User;
import com.hymer.core.security.entity.UserInfo;

@Service
public class UserService extends CommonService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private CompanyInfoDAO companyInfoDAO;

	public User getByUserName(String userName) {
		List<User> users = userDAO.getByProperty("userName", userName);
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	public ResponseJSON query(QueryObject queryObject) {
		List<Condition> realConditions = new ArrayList<Condition>();
		Condition superFilter = new Condition("role.code", Role.SUPER_ROLE_FLAG);
		superFilter.setOperator(Condition.NOT_EQ);
		realConditions.add(superFilter);
		for (Condition condition : queryObject.getConditions()) {
			if (condition.getValue() == null
					|| !StringUtils.hasText(condition.getValue().toString())) {
				continue;
			}
			if (condition.getKey().equals("userName")) {
				condition.setOperator(Condition.LIKE);
				realConditions.add(condition);
			} else if (condition.getKey().equals("roleId")) {
				Long roleId = Long.parseLong(condition.getValue().toString());
				Condition roleCondition = new Condition("role.id", roleId);
				roleCondition.setValueType(Long.class);
				realConditions.add(roleCondition);
			}
		}
		queryObject.setConditions(realConditions);
		ResponseJSON json = userDAO.getAll(queryObject);
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (User user : (List<User>) json.getData().get("data")) {
			UserDTO dto = new UserDTO(user);
			dtos.add(dto);
		}
		json.put("data", dtos);
		return json;
	}

	public User getUserById(Long id) {
		return userDAO.getById(id);
	}

	public void update(Long id, UserDTO dto) {
		User entity = getUserById(id);
		entity.setDisabled(dto.isDisabled());
		if (!entity.getRole().getId().equals(dto.getRoleId())) {
			Role role = roleDAO.getById(dto.getRoleId());
			entity.setRole(role);
		}
		update(entity);
	}

	public UserDTO getUserDTOById(Long id) {
		User entity = getUserById(id);
		UserDTO dto = new UserDTO(entity);
		return dto;
	}

	public void updatePassword(Long id, String password1, String password2) {
		User user = userDAO.getById(id);
		if (password1 != null && password1.equals(password2)) {
			user.setPassword(MD5Utils.encode(password1));
			update(user);
		} else {
			throw new RuntimeException("两次输入密码不一致!");
		}

	}

	public void addCompanyUser(User user, UserInfo userInfo) {
		userInfoDAO.save(userInfo);
		user.setInfo(userInfo);
		userInfo.setUser(user);
		userDAO.save(user);
	}

	public void addPersonalUser(User user, UserInfo userInfo) {
		userInfoDAO.save(userInfo);
		user.setInfo(userInfo);
		userInfo.setUser(user);
		userDAO.save(user);
	}

	public void addInitData() {
		User admin = new User();
		admin.setUserName("admin");
		admin.setPassword(MD5Utils.encode("admin"));
		Role role = new Role();
		role.setCode(Role.SUPER_ROLE_FLAG);
		admin.setRole(role);
		roleDAO.save(role);
		userDAO.save(admin);
	}

	public void updateInfo(CompanyInfo info, User user) {
		if (info.getId() == null) {
			companyInfoDAO.save(info);
			user.setCompanyInfo(info);
			userDAO.update(user);
		} else {
			companyInfoDAO.update(info);
			user.setCompanyInfo(info);
		}
	}

	public List<MenuDTO> queryUserMenus(Long userId, Long pid) {
		List<MenuDTO> dtos = new ArrayList<MenuDTO>();
		User user = userDAO.getById(userId);
		Set<Menu> menus = user.getRole().getMenus();
		for (Menu menu : menus) {
			if ((null == pid && 1 == menu.getLevel())
					|| (null != pid && null != menu.getParent() && pid.equals(menu.getParent().getId()))) {
				MenuDTO dto = new MenuDTO();
				dto.fromEntity(menu);
				dtos.add(dto);
			}
		}
		return dtos;
	}

}
