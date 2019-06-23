package ir.asta.training.contacts.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import org.springframework.transaction.annotation.Transactional;
import ir.asta.training.contacts.dao.CaseDao;
import ir.asta.training.contacts.dao.UserDao;
import ir.asta.training.contacts.entities.CaseEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.wise.core.datamanagement.ActionResult;
import models.Roles;
import models.Status;
import utils.Settings;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

@Named("caseManager")
public class CaseManager {
	
	@Inject
	CaseDao dao;
	@Inject
	UserDao userDao;
	
	Status status = new Status();
	Settings settings = new Settings();
	
	@Transactional
	public ActionResult<CaseEntity> postNewCase(String newCaseString, @Context HttpServletRequest request) throws JSONException, ParseException{
		ActionResult<CaseEntity> answer = new ActionResult<CaseEntity>();
		CaseEntity newCase = this.caseEntityFactory(newCaseString);
		if(!request.getSession().getAttribute("token").equals(newCase.getToken())){
			answer.setSuccess(false);
			answer.setMessage(settings.TOKEN_DOES_NOT_MATCH);
			return answer;
		}
		if(dao.save(newCase)){
			answer.setData(newCase);
			answer.setSuccess(true);
			answer.setMessage("مورد با موفقیت ثبت شد");
		}
		return answer;
	}
	
	@Transactional
	public CaseEntity caseEntityFactory(String newCaseString) throws JSONException, ParseException{
		JSONObject newCaseObj = new JSONObject(new JSONTokener(newCaseString));
		CaseEntity newCase = new CaseEntity();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		newCase.setContent(newCaseObj.getString("content"));
		newCase.setDate(formatter.parse(newCaseObj.getString("date")));
		newCase.setDescription(newCaseObj.getString("description"));
		newCase.setPoster_user(userDao.getUserByUsername(newCaseObj.getString("poster_username")));
		newCase.setResponsible_user(userDao.getUserByUsername(newCaseObj.getString("responsible_user")));
		newCase.setStatus(Status.OPEN);
		newCase.setTopic(newCaseObj.getString("topic"));
		newCase.setToken(newCaseObj.getString("token"));
		return newCase;
	}
	
	@Transactional
	public ActionResult<Boolean> deleteCase(Long id) {
		ActionResult<Boolean> answer = new ActionResult<Boolean>();
		boolean result = dao.deleteCaseById(id);
		if (result){
			answer.setData(true);
			answer.setSuccess(true);
			answer.setMessage("با موفقیت حذف شد");
		}
		else{
			answer.setData(false);
			answer.setSuccess(false);
			answer.setMessage("ایدی یافت نشد");
		}
		return answer;
	}
	
	@Transactional
	public ActionResult<Integer> deleteAllCases(String password) {
		ActionResult<Integer> answer = new ActionResult<Integer>();
		if(!password.equals(settings.password)){
			answer.setSuccess(false);
			answer.setMessage("رمز عبور صحیح نمیباشد");
			return answer;
		}
		int result = dao.deleteAllCases();
		answer.setData(result);
		answer.setSuccess(true);
		answer.setMessage("با موفقیت انجام شد");
		return answer;
	}
	
	@Transactional
	public ActionResult<List<CaseEntity>> getMyCases(String username){
		ActionResult<List<CaseEntity>> answer = new ActionResult<List<CaseEntity>>();
		List<CaseEntity> result = dao.getMyCases(username);
		result = filterCases(result);
		answer.setData(result);
		answer.setSuccess(true);
		return answer;
	}
	
	public ActionResult<List<CaseEntity>> getMyCasesToFulfill(String username){
		UserEntity user = userDao.getUserByUsername(username);
		if (user.getRole()==Roles.MANAGER){username = "";}
		ActionResult<List<CaseEntity>> answer = new ActionResult<List<CaseEntity>>();
		List<CaseEntity> result = dao.getMyCasesToFulfill(username);
		result = filterCases(result);
		answer.setData(result);
		answer.setSuccess(true);
		return answer;
	}

	@Transactional
	public ActionResult<Boolean> updateCase(CaseEntity acase){
		ActionResult<Boolean> answer = new ActionResult<Boolean>();
		UserEntity newUser = userDao.getUserByUsername(acase.getResponsible_user().getUsername());
		acase.setResponsible_user(newUser);
		if(dao.updateCase(acase)){
			answer.setSuccess(true);
			answer.setMessage("عملیات با موفقیت انجام شد");
			answer.setData(true);
		}else{
			answer.setData(false);
			answer.setMessage("خطار در هنگام به روز رسانی مورد");
			answer.setSuccess(false);
		}
		return answer;
	}

	public ActionResult<List<CaseEntity>> getAllCases(){
		ActionResult<List<CaseEntity>> answer = new ActionResult<List<CaseEntity>>();
		List<CaseEntity> result = dao.getAllCases();
		answer.setData(result);
		answer.setSuccess(true);
		return answer;
	}
	
	public List<CaseEntity> filterCases(List<CaseEntity> cases){
		List<CaseEntity> newCases = new ArrayList<CaseEntity>();
		for(CaseEntity acase : cases){
			UserEntity responsibleUser = new UserEntity();
			responsibleUser.setConfirmed(acase.getResponsible_user().isConfirmed());
			responsibleUser.setFirstname(acase.getResponsible_user().getFirstname());
			responsibleUser.setLastname(acase.getResponsible_user().getLastname());
			responsibleUser.setRole(acase.getResponsible_user().getRole());
			responsibleUser.setUsername(acase.getResponsible_user().getUsername());
			UserEntity posterUser = new UserEntity();
			posterUser.setConfirmed(acase.getPoster_user().isConfirmed());
			posterUser.setFirstname(acase.getPoster_user().getFirstname());
			posterUser.setLastname(acase.getPoster_user().getLastname());
			posterUser.setRole(acase.getPoster_user().getRole());
			posterUser.setUsername(acase.getPoster_user().getUsername());
			CaseEntity newCase = new CaseEntity();
			newCase.setContent(acase.getContent());
			newCase.setDate(acase.getDate());
			newCase.setDescription(acase.getDescription());
			newCase.setStatus(acase.getStatus());
			newCase.setTopic(acase.getTopic());
			newCase.setResponsible_user(responsibleUser);
			newCase.setPoster_user(posterUser);
			newCases.add(newCase);
		}
		return newCases;
	}
}
