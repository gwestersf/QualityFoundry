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

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * 
 * @author gwester
 *
 */
public class WebServer {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();
    
    protected static HttpServer startServer() throws IOException {
    	// add listeners for our REST resources
        ResourceConfig resourceConfig = new PackagesResourceConfig("com.force.taas.qf.resource");
        
        // create the basic server
        HttpServer server = GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
        
        // we need to serve up our static content too
        ServerConfiguration serverConfiguration = server.getServerConfiguration();
        String cwd = System.getProperty("user.dir");
        String docRootFolder = cwd + "/target/classes/web";
        serverConfiguration.addHttpHandler(new StaticHttpHandler(docRootFolder), "/static");
        return server;
    }
    
    public static void main(String[] args) throws IOException {
        // Grizzly 2 initialization
        System.out.println("Starting grizzly2...");
        HttpServer server = startServer();
        System.in.read();
        server.stop();
    }    
}
