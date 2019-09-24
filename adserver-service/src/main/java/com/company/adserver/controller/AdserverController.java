package com.company.adserver.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RefreshScope
public class AdserverController {

    private String[] ads = {
            "Get JetBlue Mint airline tickets from NYC to SEA for $199",
            "Special offer on Santorini dream vacation packages",
            "See 2 Broadway shows for the price of 1 on afternoon show times",
            "Restaurant Week, book your reservation now!",
            "Home Equity Loans @ 2.62% APR",
            "New Dog Daycare offering 1/2 off grooming services for first time PAW-rents",
    };

    private Random rnd = new Random();

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    String getAd() {
        return ads[rnd.nextInt(8)];
    }

}
