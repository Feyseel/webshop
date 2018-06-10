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


@Path("/klant")
public class KlantResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllKlanten() {
		KlantService service = ServiceProvider.getKlantService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Klant klant : service.getAllKlanten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", klant.getId());
			job.add("voornaam", klant.getVoornaam());
			job.add("achternaam", klant.getAchternaam());
			job.add("afbeelding", klant.getAfbeelding());
			job.add("woonadresId", klant.getWoonAdres().getId());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getKlantById(@PathParam("id") int id) {
		KlantService service = ServiceProvider.getKlantService();
		Klant klant = service.getKlantById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", klant.getId());
		job.add("voornaam", klant.getVoornaam());
		job.add("achternaam", klant.getAchternaam());
		job.add("afbeelding", klant.getAfbeelding());
		job.add("woonadresId", klant.getWoonAdres().getId());
		job.add("land", klant.getWoonAdres().getLand());
		job.add("stad", klant.getWoonAdres().getStad());
		job.add("straat", klant.getWoonAdres().getStraat());
		job.add("huisnummer", klant.getWoonAdres().getHuisnummer());
		if (klant.getWoonAdres().getToevoeging() != null) {
			job.add("toevoeging", klant.getWoonAdres().getToevoeging());
		} else {
			job.add("toevoeging", "");
		}
		job.add("postcode", klant.getWoonAdres().getPostcode());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertKlant(@QueryParam("Q1") String voornaam, @QueryParam("Q2") String achternaam,
			@QueryParam("Q3") String afbeelding, @QueryParam("Q4") int woonadresId) {
		Adres woonAdresId = new Adres(woonadresId);
		Klant klant = new Klant(voornaam, achternaam, afbeelding, woonAdresId);
		KlantService service = ServiceProvider.getKlantService();
		service.insertKlant(klant);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateKlant(@QueryParam("Q1") int id, @QueryParam("Q2") String voornaam,
			@QueryParam("Q3") String achternaam, @QueryParam("Q4") String afbeelding,
			@QueryParam("Q5") int woonadresId) {
		Adres woonAdresId = new Adres(woonadresId);
		Klant klant = new Klant(id, voornaam, achternaam, afbeelding, woonAdresId);
		KlantService service = ServiceProvider.getKlantService();
		service.updateKlant(klant);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteKlant(@QueryParam("Q1") int id) {
		KlantService service = ServiceProvider.getKlantService();
		service.deleteKlant(id);
	}

}



