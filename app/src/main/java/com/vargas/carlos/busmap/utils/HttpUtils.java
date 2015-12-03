package com.vargas.carlos.busmap.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    static URL url;
    static HttpURLConnection http;
    static InputStream is;
    static Reader reader;

    public static Reader get(String surl) {

        try {

            url = new URL(surl);
            http = (HttpURLConnection) url.openConnection();

            is = new BufferedInputStream(http.getInputStream());
            reader = new InputStreamReader(is);

            return reader;

        } catch (IOException e) {
            Log.e("HttpUtils", "error", e);
        }

        return null;
    }

    public static <T> T get(String surl, Class<T> cc) {
        reader = HttpUtils.get(surl);
        T rs = new Gson().fromJson(reader, cc);
        return rs;
    }
}
