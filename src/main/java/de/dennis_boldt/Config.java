package de.dennis_boldt;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * The configuration
 *
 * @author Dennis Boldt
 *
 */
public class Config {

    public static final String HOSTNAME = "localhost";
    public static final Integer PORT = 9999;
    public static final String JAXRS_RESOURCES = "de.dennis_boldt";

    public static final String FILES_PATH = "FILES";

    public static URI getBaseURI(Integer port) {
        if(port == null) {
            port = Config.PORT;
        }
        return UriBuilder.fromUri("http://" + Config.HOSTNAME).port(port).build();
    }

    public static ResourceConfig getResourceConfig(String baseurl) {
        final Map<String, Object> init_params = new HashMap<String, Object>();
        init_params.put("com.devsprint.jersey.api.container.netty.baseUri", baseurl);
        init_params.put("com.sun.jersey.config.property.packages", Config.JAXRS_RESOURCES);
        init_params.put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
        return new PackagesResourceConfig(init_params);
    }


}
