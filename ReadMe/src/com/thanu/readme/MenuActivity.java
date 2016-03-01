package com.thanu.readme;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends Activity implements View.OnClickListener {

	private ImageButton speakit;
	private ImageButton findit;
	private ImageButton spellit;
	Intent startIntent;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initialize();
		
		/*PackageInfo pInfo;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String version = pInfo.versionName;
			int versionNumber = pInfo.versionCode;
			System.out.println("Version number  " + version  + " " + versionNumber);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
                  /*System.out.println("version code " + 	android.os.Build.VERSION.SDK_INT  + " " + android.os.Build.VERSION_CODES.GINGERBREAD_MR1  + " " +
                			android.os.Build.VERSION.RELEASE
                		  );
		     // only for gingerbread and newer versions
*/                  
                 // Float  versionName = Float.parseFloat(android.os.Build.VERSION.RELEASE);
		
		System.out.println("Before finding release version");
		String version = android.os.Build.VERSION.RELEASE;
                
                  if ( android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.GINGERBREAD_MR1 ) {
                	  
                	  spellit.setVisibility(ImageView.GONE);
                	  
                	  
                  }
                  
		
		int density= getResources().getDisplayMetrics().densityDpi;
		   switch(density)
		  {
		  case DisplayMetrics.DENSITY_LOW:
		     //Toast.makeText(this, "LDPI", Toast.LENGTH_SHORT).show();
		     System.out.println("low density ");
		      break;
		  case DisplayMetrics.DENSITY_MEDIUM:
		      // Toast.makeText(this, "MDPI", Toast.LENGTH_SHORT).show();
		       System.out.println("Medium density ");// 4.0 screen   confused 9 inch tablet
		      break;
		  case DisplayMetrics.DENSITY_HIGH:
		    // Toast.makeText(this, "HDPI", Toast.LENGTH_SHORT).show();
		      System.out.println("High density ");  //  4.5 to 7 inch
		      break;
		  case DisplayMetrics.DENSITY_XHIGH:
		     //  Toast.makeText(this, "XHDPI", Toast.LENGTH_SHORT).show();
		       System.out.println("very density ");
		      break;
		  }
		   
		   
		   int screenSize = getResources().getConfiguration().screenLayout &Configuration.SCREENLAYOUT_SIZE_MASK;
		   switch(screenSize) {
		       case Configuration.SCREENLAYOUT_SIZE_LARGE:
		         //Toast.makeText(this, "Large screen",Toast.LENGTH_LONG).show();
		         System.out.println("Large Screen ");  // upto 4.5 to 7
		          break;
		        case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		        //   Toast.makeText(this, "Normal screen",Toast.LENGTH_LONG).show();
		           System.out.println("Normal Screen ");  // 4.0 screen
		            break;
		        case Configuration.SCREENLAYOUT_SIZE_SMALL:
		           // Toast.makeText(this, "Small screen",Toast.LENGTH_LONG).show();
		            System.out.println("small Screen ");
		            break;
		        default:
		           // Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();
		            System.out.println("default Screen ");
		   }


		           
		            
		            // 960   540  pixel
		            // 640   360  density
		            
		            //  960/1.5  ,  540/1.5
		            
		            
		            /*Display display = getWindowManager().getDefaultDisplay();
		            Point size = new Point();
		            display.getSize(size);
		            int width = size.x;
		            int height = size.y;
		            System.out.println("Phoe with and height in pixel "+  width  + "  " + height);*/
		            //Toast.makeText(this, "P " + width + " " + height  , Toast.LENGTH_LONG).show();
		            
		            
		            
		            
		            
		            
		            
		
	}

	private void initialize() {
		// TODO Auto-generated method stub

		speakit = (ImageButton) findViewById(R.id.imageButton1);
		spellit = (ImageButton) findViewById(R.id.imageButton2);
		findit = (ImageButton) findViewById(R.id.imageButton3);
		speakit.setOnClickListener(this);
		spellit.setOnClickListener(this);
		findit.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		switch (view.getId()) {
		case R.id.imageButton1:
			startIntent = new Intent(this, MainActivity.class);
			startActivity(startIntent);

			break;
			
		case R.id.imageButton3:
			startIntent = new Intent(this, Findname.class);
			startActivity(startIntent);

			break;
			
		case R.id.imageButton2:
			startIntent = new Intent(this, SpellActivity.class);
			startActivity(startIntent);

			break;


		default:
			break;
		}

	}

}
