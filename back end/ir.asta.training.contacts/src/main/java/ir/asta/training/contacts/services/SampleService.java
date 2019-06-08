package ir.asta.training.contacts.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.wise.core.datamanagement.ActionResult;
import models.Book;
import models.Time;

@Path("/myservice")
public interface SampleService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/book")
	public Book bookInfo(Book book);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/time/{year}/{month}/{day}")
	public Time convertTime(@PathParam("year") int year,
							@PathParam("month") Long month,
							@PathParam("day") Long day);
	
	@POST
	@Produces("text/html")
	@Path("/mysubmit")
	public String mysubmit(@FormParam("bookname")String bookname, @FormParam("author")String author);
	
}