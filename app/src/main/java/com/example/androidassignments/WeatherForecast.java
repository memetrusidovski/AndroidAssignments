package com.example.androidassignments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;



import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecast extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    public ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather_forecast);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
        pb.setMax(100);
        pb.setProgress(0);

        new ForecastQuery().execute("https://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=e7e6f4323f7764d5bc2b4d723cbe2c75&mode=xml&units=metric");



    }


    class ForecastQuery extends AsyncTask<String, Integer, String> {

        public String current;
        private String min;
        private String max;
        private String bitmap;
        private String icon = "10n";
        private Bitmap im;


        @Override
        protected String doInBackground(String... strings) {
            try {
                publishProgress(10);
                String s = loadXmlFromNetwork(strings[0]);
                im = getIcon(icon);

                publishProgress(100);

                return s;
            } catch (IOException e) {
                return getResources().getString(R.string.connection_error);
            } catch (XmlPullParserException e) {
                return getResources().getString(R.string.xml_error);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("XML", "Updating UI");
            ImageView imageView = (ImageView) findViewById(R.id.imageView3);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(im, 70,70, false));
            TextView t1 = (TextView) findViewById(R.id.currentTemp);
            TextView t2 = (TextView) findViewById(R.id.minTemp);
            TextView t3 = (TextView) findViewById(R.id.maxTemp);
            t1.setText("Current Temp: " + current);
            t2.setText("Min Temp: " + min);
            t3.setText("Max Temp: " + max);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(values != null && pb != null)
            pb.setProgress(values[0]);
        }



        public Bitmap getIcon(String n) throws IOException{
            String iconName = n;

                            //              https://i.cbc.ca/1.6649775.1668254477!/fileImage/httpImage/image.jpg_gen/derivatives/original_780/1244694521.jpg
            Bitmap image  = getImage("https://openweathermap.org/img/w/" + iconName + ".png");
            //InputStream ip = downloadUrl("http://openweathermap.org/img/w/" + iconName + ".png");
            //Bitmap image = BitmapFactory.decodeStream(ip);

            FileOutputStream outputStream = openFileOutput(   iconName + ".png", Context.MODE_PRIVATE);

            image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
            //outputStream.flush();
            //outputStream.close();



            /*FileInputStream fis = null;
            try {    fis = openFileInput(iconName + ".png" );   }
            catch (FileNotFoundException e) {    e.printStackTrace();  }
            Bitmap bm = BitmapFactory.decodeStream(fis);

             */

            return image;

        }
        // Uploads XML from stackoverflow.com, parses it, and combines it with
// HTML markup. Returns HTML string.
        private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
            InputStream stream = null;

            try {
                stream = downloadUrl(urlString);
            } finally {
                if (stream != null) {
                    //stream.close();
                }
            }

            publishProgress(50);

            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);
            parser.nextTag();

            parser.require(XmlPullParser.START_TAG, null, "current");
            parser.nextTag();


            publishProgress(70);
            Log.d("XML", parser.getName());

            List entries = new ArrayList();
            while (parser.next() != XmlPullParser.END_DOCUMENT) {

                if(parser.getName() != null && parser.getEventType() != XmlPullParser.END_TAG) {
                    Log.d("XML", parser.getName());
                    if(parser.getName().equals("temperature")){
                        Log.d("XML", parser.getAttributeValue(0));
                        current = parser.getAttributeValue(0);
                        min = parser.getAttributeValue(1);
                        max = parser.getAttributeValue(2);
                    }
                    if(parser.getName().equals("weather")){
                        Log.d("XML", "->" + parser.getAttributeValue(2));
                        icon = parser.getAttributeValue(2);
                    }

                }
            }


            publishProgress(80);



            Log.d("XML", "DONE");

            return stream.toString();
        }

        private InputStream downloadUrl(String urlString) throws IOException {
            publishProgress(30);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            Log.d("XML", "Connecting..." + urlString);
            conn.connect();
            Log.d("XML", conn.getResponseMessage());

            publishProgress(40);

            conn.disconnect();

            return conn.getInputStream();
        }

        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();   }
    }


        public Bitmap getImage(String urlString)  throws IOException {
            Log.d("XML", "->" + urlString);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(urlString).getContent());

            //connection.disconnect();
            return bitmap;
        }



}
