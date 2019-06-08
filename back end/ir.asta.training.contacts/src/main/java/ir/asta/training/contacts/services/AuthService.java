package ir.asta.training.contacts.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.wise.core.datamanagement.ActionResult;

@Path("/auth")
public interface AuthService {
	
	@POST
	@Path("/signup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ActionResult<UserEntity> signup(UserEntity user);
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ActionResult<UserEntity> login(UserEntity user);
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> logout(UserEntity user);
	
	@DELETE
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> deleteUser(@PathParam("id") int id);
	
	@DELETE
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionResult<Boolean> deleteAllUsers();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/isnewusername/{username}")
	public ActionResult<Boolean> isNewUsername(@PathParam("username") String username);
	
}
