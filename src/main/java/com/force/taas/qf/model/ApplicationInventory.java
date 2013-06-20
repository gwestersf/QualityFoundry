package com.force.taas.qf.model;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author gwester
 */
public class ApplicationInventory {
	public LinkedHashSet<String> applicationNames;
	public static final String ROOT_KEY = "1";
	
	public ApplicationInventory() {
		
	}
	
	public ApplicationInventory(LinkedHashSet<String> applicationNames) {
		this.applicationNames = applicationNames;
	}
	
	public boolean addApplication(String applicationName) {
		return applicationNames.add(applicationName);
	}

	@XmlElement
	public String getKey() {
		return ApplicationInventory.ROOT_KEY;
	}
}
