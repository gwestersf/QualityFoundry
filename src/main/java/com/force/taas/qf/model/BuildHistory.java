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

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author bbirman
 *
 */
@XmlRootElement
public class BuildHistory {
	public String appName;
	public String[] buildTestKeys;
	private String key; 
	
	public BuildHistory() { 
		key = String.valueOf(hashCode()); 
	}

	public BuildHistory(String appName, String[] buildTestKeys) {
		this.appName = appName;
		this.buildTestKeys = buildTestKeys;
		key = String.valueOf(hashCode()); 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + Arrays.hashCode(buildTestKeys);
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
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (!Arrays.equals(buildTestKeys, other.buildTestKeys))
			return false;
		return true;
	}
	
	@XmlElement
	public String getKey() {
		return key;
	}
}
