package com.dfire.consumer.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;

public class HttpUtil {
    public static final Logger logger = LoggerFactory.getLogger(com.dfire.consumer.util.HttpUtil.class);

    private static final int DEFAULT_TIMEOUT = 10000;

    public static void main(String[] args) {
        System.out.println(post("http://www.cnblogs.com/yesun/archive/2008/10/31/1323432.html", new TreeMap<String, Object>()));
    }

    public static String post(String url, Map<String, Object> params) {
        return post(url, params, DEFAULT_TIMEOUT);
    }

    public static String post(String url, Map<String, Object> params,
                              int timeout) {
        try {
            byte[] data = postReturnBytes(url, params, timeout);
            if (data == null) {
                return null;
            }
            return new String(data, "utf-8");
        } catch (Exception e) {
            logger.error("不支持的字符集!" + e);
        }
        return null;
    }

    public static byte[] postReturnBytes(String url, Map<String, Object> params) {
        return postReturnBytes(url, params, DEFAULT_TIMEOUT);
    }

    public static String post(String url, String content) {
        return post(url, content, DEFAULT_TIMEOUT);
    }

    public static String post(String url, String content, int timeout) {
        try {
            byte[] data = postReturnBytes(url, content, timeout);
            if (data == null) {
                return null;
            }
            return new String(data, "utf-8");
        } catch (Exception e) {
            logger.error("不支持的字符集!" + e);
        }
        return null;
    }

    public static byte[] postReturnBytes(String url, String content, int timeout) {
        HttpClient httpClient = null;
        HttpPost post = null;
        try {
            httpClient = new DefaultHttpClient();
            initHttpClient(httpClient, timeout);
            post = new HttpPost(url);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.addHeader("Accept-Encoding", "gzip, deflate");
            if (content != null && !content.isEmpty()) {
                try {
                    StringEntity myEntity = new StringEntity(content,
                            ContentType.create("text/plain", "UTF-8"));
                    post.setEntity(myEntity);
                } catch (Exception e) {

                }
            }
            HttpResponse httpResponse = httpClient.execute(post);

            return processResponse(httpResponse);
        } catch (Exception e) {
            logger.error("网络出错，可能的原因是：您的网络不通!\n或者服务器停掉了!" + e);
        } finally {
            if (post != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return null;
    }

    public static byte[] postReturnBytes(String url, Map<String, Object> params, int timeout) {
        HttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();
            initHttpClient(httpClient, timeout);
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.addHeader("Accept-Encoding", "gzip, deflate");
            if (params != null && !params.isEmpty()) {
                List<BasicNameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue() == null ? null : entry.getValue().toString()));
                }
                if (!pairs.isEmpty()) {
                    post.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
                }
            }
            HttpResponse httpResponse = httpClient.execute(post);

            return processResponse(httpResponse);
        } catch (Exception e) {
            String msg = "网络出错, 可能的原因是: 您的网络不通, 或者服务器停掉了! url:" + url;
            if (params != null && params.get("method") != null) {
                msg += ", method:" + params.get("method");
            }
            logger.error(msg + "," + e);
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return null;
    }

    private static byte[] processResponse(HttpResponse method) throws IOException {
        Header encodingHeader = method.getFirstHeader("Content-Encoding");

        int status = method.getStatusLine().getStatusCode();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        byte[] bs;
        int len;
        String acceptEncoding = "";
        InputStream in;
        if (null != encodingHeader) {//返回头中有编码设置
            acceptEncoding = encodingHeader.getValue();
        }

        if (acceptEncoding.toLowerCase().indexOf("gzip") != -1) {
            HttpEntity entity = method.getEntity();
            if (entity != null) {
                in = entity.getContent();

                GZIPInputStream gzipInputStream = new GZIPInputStream(in);
                bs = new byte[8 * 1024];
                while ((len = gzipInputStream.read(bs)) != -1) {
                    bout.write(bs, 0, len);
                }
            }
        } else {
            HttpEntity entity = method.getEntity();
            if (entity != null) {
                in = entity.getContent();
                bs = new byte[8 * 1024];
                while ((len = in.read(bs)) != -1) {
                    bout.write(bs, 0, len);
                }
            }
        }

        if (status != 200 && status != 206) {
            String content = new String(bout.toByteArray(), "utf-8");
            logger.error(content);
        }
        return bout.toByteArray();
    }

    private static void initHttpClient(HttpClient httpClient, int timeout) {
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
    }

    public static String get(String url, Map<String, Object> params) {
        return get(url, params, DEFAULT_TIMEOUT);
    }

    public static String get(String url, Map<String, Object> params, int timeout) {
        try {
            return new String(getBytes(url, params, timeout), "utf-8");
        } catch (Exception e) {
            logger.error("不支持的字符集!" + e);
        }
        return null;
    }

    public static byte[] getBytes(String url, Map<String, Object> params) {
        return getBytes(url, params, DEFAULT_TIMEOUT);
    }

    public static byte[] getBytes(String url, Map<String, Object> params,
                                  int timeout) {
        HttpClient httpClient = null;
        HttpGet get = null;
        try {
            httpClient = new DefaultHttpClient();
            initHttpClient(httpClient, timeout);
            get = new HttpGet(url);
            get.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            get.addHeader("Accept-Encoding", "gzip, deflate");
            if (params != null && !params.isEmpty()) {
                HttpParams httpParams = new BasicHttpParams();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    httpParams.setParameter(entry.getKey(), entry.getValue()
                            .toString());
                }
                get.setParams(httpParams);
            }

            //logger.error(get.getQueryString());
            HttpResponse httpResponse = httpClient.execute(get);

            return processResponse(httpResponse);
        } catch (Exception e) {
            logger.error("网络出错，可能的原因是：您的网络不通!\n或者服务器停掉了!" + e);
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return null;
    }

    public static String get(String url) {
        return get(url, DEFAULT_TIMEOUT);
    }

    public static String get(String url, int timeout) {
        try {
            return new String(getBytes(url, timeout), "utf-8");
        } catch (Exception e) {
            logger.error("不支持的字符集!" + e);
        }
        return null;
    }

    public static byte[] getBytes(String url) {
        return getBytes(url, DEFAULT_TIMEOUT);
    }

    public static byte[] getBytes(String url, int timeout) {
        HttpClient httpClient = null;
        HttpGet get = null;
        try {
            httpClient = new DefaultHttpClient();
            initHttpClient(httpClient, timeout);
            get = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(get);

            return processResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的字符集!", e);
        } catch (Exception e) {
            logger.error("网络出错，可能的原因是：您的网络不通!\n或者服务器停掉了!", e);
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return null;
    }
}
