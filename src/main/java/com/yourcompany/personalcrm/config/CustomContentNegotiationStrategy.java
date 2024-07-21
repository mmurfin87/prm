package com.yourcompany.personalcrm.config;

import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

public class CustomContentNegotiationStrategy implements ContentNegotiationStrategy {

    @Override
    public List<MediaType> resolveMediaTypes(NativeWebRequest request)
    {
        final String accept = request.getHeader("Accept");
        if (accept == null || accept.contains(MediaType.TEXT_HTML_VALUE))
            return List.of(MediaType.TEXT_HTML);
        
        return MediaType.parseMediaTypes(accept);
    }
}