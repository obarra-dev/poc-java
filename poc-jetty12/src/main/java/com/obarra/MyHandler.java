package com.obarra;

import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

public class MyHandler extends Handler.Abstract
{
  private static final String page = "<html><body><h1>Omar, Rules!!</h1></body></html>";

  @Override
  public boolean handle(final Request request, final Response response, final Callback callback) {
    Content.Sink.write(response, true, page, callback);
    return false;
  }
}
