package com.opstella.observability.springdemo.usecase;

import org.springframework.stereotype.Service;

@Service
public class BannerInteractor {
    public String getHeroBanner() {
        return "Tempo is a tool to observe and understand the behavior of your application in production.";
    }

    public String getSidebarBanner() {
        return "Tempo is a tool to observe and understand the behavior of your application in production.";
    }

}
