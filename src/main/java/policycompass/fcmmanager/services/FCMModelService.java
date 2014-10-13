package policycompass.fcmmanager.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import policycompass.fcmmanager.controllers.*;
import policycompass.fcmmanager.models.*;

@Path("/fcmmanager")
public class FCMModelService {

	@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllModels() throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModelList()).build();
		return rb;
	}
	
	@GET
	@Path("/models/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModel(@PathParam("id") int id) throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModel(id)).build();
		return rb;
	}

    @POST 
    @Path("/models")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createFCMModel(FCMModel fcmmodels) {
//        for(FCMModel fcmmodel : fcmmodels){
  //      	FCMModels.createFCMModel(fcmmodel);            
    //    }
 
        return Response.status(204).build();     
    }

    @PUT 
    @Path("/models/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateFCMModel(FCMModel fcmmodels) {
//        for(FCMModel fcmmodel : fcmmodels){
  //      	FCMModels.createFCMModel(fcmmodel);            
    //    }
 
        return Response.status(204).build();     
    }

    @DELETE 
    @Path("/models/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteFCMModel(@PathParam("id") int id) {
//        for(FCMModel fcmmodel : fcmmodels){
  //      	FCMModels.createFCMModel(fcmmodel);            
    //    }
 
        return Response.status(204).build();     
    }

	@GET
	@Path("/loaddata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response LoadDataService() throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.LoadData()).build();
		return rb;
	}

}
