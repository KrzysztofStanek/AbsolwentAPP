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

    public String request(Map<String, String> params) throws ExecutionException, InterruptedException {
        String params_txt="";

        for (Map.Entry<String, String> entry : params.entrySet()) {
            params_txt += "&"+entry.getKey()+"="+entry.getValue();
        }
        String request_url = this.url_base+"?"+"api_key="+this.api_key+"&request_key="+this.request_key+params_txt;
        JsonTask task = new JsonTask();
        task.execute(request_url).get();
        return task.responde;

    }

    public Map<String, String> createData(String json) throws JSONException {
        Map<String, String> data = new HashMap<>();
        Log.d("create", json);
        JSONObject obj = new JSONObject(json);
        data.put("status",obj.getString("status") );
        data.put("desc",obj.getString("desc") );
        data.put("data",obj.getString("data") );

        data.put("auth_id",obj.getString("auth_id") );
        data.put("user_id",obj.getString("user_id") );
        data.put("nick",obj.getString("nick") );
        return data;
    }

    //REJESTRACJA

    public Map<String, String> rejestruj(String nick, String imie, String haslo, String miejscowosc, String opis, String data_urodzenia) throws Exception {

        String pass_hash = Password.hash(haslo);

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "register_user");

        parametr.put("nick", nick);
        parametr.put("imie", imie);
        parametr.put("haslo", pass_hash);
        parametr.put("miejscowosc", miejscowosc);
        parametr.put("opis", opis);
        parametr.put("data_urodzenia", data_urodzenia);

        Map<String, String> data = new HashMap<>();

                
        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Boolean czyKontoIstnieje(String nick) throws JSONException, ExecutionException, InterruptedException {
        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "account_exist");

        parametr.put("nick", nick);

        Map<String, String> data = new HashMap<>();

        try {
            String responde = this.request(parametr);
            data = this.createData(responde);
            if(data.get("data").equals("TRUE")){
                Log.d("czyKontoIstnieje", "istnieje");
                return true;
            }
            else{
                Log.d("czyKontoIstnieje", "nie istnieje"+data.get("data"));
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }


    public Boolean zaloguj(String nick, String haslo) throws Exception {
        Map<String, String> parametr = new HashMap<>();

        String pass_hash = Password.hash(haslo);
        parametr.put("action", "login");

        parametr.put("nick", nick);
        parametr.put("haslo", pass_hash);

        Map<String, String> data = new HashMap<>();

        try {
            String responde = this.request(parametr);
            data = this.createData(responde);
            if(data.get("data").equals("TRUE")){
                String nick_ = data.get("nick");
                String id_ = data.get("user_id");;
                String auth_id_=data.get("auth_id");;
                autoryzacja.zaloguj(nick_, id_, auth_id_);
                return true;
            }
            else{
                //nie loguj
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean czyZalogowany() throws Exception {

        if(autoryzacja.zalogowany){
            Map<String, String> parametr = new HashMap<>();

            parametr.put("action", "isLogged");

            parametr.put("id", autoryzacja.user_id);
            parametr.put("auth_id", autoryzacja.auth_id);

            Map<String, String> data = new HashMap<>();

            try {
                String responde = this.request(parametr);
                data = this.createData(responde);
                if(data.get("data").equals("TRUE")){
                    autoryzacja.zalogowany=true;
                    return true;
                }
                else{
                    autoryzacja.zalogowany = false;
                    return false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }
        else{
            return false;
        }

    }


    public Boolean wyloguj() throws Exception {

        if(autoryzacja.zalogowany){
            Map<String, String> parametr = new HashMap<>();

            parametr.put("action", "logout");

            parametr.put("id", autoryzacja.user_id);
            parametr.put("auth_id", autoryzacja.auth_id);

            Map<String, String> data = new HashMap<>();

            try {
                String responde = this.request(parametr);
                data = this.createData(responde);
                if(data.get("data").equals("TRUE")){
                    autoryzacja.zalogowany=false;
                    return true;
                }
                else{
                    autoryzacja.zalogowany = false;
                    return false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }
        else{
            return false;
        }

    }




}
