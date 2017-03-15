package com.github.why168.volley.toolbox;

import com.android.internal.http.multipart.Part;
import com.github.why168.volley.AuthFailureError;
import com.github.why168.volley.Response;
import com.github.why168.volley.VolleyLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * MultipartRequest
 *
 * @author Edwin.Wu
 * @version 2017/3/2$ 11:58$
 * @since JDK1.8
 */
public class MultipartRequest extends StringRequest {
    private Part[] parts;

    public MultipartRequest(String url, Part[] parts, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        this.parts = parts;
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data; boundary=" + Part.getBoundary();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Part.sendParts(baos, parts);
        } catch (IOException e) {
            VolleyLog.e(e, "error when sending parts to output!");
        }
        return baos.toByteArray();
    }
}
