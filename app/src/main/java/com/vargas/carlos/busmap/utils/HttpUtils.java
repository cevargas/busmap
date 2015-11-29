package com.vargas.carlos.busmap.utils;

import android.util.Log;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    public static Reader get(String surl) {

        try {

            URL url = new URL(surl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStream is = new BufferedInputStream(http.getInputStream());
            Reader reader = new InputStreamReader(is);

            return reader;

        } catch (MalformedURLException e) {
            Log.e("MAIN", "error", e);
        } catch (IOException e) {
            Log.e("MAIN", "error", e);
        }

        return null;
    }

    public static <T> T get(String surl, Class<T> cc){
        Reader reader = HttpUtils.get(surl);
        T rs =  new Gson().fromJson(reader, cc);
        return rs;
    }
}
