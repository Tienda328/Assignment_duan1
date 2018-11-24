package com.fpoly.dong.assignment_duan1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fpoly.dong.assignment_duan1.adapter.AdapterTinTuc;
import com.fpoly.dong.assignment_duan1.model.News;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class NewsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText editText;
    private Button btn;
    private ArrayList<News> news;
    private AdapterTinTuc adapterLab;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tin Tuc");
        toolbar.setNavigationIcon(R.drawable.iconback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        news = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this,linearLayoutManager.VERTICAL,false);
       final String link = "https://bongda24h.vn/RSS/1.rss";
        new Readdata().execute(link);
    }
    public class Readdata extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMPaser xmldomParser = new XMLDOMPaser();
            Document document = xmldomParser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            String summary = "";
            String link = "";
            for(int i=0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                title = xmldomParser.getValue(element, "title");
                summary = xmldomParser.getValue(element, "description");
                link = xmldomParser.getValue(element, "link");
                news.add(new News(title, summary,link));

            }

            adapterLab = new AdapterTinTuc(NewsActivity.this, news);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterLab);
            super.onPostExecute(s);
        }

    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}



