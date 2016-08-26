package leadg.com.weather;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Админ on 25.08.2016.
 */
public class WeatherAdapter extends BaseAdapter {
    private WeatherItem[] items;
    private Activity context;
    public WeatherAdapter(Activity context, WeatherItem[] items)
    {
        this.items = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view  == null)
            view = context.getLayoutInflater().inflate(R.layout.weather_item,null);
        TextView morn = (TextView)view.findViewById(R.id.morn);
        TextView day = (TextView)view.findViewById(R.id.day);
        TextView eve = (TextView)view.findViewById(R.id.eve);
        TextView night = (TextView)view.findViewById(R.id.night);
        morn.setText("Утро: " + items[i].morn + " °C");
        day.setText("День: " + items[i].day + "°C");
        eve.setText("Вечер: " + items[i].eve + "°C");
        night.setText("Ночь: " + items[i].night + "°C");
        return view;
    }
}
