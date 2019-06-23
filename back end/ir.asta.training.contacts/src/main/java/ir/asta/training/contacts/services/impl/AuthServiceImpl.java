package ir.asta.training.contacts.services.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import ir.asta.training.contacts.dao.UserDao;
import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.training.contacts.manager.AuthManager;
import ir.asta.training.contacts.manager.ContactManager;
import ir.asta.training.contacts.services.AuthService;
import ir.asta.wise.core.datamanagement.ActionResult;
import utils.Settings;
import models.Roles;

@Named("authService")
public class AuthServiceImpl implements AuthService {
	
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	Settings settings = new Settings();
	Roles roles = new Roles();
	
	@Inject
	AuthManager manager;
	
	@Inject
	UserDao userDao;
	
	@Override
	public ActionResult<UserEntity> signup(UserEntity user) {
		return manager.signup(user, request);
	}

	@Override
	public ActionResult<UserEntity> login(UserEntity user) {
		return manager.login(user, request);
	}

	@Override
	public ActionResult<Boolean> deleteUser(String username, String token){
		if(!token.equals(request.getSession().getAttribute("token"))){
			ActionResult<Boolean> answer = new ActionResult<Boolean>();
			answer.setSuccess(false);
			answer.setMessage(settings.TOKEN_DOES_NOT_MATCH);
			return answer;
		}
		UserEntity user = userDao.getUserById((Long) request.getSession().getAttribute("user_id"));
		if(!user.getRole().equals(roles.MANAGER)){
			ActionResult<Boolean> answer = new ActionResult<Boolean>();
			answer.setSuccess(false);
			answer.setMessage(settings.ACCESS_DENIED);
			return answer;
		}
		return manager.deleteUser(username);
	}

	@Override
	public ActionResult<Boolean> deleteAllUsers() {
		return manager.clearAllUsers();
	}

	@Override
	public ActionResult<Boolean> isNewUsername(String username) {
		return manager.isNewUsername(username);
	}

	@Override
	public ActionResult<Boolean> logout(UserEntity user) {
		return manager.logout(user, request);
	}

	@Override
	public String validateToken(String token) {
		return manager.validateToken(token, request);
	}

}
