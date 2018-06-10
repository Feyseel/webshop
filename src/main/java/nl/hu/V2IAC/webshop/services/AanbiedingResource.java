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
import javax.ws.rs.FormParam;
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

@Path("/aanbieding")
public class AanbiedingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAanbiedingen() {
		AanbiedingService service = ServiceProvider.getAanbiedingService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Aanbieding aanbieding : service.getAllAanbiedingen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", aanbieding.getId());
			job.add("vanDatum", new SimpleDateFormat("dd/MM/yyyy").format(aanbieding.getVanDatum()).toString());
			job.add("totDatum", new SimpleDateFormat("dd/MM/yyyy").format(aanbieding.getTotDatum()).toString());
			job.add("nieuwPrijs", aanbieding.getNieuwPrijs());
			job.add("ProductId", aanbieding.getProduct().getId());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAanbiedingById(@PathParam("id") int id) {
		AanbiedingService service = ServiceProvider.getAanbiedingService();
		Aanbieding aanbieding = service.getAanbiedingById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", aanbieding.getId());
		job.add("vanDatum", new SimpleDateFormat("dd/MM/yyyy").format(aanbieding.getVanDatum()).toString());
		job.add("totDatum", new SimpleDateFormat("dd/MM/yyyy").format(aanbieding.getTotDatum()).toString());
		job.add("nieuwPrijs", aanbieding.getNieuwPrijs());
		job.add("productId", aanbieding.getProduct().getId());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED })
	public void insertAanbieding(@FormParam("start_date") long vandatum, @FormParam("end_date") long totdatum,
			@FormParam("new_price") double nieuwPrijs, @FormParam("product_id") int productid) {
		Date vanDatum = new Date(vandatum * 1000);
		Date totDatum = new Date(vandatum * 1000);
		Product productId = new Product(productid);
		Aanbieding aanbieding = new Aanbieding(vanDatum, totDatum, nieuwPrijs, productId);
		AanbiedingService service = ServiceProvider.getAanbiedingService();
		service.insertAanbieding(aanbieding);
		System.out.println(nieuwPrijs);

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateAanbieding(@QueryParam("Q1") int id, @QueryParam("Q2") String vandatum,
			@QueryParam("Q3") String totdatum, @QueryParam("Q4") double nieuwPrijs, @QueryParam("Q5") int productid)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date vanDatumUtil = format.parse(vandatum);
		java.util.Date totDatumUtil = format.parse(totdatum);
		Date vanDatumSql = new Date(vanDatumUtil.getTime());
		Date totDatumSql = new Date(totDatumUtil.getTime());
		Product productId = new Product(productid);
		Aanbieding aanbieding = new Aanbieding(id, vanDatumSql, totDatumSql, nieuwPrijs, productId);
		AanbiedingService service = ServiceProvider.getAanbiedingService();
		service.updateAanbieding(aanbieding);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAanbieding(@QueryParam("Q1") int id) {
		AanbiedingService service = ServiceProvider.getAanbiedingService();
		service.deleteAanbieding(id);
	}
}
