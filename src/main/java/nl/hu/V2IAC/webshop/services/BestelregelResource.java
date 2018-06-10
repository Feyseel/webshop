package nl.hu.V2IAC.webshop.services;

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

@Path("/bestelregel")
public class BestelregelResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllBestelregels() {
		BestelregelService service = ServiceProvider.getBestelregelService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Bestelregel bestelregel : service.getAllBestelregels()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", bestelregel.getId());
			job.add("aantal", bestelregel.getAantal());
			job.add("subtotaal", bestelregel.getSubtotaal());
			job.add("productId", bestelregel.getProduct().getId());
			job.add("bestellingId", bestelregel.getBestelling().getId());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBestelregelById(@PathParam("id") int id) {
		BestelregelService service = ServiceProvider.getBestelregelService();
		Bestelregel bestelregel = service.getBestelregelById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", bestelregel.getId());
		job.add("aantal", bestelregel.getAantal());
		job.add("subtotaal", bestelregel.getSubtotaal());
		job.add("productId", bestelregel.getProduct().getId());
		job.add("bestellingId", bestelregel.getBestelling().getId());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertBestelregel(@QueryParam("Q1") int aantal, @QueryParam("Q2") double subtotaal,
			@QueryParam("Q3") int productid, @QueryParam("Q4") int bestellingid) {
		Product productId = new Product(productid);
		Bestelling bestellingId = new Bestelling(bestellingid);
		Bestelregel bestelregel = new Bestelregel(aantal, subtotaal, productId, bestellingId);
		BestelregelService service = ServiceProvider.getBestelregelService();
		service.insertBestelregel(bestelregel);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateBestelregel(@QueryParam("Q1") int id, @QueryParam("Q2") int aantal,
			@QueryParam("Q3") double subtotaal, @QueryParam("Q4") int productid, @QueryParam("Q5") int bestellingid) {
		Product productId = new Product(productid);
		Bestelling bestellingId = new Bestelling(bestellingid);
		Bestelregel bestelregel = new Bestelregel(id, aantal, subtotaal, productId, bestellingId);
		BestelregelService service = ServiceProvider.getBestelregelService();
		service.insertBestelregel(bestelregel);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBestelregel(@QueryParam("Q1") int id) {
		BestelregelService service = ServiceProvider.getBestelregelService();
		service.deleteBestelregel(id);
	}

}
