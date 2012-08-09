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
import com.force.taas.qf.model.BuildTest;
import com.force.taas.qf.model.TestResult;
import com.force.taas.qf.model.TestInventory;
import com.force.taas.qf.model.BuildHistory;

/**
 * 
 * @author bbirman
 *
 */
@Path("/builds")
public class BuildResource {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@PathParam("{buildHistoryKey}/{buildKey}")
	public Object[] doGetBuild(@PathParam("buildHistoryKey") String buildHistoryKey, 
			@PathParam("buildKey") String buildKey) throws Exception {

		BuildTest thisBuild = (BuildTest) PersistenceService.getBucket().fetch(buildKey).execute();
	
		String[] testResultKeys = thisBuild.testResultKeys;
		int size = testResultKeys.length;
		TestResult[] testResults = new TestResult[size];
		TestInventory[] testInventories = new TestInventory[size]; 
		
		for(int i=0; i<size; i++){
			TestResult tr = (TestResult) PersistenceService.getBucket().fetch(testResultKeys[i]).execute();
			TestInventory ti = (TestInventory) PersistenceService.getBucket().fetch(tr.testInventoryID).execute();
			testResults[i] = tr;
			testInventories[i] = ti; 	
		}
				
		Object[] toRet = new Object[4];
		toRet[0] = thisBuild; 
		toRet[1] = getBuildHistory(buildHistoryKey);
		toRet[2] = testResults;
		toRet[3] = testInventories; 
		
		return toRet; 
	}
	
	public BuildTest[] getBuildHistory(String buildHistoryKey) throws Exception {
	    	
		BuildHistory bh = (BuildHistory) PersistenceService.getBucket().fetch(buildHistoryKey).execute();
		BuildTest[] buildTests = new BuildTest[bh.buildTestKeys.length]; 
		for(int i = 0; i < buildTests.length; i++){
			buildTests[i] = PersistenceService.getBucket().fetch(bh.buildTestKeys[i], BuildTest.class).execute();
		}
		
		return buildTests; 
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response doPut(BuildTest buildTest) throws Exception {
		PersistenceService.getBucket().store(buildTest.getKey(), buildTest).execute();
		return Response.status(204).build();
	}
	   
    
}
