package com.example.demo.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

// Had to remove @ComponentScan and @EnableWebMvc annotations in order to solve the
// INDENT_OUTPUT and FAIL_ON_UNKNOWN_PROPERTIES that was not working with the JSON Annotations
// once XML build files were added to the ClassPath
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {

        // Find the Jackson JSON Converter if it exists
        Optional<HttpMessageConverter<?>> converterFound = converters.stream()
                .filter(c -> c instanceof AbstractJackson2HttpMessageConverter)
                .findFirst();

        if (converterFound.isPresent()) {
            AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) converterFound.get();
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

        // Find the Jackson XML Converter if it exists
        Optional<HttpMessageConverter<?>> XMLConverterFound = converters.stream()
                .filter(c -> c instanceof MappingJackson2XmlHttpMessageConverter)
                .findFirst();

        if (XMLConverterFound.isPresent()) {
            MappingJackson2XmlHttpMessageConverter converter = (MappingJackson2XmlHttpMessageConverter) XMLConverterFound.get();
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
