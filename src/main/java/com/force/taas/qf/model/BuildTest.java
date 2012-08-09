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
 * @author bbirman
 *
 */
@XmlRootElement
public class BuildTest {
	public String buildTestID;
	public TriggeredByEnum triggeredBy;
	public BuildOwnerEnum owner;
	public String changelist;
	public Date startGMT;
	public Date endGMT;
	public String[] testResultKeys;
	private String key; 
	
	public BuildTest() {
		key = String.valueOf(hashCode()); 
	}

	public BuildTest(String buildTestID, TriggeredByEnum triggeredBy, BuildOwnerEnum owner, 
			String changelist, Date startGMT, Date endGMT, 
			String[] testResultKeys) { 
		this.buildTestID = buildTestID; 
		this.triggeredBy = triggeredBy;
		this.owner = owner;
		this.changelist = changelist;
		this.startGMT = startGMT;
		this.endGMT = endGMT;  
		this.testResultKeys = testResultKeys; 
		key = String.valueOf(hashCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buildTestID == null) ? 0 : buildTestID.hashCode());
		result = prime * result
				+ ((changelist == null) ? 0 : changelist.hashCode());
		result = prime * result
				+ ((startGMT == null) ? 0 : startGMT.hashCode());
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
		BuildTest other = (BuildTest) obj;
		if (buildTestID == null) {
			if (other.buildTestID != null)
				return false;
		} else if (!buildTestID.equals(other.buildTestID))
			return false;
		if (changelist == null) {
			if (other.changelist != null)
				return false;
		} else if (!changelist.equals(other.changelist))
			return false;
		if (startGMT == null) {
			if (other.startGMT != null)
				return false;
		} else if (!startGMT.equals(other.startGMT))
			return false;
		return true;
	}
	
	@XmlElement
	public String getKey() {
		return key;
	}
}
