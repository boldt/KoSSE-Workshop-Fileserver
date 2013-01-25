package de.dennis_boldt.grizzly;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

import de.dennis_boldt.Config;

public class GrizzlyServer {

    public GrizzlyServer(int port) {
        System.out.print("Starting Grizzly...");
        try {
            URI baseuri = Config.getBaseURI(port);
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

    public static void main(String[] args) {
        Integer port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = Config.PORT;
        }

        new GrizzlyServer(port);
    }
}