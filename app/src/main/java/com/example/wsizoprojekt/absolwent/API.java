package com.example.wsizoprojekt.absolwent;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.*;

public class API {
    public static String result="";

    public String url_base = "http://91.196.48.246/absolwentAPP/request_2314151352.php";
    public String api_key = "15135fdg34245g1fdas1agKKas1";
    public String request_key = "jjGMg221gb02199gva";
    ///request_2314151352.php?api_key=15135fdg34245g1fdas1agKKas1&request_key=jjGMg221gb02199gva

    public void request(Map<String, String> params) throws ExecutionException, InterruptedException {
        String params_txt="";

        for (Map.Entry<String, String> entry : params.entrySet()) {
            params_txt += "&"+entry.getKey()+"="+entry.getValue();
        }
        String request_url = this.url_base+"?"+"api_key="+this.api_key+"&request_key="+this.request_key+params_txt;
        new JsonTask().execute(request_url).get();
        Log.d("W wydoku", result);

    }

    public Map<String, String> createData(String json) throws JSONException {
        Map<String, String> data = new HashMap<>();
        Log.d("create", json);
        JSONObject obj = new JSONObject(json);
        data.put("status",obj.getString("status") );
        return data;
    }

    //REJESTRACJA

    public Map<String, String> rejestruj(String nick, String imie, String haslo, String miejscowosc, String opis, String data_urodzenia) throws JSONException, ExecutionException, InterruptedException {
        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "register_user");

        parametr.put("nick", nick);
        parametr.put("imie", imie);
        parametr.put("haslo", haslo);
        parametr.put("miejscowosc", miejscowosc);
        parametr.put("opis", opis);
        parametr.put("data_urodzenia", data_urodzenia);

        this.request(parametr);

        Map<String, String> data = null;
                
        try {
            this.request(parametr);
            data = this.createData("{\"status\":\"SUCCESS\",\"desc\":\"ZAREJESTROWANO\"}");
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }




}
