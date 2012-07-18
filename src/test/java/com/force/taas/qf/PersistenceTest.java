package com.force.taas.qf;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.force.taas.qf.model.TestResult;
import com.force.taas.qf.model.TestStatusEnum;

public class PersistenceTest {
    private HttpServer httpServer;

    @Before
    public void setUp() throws IOException {
        httpServer = WebServer.startServer();
    }

    @After
    public void tearDown() {
        httpServer.stop();
        httpServer = null;
    }

	@Test
	public void testPutInDb() throws Exception {
		TestResult recentResult = new TestResult(TestStatusEnum.SUCCESS, new Date(System.currentTimeMillis()),
				0, "MyTestClazz", "myTest",
				"`T`Loglines");
		
		HttpClient client = new DefaultHttpClient();
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("localhost").setPort(9998).setPath("/tests");
		URI uri = builder.build();
		HttpPut request = new HttpPut(uri);
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Accept", "application/json");
		
		JAXBContext jc = JAXBContext.newInstance(TestResult.class);
		Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(new MappedNamespaceConvention(new Configuration()), writer);
 
        marshaller.marshal(recentResult, xmlStreamWriter);
		request.setEntity(new StringEntity(writer.toString()));
		HttpResponse response = client.execute(request);
		
		assertEquals("Wrong HTTP response code", 204, response.getStatusLine().getStatusCode());
	}
}
