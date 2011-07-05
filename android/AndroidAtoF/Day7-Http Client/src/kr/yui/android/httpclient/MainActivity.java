package kr.yui.android.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	
	private int imageNum = 1;
	private ImageView imageView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imageView = (ImageView)findViewById(R.id.main_imageview_id);
        initButtons();
    }
    
    
    private void initButtons() {
    	((Button)findViewById(R.id.main_getimgbtn_id)).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				loadImage();
			}
		});
    	
    	((Button)findViewById(R.id.main_getmusicbtn_id)).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private void loadImage() {
    	
    	try {
    		
    		
    		System.out.println("ImageNumber:=========>" + imageNum);
    		URL url = new URL("http://www.telepasi.kr/share/photo" + (imageNum+1) + ".jpg");
    		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    		
    		conn.setDoInput(true);
    		conn.setReadTimeout(5000);
    		conn.connect();
    		
    		InputStream stream = conn.getInputStream();
    		
    		Bitmap bm = BitmapFactory.decodeStream(stream);
    		imageView.setImageBitmap(bm);
    		imageView.setScaleType(ScaleType.CENTER);
    		
    		conn.disconnect();
    		
    		imageNum = ((imageNum++) % 5) + 1;
    		
    	} catch (IOException e) {
    		
    	}
    	
    }
    
}