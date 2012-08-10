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
import java.util.LinkedHashSet;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author bbirman
 *
 */
@XmlRootElement
public class BuildTest implements QualityFoundryObject {
	public String buildTestID;
	public TriggeredByEnum triggeredBy;
	public BuildOwnerEnum owner;
	public String changelist;
	public Date startGMT;
	public Date endGMT;
	public LinkedHashSet<String> testResultKeys;
	private String key; 
	
	public BuildTest() {
		
	}

	public BuildTest(String buildTestID, TriggeredByEnum triggeredBy, BuildOwnerEnum owner, 
			String changelist, Date startGMT, Date endGMT, 
			LinkedHashSet<String> testResultKeys) { 
		this.buildTestID = buildTestID; 
		this.triggeredBy = triggeredBy;
		this.owner = owner;
		this.changelist = changelist;
		this.startGMT = startGMT;
		this.endGMT = endGMT;  
		this.testResultKeys = testResultKeys;
	}
	
	public String generateUniqueKey() {
		return UUID.randomUUID().toString();
	}
	
	@XmlElement
	public String getKey() {
		if(buildTestID != null) {
			return buildTestID;
		}
		key = generateUniqueKey();
		return key;
	}
}
