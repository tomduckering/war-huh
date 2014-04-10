package org.duckering.crowdcontrol;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class CrowdControl {

    public static final String CROWD_HOME_INIT_PARAM_KEY = "crowd.home";

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        int port = properties.port();
        String pathToWarFile = properties.warPath();

        Server server = new Server(port);

        WebAppContext webAppContext = new WebAppContext();

        webAppContext.setContextPath("/");
        webAppContext.setWar(pathToWarFile);

        webAppContext.setInitParameter(CROWD_HOME_INIT_PARAM_KEY, properties.crowdHome());


        ContextHandler healthCheckHandler = new ContextHandler();
        healthCheckHandler.setContextPath("/healthcheck");
        healthCheckHandler.setHandler(new HealthCheckHandler());

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{healthCheckHandler,webAppContext});

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}