package com.simple.banking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.banking.model.AccountDebitCreditTransaction;
import com.simple.banking.model.AccountHolder;
import com.simple.banking.service.SimpleBankingService;

@Controller
@RequestMapping(value = "/")
public class AccountHolderController {
	@Autowired
	SimpleBankingService simplebankingService;

	public AccountHolderController(SimpleBankingService simplebankingService) {
		this.simplebankingService = simplebankingService;
	}

	@GetMapping("/home")
	public String showHomePage(Model model, HttpSession session) {
		String errorAccountDetails = (String) session.getAttribute("errorAccountDetails");
		if(errorAccountDetails != null) {
			model.addAttribute("errorAccountDetails", errorAccountDetails);
		}
		return "home";
	}

	@RequestMapping(value = "/registerAccountHolder", method = RequestMethod.GET)
	public String createNewAccountHolder(Model model) {
		// Add new user to model to be bound with view (JSP)
		model.addAttribute("accountHolder", new AccountHolder());
		return "accountHolderRegister";
	}

	@RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
	public String showUserPageGet(Model model, HttpSession session) {
		String accountPin = (String) session.getAttribute("accountPin");
		model.addAttribute("acctDtl", simplebankingService.getAccountDetails(accountPin));
		model.addAttribute("transactions", simplebankingService.getTransactionHistory(accountPin));
		return "accountHolderDetails";
	}

	@RequestMapping(value = "/saveNewAccount", method = RequestMethod.POST)
	public String showUserPagePost(@ModelAttribute("accountHolder") AccountHolder accountHolder, HttpSession session) {
		session.removeAttribute("acctDtl");
		session.removeAttribute("accountPin");
		System.out.print(accountHolder.getFirstName());
		AccountHolder acct = simplebankingService.saveAccount(accountHolder);
		session.setAttribute("accountPin", acct.getAccountPin());
		return "redirect:accountDetails";
	}

	@RequestMapping(value = "/getAccountDetails", method = RequestMethod.POST)
	public String getAccountDetails(@ModelAttribute("accountHolder") AccountHolder accountHolder, HttpSession session) {
		session.removeAttribute("acctDtl");
		session.removeAttribute("accountPin");
		AccountHolder acct = simplebankingService.getAccountDetails(accountHolder.getAccountPin());
		String errorResponse = (acct == null) ? "No records found for account pin: " + accountHolder.getAccountPin()
				: null;
		if ((acct != null) && (!acct.getFirstName().toUpperCase()
				.equalsIgnoreCase(accountHolder.getFirstName().toUpperCase())
				|| !acct.getLastName().toUpperCase().equalsIgnoreCase(accountHolder.getLastName().toUpperCase()))) {
			errorResponse = "Account holder details does not match";
		}
		if (errorResponse != null) {
			session.setAttribute("errorAccountDetails", errorResponse);
			return "redirect:home";
		}
		session.removeAttribute("accountPin");
		session.setAttribute("accountPin", acct.getAccountPin());
		return "redirect:accountDetails";
	}

	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public String getAccountDetails(@ModelAttribute("accountTransaction") AccountDebitCreditTransaction accountTransaction, HttpSession session) {
		simplebankingService.saveAccountTransaction(accountTransaction);
		session.removeAttribute("accountPin");
		session.setAttribute("accountPin", accountTransaction.getAccountPin());
		return "redirect:accountDetails";
	}
}