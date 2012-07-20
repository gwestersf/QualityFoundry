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
 * @author bbirman
 *
 */
@XmlRootElement
public class BuildTest {
	public String id;
	public TriggeredByEnum triggeredBy;
	public BuildOwnerEnum owner;
	public String changelist;
	public Date startGMT;
	public Date endGMT;
	public String[] testResults;
	
	public BuildTest() { }

	public BuildTest(String id, TriggeredByEnum triggeredBy, BuildOwnerEnum owner, 
			String changelist, Date startGMT, Date endGMT, 
			String[] testResults) {
		this.id = id;
		this.triggeredBy = triggeredBy;
		this.owner = owner;
		this.changelist = changelist;
		this.startGMT = startGMT;
		this.endGMT = endGMT;  
		this.testResults = testResults; 
	}
}
