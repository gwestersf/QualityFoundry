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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.force.taas.qf.model.TestInventory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author gwester
 *
 */
public class JerseyApiClientTest {

    private HttpServer httpServer;
    private WebResource r;

    @Before
    public void setUp() throws IOException {
        //start the Grizzly2 web container 
        httpServer = WebServer.startServer();

        // create the client
        Client c = Client.create();
        r = c.resource(WebServer.BASE_URI);
    }

    @After
    public void tearDown() {
        httpServer.stop();
        httpServer = null;
        r = null;
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testJsonMessage() {
        TestInventory result = r.path("tests").queryParam("testClass", "common.api").get(TestInventory.class);
        assertEquals("Check for testName", null, result.testName);
    }

}
