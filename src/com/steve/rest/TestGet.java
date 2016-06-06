package com.steve.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/testget")
public class TestGet {
	@POST
	public String getMessage() {
		return "Hello World.";
	}
}
