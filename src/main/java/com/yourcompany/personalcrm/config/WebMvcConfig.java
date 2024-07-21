package com.yourcompany.personalcrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.MustacheResolver;
import com.github.mustachejava.resolver.DefaultResolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer 
{
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        //configurer.defaultContentType(MediaType.TEXT_HTML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(0, mustacheHttpMessageConverter(mustacheFactory(mustacheResolver()), request));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(postRedirectGetInterceptor());
    }

    @Bean
    public PostRedirectGetInterceptor postRedirectGetInterceptor()
    {
        return new PostRedirectGetInterceptor();
    }

    @Bean
    public MustacheResolver mustacheResolver()
    {
        //return new DirectoryHandlingMustacheResolver("templates", "html");
        return new DefaultResolver("templates");
    }

    @Bean
    public MustacheFactory mustacheFactory(@NonNull final MustacheResolver mustacheResolver)
    {
        return new DefaultMustacheFactory(mustacheResolver);
    }

    @Bean
    public MustacheHttpMessageConverter mustacheHttpMessageConverter(@NonNull final MustacheFactory mustacheFactory, @NonNull final HttpServletRequest request)
    {
        return new MustacheHttpMessageConverter(mustacheFactory, request);
    }

    @Autowired
    private HttpServletRequest request;
}