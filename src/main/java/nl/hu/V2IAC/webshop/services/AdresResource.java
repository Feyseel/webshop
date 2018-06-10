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

@Path("/adres")
public class AdresResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAdressen() {
		AdresService service = ServiceProvider.getAdresService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Adres adres : service.getAllAdressen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", adres.getId());
			job.add("land", adres.getLand());
			job.add("stad", adres.getStad());
			job.add("straat", adres.getStraat());
			job.add("huisnummer", adres.getHuisnummer());
			if (adres.getToevoeging() != null) {
				job.add("toevoeging", adres.getToevoeging());
			}
			job.add("postcode", adres.getPostcode());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdresById(@PathParam("id") int id) {
		AdresService service = ServiceProvider.getAdresService();
		Adres adres = service.getAdresById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", adres.getId());
		job.add("land", adres.getLand());
		job.add("stad", adres.getStad());
		job.add("straat", adres.getStraat());
		job.add("huisnummer", adres.getHuisnummer());
		if (adres.getToevoeging() != null) {
			job.add("toevoeging", adres.getToevoeging());
		}
		job.add("postcode", adres.getPostcode());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertAdres(@QueryParam("Q1") String land, @QueryParam("Q2") String stad,
			@QueryParam("Q3") String straat, @QueryParam("Q4") int huisnummer, @QueryParam("Q5") String toevoeging,
			@QueryParam("Q6") String postcode) {
		Adres adres = new Adres(land, stad, straat, huisnummer, toevoeging, postcode);
		AdresService service = ServiceProvider.getAdresService();
		service.insertAdres(adres);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateAdres(@QueryParam("Q1") int id, @QueryParam("Q2") String land, @QueryParam("Q3") String stad,
			@QueryParam("Q4") String straat, @QueryParam("Q5") int huisnummer, @QueryParam("Q6") String toevoeging,
			@QueryParam("Q7") String postcode) {
		Adres adres = new Adres(id, land, stad, straat, huisnummer, toevoeging, postcode);
		AdresService service = ServiceProvider.getAdresService();
		service.updateAdres(adres);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAdres(@QueryParam("Q1") int id) {
		AdresService service = ServiceProvider.getAdresService();
		service.deleteAdres(id);
	}
}
