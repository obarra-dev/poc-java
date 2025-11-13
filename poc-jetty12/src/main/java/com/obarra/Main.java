package com.obarra;

import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.Callback;

public class Main
    implements Runnable
{
  private static final String page = "<html><body><h1>Omar, Rules!!</h1></body></html>";

  public static void main(String[] args) {
    Main main = new Main();
    main.run();
  }

  @Override
  public void run() {
    Server server = new Server();

    HttpConfiguration httpConfiguration = new HttpConfiguration();
    HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfiguration);

    ServerConnector connector = new ServerConnector(server, httpConnectionFactory);
    connector.setPort(8089);
    server.addConnector(connector);

    server.setHandler(new MyHandler());

    try {
      server.start();
      server.join();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public static class MyHandler
      extends Handler.Abstract
  {
    @Override
    public boolean handle(final Request request, final Response response, final Callback callback) throws Exception {
      Content.Sink.write(response, true, page, callback);
      return false;
    }
  }
}