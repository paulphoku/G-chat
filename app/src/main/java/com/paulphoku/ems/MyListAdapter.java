package com.paulphoku.ems;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter {
    private final Activity context;
    private final String[] trans_day;
    private final String[] trans_monthYear;
    private final String[] trans_amt;
    private final String[] trans_title;
    private final String[] trans_type;




    public MyListAdapter(Activity context, String[] trans_day, String[] trans_monthYear,String[] trans_amt, String[] trans_type,String[]  trans_title) {
//        super(context, R.layout.lst_v_trans, "maintitle");
        // TODO Auto-generated constructor stub

        this.context=context;
        this.trans_type=trans_type;
        this.trans_amt=trans_amt;
        this.trans_monthYear=trans_monthYear;
        this.trans_day=trans_day;
        this.trans_title=trans_title;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lst_v_trans, null,true);

        TextView title = (TextView) rowView.findViewById(R.id.trans_tile);
        TextView amt = (TextView) rowView.findViewById(R.id.trans_amt);
        TextView day = (TextView) rowView.findViewById(R.id.trans_day);
        TextView type = (TextView) rowView.findViewById(R.id.trans_type);
        TextView month = (TextView) rowView.findViewById(R.id.trans_monthYear);

        title.setText(trans_title[position]);
        amt.setText(trans_amt[position]);
        day.setText(trans_day[position]);
        type.setText(trans_type[position]);
        month.setText(trans_monthYear[position]);


        return rowView;

    };

}
