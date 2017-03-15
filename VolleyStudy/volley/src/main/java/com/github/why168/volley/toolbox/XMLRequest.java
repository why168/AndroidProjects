package com.github.why168.volley.toolbox;

import com.github.why168.volley.NetworkResponse;
import com.github.why168.volley.ParseError;
import com.github.why168.volley.Request;
import com.github.why168.volley.Response;
import com.github.why168.volley.Response.ErrorListener;
import com.github.why168.volley.Response.Listener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * XMLRequest
 *
 * @author Edwin.Wu
 * @version 2017/3/14 17:42
 * @since JDK1.8
 */
public class XMLRequest extends Request<XmlPullParser> {
    private final Listener<XmlPullParser> mListener;

    public XMLRequest(int method, String url, Listener<XmlPullParser> listener,
                      ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public XMLRequest(String url, Listener<XmlPullParser> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        mListener.onResponse(response);
    }
}
