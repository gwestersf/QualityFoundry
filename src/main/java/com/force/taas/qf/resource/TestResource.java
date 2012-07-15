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
package com.force.taas.qf.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.basho.riak.client.RiakFactory;
import com.force.taas.qf.PersistenceService;

/**
 * 
 * @author gwester
 *
 */
@Path("/tests")
public class TestResource {

    @GET
    @Produces("text/plain")
    public String getIt() throws Exception {
        return PersistenceService.getBucket().getName();
    }
}
