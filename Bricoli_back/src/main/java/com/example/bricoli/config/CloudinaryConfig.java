package com.example.bricoli.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class CloudinaryConfig {


    @Bean
    Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dthwvnpcz");
        config.put("api_key", "818499498816229");
        config.put("api_secret", "4Fep8M8JCEz6QmnZ4Afkp26pWaY");
        return new Cloudinary(config);
    }
}