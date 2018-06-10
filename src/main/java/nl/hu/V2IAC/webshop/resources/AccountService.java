package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Account;
import nl.hu.V2IAC.webshop.persistence.AccountDAO;

public class AccountService {

	private AccountDAO accountDAO = new AccountDAO();

	public ArrayList<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}

	public Account getAccountById(int id) {
		return accountDAO.getAccountById(id);
	}

	public void insertAccount(Account account) {
		accountDAO.insertAccount(account);
	}

	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
	}

	public void deleteAccount(int id) {
		accountDAO.deleteAccount(id);
	}

}
