package policycompass.fcmmanager.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONObject;

import policycompass.fcmmanager.controllers.*;

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

	@GET
	@Path("/modelsid")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModelID() throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.getFCMModelID()).build();
		return rb;
	}

    @POST 
    @Path("/models")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createFCMModel(JSONObject fcmmodel) {
        return Response.ok(FCMModels.createFCMModel(fcmmodel)).build();     
//        return Response.ok(fcmmodel).build();     
    }

    @PUT 
    @Path("/models/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateFCMModel(@PathParam("id") int id, JSONObject fcmmodel) {
    	return Response.ok(FCMModels.updateFCMModel(id, fcmmodel)).build();
//    	FCMModels.updateFCMModel(id, fcmmodel);
//        return Response.ok(fcmmodel).build();     
    }


    @DELETE 
    @Path("/models/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteFCMModel(@PathParam("id") int id) {
    	FCMModels.deleteFCMModel(id);            
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

	@GET
	@Path("/activators")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllActivators() throws Exception {
		Response rb = null;
		rb = Response.ok(FCMConceptActivators.retrieveFCMActivatorList()).build();
		return rb;
	}

}
