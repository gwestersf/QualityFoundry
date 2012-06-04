package com.qualityfoundry.api;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

@Path("/results/")
public class TestResultsResource {

    @GET @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{externalPath: .*}")
    public Response getPrimary(
            //@QueryParam("testRunId") String testRunId,
            @PathParam("externalPath") String externalPath,
            @Context HttpHeaders headers,
            InputStream dataIn)
    {
        return handleGet("GET", externalPath, headers,
                dataIn, System.currentTimeMillis());
    }

	private Response handleGet(String method, String externalPath,
			HttpHeaders headers, InputStream dataIn, long currentTimeMillis) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
