package com.hcl.Referral.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/PaymentPage")
	public String paymentPage() {

		return "PaymentPage";
	}

	@GetMapping("/Registration")
	public String Registration() {

		return "Registration";
	}

}
