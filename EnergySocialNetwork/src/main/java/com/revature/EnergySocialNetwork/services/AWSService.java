package com.revature.EnergySocialNetwork.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.DisplayDAO;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Component
@Transactional
public class AWSService {
    private static final Logger logger = LoggerFactory.getLogger(AWSService.class);

    @Autowired
    private DisplayDAO displayDAO;
    @Autowired
    private ProfileDAO profileDAO;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3Client;

    public URL storeDisplayPictureInS3(MultipartFile file, String fileName, String contentType, Integer profileId) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(file.getSize());
        Profile profile = profileDAO.getOne(profileId);
        String username = profile.getUsername();
        //TODO add cache control
        try {
            amazonS3Client.putObject(bucketName + "/display-pictures/" + username, fileName, file.getInputStream(), objectMetadata);
        } catch(AmazonClientException | IOException exception) {
            throw new RuntimeException("Error while uploading file.");
        }
        return amazonS3Client.getUrl(bucketName, fileName);
    }
    public URL storeProfilePictureInS3(MultipartFile file, String fileName, String contentType, Integer profileId) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(file.getSize());
        Profile profile = profileDAO.getOne(profileId);
        String username = profile.getUsername();
        //TODO add cache control
        try {
            amazonS3Client.putObject(bucketName + "/profile-pictures/" + username, fileName, file.getInputStream(), objectMetadata);
        } catch (AmazonClientException | IOException exception) {
            throw new RuntimeException("Error while uploading file.");
        }
        return amazonS3Client.getUrl(bucketName, fileName);
    }

    public Profile storeProfilePictureURLtoProfileObject(String url, Integer profileId){
        Profile profile = profileDAO.getOne(profileId);
        profile.setImg(url);

        return profile;

    }
    public Display storeDisplayPictureURLstoDisplayObject(String url, Integer profileId, Integer displayId){
        Display display = displayDAO.getOne(displayId);
        List<String> imgURLs = display.getImg();
        imgURLs.add(url);

        display.setImg(imgURLs);

        return display;

    }

    public URL fetchObject(String fileName) {
        S3Object s3Object;
        URL s3ObjectURL =  amazonS3Client.getUrl(bucketName, fileName);
        s3Object = amazonS3Client.getObject(new GetObjectRequest(bucketName, fileName));
        return s3ObjectURL;
    }

    public void deleteObject(String key) {
        try {
            amazonS3Client.deleteObject(bucketName, key);
        }catch (AmazonServiceException serviceException) {
            logger.error(serviceException.getErrorMessage());
        } catch (AmazonClientException exception) {
            logger.error("Error while deleting File.");
        }
    }
}
