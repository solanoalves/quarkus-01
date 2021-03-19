package br.solano.resource;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.solano.business.AccountBusiness;
import br.solano.dto.EventDTO;
import br.solano.exception.AccountNotFoundException;

@Path("/event")
public class EventResource {
    
    @Inject
    AccountBusiness accountBusiness;
    
    @POST
    public Response processEvent(EventDTO eventDTO) {
        Response response = null;
        try {
            response = Response.ok(accountBusiness.processAccountEvent(eventDTO))
                            .status(Status.CREATED)
                            .build();
        } catch (AccountNotFoundException e) {
            response = Response.status(Status.NOT_FOUND)
                    .entity(0)
                    .build();
        }
        return response;
    }
}
