package com.github.why168.volley.toolbox;

import com.github.why168.volley.AuthFailureError;
import com.github.why168.volley.NetworkResponse;
import com.github.why168.volley.ParseError;
import com.github.why168.volley.Request;
import com.github.why168.volley.Response;
import com.github.why168.volley.Response.ErrorListener;
import com.github.why168.volley.Response.Listener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson. * @author Edwin.Wu
 *
 * @version 2017/3/2$ 11:15$
 * @since JDK1.8
 */
public class GsonRequest<T> extends Request<T> {
    private Gson gson;
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers, Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat("MM/dd/yy HH:mm:ss");
            gson = builder.create();
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}