package de.dennis_boldt.cors;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 *
 * @author Dennis Boldt
 *
 */
public class CORSRequest implements ContainerRequestFilter {

	public ContainerRequest filter(ContainerRequest request) {
		return request;
	}

}
