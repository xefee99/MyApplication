package kr.ac.seoultech.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kr.ac.seoultech.myapplication.R;
import kr.ac.seoultech.myapplication.model.Todo;

public class TodoAdapter extends BaseAdapter {

    private Context context;
    private int layoutId;
    private List<Todo> items;
    private LayoutInflater inflater;

    public TodoAdapter(Context context, int layoutId, List<Todo> items) {
        this.context = context;
        this.layoutId = layoutId;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(this.layoutId, null);
        }

        TextView txtCreateAt = (TextView) convertView.findViewById(R.id.txt_create_at);




        return convertView;
    }
}
