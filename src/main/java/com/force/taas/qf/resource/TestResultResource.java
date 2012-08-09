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
import com.force.taas.qf.model.TestInventory;
import com.force.taas.qf.model.TestResult;
import com.force.taas.qf.model.TestMessage;


/**
 * 
 * @author bbirman
 *
 */
@Path("/testresults")
public class TestResultResource {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@PathParam("{testResultKey}")
	public Object[] doGetTestResult(@PathParam("testResultKey") String testResultKey) throws Exception {
	    TestResult thisTestResult =  PersistenceService.getBucket().fetch(testResultKey, TestResult.class).execute();
	    TestInventory parentTest = getParentTest(thisTestResult);
	    TestMessage[] testMessages = getTestMessage(thisTestResult); 
	    Object[] completeTestResult = new Object[3];
	    completeTestResult[0] = thisTestResult;
	    completeTestResult[1] = testMessages;
	    completeTestResult[2] = parentTest; 
	    	
	    return completeTestResult; 
	}
	

	private TestInventory getParentTest(TestResult thisTestResult) throws Exception {
	    return (TestInventory) PersistenceService.getBucket().fetch(thisTestResult.testInventoryID).execute();
	}
	
	private TestMessage[] getTestMessage(TestResult thisTestResult) throws Exception {
		int length = thisTestResult.testMessageKeys.length; 
		TestMessage[] messages = new TestMessage[length]; 
		
		for(int i = 0; i<length; i++){
			messages[i] = (TestMessage) PersistenceService.getBucket().fetch(thisTestResult.testMessageKeys[i]).execute();
		}
	    return messages;
	}
	
	    
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response doPut(TestResult testResult) throws Exception {
		PersistenceService.getBucket().store(testResult.getKey(), testResult).execute();
	    return Response.status(204).build();
	}
}
