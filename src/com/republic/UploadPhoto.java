package com.republic;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadPhoto extends AsyncTask<Void, Void, String> {
    byte[] data;
    public UploadPhoto(byte[] data){
        this.data = data;
    }

    public String doInBackground(Void... input)      {
        String responseString;
        try{
            String postURL = "http://app-republic.appspot.com/image";      // URL on server for echo test
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(postURL);

            MultipartEntity fileEntity = new MultipartEntity(HttpMultipartMode.STRICT);
            fileEntity.addPart("image", new ByteArrayBody(data, "image/png", "camera.png"));
            postRequest.setEntity(fileEntity);
            HttpResponse response = httpclient.execute(postRequest);        // Execute request against server, get response
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){             // If everything went ok, read off response
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (Exception e){
            responseString = e.toString();
        }
        return responseString;
    }

    public void onPostExecute(String response){ }
}