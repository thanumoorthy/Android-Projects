package com.thanu.yogasanam;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;

public class CustomList extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	Typeface tf ;
	public CustomList(Activity context,
	String[] web, Integer[] imageId) {
	super(context, R.layout.list_row, web);
	this.context = context;
	this.web = web;
	this.imageId = imageId;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
	tf = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/SaiVrishintscii.ttf");
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.list_row, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
	txtTitle.setTypeface(tf);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);
	txtTitle.setText(web[position]);
	imageView.setImageResource(imageId[position]);
	return rowView;
	}
}
