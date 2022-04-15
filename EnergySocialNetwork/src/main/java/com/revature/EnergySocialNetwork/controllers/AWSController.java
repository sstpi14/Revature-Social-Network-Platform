package com.revature.EnergySocialNetwork.controllers;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.JsonResponse;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.services.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("upload")
public class AWSController {

    @Autowired
    private AWSService awsService;


    //uploading a file to the S3 bucket under the profile-pictures folder
    @PostMapping("profilepic/{profileId}")
    public JsonResponse uploadProfilePicture(@ModelAttribute MultipartFile file, @PathVariable Integer profileId) {
        JsonResponse jsonResponse;
        if (file == null) {
            jsonResponse = new JsonResponse(false, "File is null", null);
        } else {
            URL url = awsService.storeProfilePictureInS3(file, file.getOriginalFilename(), file.getContentType(), profileId);
            String url2 = url.toString();
            Profile profile = awsService.storeProfilePictureURLtoProfileObject(url2, profileId);
            jsonResponse = new JsonResponse(true, "File at URL: " + url2 + " was successfully uploaded and updated to the profile", null);
        }
        return jsonResponse;
    }

    //uploading a file to the S3 bucket under the display-pictures folder
    @PostMapping("displaypic/{profileId}/{displayId}")
    public JsonResponse uploadDisplayPicture(@ModelAttribute MultipartFile file, @PathVariable Integer profileId, @PathVariable Integer displayId) {
        JsonResponse jsonResponse;
        if (file == null) {
            jsonResponse = new JsonResponse(false, "File is null", null);
        } else {
            URL url = awsService.storeDisplayPictureInS3(file, file.getOriginalFilename(), file.getContentType(), profileId);
            String url2 = url.toString();
            Display display = awsService.storeDisplayPictureURLstoDisplayObject(url2, profileId, displayId);
            jsonResponse = new JsonResponse(true, "File at URL: " + url2 + " was successfully uploaded and updated to the Display", null);
        }
        return jsonResponse;
    }

}
