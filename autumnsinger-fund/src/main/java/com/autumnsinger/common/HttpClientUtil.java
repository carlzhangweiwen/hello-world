package com.autumnsinger.common;

import com.autumnsinger.common.domain.Response;
import com.autumnsinger.common.enums.ResponseEnum;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by carl on 17-7-16.
 */
public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * post提交請求並獲取數據
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static Response post(String url, Map<String,String> params) throws IOException {
        return request("post", url, params,null);
    }

    /**
     * get獲取數據
     * @param url
     * @return
     * @throws IOException
     */
    public static Response get(String url) throws IOException{
        return get(url, null,null);
    }

    /**
     * get請求獲取數據
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static Response get(String url, Map<String,String> params, Map<String,String> headers) throws IOException {
        return request("get", url, params,headers);
    }

    /**
     * 發起http請求，並獲取返回值
     * @param action
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static Response request(String action, String url, Map<String,String> params, Map<String,String> headers) throws IOException {
        CloseableHttpClient httpClient = getCloseableHttpClient();
        try {
            logger.info("url:{},params:{}" , new Object[]{url,params});
            RequestConfig requestConfig = getRequestConfig();

            HttpClientContext localContext = HttpClientContext.create();
            if(params != null){
                params.forEach((k, v)->{
                    localContext.setAttribute(k, v);
                });
            }

            HttpRequestBase requestBase;
            if("post".equals(action)){
                final HttpPost httpPost = new HttpPost(url);
                //set header
                if(headers != null){
                    headers.forEach((k,v) -> httpPost.setHeader(k, v));
                }
                requestBase = httpPost;
            }else {
                final HttpGet httpGet = new HttpGet(url);
                //set header
                if(headers != null){
                    headers.forEach((k,v) -> httpGet.setHeader(k, v));
                }
                requestBase = httpGet;
            }
            requestBase.setConfig(requestConfig);

            requestBase.setHeader("User-Agent",
                    "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0");
            CloseableHttpResponse resp = httpClient.execute(requestBase, localContext);
            int statusCode = resp.getStatusLine().getStatusCode();
            ResponseEnum status = (statusCode == 200) ? ResponseEnum.SUCCESS : ResponseEnum.FAIL;
            String respContent = EntityUtils.toString(resp.getEntity());
            logger.info("status:{},respContent:{}", new Object[]{statusCode,respContent});

            return new Response(status, respContent);

        } finally {
            try{
                httpClient.close();
            }catch(IOException e){
                logger.error(e.getMessage(), e);
            }

        }
    }

    /**
     * 設置請求連接池，並獲取httpClinet
     * @return
     */
    private static CloseableHttpClient getCloseableHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        //// Increase max connections for localhost:80 to 50
        //HttpHost localhost = new HttpHost("locahost", 80);
        //cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        return HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    /**
     * 通用配置
     * @return
     */
    private static RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                        .setSocketTimeout(10000)
                        .setConnectTimeout(10000)
                        .build();
    }
}
