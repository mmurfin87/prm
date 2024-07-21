package com.yourcompany.personalcrm.config;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.MustacheNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class MustacheHttpMessageConverter implements HttpMessageConverter<Object>
{
    private final MustacheFactory mustacheFactory;
    private final HttpServletRequest request;

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType)
    {
        return false; // This converter is for writing only
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType)
    {
        return mediaType == null || mediaType.includes(MediaType.TEXT_HTML);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes()
    {
        return Collections.singletonList(MediaType.TEXT_HTML);
    }

    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException
    {
        throw new HttpMessageNotReadableException("This converter does not support reading", inputMessage);
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException
    {
        String templateName = determineTemplateName(o);
        log.info("Template: {}", templateName);
        if (templateName == null)
            throw new MustacheNotFoundException(String.format("Template not found: %s", templateName));
        
            final Mustache mustache = mustacheFactory.compile(templateName);

        outputMessage.getHeaders().setContentType(MediaType.TEXT_HTML);
        try (Writer writer = new OutputStreamWriter(outputMessage.getBody(), StandardCharsets.UTF_8))
        {
            mustache.execute(writer, o);
        }
    }

    private String determineTemplateName(Object o)
    {
        final HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handlerMethod == null)
        {
            log.error("Unknown Method: {}", request.getPathInfo());
            throw new IllegalStateException("Handling return value from unknown method");
        }
        String templatePath = null;
        {
            final View anno = handlerMethod.getMethodAnnotation(View.class);
            if (anno != null)
                templatePath = anno.value();
            else
            {
                final Object match = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
                log.info("Found pattern to match: {}", match);
                if (match instanceof String matchingPattern)
                    templatePath = matchingPattern.replaceAll("[{}]", "");
            }
        }
        if (templatePath == null)
            throw new IllegalStateException("Handling unannotated method");
        return templatePath + ".html";
    }
}