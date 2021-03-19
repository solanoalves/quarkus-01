package br.solano.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.solano.business.AccountBusiness;
import br.solano.exception.AccountNotFoundException;


@Path("/balance")
public class BalanceResource {
	
	@Inject
	AccountBusiness accountBusiness;
	
    @GET
    public Response getBalance(@QueryParam("account_id") Integer accountId) {
    	Response response = null;
    	try {
			response = Response.ok(accountBusiness.getBalanceFromAccount(accountId))
							.build();
		} catch (AccountNotFoundException e) {
			response = Response.status(Status.NOT_FOUND)
					.entity(0)
					.build();
		}
    	return response;
    }
}
