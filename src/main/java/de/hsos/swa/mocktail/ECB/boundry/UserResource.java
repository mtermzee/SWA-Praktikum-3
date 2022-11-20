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

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import de.hsos.swa.mocktail.ECB.control.user.UserPost;
import de.hsos.swa.mocktail.ECB.entity.User;

@Path("/Users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(title = "Users", version = "1.2", description = "You can get/add/edit your Users"))

public class UserResource {
    @Inject
    UserPost Post;

    @PostConstruct
    public void init() {

    }

    @POST
    @Path("{username}")
    @Operation(summary = "Change User")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))))
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
