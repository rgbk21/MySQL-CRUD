package com.example.demo.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan({ "com.example.demo.web" })
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        // Find the Jackson Converter if it exists
        Optional<HttpMessageConverter<?>> converterFound = converters.stream()
                .filter(c -> c instanceof AbstractJackson2HttpMessageConverter)
                .findFirst();

        if (converterFound.isPresent()) {
            AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) converterFound.get();
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
