package br.solano.resource;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.solano.business.StoreBusiness;

@Path("/reset")
public class StoreResource {
    
    @Inject
    StoreBusiness storeBusiness;
    
    @POST
    public Response reset() {
        storeBusiness.resetRepository();
        return Response.ok("OK")
                    .build();
    }
}
