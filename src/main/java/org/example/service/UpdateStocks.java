package org.example.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UpdateStocks {
    public void updateStocks(MultipartFile file) throws IOException;
}
