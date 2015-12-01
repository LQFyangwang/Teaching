package com.ht.resource;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ImageActivity extends Activity {
	
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		imageView = (ImageView) findViewById(R.id.imageView);
		
		String image = getIntent().getStringExtra("image");
		if (image == null || image.length() <= 0) {
			imageView.setImageResource(R.drawable.book);
		} else {
			if (image.equals("book")) {
				imageView.setImageResource(R.drawable.book);;
			} else if (image.equals("camera")) {
				imageView.setImageResource(R.drawable.my_camera);
			}
		}
	}
}
