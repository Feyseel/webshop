package nl.hu.V2IAC.webshop.services;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import nl.hu.V2IAC.webshop.model.*;
import nl.hu.V2IAC.webshop.resources.*;

@Path("/bestelling")
public class BestellingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllBestellingen() {
		BestellingService service = ServiceProvider.getBestellingService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Bestelling bestelling : service.getAllBestellingen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", bestelling.getId());
			job.add("afleveradresId", bestelling.getAfleverAdres().getId());
			job.add("klantId", bestelling.getAccount().getId());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBestellingById(@PathParam("id") int id) {
		BestellingService service = ServiceProvider.getBestellingService();
		Bestelling bestelling = service.getBestellingById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", bestelling.getId());
		job.add("afleveradresId", bestelling.getAfleverAdres().getId());
		job.add("klantId", bestelling.getAccount().getId());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public String insertBestelling(@FormParam("klant") String klant, @FormParam("cart") String cart) {
		JSONObject JSONklant = new JSONObject(klant);
		JSONObject JSONcart = new JSONObject("{ cart: " + cart + "}");
		JSONArray JSONcartArray = JSONcart.getJSONArray("cart");
		Adres adresObject = new Adres("Nederland", JSONklant.get("stad").toString(), JSONklant.get("straat").toString(),
				Integer.parseInt(JSONklant.get("huisnummer").toString()), JSONklant.get("toevoeging").toString(),
				JSONklant.get("postcode").toString());
		Klant klantObject = new Klant(Integer.parseInt(JSONklant.get("id").toString()),
				JSONklant.get("voornaam").toString(), JSONklant.get("achternaam").toString(), adresObject);
		Bestelling bestelling = new Bestelling();
		bestelling.setKlant(klantObject);
		for (int i = 0; i < JSONcartArray.length(); i++) {
			JSONObject obj = JSONcartArray.getJSONObject(i);
			Product product = new Product(obj.getInt("product_id"));
			Bestelregel bestelregel = new Bestelregel(obj.getInt("quantity"), obj.getDouble("price"), product);
			bestelling.addBestelregel(bestelregel);
		}
		BestellingService service = ServiceProvider.getBestellingService();
		int id = service.insertBestelling(bestelling);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("bestellingId", id);
		return job.build().toString();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateBestelling(@QueryParam("Q1") int id, @QueryParam("Q2") int afleveradresid,
			@QueryParam("Q3") int accountid) {
		Adres afleveradresId = new Adres(afleveradresid);
		Account accountId = new Account(accountid);
		Bestelling bestelling = new Bestelling(id, afleveradresId, accountId);
		BestellingService service = ServiceProvider.getBestellingService();
		service.updateBestelling(bestelling);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBestelling(@QueryParam("Q1") int id) {
		BestellingService service = ServiceProvider.getBestellingService();
		service.deleteBestelling(id);
	}

}
