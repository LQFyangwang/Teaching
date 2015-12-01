package com.ht.graph;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class Graph2DActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CanvasView canvasView = new CanvasView(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		canvasView.setLayoutParams(params);
		setContentView(canvasView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph2_d, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

class CanvasView extends View {
	
	private Paint paint;

	public CanvasView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setStrokeWidth(10);
		
		canvas.drawPoint(20, 60, paint);
		
		paint.setColor(Color.BLUE);
		
		paint.setStrokeWidth(1);
		canvas.drawLine(20, 80, 220, 80, paint);
		canvas.drawLines(new float[]{20, 100, 220, 100, 20, 120, 220, 120}, paint);
		
		paint.setStyle(Style.FILL);
		canvas.drawCircle(60, 200, 50, paint);// default
		paint.setStyle(Style.STROKE);
		canvas.drawCircle(60,  320, 50, paint);
		
		RectF rect = new RectF();
		rect.left = 50;
		rect.top = 400;
		rect.right = 200;
		rect.bottom = 480;
		canvas.drawArc(rect, 90, 90, false, paint);
		
		rect.left = 50;
		rect.top = 500;
		rect.right = 200;
		rect.bottom = 580;
		canvas.drawArc(rect, 90, 100, true, paint);
		
		rect.left = 300;
		rect.top = 500;
		rect.right = 450;
		rect.bottom = 580;
		paint.setStyle(Style.FILL);
		canvas.drawArc(rect, 90, 120, true, paint);
		
		paint.setTextSize(30);
		canvas.drawText("Android绘制文本样式", 50, 650, paint);
		
		paint.setStyle(Style.STROKE);
		paint.setTextSize(30);
		canvas.drawText("Android绘制文本样式", 50, 750, paint);
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bmw);
		canvas.drawBitmap(bitmap, 50, 800, null);
		
		Drawable drawable = getResources().getDrawable(R.drawable.bmw);
		drawable.setBounds(200, 800, 200 + drawable.getIntrinsicWidth(), 800 + drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		
		paint.setAlpha(90);
		canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
				new Rect(350, 800, 350 + bitmap.getWidth(), 800 + bitmap.getHeight()), paint);
		
		super.onDraw(canvas);
	}
	
	
	
}
