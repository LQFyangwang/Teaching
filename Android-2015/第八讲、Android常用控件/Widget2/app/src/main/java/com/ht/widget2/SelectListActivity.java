package com.ht.widget2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SelectListActivity extends ActionBarActivity {
	
	private ListView selectListView;
	
	private Button showAllBrands;
	private Button addBrand;
	private Button removeBrand;
	private Button editBrand;
	private EditText brandNameTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_list);
		
		selectListView = (ListView) findViewById(R.id.selectList);
		showAllBrands = (Button) findViewById(R.id.showAllBrands);
		addBrand = (Button) findViewById(R.id.addBrand);
		removeBrand = (Button) findViewById(R.id.removeBrand);
		editBrand = (Button) findViewById(R.id.editBrand);
		brandNameTxt = (EditText) findViewById(R.id.brandNameTxt);
		
		int brandImages[] = new int[]{R.drawable.bmw, R.drawable.benz, R.drawable.audi, R.drawable.prosche,
				R.drawable.peugeot, R.drawable.lexus, R.drawable.land_rover, R.drawable.mg, 
				R.drawable.honda, R.drawable.ford};
		final String brandNames[] = new String[]{"宝马", "奔驰", "奥迪", "保时捷", "标致", "雷克萨斯", "路虎", "名爵", "本田", "福特"};
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (int i = 0, length = brandImages.length; i < length; i++) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("brandImage", brandImages[i]);
			data.put("brandTxt", brandNames[i]);
			dataList.add(data);
		}
		final SelectListAdapter slAdapter = new SelectListAdapter(dataList, this);
		selectListView.setAdapter(slAdapter);
		
		selectListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (slAdapter.isCheckedItem(position)) {
					slAdapter.removeCheckedItem(position);
				} else {
					slAdapter.addCheckedItem(position);
				}
				slAdapter.notifyDataSetChanged();
			}
			
		});
		
		selectListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				Log.d("Select List Activity", "当前已显示的条数：" + totalItemCount + "可见的条数：" + visibleItemCount);
			}
			
		});
		
		showAllBrands.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<Integer> checkedItems = slAdapter.getCheckedItems();
				String msg = "喜欢的汽车品牌：";
				for (int i : checkedItems) {
					msg += brandNames[i] + "  ";
				}
				new AlertDialog.Builder(SelectListActivity.this).setMessage(msg).show();
			}
			
		});
		
		addBrand.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<Integer> checkedItems = slAdapter.getCheckedItems();
				if (checkedItems.isEmpty()) {
					new AlertDialog.Builder(SelectListActivity.this).setMessage("请选择一个品牌，在该品牌下面添加品牌").show();
				} else if (checkedItems.size() >= 2) {
					new AlertDialog.Builder(SelectListActivity.this).setMessage("请只选择一个品牌").show();
				} else {
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("brandImage", R.drawable.bmw);
					data.put("brandTxt", "宝马");
					slAdapter.addBrand(data, checkedItems.get(0));
				}
			}
			
		});
		
		removeBrand.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<Integer> checkedItems = slAdapter.getCheckedItems();
				if (checkedItems.isEmpty()) {
					new AlertDialog.Builder(SelectListActivity.this).setMessage("请选择需要移除的品牌").show();
				} else if (checkedItems.size() >= 2) {
					new AlertDialog.Builder(SelectListActivity.this).setMessage("请只选择一个品牌").show();
				} else {
					slAdapter.removeBrands(checkedItems.get(0));
				}
			}
			
		});
		
		editBrand.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<Integer> checkedItems = slAdapter.getCheckedItems();
				if (checkedItems.isEmpty()) {
					new AlertDialog.Builder(SelectListActivity.this).setMessage("请选择一个品牌，在该品牌下面添加品牌").show();
				} else if (checkedItems.size() >= 2) {
					new AlertDialog.Builder(SelectListActivity.this).setMessage("请只选择修改一个品牌").show();
				} else {
					slAdapter.editBrand(brandNameTxt.getText().toString(), checkedItems.get(0));
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_list, menu);
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

class SelectListAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> dataList;
	private LayoutInflater inflater;
	private List<Integer> checkedItems = new ArrayList<Integer>();
	
	public SelectListAdapter() {
		
	}
	
	public SelectListAdapter(Context context) {
		this.context = context;
	}
	
	public SelectListAdapter(List<Map<String, Object>> dataList, Context context) {
		this.context = context;
		this.dataList = dataList;
	}
	
	public void addCheckedItem(Integer position) {
		dataList.get(position).put("checkImage", R.drawable.check);
		checkedItems.add(position);
	}
	
	public void removeCheckedItem(Integer position) {
		dataList.get(position).put("checkImage", android.R.color.transparent);
		checkedItems.remove(position);
	}
	
	public boolean isCheckedItem(Integer position) {
		return checkedItems.contains(position);
	}
	
	public List<Integer> getCheckedItems() {
		return checkedItems;
	}
	
	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.select_list_item, null);
			holder.brandImage = (ImageView) convertView.findViewById(R.id.brandImage);
			holder.brandTxt = (TextView) convertView.findViewById(R.id.brandTxt);
			holder.checkImage = (ImageView) convertView.findViewById(R.id.checkImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Map<String, Object> data = dataList.get(position);
		holder.brandImage.setImageResource((Integer) data.get("brandImage"));
		holder.brandTxt.setText((String) data.get("brandTxt"));
		Object checkImage = data.get("checkImage");
		int checkImageResId = checkImage == null ? android.R.color.transparent : (Integer) checkImage;
		holder.checkImage.setImageResource(checkImageResId);
		return convertView;
	}
	
	public void addBrand(Map<String, Object> data, int position) {
		dataList.add(position + 1, data);
		notifyDataSetChanged();
	}
	
	public void removeBrands(int position) {
		removeCheckedItem(position);
		dataList.remove(position);
		notifyDataSetChanged();
	}
	
	public void editBrand(String brandName, int position) {
		dataList.get(position).put("brandTxt", brandName);
		notifyDataSetChanged();
	}
}

class ViewHolder {
	ImageView brandImage;
	TextView brandTxt;
	ImageView checkImage;
}
