package com.watkins.etherscan.controller;

import com.watkins.etherscan.client.EtherscanAccountClient;
import com.watkins.etherscan.model.Balance;
import com.watkins.etherscan.model.QueryParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log4j2
public class EtherscanController {

    private final EtherscanAccountClient transactionClient;

    @GetMapping("/query")
    public String createQuery(Model model) {
        model.addAttribute("parameters", new QueryParameters());
        return "query";
    }

    @PostMapping("/query")
    public String queryResult(@ModelAttribute QueryParameters parameters, Model model) {
        Balance balance = transactionClient.getAccountBalance(parameters);
        model.addAttribute("balance", balance);
        return "result";
    }
}
