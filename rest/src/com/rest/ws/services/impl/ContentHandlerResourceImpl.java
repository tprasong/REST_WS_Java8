package com.rest.ws.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.Reader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.StreamingOutput;

import com.rest.ws.services.ContentHandlerResource;

public class ContentHandlerResourceImpl implements ContentHandlerResource {
	private String storage;
	@Override
	public StreamingOutput get() {
		// TODO Auto-generated method stub
		
			return new StreamingOutput(){

				@Override
				public void write(OutputStream output) throws IOException,
						WebApplicationException {
					if(storage != null && !storage.isEmpty())
					{
						output.write(storage.getBytes());
					}else
					{
						output.write("Hello world".getBytes());
					}
				}
		};
	}
	
	@Override
	public void put(InputStream is) {
		byte[] bytes = null;
		try {
			bytes = readFromStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bytes != null)
		{
			storage = new String(bytes);
		}
		System.out.println(storage);
	}

	private byte[] readFromStream(InputStream is)  throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte [] buffer = new byte[1000];
		int wasRead = 0;
		do{
			wasRead = is.read(buffer);
			if(wasRead > 0){
				baos.write(buffer, 0, wasRead);
			}
		}while(wasRead > -1);
		return baos.toByteArray();
	}

	@Override
	public void putReader(Reader reader) {
		LineNumberReader lineReader = new LineNumberReader(reader);
		StringBuilder sb = new StringBuilder();
		String line = null;
		do{
			try {
				line = lineReader.readLine();
				if(line != null)
				{
					if(sb.length() > 0)
					{
						sb.append("\n");
					}
					sb.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(line != null);
		if(sb.length() > 0)
		{
			storage = sb.toString();
		}
	}

	@Override
	public InputStream getFile(@PathParam("filepath") String filePath) {
		String basePath = "/Users/prasong/Dropbox/Document/AEON/";
		String readFromFile = basePath + filePath;
		InputStream iStream = null;
		try {
			iStream = new FileInputStream(new File(readFromFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iStream;
	}

	@Override
	@POST
	@Path("/MultivaluedMap")
	@Produces("application/x-www-form-urlencoded")
	@Consumes("application/x-www-form-urlencoded")
	public MultivaluedMap<String, String> formValues(
			MultivaluedMap<String, String> form) {
		// TODO Auto-generated method stub
		return form;
	}
	
	
}
