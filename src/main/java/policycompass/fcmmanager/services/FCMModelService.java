package policycompass.fcmmanager.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import policycompass.fcmmanager.controllers.*;
import policycompass.fcmmanager.models.*;

@Path("/fcmmanager")
public class FCMModelService {

/*	@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllModels() throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModelList()).build();
		return rb;
	}
*/
	
	@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FCMModel> retrieveAllModels() throws Exception {
		return FCMModels.retrieveFCMModelList();
	}

	
	@GET
	@Path("/models/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModel(@PathParam("id") int id) throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModel(id)).build();
		return rb;
	}

    @POST @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public Response createPodcasts(ArrayList<FCMModel> fcmmodels) {
        for(FCMModel fcmmodel : fcmmodels){
        	FCMModels.createFCMModel(fcmmodel);            
        }
 
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
