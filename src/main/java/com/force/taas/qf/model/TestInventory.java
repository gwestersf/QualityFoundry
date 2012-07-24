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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*; 

/**
 * 
 * @author bbirman
 *
 */
@XmlRootElement
public class TestInventory {
	public String id;
	public String testName;
	public String className; 
	public String packageName; 
	public String[] testResults; 
	
	private String key; 
	
	public TestInventory() { }

	public TestInventory(String id, String testName, String className, String packageName,
			String[] testResults) {
		
		this.id = id; 
		this.testName = testName;
		this.className = className;
		this.packageName = packageName; 
		this.testResults = testResults;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result
				+ ((testName == null) ? 0 : testName.hashCode());
		return result; 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		TestInventory other = (TestInventory) obj;
		if (this.className == null) {
			if (other.className != null)
				return false;
		} else if (!this.className.equals(other.className))
			return false;
		if (this.packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!this.packageName.equals(other.packageName))
			return false;
		if (this.testName == null) {
			if (other.testName != null)
				return false;
		} else if (!this.testName.equals(other.testName))
			return false;
		return true;
	}

	@XmlElement
	public String getKey() {
		key = String.valueOf(hashCode()); 
		return key;
	}

}
