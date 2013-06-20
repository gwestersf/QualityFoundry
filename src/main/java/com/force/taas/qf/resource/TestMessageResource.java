// Copyright (c) 2012 Gregory D. Wester
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.force.taas.qf.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.force.taas.qf.PersistenceService;
import com.force.taas.qf.model.TestMessage;


/**
 * 
 * @author bbirman
 *
 */
@Path("/testmessages")
public class TestMessageResource implements QualityFoundryResource {
    	   
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@PathParam("{testMessageKey}")
	public TestMessage doGet(@PathParam("testMessageKey") String testMessageKey) throws Exception {
		return PersistenceService.getBucket().fetch(testMessageKey, TestMessage.class).execute();
	}
	
	    
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response doPut(TestMessage testMessage) throws Exception {
		PersistenceService.getBucket().store(testMessage.getKey(), testMessage).execute();
		return Response.status(204).build();
	}
}
