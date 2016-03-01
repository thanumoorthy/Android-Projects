package com.thanu.yogasanam;

import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LaunchActivity extends Activity implements View.OnClickListener {

	TextView heading;
	TextView content;
	Button go;
	Intent yoga;
	Typeface tf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Yogasanam");
		setContentView(R.layout.activity_launch);
		tf = Typeface.createFromAsset(getAssets(), "fonts/SaiVrishintscii.ttf");

		heading = (TextView) findViewById(R.id.title);		
		content = (TextView) findViewById(R.id.textView1);
		heading.setTypeface(tf);
		content.setTypeface(tf);
		heading.setText(Html.fromHtml(TamilUtil.convertToTamil(TamilUtil.TSCII,"<b>யோகாசனம்</b>")));
		content.setText(Html.fromHtml(TamilUtil.convertToTamil(TamilUtil.TSCII,"உலகம் முழுவதும் மிக வேகமாக பரவி வருவது யோகக்கலை.யோகமானது சராசரி மனிதனுக்கு அழகு,ஆரோக்கியம், இளமை தந்து நன்மை பயக்ககூடியது .எளிமையானது.ஆண், பெண் வயது வேறுபாடின்றி அனைவருக்கும் உகந்தது.ஆங்கில வைத்திய முறைகளால் முழுவதும் குணமாக்க முடியாத ஆஸ்துமா, நீரிழிவு மற்றும் வாத நோய்களையும் யோகப் பயிற்சிகளினால் கட்டுப்படுத்தலாம்." +
				"<br/>இதில் நீங்கள் <b>40 வகையான யோகப் பயிற்சிகளை</b> தெரிந்து கொள்ளலாம்.சில பயிற்சிகளுக்கு வீடியோ தொகுப்புகள் இணைக்கப்பட்டுள்ளது.")));
		
		go = (Button) findViewById(R.id.yoga);
		
		go.setTypeface(tf);
		go.setText(TamilUtil.convertToTamil(TamilUtil.TSCII,"பயிற்சிகள்"));
		go.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.yoga:
			yoga = new Intent("android.intent.action.YOGAMENU");
			startActivity(yoga);
			break;

		}

	}

}
