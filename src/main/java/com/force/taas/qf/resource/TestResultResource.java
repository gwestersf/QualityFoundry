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
import com.force.taas.qf.model.BuildHistory;
import com.force.taas.qf.model.BuildTest;
import com.force.taas.qf.model.TestInventory;
import com.force.taas.qf.model.TestResult;
import com.force.taas.qf.model.TestMessage;


/**
 * 
 * @author bbirman
 *
 */
@Path("/testresults")
public class TestResultResource implements QualityFoundryResource {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@PathParam("{buildTestID}")
	public List<TestResult> doGet(@PathParam("buildTestID") String buildTestId) throws Exception {
		BuildTest testRun = PersistenceService.getBucket().fetch(buildTestId, BuildTest.class).execute();
		
		List<TestResult> testResults = new ArrayList<TestResult>();
		for(String key : testRun.testResultKeys) {
			TestResult testResult = PersistenceService.getBucket().fetch(key, TestResult.class).execute();
			
			// we need to send back the test name
			TestInventory testInfo = PersistenceService.getBucket().fetch(key, TestInventory.class).execute();
			testResult.setSimpleName(testInfo.packageName + "." + testInfo.className + "." + testInfo.testName);
			
			testResult.testInventoryID = null;  // truncate unneeded data
			testResult.buildTestID = null;	// truncate unneeded data
			testResults.add(testResult);
		}
		return testResults;
	}
	    
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response doPut(TestResult testResult) throws Exception {
		PersistenceService.getBucket().store(testResult.getKey(), testResult).execute();
	    return Response.status(204).build();
	}
}
