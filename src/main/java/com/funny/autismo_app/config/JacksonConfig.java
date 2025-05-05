package com.funny.autismo_app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;

@Configuration
public class JacksonConfig implements WebMvcConfigurer {

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(objectMapper);
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.valueOf("application/json;charset=UTF-8")); // <- adiciona suporte ao tipo com charset
        converter.setSupportedMediaTypes(supportedMediaTypes);
        return converter;
    }
}
