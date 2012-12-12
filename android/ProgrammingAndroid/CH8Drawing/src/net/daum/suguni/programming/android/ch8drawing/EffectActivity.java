package net.daum.suguni.programming.android.ch8drawing;

import net.daum.suguni.programming.android.ch8drawing.drawable.HelloAndroidTextDrawable;
import net.daum.suguni.programmingandroid.ch8drawing.view.EffectWidget;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EffectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_effect);
		
		LinearLayout container1 = (LinearLayout) findViewById(R.id.effect_container1);
		LinearLayout container2 = (LinearLayout) findViewById(R.id.effect_container2);
		
		container1.addView(new EffectWidget(this, 1,
				new EffectWidget.PaintEffect() {
					@Override
					public void setEffect(Paint paint) {
						paint.setShadowLayer(1f, 3f, 4f, Color.BLUE);
					}
				}));
		container2.addView(new EffectWidget(this, 2,
				new EffectWidget.PaintEffect() {
					@Override
					public void setEffect(Paint paint) {
						paint.setShadowLayer(3f, -8f, 7f, Color.GREEN);
					}
				}));
		
		container1.addView(new EffectWidget(this, 3,
				new EffectWidget.PaintEffect() {
					@Override
					public void setEffect(Paint paint) {
						paint.setShader(
								new LinearGradient(
										0f, 0f, 100f, 10f,
										new int[] { Color.BLACK, Color.RED, Color.YELLOW },
										new float[] { 0f, 0.5f, 0.95f },
										Shader.TileMode.REPEAT));
					}
				}));
		container2.addView(new EffectWidget(this, 4,
				new EffectWidget.PaintEffect() {
					@Override
					public void setEffect(Paint paint) {
						paint.setShader(
								new LinearGradient(
										0f, 40f, 15f, 40f,
										Color.BLUE, Color.GREEN,
										Shader.TileMode.REPEAT));
					}
				}));
		
		container1.addView(new EffectWidget(this, 5,
				new EffectWidget.PaintEffect() {
					@Override
					public void setEffect(Paint paint) {
						paint.setMaskFilter(new BlurMaskFilter(2, BlurMaskFilter.Blur.NORMAL));
					}
				}));
		View w = new EffectWidget(this, 6,
				new EffectWidget.PaintEffect() {
					@Override
					public void setEffect(Paint paint) { }
				});
		container2.addView(w);
		w.setBackgroundResource(R.drawable.throbber);
		AnimationDrawable drawable = (AnimationDrawable) w.getBackground();
		drawable.start();
		
		TranslateAnimation ta = new TranslateAnimation(0, 0, 0, 500);
		w.setAnimation(ta);
		ta.setDuration(2000);
		ta.start();
		
		ImageView canvas = (ImageView) findViewById(R.id.effect_canvas);
		canvas.setBackgroundColor(Color.GRAY);
		canvas.setImageDrawable(new HelloAndroidTextDrawable());
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
				WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		
//		canvas.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_effect, menu);
		return true;
	}

}
