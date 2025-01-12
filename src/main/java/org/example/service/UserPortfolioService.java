package org.example.service;

import org.example.dto.PortfolioDTO;

public interface UserPortfolioService {
    public PortfolioDTO getPortfolioById(String userId);
}
