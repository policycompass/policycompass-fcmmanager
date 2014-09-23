package policycompass.fcmmanager.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import policycompass.fcmmanager.controllers.*;

@Path("/v1/fcmmanager")
public class FCMModelService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllModels() throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.getAllModelsList()).build();
		return rb;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModel(@PathParam("id") int id) throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieve(id)).build();
//		rb = Response.ok(id).build();
		return rb;
	}
}
