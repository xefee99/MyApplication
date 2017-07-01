package kr.ac.seoultech.myapplication.http;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UserHttpService {

    private final static String TAG = UserHttpService.class.getSimpleName();

    private final static String URL_SERVER = "http://10.20.26.22:8080/todoapi";

    private Context context;


    public void join(String loginId, String password, String name) {

        try {
            URL url = new URL(URL_SERVER + "/join");
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            // 데이터 전송
            StringBuilder sb = new StringBuilder();
            sb.append("loginId=").append(loginId).append("&");
            sb.append("password=").append(password).append("&");
            sb.append("name=").append(name);

            OutputStream os = connection.getOutputStream();
            os.write(sb.toString().getBytes());
            os.flush();

            // 데이터 수신
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            char[] buffer = new char[4096];
            int size = 0;
            StringBuilder resultSb = new StringBuilder();
            while ((size = br.read(buffer)) != -1) {
                resultSb.append(buffer, 0, size);
            }

            Toast.makeText(context, resultSb.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "result : " + resultSb.toString());


        } catch (Exception e) {
            e.printStackTrace();;
        }


    }


}
