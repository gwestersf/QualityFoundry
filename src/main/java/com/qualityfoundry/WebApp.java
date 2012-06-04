package com.qualityfoundry;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

public class WebApp extends Service<WebAppConfig> {

	
	protected WebApp() {
		super("QualityFoundry");
	}

    public static void main(String[] args) throws Exception {
        new WebApp().run(args);
    }
	
	@Override
	protected void initialize(WebAppConfig arg0, Environment arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
