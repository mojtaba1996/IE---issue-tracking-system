package ir.asta.training.contacts.services.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.manager.ContactManager;
import ir.asta.training.contacts.services.ContactService;
import ir.asta.training.contacts.services.SampleService;
import ir.asta.wise.core.datamanagement.ActionResult;
import models.Book;
import models.Time;

@Named("sampleService")
public class SampleServiceImpl implements SampleService {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	@Override
	public Book bookInfo(Book book) {
		if (request.getSession().getAttribute("requestCount") == null){
			request.getSession().setAttribute("requestCount", 1);			
		}
		else{
			int temp_count1 = (Integer)request.getSession().getAttribute("requestCount");
			request.getSession().setAttribute("requestCount", temp_count1 + 1);
		}
		if (request.getServletContext().getAttribute("requestCount") == null){
			request.getServletContext().setAttribute("requestCount", 1);
		}
		else{
			int temp_count2 = (Integer)request.getServletContext().getAttribute("requestCount");
			request.getServletContext().setAttribute("requestCount", temp_count2 + 1);
		}
		
		
		String bookname = book.getBookname() + ", a novel";
		String author = "Mr " + book.getAuthor();
		String comment = bookname + " written by " + author + " is a great book";
		return new Book(bookname, author, comment);
	}

	@Override
	public Time convertTime(int year, Long month, Long day) {
		if (request.getSession().getAttribute("requestCount") == null){
			request.getSession().setAttribute("requestCount", 1);			
		}
		else{
			int temp_count = (Integer)request.getSession().getAttribute("requestCount");
			request.getSession().setAttribute("requestCount", temp_count + 1);
		}
		if (request.getServletContext().getAttribute("requestCount") == null){
			request.getServletContext().setAttribute("requestCount", 1);
		}
		else{
			int temp_count2 = (Integer)request.getServletContext().getAttribute("requestCount");
			request.getServletContext().setAttribute("requestCount", temp_count2 + 1);
		}
		return new Time(1398, 02, 20);
	}

	@Override
	public String mysubmit(String bookname, String author) {
		String out = "";
		out += "<HTML>";
		out += "<BODY>";
		out += "Book Name: " + bookname + "<br>";
		out += "Author: " + author + "<br>";
		out += "Session Requests: " + request.getSession().getAttribute("requestCount") + "<br>";
		out += "Application Request: " + request.getServletContext().getAttribute("requestCount") + "<br>";
		out += "</BODY>";
		out += "</HTML>";
		return out;
	}

}
