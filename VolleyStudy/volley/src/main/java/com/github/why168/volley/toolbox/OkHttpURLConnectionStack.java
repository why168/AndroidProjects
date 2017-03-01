package com.github.why168.volley.toolbox;

import com.github.why168.volley.AuthFailureError;
import com.github.why168.volley.Request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * An HttpStack that performs request over an {@link okhttp3.OkHttpClient}.
 *
 * @author Edwin.Wu
 * @version 2017/3/1$ 11:16$
 * @since JDK1.8
 */
public class OkHttpURLConnectionStack implements HttpStack {
    protected final OkHttpClient mClient;

    public OkHttpURLConnectionStack() {
        this(new OkHttpClient());
    }

    public OkHttpURLConnectionStack(OkHttpClient client) {
        this.mClient = client;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        OkHttpClient.Builder builder = mClient.newBuilder();

        int timeoutMs = request.getTimeoutMs();
        builder.connectTimeout(timeoutMs, TimeUnit.MILLISECONDS);
        builder.readTimeout(timeoutMs, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeoutMs, TimeUnit.MILLISECONDS);

        String url = request.getUrl();
        HashMap<String, String> map = new HashMap<String, String>();
        map.putAll(request.getHeaders());
        map.putAll(additionalHeaders);

        URL parsedUrl = new URL(url);

        okhttp3.Request.Builder okHttpRequestBuilder = new okhttp3.Request.Builder();
        okHttpRequestBuilder.url(parsedUrl);

        for (String headerName : map.keySet()) {
            okHttpRequestBuilder.addHeader(headerName, map.get(headerName));
        }

        for (final String name : additionalHeaders.keySet()) {
            okHttpRequestBuilder.addHeader(name, additionalHeaders.get(name));
        }

        setConnectionParametersForRequest(okHttpRequestBuilder, request);

        okhttp3.Request build = okHttpRequestBuilder.build();
        Call okHttpCall = mClient.newCall(build);
        Response okHttpResponse = okHttpCall.execute();
        StatusLine responseStatus = new BasicStatusLine(
                parseProtocol(okHttpResponse.protocol()),
                okHttpResponse.code(),
                okHttpResponse.message()
        );

        BasicHttpResponse response = new BasicHttpResponse(responseStatus);
        response.setEntity(entityFromOkHttpResponse(okHttpResponse));

        Headers responseHeaders = okHttpResponse.headers();

        for (int i = 0, len = responseHeaders.size(); i < len; i++) {
            final String name = responseHeaders.name(i), value = responseHeaders.value(i);

            if (name != null) {
                response.addHeader(new BasicHeader(name, value));
            }
        }
        return response;
    }

    @SuppressWarnings("deprecation")
    /* package */ static void setConnectionParametersForRequest(okhttp3.Request.Builder builder,
                                                                Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case Request.Method.DEPRECATED_GET_OR_POST:
                // This is the deprecated way that needs to be handled for backwards compatibility.
                // If the request's post body is null, then the assumption is that the request is
                // GET.  Otherwise, it is assumed that the request is a POST.
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.getPostBodyContentType()), postBody));
                }
                break;
            case Request.Method.GET:
                // Not necessary to set the request method because connection defaults to GET but
                // being explicit here.
                builder.get();
                break;
            case Request.Method.DELETE:
                builder.delete();
                break;
            case Request.Method.POST:
                builder.post(createRequestBody(request));
                break;
            case Request.Method.PUT:
                builder.put(createRequestBody(request));
                break;
            case Request.Method.HEAD:
                builder.head();
                break;
            case Request.Method.OPTIONS:
                builder.method("OPTIONS", null);
                break;
            case Request.Method.TRACE:
                builder.method("TRACE", null);
                break;
            case Request.Method.PATCH:
                builder.patch(createRequestBody(request));
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static ProtocolVersion parseProtocol(final Protocol p) {
        switch (p) {
            case HTTP_1_0:
                return new ProtocolVersion("HTTP", 1, 0);
            case HTTP_1_1:
                return new ProtocolVersion("HTTP", 1, 1);
            case SPDY_3:
                return new ProtocolVersion("SPDY", 3, 1);
            case HTTP_2:
                return new ProtocolVersion("HTTP", 2, 0);
        }

        throw new IllegalAccessError("Unkwown protocol");
    }

    private static HttpEntity entityFromOkHttpResponse(Response r) throws IOException {
        BasicHttpEntity entity = new BasicHttpEntity();
        ResponseBody body = r.body();

        entity.setContent(body.byteStream());
        entity.setContentLength(body.contentLength());
        entity.setContentEncoding(r.header("Content-Encoding"));

        if (body.contentType() != null) {
            entity.setContentType(body.contentType().type());
        }
        return entity;
    }

    private static RequestBody createRequestBody(Request r) throws AuthFailureError {
        final byte[] body = r.getBody();
        if (body == null) return null;

        return RequestBody.create(MediaType.parse(r.getBodyContentType()), body);
    }
}
