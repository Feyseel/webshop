package nl.hu.V2IAC.webshop.model;

public class ProductCategorie {
	private Product product;
	private Categorie categorie;

	public ProductCategorie() {
	}

	public ProductCategorie(Product product, Categorie categorie) {
		super();
		this.product = product;
		this.categorie = categorie;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}
