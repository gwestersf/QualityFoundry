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
package com.force.taas.qf;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import junit.framework.TestCase;

/**
 * 
 * @author gwester
 *
 */
public class MainTest extends TestCase {

    private HttpServer httpServer;
    
    private WebResource r;

    public MainTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        //start the Grizzly2 web container 
        httpServer = WebServer.startServer();

        // create the client
        Client c = Client.create();
        r = c.resource(WebServer.BASE_URI);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        httpServer.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    public void testBucketExists() {
        String responseMsg = r.path("tests").get(String.class);
        assertEquals("prodtest", responseMsg);
    }

}
