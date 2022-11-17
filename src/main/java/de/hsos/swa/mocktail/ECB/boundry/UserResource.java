package de.hsos.swa.mocktail.ECB.boundry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.mocktail.ECB.control.user.UserPost;

@Path("/Users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserResource {
    @Inject
    UserPost Post;

    @PostConstruct
    public void init() {

    }

    @POST
    @Path("{username}")
    public Response changeUser(@PathParam("username") String username) {
        if (username != null) {
            boolean created = Post.changeUser(username);
            if (created) {
                return Response.status(Response.Status.CREATED).entity("Welcome to our Bar " + username)
                        .type("text/plain").build();
            }
            return Response.status(Response.Status.CREATED).entity("Welcome Back " + username)
                    .type("text/plain").build();
        }
        return Response.status(Status.BAD_REQUEST).entity("User was not created successfully").type("text/plain")
                .build();
    }
}
