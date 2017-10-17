package com.iydsj.sw.common.ext;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * 统一加载配置文件，由于是只要修改路径，所以这里直接对location进行处理,
 * 完整路径示例 linux系统：/data/appdatas/sw-erp-web/config
 * 完整路径示例 windows：C:\\data\\appdatas\\sw-erp-web\\config
 */
public class SWPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 配置文件路径,其实是配置文件名称
     */
    protected String[] paths;

    private boolean ignoreResourceNotFound = false;

    private String fileEncoding;

    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    @Override
    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    protected void loadProperties(Properties props) throws IOException {
        if (this.paths != null) {
            for (String path : this.paths) {
                if (logger.isInfoEnabled()) {
                    logger.info("Loading properties file from " + path);
                }
                String os = System.getProperty("os.name");
                if (os.toLowerCase().contains("window")){
                    path = "C:" + path;
                }
                FileSystemResource fileSystemResource = new FileSystemResource(path);
                try {
                    fillProperties(
                            props, new EncodedResource(fileSystemResource, this.fileEncoding), this.propertiesPersister);
                }
                catch (IOException ex) {
                    if (this.ignoreResourceNotFound) {
                        if (logger.isWarnEnabled()) {
                            logger.warn("Could not load properties from " + path + ": " + ex.getMessage());
                        }
                    }
                    else {
                        throw ex;
                    }
                }
            }
        }
    }

    void fillProperties(Properties props, EncodedResource resource, PropertiesPersister persister)
            throws IOException {

        InputStream stream = null;
        Reader reader = null;
        try {
            String filename = resource.getResource().getFilename();
            if (filename != null && filename.endsWith(".xml")) {
                stream = resource.getInputStream();
                persister.loadFromXml(props, stream);
            }
            else if (resource.requiresReader()) {
                reader = resource.getReader();
                persister.load(props, reader);
            }
            else {
                stream = resource.getInputStream();
                persister.load(props, stream);
            }
        }
        finally {
            if (stream != null) {
                stream.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}
