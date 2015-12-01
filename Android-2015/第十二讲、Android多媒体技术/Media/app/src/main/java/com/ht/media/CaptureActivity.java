package com.ht.media;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CaptureActivity extends Activity {
	
	private Button beginCaptureBtn;
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture);
		
		beginCaptureBtn = (Button) findViewById(R.id.beginCaptureBtn);
		image = (ImageView) findViewById(R.id.image);
		
		beginCaptureBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(CaptureActivity.this, CameraPreviewActivity.class), 1);
			}
			
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == 5) {
				File captureFile = new File( android.os.Environment.getExternalStorageDirectory() + "my capture");
				try {
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(captureFile));
					CameraPreviewActivity.cameraBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();
					image.setImageBitmap(CameraPreviewActivity.cameraBitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
