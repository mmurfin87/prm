package com.yourcompany.personalcrm.config;

import java.io.File;
import java.io.Reader;

import com.github.mustachejava.resolver.DefaultResolver;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DirectoryHandlingMustacheResolver extends DefaultResolver
{
    public DirectoryHandlingMustacheResolver(@NonNull final String resourceRoot, final String templateSuffix)
    {
        super(resourceRoot);
        this.root = new File(resourceRoot);
        this.suffix = "." + templateSuffix;
    }

    public DirectoryHandlingMustacheResolver(@NonNull final File fileRoot, final String templateSuffix)
    {
        super(fileRoot);
        this.root = fileRoot;
        this.suffix = "." + templateSuffix;
    }
    
    @Override
    public Reader getReader(String resourceName)
    {
        return super.getReader(resourceName + suffix);
    }

    @NonNull
    private final File root;
    private final String suffix;
}
