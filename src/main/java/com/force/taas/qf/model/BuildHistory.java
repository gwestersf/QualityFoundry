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

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author bbirman
 *
 */
@XmlRootElement
public class BuildHistory implements QualityFoundryObject {
	public String applicationName;
	public LinkedHashSet<String> buildTestKeys;
	
	public BuildHistory() {
		
	}

	public BuildHistory(String applicationName, LinkedHashSet<String> buildTestKeys) {
		this.applicationName = applicationName;
		this.buildTestKeys = buildTestKeys;
	}
	
	public boolean addBuildTest(String buildTestKey) {
		if(buildTestKeys == null) {
			buildTestKeys = new LinkedHashSet<String>();
		}
		return buildTestKeys.add(buildTestKey);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result
				+ ((buildTestKeys == null) ? 0 : buildTestKeys.hashCode());
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
		BuildHistory other = (BuildHistory) obj;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (buildTestKeys == null) {
			if (other.buildTestKeys != null)
				return false;
		} else if (!buildTestKeys.equals(other.buildTestKeys))
			return false;
		return true;
	}

	@XmlElement
	public String getKey() {
		return applicationName;
	}
}
