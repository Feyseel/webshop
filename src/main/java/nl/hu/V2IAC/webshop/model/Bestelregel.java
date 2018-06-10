package nl.hu.V2IAC.webshop.model;

public class Bestelregel {

	private int id;
	private int aantal;
	private double subtotaal;
	private Product product;
	private Bestelling bestelling;

	public Bestelregel() {
	}

	public Bestelregel(int aantal, double subtotaal, Product product) {
		this.aantal = aantal;
		this.subtotaal = subtotaal;
		this.product = product;
	}

	public Bestelregel(int aantal, double subtotaal, Product product, Bestelling bestelling) {
		this.aantal = aantal;
		this.subtotaal = subtotaal;
		this.product = product;
		this.bestelling = bestelling;
	}

	public Bestelregel(int id, int aantal, double subtotaal, Product product, Bestelling bestelling) {
		super();
		this.id = id;
		this.aantal = aantal;
		this.subtotaal = subtotaal;
		this.product = product;
		this.bestelling = bestelling;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public double getSubtotaal() {
		return subtotaal;
	}

	public void setSubtotaal(double subtotaal) {
		this.subtotaal = subtotaal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Bestelling getBestelling() {
		return bestelling;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	};

}
