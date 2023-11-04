package com.example.pocjakarta10.moreend;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/hello-world/scan")
public class ScanResource {

    @GET
    @Path("/{repositoryName}{path : /?(.*)}")
    @Produces("text/plain")
    public String scan(@PathParam("repositoryName") final String repositoryName,
                       @PathParam("path") final String path) {
        return "Hello, Scan!";
    }

    @GET
    @Path("/configure/{repositoryName}{path : /?(.*)}")
    @Produces("text/plain")
    public String conf(@PathParam("repositoryName") final String repositoryName,
                       @PathParam("path") final String path) {
        return "Hello, Config!";
    }
}