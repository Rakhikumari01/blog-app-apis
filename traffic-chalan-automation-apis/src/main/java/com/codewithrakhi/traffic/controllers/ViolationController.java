package com.codewithrakhi.traffic.controllers;

import com.codewithrakhi.traffic.payload.ApiResponse;
import com.codewithrakhi.traffic.payload.ViolationDto;
import com.codewithrakhi.traffic.services.FileService;
import com.codewithrakhi.traffic.services.ViolationService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("api/")
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/violation")
    public ResponseEntity<ViolationDto> addViolation(@RequestBody ViolationDto violationDto, @PathVariable Integer userId)
    {
       ViolationDto addViolation = this.violationService.addViolation(violationDto,userId);
       return new ResponseEntity<ViolationDto>(addViolation, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{violationId}")
    public ResponseEntity<ApiResponse> deleteViolation(@PathVariable Integer violationId)
    {
         this.violationService.deleteViolation(violationId);
         return new ResponseEntity<ApiResponse>(new ApiResponse("violation is successfully deleted!!",true),HttpStatus.OK);
    }

    @PutMapping("/update/{violationId}")
    public ResponseEntity<ViolationDto> updateViolation (@RequestBody ViolationDto violationDto, @PathVariable Integer violationId)
    {
        ViolationDto updateViolation = this.violationService.updateViolation(violationDto ,violationId);
        return new ResponseEntity<ViolationDto>(updateViolation, HttpStatus.OK);

    }

      @GetMapping("/getAllViolation/{violationId}")
    public ResponseEntity<ViolationDto> getAllViolationById(@PathVariable Integer violationId)
    {
        ViolationDto getAllViolation = this.violationService.getViolationById(violationId);
        return new ResponseEntity<ViolationDto>(getAllViolation,HttpStatus.OK);
    }

  @GetMapping("/user/violation/{userId}")
    public ResponseEntity<List<ViolationDto>> getViolationByUser(@PathVariable Integer userId)
    {
        List<ViolationDto> getViolationByUser = this.violationService.getViolationByUser(userId);

        return new ResponseEntity<List<ViolationDto>>(getViolationByUser,HttpStatus.OK);
    }


    //violation image upload
    @PostMapping("/violation/image/upload/{violationId}")
    public ResponseEntity<ViolationDto> uploadViolationImage(@RequestParam("image")MultipartFile image,@PathVariable Integer violationId)throws IOException {


        ViolationDto violationDto  =violationService.getViolationById(violationId);
        String fileName = this.fileService.uploadImage(path, image);
       violationDto.setViolationImage(fileName);
       ViolationDto updateViolation = this.violationService.updateViolation(violationDto,violationId);
      return new ResponseEntity<ViolationDto>(updateViolation,HttpStatus.OK);

    }

  @GetMapping(value="/violation/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
    {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
