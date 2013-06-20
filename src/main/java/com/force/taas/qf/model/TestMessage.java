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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author bbirman
 *
 */
@XmlRootElement
public class TestMessage implements QualityFoundryObject {
	public String testResultID; 
	public MessageTypeEnum type;
	public String message; 
	private String key; 
	
	public TestMessage() {

	}

	public TestMessage(String testResultID, MessageTypeEnum type, String message) {
		this.testResultID = testResultID; 
		this.type = type;
		this.message = message; 
		key = String.valueOf(hashCode()); 
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((testResultID == null) ? 0 : testResultID.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TestMessage other = (TestMessage) obj;
		if (testResultID == null) {
			if (other.testResultID != null)
				return false;
		} else if (!testResultID.equals(other.testResultID))
			return false;
		if (type != other.type)
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
