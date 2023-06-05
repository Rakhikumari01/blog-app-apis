package com.codewithrakhi.traffic.services;

import org.springframework.web.multipart.MultipartFile;

public interface OcrService {

    String readImageText(MultipartFile file);
}
