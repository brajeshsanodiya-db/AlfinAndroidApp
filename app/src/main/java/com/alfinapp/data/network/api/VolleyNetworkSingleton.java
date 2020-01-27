package com.alfinapp.data.network.api;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class VolleyNetworkSingleton {
    private static final String DEFAULT_CACHE_DIR = "volley";
    private static VolleyNetworkSingleton mInstance;
    Context mContext;
    HurlStack hurlStack = new HurlStack() {
        @Override
        protected HttpURLConnection createConnection(URL url) throws IOException {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
            try {
                httpsURLConnection.setSSLSocketFactory(getSSLSocketFactory());
                httpsURLConnection.setHostnameVerifier(getHostnameVerifier());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return httpsURLConnection;
        }
    };
    private RequestQueue mRequestQueue;

    private VolleyNetworkSingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();

    }

    private VolleyNetworkSingleton(Context context, int type) {
        mContext = context;
        if (type == 1) {
            mRequestQueue = getRequestQueue();
        } else if (type == 2) {
            mRequestQueue = getSingleRequestQueue();
        }
    }

    public static synchronized VolleyNetworkSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyNetworkSingleton(context);
        }
        return mInstance;
    }

    public static synchronized VolleyNetworkSingleton getInstance(Context context, int type) {
        if (mInstance == null) {
            mInstance = new VolleyNetworkSingleton(context, type);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public RequestQueue getSingleRequestQueue() {
        if (mRequestQueue == null) {
            File cacheDir = new File(mContext.getCacheDir(), DEFAULT_CACHE_DIR);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(new DiskBasedCache(cacheDir), network, 1);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setShouldCache(false);
        getRequestQueue().add(req);
    }

    public void cancelRequest(String tag){
        getRequestQueue().cancelAll(tag);
    }

    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    private SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                        return myTrustedAnchors;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());

        return sc.getSocketFactory();
    }
}