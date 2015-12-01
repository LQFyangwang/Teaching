package com.ht.widget2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class CarBrandsActivity extends ActionBarActivity {
	
	private ImageView carBrand;
	private GridView carBrands;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_brands);
		
		carBrands = (GridView) findViewById(R.id.carBrands);
		carBrand = (ImageView) findViewById(R.id.carBrand);
		
		List<Map<String, Object>> cells = new ArrayList<Map<String, Object>>();
		final int[] carBrandIds = {R.drawable.bmw, R.drawable.benz, R.drawable.audi, R.drawable.prosche, 
				R.drawable.peugeot, R.drawable.mg, R.drawable.honda, R.drawable.ford,
				R.drawable.lexus, R.drawable.land_rover}; 
		String[] carBrandNames = {"宝马", "奔驰", "奥迪", "保时捷", "标致",
				"名爵", "本田", "福特", "雷克萨斯", "路虎"};
		for (int i = 0, length = carBrandIds.length; i < length; i++) {
			Map<String, Object> cell = new HashMap<String, Object>();
			cell.put("carBrand", carBrandIds[i]);
			cell.put("carBrandName", carBrandNames[i]);
			cells.add(cell);
		}
		final SimpleAdapter carBrandsAdapter = new SimpleAdapter(this, cells, R.layout.car_brand_cell,
				new String[]{"carBrand", "carBrandName"}, new int[]{R.id.carBrandImg, R.id.carBrandName});
		carBrands.setAdapter(carBrandsAdapter);
		
		carBrands.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				carBrand.setImageResource(carBrandIds[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		
		carBrands.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				carBrand.setImageResource(carBrandIds[position]);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.car_brands, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
