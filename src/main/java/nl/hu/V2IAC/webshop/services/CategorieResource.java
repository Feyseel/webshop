package nl.hu.V2IAC.webshop.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

@Path("/categorie")
public class CategorieResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCategorieen() {
		CategorieService service = ServiceProvider.getCategorieService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Categorie categorie : service.getAllCategorieen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", categorie.getId());
			job.add("naam", categorie.getNaam());
			job.add("omschrijving", categorie.getOmschrijving());
			job.add("afbeelding", categorie.getAfbeelding());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCategorieById(@PathParam("id") int id) {
		CategorieService service = ServiceProvider.getCategorieService();
		ArrayList<Categorie> c = service.getAllCategorieenById(id);
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonArrayBuilder jabProduct = Json.createArrayBuilder();
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("naam", c.get(0).getNaam());
		job.add("omschrijving", c.get(0).getOmschrijving());
		for (Categorie categorie : service.getAllCategorieenById(id)) {
			if (categorie.getProduct().getNaam() != null) {
				JsonObjectBuilder jobProduct = Json.createObjectBuilder();
				jobProduct.add("product_id", categorie.getProduct().getId());
				jobProduct.add("product_image", categorie.getProduct().getAfbeelding());
				jobProduct.add("product_title", categorie.getProduct().getNaam());
				jobProduct.add("product_price", categorie.getProduct().getPrijs());
				jobProduct.add("product_description", categorie.getProduct().getOmschrijving());
				jobProduct.add("in_sale", categorie.getProduct().isAanbieding());
				if (categorie.getProduct().isAanbieding() == true) {
					jobProduct.add("product_new_price", categorie.getProduct().getAanbieding().getNieuwPrijs());
					jobProduct.add("product_sale_start", new SimpleDateFormat("dd/MM/yyyy")
							.format(categorie.getProduct().getAanbieding().getVanDatum()).toString());
					jobProduct.add("product_sale_end", new SimpleDateFormat("dd/MM/yyyy")
							.format(categorie.getProduct().getAanbieding().getTotDatum()).toString());
				}
				jabProduct.add(jobProduct);
			}
		}
		job.add("producten", jabProduct);
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertCategorie(@QueryParam("Q1") String naam, @QueryParam("Q2") String omschrijving,
			@QueryParam("Q3") String afbeelding) {
		Categorie categorie = new Categorie(naam, omschrijving, afbeelding);
		CategorieService service = ServiceProvider.getCategorieService();
		service.insertCategorie(categorie);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCategorie(@QueryParam("Q1") int id, @QueryParam("Q2") String naam,
			@QueryParam("Q3") String omschrijving, @QueryParam("Q4") String afbeelding) {
		Categorie categorie = new Categorie(id, naam, omschrijving, afbeelding);
		CategorieService service = ServiceProvider.getCategorieService();
		service.updateCategorie(categorie);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCategorie(@QueryParam("Q1") int id) {
		CategorieService service = ServiceProvider.getCategorieService();
		service.deleteCategorie(id);
	}
}
