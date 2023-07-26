package com.party_grouping.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ImageController {
    @Value("${image}")
    String fileDir;

    @GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable("imagename") String imagename) // A
            throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                String path = fileDir +
                        imagename +
                        ".JPG";

        try {
            fis = new FileInputStream(path);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }
}