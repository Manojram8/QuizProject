package com.example.quizproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private List<HistoryItem> historyList;

    public HistoryAdapter(Context context, List<HistoryItem> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        }

        HistoryItem historyItem = historyList.get(position);

        TextView topicTextView = convertView.findViewById(R.id.topicTextView);
        TextView scoreTextView = convertView.findViewById(R.id.scoreTextView);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);

        topicTextView.setText(historyItem.getTopic());
        scoreTextView.setText("Score: " + historyItem.getScore());
        dateTextView.setText("Date: " + historyItem.getDate());

        return convertView;
    }
}
