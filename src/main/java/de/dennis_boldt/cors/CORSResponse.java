package de.dennis_boldt.cors;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 *
 * @author Dennis Boldt
 *
 */
public class CORSResponse implements ContainerResponseFilter {

	@Context
	private SecurityContext context;

	public ContainerResponse filter(ContainerRequest request,
			ContainerResponse response) {
		response.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Credentials",
				"true");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept");
		return response;
	}

}
