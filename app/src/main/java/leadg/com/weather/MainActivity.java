package leadg.com.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
 private String dayli_url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Kazan&mode=json&units=metric&cnt=14&appid=df5f9680024087caecf0b40f68bf07f9";
    private String now_weather = "http://api.openweathermap.org/data/2.5/weather?id=551487&appid=df5f9680024087caecf0b40f68bf07f9&units=metric";
    private AsyncHttpClient client;
    private TextView now, morning, evening;
    private ListView weather;
    private TextClock textClock;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new AsyncHttpClient();
        initUI();
        LoadDayli();
        LoadNow();
    }
    public void initUI()
    {
now = (TextView)findViewById(R.id.now);
        weather = (ListView)findViewById(R.id.weather);
        morning = (TextView)findViewById(R.id.morning);
        evening = (TextView)findViewById(R.id.evening);
        textClock  = (TextClock) findViewById(R.id.textClock);

    }
public void LoadNow()
{
    final MainActivity _this = this;
    client.get(now_weather,new JsonHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            try {
                JSONObject main = response.getJSONObject("main");
              now.setText("Сейчас на улице " + main.getString("temp") + " °C");
               morning.setText("Давление " + main.getString("pressure") + " мм р.с");
               evening.setText("Влажность воздуха " + main.getString("humidity") + "%");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });
}
    public void LoadDayli()
    {
        final MainActivity _this = this;
client.get(dayli_url,new JsonHttpResponseHandler() {

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        WeatherItem[] items;
        try {
            JSONArray list = response.getJSONArray("list");
            items = new WeatherItem[list.length()];
            for(int  i=0;i < list.length();i ++)
            {
                JSONObject item = list.getJSONObject(i).getJSONObject("temp");

items[i] = new WeatherItem(item.getString("morn"),item.getString("day"),item.getString("eve"),item.getString("night"));
weather.setAdapter(new WeatherAdapter(_this,items));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
});
    }
}
