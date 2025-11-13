package com.obarra;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.Callback;

public class Main2
{

  public static void main(String[] args) throws Exception {
    Server server = new Server(8089); // Create a server on port 8080

    // Create a ServletContextHandler
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/"); // Set the context path for your web application
    server.setHandler(context); // Set the handler for the server

    // Add your servlet
    ServletHolder servletHolder = new ServletHolder(new BlockingServlet());
    context.addServlet(servletHolder, "/blocking-servlet"); // Map the servlet to the /myServlet path

    server.start(); // Start the server
    server.join();  // Wait for the server to stop
  }
}