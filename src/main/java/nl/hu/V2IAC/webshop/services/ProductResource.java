package nl.hu.V2IAC.webshop.services;

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

@Path("/product")
public class ProductResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducten() {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Product product : service.getAllProducten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("product_id", product.getId());
			job.add("product_image", product.getAfbeelding());
			job.add("product_title", product.getNaam());
			job.add("product_price", product.getPrijs());
			job.add("product_description", product.getOmschrijving());
			job.add("in_sale", product.isAanbieding());
			if (product.isAanbieding() == true) {
				job.add("product_new_price", product.getAanbieding().getNieuwPrijs());
				job.add("product_sale_start",
						new SimpleDateFormat("dd/MM/yyyy").format(product.getAanbieding().getVanDatum()).toString());
				job.add("product_sale_end",
						new SimpleDateFormat("dd/MM/yyyy").format(product.getAanbieding().getTotDatum()).toString());
			}

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductById(@PathParam("id") int id) {
		ProductService service = ServiceProvider.getProductService();
		Product product = service.getProductById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("product_id", product.getId());
		job.add("product_image", product.getAfbeelding());
		job.add("product_title", product.getNaam());
		job.add("product_price", product.getPrijs());
		job.add("product_description", product.getOmschrijving());
		job.add("in_sale", product.isAanbieding());
		if (product.isAanbieding() == true) {
			job.add("product_new_price", product.getAanbieding().getNieuwPrijs());
			job.add("product_sale_start",
					new SimpleDateFormat("dd/MM/yyyy").format(product.getAanbieding().getVanDatum()).toString());
			job.add("product_sale_end",
					new SimpleDateFormat("dd/MM/yyyy").format(product.getAanbieding().getTotDatum()).toString());
		}

		return job.build().toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public void insertProduct(@FormParam("product_title") String naam,
			@FormParam("product_description") String omschrijving, @FormParam("product_price") double prijs,
			@FormParam("product_image") String afbeelding) {
		Product product = new Product(naam, omschrijving, prijs, afbeelding);
		ProductService service = ServiceProvider.getProductService();
		service.insertProduct(product);
		System.out.println(product.getAfbeelding());
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProduct(@FormParam("product_title") String naam,
			@FormParam("product_description") String omschrijving, @FormParam("product_price") double prijs,
			@FormParam("product_image") String afbeelding, @FormParam("product_id") int id) {
		Product product = new Product(id, naam, omschrijving, prijs, afbeelding);
		ProductService service = ServiceProvider.getProductService();
		service.updateProduct(product);
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProduct(@QueryParam("Q1") int id) {
		ProductService service = ServiceProvider.getProductService();
		service.deleteProduct(id);
	}
}
