package com.obarra;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BlockingServlet extends HttpServlet
{

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException
  {

    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("""
        {
          "status": "ok",
          "message": "Omar rules!, this is a blocking servlet response"
        }
        """);
  }
}