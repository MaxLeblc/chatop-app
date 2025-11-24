package com.chatop.api.service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * Service for uploading images to Cloudinary
 */
@Service
public class CloudinaryService {

    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);
    private final Cloudinary cloudinary;

    public CloudinaryService(@Value("${cloudinary.url}") String cloudinaryUrl) {
        this.cloudinary = new Cloudinary(cloudinaryUrl);
    }

    /**
     * Upload image to Cloudinary and return the URL
     */
    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            logger.warn("Attempted to upload null or empty file");
            return null;
        }

        try {
            logger.info("Uploading image to Cloudinary: {} (size: {} bytes)", 
                file.getOriginalFilename(), file.getSize());
            
            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                    "folder", "chatop/rentals",
                    "resource_type", "image"
                )
            );

            String secureUrl = (String) uploadResult.get("secure_url");
            logger.info("Image uploaded successfully: {}", secureUrl);
            
            return secureUrl;
        } catch (IOException ex) {
            logger.error("Failed to upload image to Cloudinary: {}", ex.getMessage(), ex);
            throw ex;
        }
    }
}
