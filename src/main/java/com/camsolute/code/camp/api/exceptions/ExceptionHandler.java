package com.camsolute.code.camp.api.exceptions;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Exception>{
	@Override
	public Response toResponse(Exception exception) {
		if(exception instanceof IOException) {
			return Response.status(503).build();
		}
		else {
			return Response.status(500).build();
		}
	}
}
