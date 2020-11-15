package eu.getmangos.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "accountClient")
@RegisterProvider(UnknownUriExceptionMapper.class)
@Path("/v1")
public interface AccountResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccount(@PathParam("id") Integer id) throws UnknownUriException, ProcessingException;

}
