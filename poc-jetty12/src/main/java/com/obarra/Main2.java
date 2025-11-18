package com.obarra;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;

public class Main2
{
  public static void main(String[] args) throws Exception {
    Server server = new Server(8089);

    ServletHolder servletHolder = new ServletHolder(new BlockingServlet());
    ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    servletContextHandler.addServlet(servletHolder, "/blocking-servlet");
    server.setHandler(servletContextHandler);

    server.start();
    // Wait for the server to stop
    server.join();
  }
}