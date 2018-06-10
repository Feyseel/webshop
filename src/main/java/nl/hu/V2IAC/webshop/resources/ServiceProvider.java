package nl.hu.V2IAC.webshop.resources;

public class ServiceProvider {

	private static AanbiedingService aanbiedingService = new AanbiedingService();
	private static AccountService accountService = new AccountService();
	private static AdresService adresService = new AdresService();
	private static BestellingService bestellingService = new BestellingService();
	private static BestelregelService bestelregelService = new BestelregelService();
	private static CategorieService categorieService = new CategorieService();
	private static KlantService klantService = new KlantService();
	private static ProductService productService = new ProductService();
	private static ProductCategorieService productCategorieService = new ProductCategorieService();

	public static AanbiedingService getAanbiedingService() {
		return aanbiedingService;
	}

	public static AccountService getAccountService() {
		return accountService;
	}

	public static AdresService getAdresService() {
		return adresService;
	}

	public static BestellingService getBestellingService() {
		return bestellingService;
	}

	public static BestelregelService getBestelregelService() {
		return bestelregelService;
	}

	public static CategorieService getCategorieService() {
		return categorieService;
	}

	public static KlantService getKlantService() {
		return klantService;
	}

	public static ProductService getProductService() {
		return productService;
	}

	public static ProductCategorieService getProductCategorieService() {
		return productCategorieService;
	}
}