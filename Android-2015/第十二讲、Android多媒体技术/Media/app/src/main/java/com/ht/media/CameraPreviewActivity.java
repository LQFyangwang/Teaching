package com.ht.media;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class CameraPreviewActivity extends Activity {
	
	private Preview preview;
	public static Bitmap cameraBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		preview = new Preview(this);
		setContentView(preview);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			preview.takePicture();
		}
		return super.onTouchEvent(event);
	}



	class Preview extends SurfaceView implements SurfaceHolder.Callback {
		
		private SurfaceHolder holder;
		private Camera camera;

		public Preview(Context context) {
			super(context);
			holder = getHolder();
			holder.addCallback(this);
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			camera = Camera.open();
			try {
				camera.setPreviewDisplay(holder);
			} catch (IOException e) {
				camera.release();
				camera = null;
				e.printStackTrace();
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			camera.startPreview();
			final ImageView focusImg = new ImageView(CameraPreviewActivity.this);
			focusImg.setImageResource(R.drawable.notification_template_icon_bg);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			focusImg.setScaleType(ScaleType.CENTER);
			focusImg.setVisibility(VISIBLE);
			addContentView(focusImg, params);
			camera.autoFocus(new AutoFocusCallback() {

				@Override
				public void onAutoFocus(boolean success, Camera camera) {
					if (success) {
						focusImg.setImageResource(R.drawable.abc_list_focused_holo);
					}
				}
				
			});
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.release();
			camera = null;
		}
		
		public void takePicture() {
			if (camera != null) {
				camera.takePicture(null, null, new PictureCallback() {
		
					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						cameraBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
						setResult(5);
						camera.stopPreview();
						camera = null;
						finish();
					}
					
				});
			}
		}
		
	}
	
}