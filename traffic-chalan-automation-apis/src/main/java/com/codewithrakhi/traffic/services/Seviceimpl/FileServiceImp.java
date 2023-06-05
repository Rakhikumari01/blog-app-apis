package com.codewithrakhi.traffic.services.Seviceimpl;

import com.codewithrakhi.traffic.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service

public class FileServiceImp implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File Name
        String name = file.getOriginalFilename();
        //abc.png

        //random name generate files
        String randomId = UUID.randomUUID().toString();
        String filename1 =randomId.concat(name.substring(name.lastIndexOf(".")));

       //full path
        String filepath = path+ File.separator+filename1;


        //create folder if not craeted
        File f = new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filepath));

        return filename1;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {


        String fullPath = path+File.separator+filename;
        InputStream is = new FileInputStream((fullPath));

        return is;

    }
}
