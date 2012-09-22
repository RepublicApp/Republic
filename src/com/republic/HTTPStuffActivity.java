package com.republic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HTTPStuffActivity extends Activity {
    private ImageView img;

    private class GetImageAsync extends AsyncTask<Void, Void, Bitmap>{
        public Bitmap doInBackground(Void... input){
            try{
                HttpClient httpclient = new DefaultHttpClient();

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                URI uri = URIUtils.createURI(   "http", "i.imgur.com", -1, "/AxUkT.jpg",
                        URLEncodedUtils.format(params, "UTF-8"), null);

                HttpGet getRequest = new HttpGet(uri);
                HttpResponse response = httpclient.execute(getRequest);        // Execute request against server, get response
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){             // If everything went ok, read off response
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    return BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
                } else{
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (Exception e){
                // Probably should do something here
            }
            return null;
        }

        public void onPostExecute(Bitmap result){
            img.setImageBitmap(result);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                                 // Possibly restore from saved state

        img = new ImageView(this);
        setContentView(img);

        GetImageAsync async = new GetImageAsync();
        async.execute();
    }
}