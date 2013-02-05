package de.dennis_boldt.cors;


import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 *
 * @author Dennis Boldt
 *
 */
public class CORSFilter implements ResourceFilter {

    public ContainerResponseFilter getResponseFilter() {
        return new CORSResponse();
    }

    public ContainerRequestFilter getRequestFilter() {
        return new CORSRequest();
    }

}
