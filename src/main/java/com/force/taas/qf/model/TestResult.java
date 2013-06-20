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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author gwester
 *
 */
@XmlRootElement
public class TestResult implements QualityFoundryObject {
	public String testInventoryID;
	public String buildTestID;
	public TestStatusEnum status;
	public String[] testMessageKeys;
	public Date startGMT;
	public Date endGMT;
	
	// this is not stored in the database; just here so we can send it back
	public String simpleName;
	private String key; 

	
	public TestResult() {

	}

	public TestResult(String testInventoryID, String buildTestID, TestStatusEnum status, 
			String[] testMessageKeys, Date startGMT, Date endGMT) {
		this.testInventoryID = testInventoryID;
		this.buildTestID = buildTestID;
		this.status = status;
		this.testMessageKeys = testMessageKeys;
		this.startGMT = startGMT;
		this.endGMT = endGMT;
		key = String.valueOf(hashCode());
	}
	
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buildTestID == null) ? 0 : buildTestID.hashCode());
		result = prime * result
				+ ((testInventoryID == null) ? 0 : testInventoryID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestResult other = (TestResult) obj;
		if (buildTestID == null) {
			if (other.buildTestID != null)
				return false;
		} else if (!buildTestID.equals(other.buildTestID))
			return false;
		if (testInventoryID == null) {
			if (other.testInventoryID != null)
				return false;
		} else if (!testInventoryID.equals(other.testInventoryID))
			return false;
		return true;
	}
	
	@XmlElement
	public String getKey() {
		if(key == null) {
			key = String.valueOf(hashCode()); 
		}
		return key;
	}
}
