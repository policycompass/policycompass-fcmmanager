package policycompass.fcmmanager.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONObject;

import policycompass.fcmmanager.controllers.*;
import policycompass.fcmmanager.simulation.pcjfcm;

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
	@Path("/metrics/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModelsByMetrics(@PathParam("id") int id) throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModelsListByMetrics(id)).build();
		return rb;
	}

	@GET
	@Path("/datasets/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModelsByDatasets(@PathParam("id") int id) throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModelsListByDatasets(id)).build();
		return rb;
	}

	@GET
	@Path("/individuals/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFCMModelsByIndividuals(@PathParam("id") int id) throws Exception {
		Response rb = null;
		rb = Response.ok(FCMModels.retrieveFCMModelsListByIndividuals(id)).build();
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

	@POST 
    @Path("/simulation")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response runFCMSimulation(JSONObject fcmmodel) {
        return Response.ok(pcjfcm.runFCMSimulation(fcmmodel)).build();     
//        return Response.ok(fcmmodel).build();     
    }

	@POST 
    @Path("/impactanalysis/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response runImpactAnaylsis(@PathParam("id") int id, JSONObject fcmmodel) {
        return Response.ok(pcjfcm.runImpactAnalysis(id, fcmmodel)).build();     
//        return Response.ok(fcmmodel).build();     
    }

}
