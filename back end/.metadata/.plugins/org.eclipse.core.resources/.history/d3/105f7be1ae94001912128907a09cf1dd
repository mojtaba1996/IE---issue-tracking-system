package ir.asta.training.contacts.services;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;

import ir.asta.training.contacts.entities.CaseEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.wise.core.datamanagement.ActionResult;
@Path("/case")
public interface CaseService {
	@POST
	@Path("/postnewcase")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ActionResult<CaseEntity> postNewCase(String newCaseString) throws JSONException, ParseException;
	
	@DELETE
	@Path("/deletecase/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> deleteCase(@PathParam("id") Long id);
	
	@DELETE
	@Path("/deleteallcases/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Integer> deleteAllCases(@PathParam("password") String password);
	
	@GET
	@Path("/getmycases/{username}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<List<CaseEntity>> getMyCases(@PathParam("username") String username,
														@PathParam("token") String token);
	
	@GET
	@Path("/getmycasestofulfill/{username}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<List<CaseEntity>> getMyCasesToFulfill(@PathParam("username") String username,
			@PathParam("token") String token);
	
	@PUT
	@Path("/updateacase")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> updateACase(CaseEntity acase);
}
