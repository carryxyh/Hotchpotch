package com.iydsj.sw.common.ext;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class SWPropertiesFactoryBean extends PropertiesFactoryBean {

    protected String[] paths;
    private boolean ignoreResourceNotFound = false;
    private String fileEncoding;
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    public SWPropertiesFactoryBean() {
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    protected void loadProperties(Properties props) throws IOException {
        if(this.paths != null) {
            String[] arr$ = this.paths;
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String path = arr$[i$];
                if(this.logger.isInfoEnabled()) {
                    this.logger.info("Loading properties file from " + path);
                }

                String os = System.getProperty("os.name");
                if(os.toLowerCase().contains("window")) {
                    path = "C:" + path;
                }

                FileSystemResource fileSystemResource = new FileSystemResource(path);

                try {
                    this.fillProperties(props, new EncodedResource(fileSystemResource, this.fileEncoding), this.propertiesPersister);
                } catch (IOException var9) {
                    if(!this.ignoreResourceNotFound) {
                        throw var9;
                    }

                    if(this.logger.isWarnEnabled()) {
                        this.logger.warn("Could not load properties from " + path + ": " + var9.getMessage());
                    }
                }
            }
        }

    }

    void fillProperties(Properties props, EncodedResource resource, PropertiesPersister persister) throws IOException {
        InputStream stream = null;
        Reader reader = null;

        try {
            String filename = resource.getResource().getFilename();
            if(filename != null && filename.endsWith(".xml")) {
                stream = resource.getInputStream();
                persister.loadFromXml(props, stream);
            } else if(resource.requiresReader()) {
                reader = resource.getReader();
                persister.load(props, reader);
            } else {
                stream = resource.getInputStream();
                persister.load(props, stream);
            }
        } finally {
            if(stream != null) {
                stream.close();
            }

            if(reader != null) {
                reader.close();
            }

        }

    }
}
