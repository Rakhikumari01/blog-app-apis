package com.codewithrakhi.traffic.services.Seviceimpl;

import com.codewithrakhi.traffic.services.OcrService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;

@Service
public class OcrServiceImpl implements OcrService {
    @Override
    public String readImageText(MultipartFile file) {

        try {
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Users\\RAKHI KUMARI\\Downloads\\blog-app-apis\\traffic-chalan-automation-apis\\images/mutilLanguageText.png"); // Set the path to the tessdata directory
            tesseract.setLanguage("eng"); // Set the language of the text to be extracted

            // Perform OCR on the image file
          tesseract.setPageSegMode(1);
          tesseract.setOcrEngineMode(1);
          String result =tesseract.doOCR();


    }
}
