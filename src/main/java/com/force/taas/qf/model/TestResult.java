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
	public TestStatusEnum status;
	public Date reportDateTime;
	public int runTimeInMillis;
	public String fullClassName;
	public String testName;
	public String result;
	
	public TestResult() { }

	public TestResult(TestStatusEnum status, Date reportDateTime,
			int runTimeInMillis, String fullClassName, String testName,
			String result) {
		this.status = status;
		this.reportDateTime = reportDateTime;
		this.runTimeInMillis = runTimeInMillis;
		this.fullClassName = fullClassName;
		this.testName = testName;
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fullClassName == null) ? 0 : fullClassName.hashCode());
		result = prime * result
				+ ((reportDateTime == null) ? 0 : reportDateTime.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		TestResult other = (TestResult) obj;
		if (fullClassName == null) {
			if (other.fullClassName != null)
				return false;
		} else if (!fullClassName.equals(other.fullClassName))
			return false;
		if (reportDateTime == null) {
			if (other.reportDateTime != null)
				return false;
		} else if (!reportDateTime.equals(other.reportDateTime))
			return false;
		if (testName == null) {
			if (other.testName != null)
				return false;
		} else if (!testName.equals(other.testName))
			return false;
		return true;
	}

	public String getKey() {
		return String.valueOf(hashCode());
	}
}
