package de.dennis_boldt.grizzly;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

import de.dennis_boldt.Config;

/**
 *
 * @author Dennis Boldt
 *
 */
public class GrizzlyServer {

    public GrizzlyServer() {
        System.out.print("Starting Grizzly...");
        try {
            URI baseuri = Config.getBaseURI();
            HttpServer server = GrizzlyServerFactory.createHttpServer(baseuri, Config.getResourceConfig(baseuri.toString()));
            System.out.println("Grizzly server started: " + baseuri.toString());
            System.in.read();
            System.out.print("Stop server...");
            server.stop();
            System.out.println("done");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	// TODO: use args4j
    public static void main(String[] args) {
        if (args.length == 1) {
        	Config.PORT = Integer.parseInt(args[1]);
        }
        new GrizzlyServer();
    }
}