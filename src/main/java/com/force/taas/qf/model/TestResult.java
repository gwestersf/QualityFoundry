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
package com.force.taas.qf.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author gwester
 *
 */
@XmlRootElement
public class TestResult {
	String testInventoryID;
	String buildTestID;
	public TestStatusEnum status;
	String[] testMessages;
	public Date startGMT;
	public Date endGMT;

	
	public TestResult() { }

	public TestResult(String testInventoryID, String buildTestID, TestStatusEnum status, 
			String[] testMessages, Date startGMT, Date endGMT) {
		this.testInventoryID = testInventoryID;
		this.buildTestID = buildTestID;
		this.status = status;
		this.testMessages = testMessages;
		this.startGMT = startGMT;
		this.endGMT = endGMT;
	}
}
