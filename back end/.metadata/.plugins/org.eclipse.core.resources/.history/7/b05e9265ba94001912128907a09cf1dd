package ir.asta.training.contacts.services;

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

import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.wise.core.datamanagement.ActionResult;

@Path("/users")
public interface UserService {
	
	@GET
	@Path("/getresponsibleusers/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<List<UserEntity>> getResponsibleUsers(@PathParam("token") String token);
	
	@POST
	@Path("/addconfirmeduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> addConfirmedUser(UserEntity user);
	
	@GET
	@Path("/showmeallusers/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<List<UserEntity>> showMeAllUsers(@PathParam("password") String password);
	
	@GET
	@Path("/getuserbyusername/{username}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<UserEntity> getUserByUsername(@PathParam("username") String username,
														@PathParam("token") String token);
	
	@PUT
	@Path("/editprofile")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> editProfile(UserEntity user);
}
