package com.thanu.yogasanam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

public class HomeActivity extends Activity {

	TextView tv;
	Typeface tf;
	ListView list;
	Intent detailIntent;
	String[] web = new String[40];
	Integer[] imageId = new Integer[40];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setTitle("Yogasanam");
		intialize();

		CustomList adapter = new CustomList(HomeActivity.this, web, imageId);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(HomeActivity.this, getString(R.string.yoga8),
				// Toast.LENGTH_SHORT).show();
				detailIntent = new Intent("android.intent.action.DETAIL");
				detailIntent.putExtra("indexValue", "yoga_"
						+ (position + 1));
				startActivity(detailIntent);

			}
		});

		/*
		 * tv = (TextView) findViewById(R.id.tv); tv.setTypeface(tf); String
		 * TSCIIString = TamilUtil.convertToTamil(TamilUtil.TSCII,
		 * "வணக்கம் அன்ரொயிட்   , யோகாசனம் "); tv.setText(TSCIIString);
		 */
	}

	private void intialize() {
		tf = Typeface.createFromAsset(getAssets(), "fonts/SaiVrishintscii.ttf");
		web[0] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga1));
		web[1] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga2));
		web[2] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga3));
		web[3] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga4));
		web[4] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga5));
		web[5] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga6));
		web[6] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga7));
		web[7] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga8));
		web[8] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga9));
		web[9] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga10));
		web[10] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga11));
		web[11] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga12));
		web[12] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga13));
		web[13] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga14));
		web[14] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga15));
		web[15] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga16));
		web[16] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga17));
		web[17] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga18));
		web[18] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga19));
		web[19] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga20));
		web[20] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga21));
		web[21] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga22));
		web[22] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga23));
		web[23] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga24));
		web[24] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga25));
		web[25] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga26));
		web[26] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga27));
		web[27] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga28));
		web[28] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga29));
		web[29] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga30));
		web[30] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga31));
		web[31] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga32));
		web[32] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga33));
		web[33] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga34));
		web[34] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga35));
		web[35] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga36));
		web[36] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga37));
		web[37] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga38));
		web[38] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga39));
		web[39] = TamilUtil.convertToTamil(TamilUtil.TSCII,
				getString(R.string.yoga40));

		imageId[0] = R.drawable.icon_1;
		imageId[1] = R.drawable.icon_2;
		imageId[2] = R.drawable.icon_3;
		imageId[3] = R.drawable.icon_4;
		imageId[4] = R.drawable.icon_5;
		imageId[5] = R.drawable.icon_6;
		imageId[6] = R.drawable.icon_7;
		imageId[7] = R.drawable.icon_8;
		imageId[8] = R.drawable.icon_9;
		imageId[9] = R.drawable.icon_10;
		imageId[10] = R.drawable.icon_11;
		imageId[11] = R.drawable.icon_12;
		imageId[12] = R.drawable.icon_13;
		imageId[13] = R.drawable.icon_14;
		imageId[14] = R.drawable.icon_15;
		imageId[15] = R.drawable.icon_16;
		imageId[16] = R.drawable.icon_17;
		imageId[17] = R.drawable.icon_18;
		imageId[18] = R.drawable.icon_19;
		imageId[19] = R.drawable.icon_20;
		imageId[20] = R.drawable.icon_21;
		imageId[21] = R.drawable.icon_22;
		imageId[22] = R.drawable.icon_23;
		imageId[23] = R.drawable.icon_24;
		imageId[24] = R.drawable.icon_25;
		imageId[25] = R.drawable.icon_26;
		imageId[26] = R.drawable.icon_27;
		imageId[27] = R.drawable.icon_28;
		imageId[28] = R.drawable.icon_29;
		imageId[29] = R.drawable.icon_30;
		imageId[30] = R.drawable.icon_31;
		imageId[31] = R.drawable.icon_32;
		imageId[32] = R.drawable.icon_33;
		imageId[33] = R.drawable.icon_34;
		imageId[34] = R.drawable.icon_35;
		imageId[35] = R.drawable.icon_36;
		imageId[36] = R.drawable.icon_37;
		imageId[37] = R.drawable.icon_38;
		imageId[38] = R.drawable.icon_39;
		imageId[39] = R.drawable.icon_40;
		

	}
	/*
	 * public String getyogaName(String type){
	 * 
	 * String name = (String)
	 * getResources().getText(getResources().getIdentifier(type, "string",
	 * null));
	 * 
	 * return name;
	 * 
	 * }
	 */
}
