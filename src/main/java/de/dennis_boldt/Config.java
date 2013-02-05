package de.dennis_boldt;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 *
 * @author Dennis Boldt
 *
 */
public class Config {

    public static String HOSTNAME = "localhost";
    public static Integer PORT = 9999;

    public static final String JAXRS_RESOURCES = "de.dennis_boldt";
    public static final String FILES_PATH = "FILES";

    public static URI getBaseURI() {
        return UriBuilder.fromUri("http://" + Config.HOSTNAME).port(Config.PORT).build();
    }

    public static ResourceConfig getResourceConfig(String baseurl) {
        final Map<String, Object> init_params = new HashMap<String, Object>();
        init_params.put(PackagesResourceConfig.PROPERTY_PACKAGES, Config.JAXRS_RESOURCES);
        init_params.put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
        return new PackagesResourceConfig(init_params);
    }


}
