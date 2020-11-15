package eu.getmangos.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import eu.getmangos.dto.LinksDTO;
import eu.getmangos.dto.RealmDTO;
import eu.getmangos.dto.UptimeDTO;

public interface RealmResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves an Realm given the id",
        description = "This API is retrieving the Realm with the given from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The Realm", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = RealmDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Realm not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "realm")
    public Response findRealm(@PathParam("id") Integer id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all realms",
        description = "This API is retrieving all realms from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of realms", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = RealmDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "realm")
    public Response findAllRealms();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new Realm",
        description = "This API is creating a new Realm based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The Realm has been created", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "realm")
    public Response addRealm(RealmDTO entity);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit an Realm",
        description = "This API is updating an existing Realm based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The Realm has been updated", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Realm not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "realm")
    public Response editRealm(@PathParam("id") Integer id, RealmDTO entity);

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an Realm",
        description = "This API is deleting an existing Realm based on the provided id."
            +"It will also delete the link with the accounts and the uptime information for the given realm."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The Realm has been deleted", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Realm not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "realm")
    public Response deleteRealm(@PathParam("id") Integer id);

    @GET
    @Path("link/realm/{realmID}/account/{accountID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a link between an account and a realm",
        description = "This API is retrieving the link with the given id from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The link between the account and the realm", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = LinksDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Link not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "link")
    public Response findRealmCharacters(@PathParam("realmID") Integer realmID, @PathParam("accountID") Integer accountID);

    @GET
    @Path("link/realm/{realmID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all links for a given realm",
        description = "This API is retrieving the links with the given realm id from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of links for the given realm", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = LinksDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Links not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "link")
    public Response findRealmCharactersByRealmID(@PathParam("realmID") Integer realmID);

    @GET
    @Path("link/account/{accountID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all links for a given account",
        description = "This API is retrieving the links with the given account id from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of links for the given account", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = LinksDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Links not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "link")
    public Response findRealmCharactersByAccountID(@PathParam("accountID") Integer accountID);

    @GET
    @Path("link")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all links in the database",
        description = "This API is retrieving all links from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of RealmCharacters", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = LinksDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "link")
    public Response findAllRealmCharacters();

    @POST
    @Path("link")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new link between a realm and an account",
        description = "This API is creating a new link based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The link has been created", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "link")
    public Response addRealmCharacters(LinksDTO entity);

    @PUT
    @Path("link/realm/{realmID}/account/{accountID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a link",
        description = "This API is updating an existing link based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The link has been updated", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Link not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "link")
    public Response editRealmCharacters(@PathParam("accountID") Integer accountID, @PathParam("realmID") Integer realmID, LinksDTO entity);

    @DELETE
    @Path("link/realm/{realmID}/account/{accountID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a link",
        description = "This API is deleting an existing link based on the provided id."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The link has been deleted", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Link not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "link")
    public Response deleteRealmList(@PathParam("accountID") Integer accountID, @PathParam("realmID") Integer realmID);

    @GET
    @Path("status/realm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all uptimes data for a given realm",
        description = "This API is retrieving the uptime data from the database for a given realm."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The uptimes version", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = UptimeDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name="status")
    public Response getUptimesForRealm(@PathParam("id") Integer realmId);

    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves all uptimes data",
        description = "This API is retrieving the uptime data from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The uptimes", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = UptimeDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "status")
    public Response getUptimes();

}
