package com.obarra;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main
{
  public static void main(String[] args) throws Exception {
    Server server = new Server();

    HttpConfiguration httpConfiguration = new HttpConfiguration();
    HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfiguration);

    ServerConnector connector = new ServerConnector(server, httpConnectionFactory);
    connector.setPort(8089);
    server.addConnector(connector);

    server.setHandler(new MyHandler());

    server.start();
    server.join();
  }
}