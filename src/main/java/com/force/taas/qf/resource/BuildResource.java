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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.force.taas.qf.PersistenceService;
import com.force.taas.qf.model.BuildTest;
import com.force.taas.qf.model.BuildHistory;

/**
 * 
 * @author bbirman
 *
 */
@Path("/builds")
public class BuildResource implements QualityFoundryResource {
    	   
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@PathParam("{applicationName}")
	public List<BuildTest> doGet(@PathParam("applicationName") String applicationName) throws Exception {
		BuildHistory buildHistory = PersistenceService.getBucket().fetch(applicationName, BuildHistory.class).execute();
		
		List<BuildTest> testRuns = new ArrayList<BuildTest>();
		for(String key : buildHistory.buildTestKeys) {
			BuildTest testRun = PersistenceService.getBucket().fetch(key, BuildTest.class).execute();
			testRun.testResultKeys = null;	// truncate the data
			testRuns.add(testRun);
		}
		return testRuns;
	}
	    
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response doPut(BuildHistory buildHistory) throws Exception {
		PersistenceService.getBucket().store(buildHistory.getKey(), buildHistory).execute();
		return Response.status(204).build();
	}
	   
	//TODO: potentially add method to retrieve list of BuildTests 
    
}
