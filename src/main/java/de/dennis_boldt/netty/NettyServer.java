package de.dennis_boldt.netty;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.devsprint.jersey.api.container.netty.JerseyHandler;
import com.sun.jersey.api.container.ContainerFactory;

import de.dennis_boldt.Config;

public class NettyServer {

    public NettyServer(Integer port) {
        // Configure the server
        ServerBootstrap bootstrap = new ServerBootstrap(
            new NioServerSocketChannelFactory(
                    Executors.newCachedThreadPool(),
                    Executors.newCachedThreadPool()
            ));

        String baseurl = Config.getBaseURI(port).toString() + "/";

        JerseyHandler jh =  ContainerFactory.createContainer(JerseyHandler.class, Config.getResourceConfig(baseurl));
        bootstrap.setPipelineFactory(new NettyServerChannelPipelineFactory(jh));
        bootstrap.bind(new InetSocketAddress(port));

        System.out.println("Netty server started: " + baseurl);
    }

    public static void main(String[] args) {
        Integer port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = Config.PORT;
        }
        new NettyServer(port);
    }
}
