package de.hsos.swa.mocktail.ECB.boundry;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import de.hsos.swa.mocktail.ECB.control.ingredient.IngredientService;
import de.hsos.swa.mocktail.ECB.control.mocktail.MocktailService;
import de.hsos.swa.mocktail.ECB.entity.Mocktail;

@Path("/mocktails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(title = "Mocktails", version = "1.2", description = "You can get/add/edit your drinks "))

public class MocktailResource {
    // Inject - for dependency injection Initialize the MocktailRepository
    @Inject
    MocktailService mocktailService;

    @Inject
    IngredientService ingredientService;

    private static final Logger log = Logger.getLogger(MocktailResource.class.getName());

    @PostConstruct
    public void init() {
        // add data
        int m1 = mocktailService.addMocktail("Sex on the Beach");

        // sex on the beach rezept
        // add rezept to Mocktail
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Wodka"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Pfirsichlikör"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Cranberry Direktsaft"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Orangensaft"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Eiswürfel"), m1);
    }

    @GET
    @Operation(summary = "Get all mocktails")
    @Counted(name = "COUNT-getMocktails", description = "Count how many times the getMocktails() was called")
    @Timed(name = "TIME-getMocktails", description = "How long it takes to invoke the getMocktails()", unit = MetricUnits.MILLISECONDS)
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))))
    @Timeout(value = 2000, unit = ChronoUnit.MILLIS)
    @CircuitBreaker(failureRatio = 0.25, requestVolumeThreshold = 10)
    @Fallback(fallbackMethod = "recommendation")
    @Retry(maxRetries = 3, delay = 2000)
    public Response getMocktails() {
        log.info("Get all mocktails");
        log.debug(System.currentTimeMillis() + " - MocktailResource: GET /mocktails: started");

        List<Mocktail> mocktails = this.mocktailService.getMocktails();
        if (!mocktails.isEmpty())
            return Response.ok(mocktails).build();

        log.trace(System.currentTimeMillis() + " - MocktailResource: GET /mocktails: " + mocktails);
        log.debug(System.currentTimeMillis() + " - MocktailResource: GET /mocktails: ended");

        return Response.status(Status.NOT_FOUND).entity("No mocktails was found").type("text/plain").build();
    }

    // Fallback method for getMocktails() if the mocktailKatalog is not available
    private Response recommendation() {
        log.info("************* Recommendation *************");
        List<Mocktail> mocktails = new ArrayList<>();
        return Response.ok(mocktails).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get mocktail by id")
    @Counted(name = "COUNT-getMocktailById", description = "Count how many times the getMocktailById() was called")
    @Timed(name = "TIME-getMocktailById", description = "How long it takes to invoke the getMocktailById()", unit = MetricUnits.MILLISECONDS)
    @CircuitBreaker(failureRatio = 0.25, requestVolumeThreshold = 10)
    // @Gauge(name = "GAUGE-getMocktailById", description = "Time of
    // getMocktailById()", unit = MetricUnits.NONE)
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))))
    public Response getMocktailById(@PathParam("id") int id) {
        log.info("Get mocktail by id: " + id);
        log.debug(System.currentTimeMillis() + " - MocktailResource: GET /mocktails/{id}: started");

        Mocktail mocktail = this.mocktailService.getMocktailById(id);
        if (mocktail != null)
            return Response.ok(mocktail).build();

        log.trace(System.currentTimeMillis() + " - MocktailResource: GET /mocktails: " + id);
        log.debug(System.currentTimeMillis() + " - MocktailResource: GET /mocktails/{id}: ended");

        return Response.status(Status.NOT_FOUND).entity("No mocktail was found").type("text/plain").build();
    }

    @GET
    @Path("search/{name}")
    @Operation(summary = "Search mocktail by name")
    public Response getMocktailByName(@PathParam("name") String name) {
        List<Mocktail> mocktails = this.mocktailService.getMocktailByName(name);
        if (!mocktails.isEmpty())
            return Response.ok(mocktails).build();

        return Response.status(Status.NOT_FOUND).entity("No mocktails was found").type("text/plain").build();
    }

    @POST
    @Path("{mocktail}")
    @Operation(summary = "Add mocktail")
    @Counted(name = "COUNT-addMMocktail", description = "Count how many times the addMMocktail() was called")
    @CircuitBreaker(failureRatio = 0.25, requestVolumeThreshold = 10)
    @Timed(name = "TIME-addMMocktail", description = "How long it takes to invoke the addMMocktail()", unit = MetricUnits.MILLISECONDS)
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))))
    public Response addMocktail(@PathParam("mocktail") String mocktail) {
        log.info("Add mocktail: " + mocktail);
        log.debug(System.currentTimeMillis() + " - MocktailResource: POST /mocktails/{mocktail}: started");

        if (mocktail != null) {
            int id = this.mocktailService.addMocktail(mocktail);
            if (id != -1)
                return Response.ok(id).entity("Mocktail has been added successfully").type("text/plain").build();
        }

        log.trace(System.currentTimeMillis() + " - MocktailResource: POST /mocktails/{mocktail}: " + mocktail);
        log.debug(System.currentTimeMillis() + " - MocktailResource: POST /mocktails/{mocktail}: ended");

        return Response.status(Status.BAD_REQUEST).entity("Mocktail was not added successfully").type("text/plain")
                .build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Update mocktail")
    @Counted(name = "COUNT-updateMocktail", description = "Count how many times the updateMocktail() was called")
    @CircuitBreaker(failureRatio = 0.25, requestVolumeThreshold = 10)
    @Timed(name = "TIME-updateMocktail", description = "How long it takes to invoke the updateMocktail()", unit = MetricUnits.MILLISECONDS)
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))))
    public Response updateMocktail(@PathParam("id") int id, @QueryParam("mocktail") String mocktail) {
        log.info("Add ingredient to mocktail: " + id + " - " + mocktail);
        log.debug(System.currentTimeMillis() + " - MocktailResource: PUT /mocktails/{id}: started");

        if (mocktail != null) {
            boolean updated = this.mocktailService.updateMocktail(id, mocktail);
            if (updated)
                return Response.ok().entity("Mocktail has been updated successfully").type("text/plain").build();
        }

        log.trace(System.currentTimeMillis() + " - MocktailResource: PUT /mocktails/{id}: " + id + " " + mocktail);
        log.debug(System.currentTimeMillis() + " - MocktailResource: PUT /mocktails/{id}: ended");

        return Response.status(Status.BAD_REQUEST).entity("Mocktail was not updated successfully").type("text/plain")
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete mocktail by id")
    @Counted(name = "COUNT-deleteMocktail", description = "Count how many times the deleteMocktail() was called")
    @Timed(name = "TIME-deleteMocktail", description = "How long it takes to invoke the deleteMocktail()", unit = MetricUnits.MILLISECONDS)
    @CircuitBreaker(failureRatio = 0.25, requestVolumeThreshold = 10)
    // @Gauge(name = "GAUGE-deleteMocktail", description = "Time of
    // deleteMocktail()", unit = MetricUnits.NONE)
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))))
    public Response deleteMocktail(@PathParam("id") int id) {
        log.info("Delete mocktail by id: " + id);
        log.debug(System.currentTimeMillis() + " - MocktailResource: DELETE /mocktails/{id}: started");

        boolean deleted = this.mocktailService.deleteMocktail(id);
        if (deleted)
            return Response.ok().entity("Mocktail has been deleted successfully").type("text/plain").build();

        log.trace(System.currentTimeMillis() + " - MocktailResource: DELETE /mocktails/{id}: " + id);
        log.debug(System.currentTimeMillis() + " - MocktailResource: DELETE /mocktails/{id}: ended");

        return Response.status(Status.BAD_REQUEST).entity("Mocktail was not deleted successfully").type("text/plain")
                .build();
    }

}
