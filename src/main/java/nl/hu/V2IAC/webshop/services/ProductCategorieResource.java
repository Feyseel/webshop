package nl.hu.V2IAC.webshop.services;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import nl.hu.V2IAC.webshop.model.*;
import nl.hu.V2IAC.webshop.resources.*;

@Path("/product-categorie")
public class ProductCategorieResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProductCategorieen() {
		ProductCategorieService service = ServiceProvider.getProductCategorieService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (ProductCategorie productCategorie : service.getAllProductCategorieen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("productId", productCategorie.getProduct().getId());
			job.add("categorieId", productCategorie.getCategorie().getId());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{productid}/{categorieid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductCategorieById(@PathParam("productid") int productId,
			@PathParam("categorieid") int categorieId) {
		ProductCategorieService service = ServiceProvider.getProductCategorieService();
		ProductCategorie productCategorie = service.getProductCategorieById(productId, categorieId);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("productId", productCategorie.getProduct().getId());
		job.add("categorieId", productCategorie.getCategorie().getId());

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertProductCategorie(@QueryParam("Q1") int productid, @QueryParam("Q2") int categorieid) {
		Product productId = new Product(productid);
		Categorie categorieId = new Categorie(categorieid);
		ProductCategorie productCategorie = new ProductCategorie(productId, categorieId);
		ProductCategorieService service = ServiceProvider.getProductCategorieService();
		service.insertProductCategorie(productCategorie);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteProductCategorie(@QueryParam("Q1") int productId, @QueryParam("Q2") int categorieId) {
		ProductCategorieService service = ServiceProvider.getProductCategorieService();
		service.deleteProductCategorie(productId, categorieId);
	}
}
