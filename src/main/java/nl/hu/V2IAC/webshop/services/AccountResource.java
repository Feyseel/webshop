package nl.hu.V2IAC.webshop.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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

import nl.hu.V2IAC.webshop.model.*;
import nl.hu.V2IAC.webshop.resources.*;


@Path("/account")
public class AccountResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAccounts() {
		AccountService service = ServiceProvider.getAccountService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Account account : service.getAllAccounts()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", account.getId());
			job.add("role", account.getRole());
			job.add("gebruikersnaam", account.getUsername());
			job.add("openDatum", new SimpleDateFormat("dd/MM/yyyy").format(account.getOpenDatum()).toString());
			job.add("actief", account.isActief());
			job.add("factuuradresId", account.getFactuurAdres().getId());
			job.add("klantId", account.getKlant().getId());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAccountById(@PathParam("id") int id) {
		AccountService service = ServiceProvider.getAccountService();
		Account account = service.getAccountById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", account.getId());
		job.add("role", account.getRole());
		job.add("gebruikersnaam", account.getUsername());
		job.add("openDatum", new SimpleDateFormat("dd/MM/yyyy").format(account.getOpenDatum()).toString());
		job.add("actief", account.isActief());
		job.add("factuuradresId", account.getFactuurAdres().getId());
		job.add("klantId", account.getKlant().getId());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertAccount(@QueryParam("Q1") String role, @QueryParam("Q2") String username,
			@QueryParam("Q3") String password, @QueryParam("Q4") String opendatum, @QueryParam("Q5") boolean actief,
			@QueryParam("Q6") int factuuradresid, @QueryParam("Q7") int klantid) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date openDatum = format.parse(opendatum);
		Date openDatumSql = new Date(openDatum.getTime());
		Adres factuuradresId = new Adres(factuuradresid);
		Klant klantId = new Klant(klantid);
		Account account = new Account(role, username, password, openDatumSql, actief, factuuradresId, klantId);
		AccountService service = ServiceProvider.getAccountService();
		service.insertAccount(account);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateAccount(@QueryParam("Q1") int id, @QueryParam("Q2") String password) {
		Account account = new Account(id, password);
		AccountService service = ServiceProvider.getAccountService();
		service.updateAccount(account);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAccount(@QueryParam("Q1") int id) {
		AccountService service = ServiceProvider.getAccountService();
		service.deleteAccount(id);
	}

}
