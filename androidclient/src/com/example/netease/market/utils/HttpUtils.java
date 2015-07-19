package com.example.netease.market.utils;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Log;


public class HttpUtils {
	private static final String LOG_TAG = "HttpUtils";
	public static final String CHARSET = HTTP.UTF_8;
//	private static final String USERAGENT = "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) " +
//			"AppleWebKit/553.1(KHTML,like Gecko) " +
//			"Version/4.0 Mobile Safari/533.1";
	public static final String USERAGENT = "android";
	
	
	private static HttpClient mClient;
	
	public static synchronized HttpClient getHttpClient() {
		if(mClient != null)
			return mClient;

		HttpParams params = new BasicHttpParams();
		
		//设置协议参数
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams.setUserAgent(params, USERAGENT);
		
		//超时设置
		//1.从连接池取连接超时时间
		ConnManagerParams.setTimeout(params, 1000);
		//2.建立连接超时时间，该socket连接的超时时间
		HttpConnectionParams.setConnectionTimeout(params, 4000); //4S
		//3.socket 请求超时，从服务器获取响应数据需要等待的时间
		HttpConnectionParams.setSoTimeout(params, 10000); //10S
		HttpConnectionParams.setSocketBufferSize(params, 16 * 1024); // 16K
		
		//设置模式
		SchemeRegistry reg = new SchemeRegistry();
		reg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		reg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		
		// 使用线程安全的连接管理来创建HttpClient
        ClientConnectionManager connMgr = new ThreadSafeClientConnManager(params, reg);
        mClient = new DefaultHttpClient(connMgr, params);

		return mClient;
	}
	
	public static String get(Context context, String uri) throws Exception {
		return get(context, new HttpGet(uri));
	}
	
	public static HttpHost getProxyHttpHost(Context context) {
		if(NetUtils.NETTYPE_WAP == NetUtils.getNetworkType(context)){
			// 获取默认代理主机ip
			String proxyHost = android.net.Proxy.getDefaultHost();
			if(proxyHost == null)
				proxyHost = "10.0.0.172";
			int proxyPort = android.net.Proxy.getDefaultPort();
			
			Log.d(LOG_TAG, "create proxy:" + proxyHost + "," + proxyPort);
			
			return new HttpHost(proxyHost, proxyPort);
		}
		
		return null;
	}
	
	public static Proxy getProxyProxy(Context context) {
		if(NetUtils.NETTYPE_WAP == NetUtils.getNetworkType(context)){
			// 获取默认代理主机ip
			String proxyHost = android.net.Proxy.getDefaultHost();
			if(proxyHost == null)
				proxyHost = "10.0.0.172";
			int proxyPort = android.net.Proxy.getDefaultPort();
			
			Log.d(LOG_TAG, "create proxy:" + proxyHost + "," + proxyPort);
			
			final InetSocketAddress sa = new InetSocketAddress(proxyHost, proxyPort);
			return new Proxy(Proxy.Type.HTTP, sa);
		}
		
		return null;
	}
	
	public static String get(Context context, HttpGet get) throws Exception {
		getHttpClient().getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, getProxyHttpHost(context));
		
		HttpResponse rsp = getHttpClient().execute(get);
		int code = rsp.getStatusLine().getStatusCode();
		
		if (code == HttpStatus.SC_OK) {
			HttpEntity ret = rsp.getEntity();
			if (ret != null) {
				return EntityUtils.toString(ret, CHARSET);
			} else {
				Log.e(LOG_TAG, "rsp.getEntity FAIL!");
			}
		} else {
			Log.e("", "get() HttpStatus ERROR, code = " + code);
		}

		return null;
	}
	
	public static String post(Context context, String uri, NameValuePair... params) throws Exception {
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		for (NameValuePair p : params) {
			list.add(p);
		}

		UrlEncodedFormEntity entity;
		entity = new UrlEncodedFormEntity(list, CHARSET);

		// 创建post请求
		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);

		return post(context, post);
	}
	
	public static String post(Context context, HttpPost post) throws Exception {
		getHttpClient().getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, getProxyHttpHost(context));
		
		HttpResponse rsp = getHttpClient().execute(post);
		int code = rsp.getStatusLine().getStatusCode();

		if (code == HttpStatus.SC_OK) {
			HttpEntity ret = rsp.getEntity();
			if (ret != null) {
				return EntityUtils.toString(ret, CHARSET);
			} else {
				Log.e(LOG_TAG, "rsp.getEntity FAIL!");
			}
		} else {
			Log.e("", "post() HttpStatus ERROR, code = " + code);
		}

		return null;
	}
}
