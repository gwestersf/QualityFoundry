package com.force.taas.qf.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.cap.UnresolvedConflictException;
import com.basho.riak.client.convert.ConversionException;
import com.force.taas.qf.Globals;
import com.force.taas.qf.PersistenceService;
import com.force.taas.qf.model.ApplicationInventory;

@Path("/application")
public class ApplicationResource implements QualityFoundryResource {

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public ApplicationInventory doGet() throws Exception {
		//JSONConverter jsonifier = new JSONConverter(ApplicationInventory.class, Globals.BUCKET_NAME);
		return PersistenceService.getBucket().fetch(Globals.ROOT_KEY, ApplicationInventory.class).execute();
	}
}
