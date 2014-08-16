package com.rest.ws.services;

import java.io.InputStream;
import java.io.Reader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.StreamingOutput;

@Path("/ContextHandler")
public interface ContentHandlerResource {
	@GET
	@Path("/streamingOutput")
	@Produces("text/plain")
	public StreamingOutput get();
	
	@PUT
	@Path("/inputStream")
	public void put(InputStream is);
	
	@PUT
	@Path("/reader")
	public void putReader(Reader reader);
	
	@GET
	@Path("/file/{filepath: .*}")
	@Produces("image/*")
	public InputStream getFile(@PathParam("filepath") String filePath);
	
	@POST
	@Path("/MultivaluedMap")
	@Produces("application/x-www-form-urlencoded")
	@Consumes("application/x-www-form-urlencoded")
	public MultivaluedMap<String, String> formValues(MultivaluedMap<String, String> form);
}
