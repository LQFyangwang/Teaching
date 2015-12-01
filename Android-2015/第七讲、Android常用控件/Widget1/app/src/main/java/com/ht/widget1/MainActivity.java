package com.ht.widget1;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextView htmlTxt;
	private TextView linkTxt;
	private TextView imageTxt;
	
	private EditText iconEdit;
	private AutoCompleteTextView autoEdit;
	
	private Button imgBtn1;
	
	private RadioGroup gender;
	
	private CheckBox subject1;
	private CheckBox subject2;
	private CheckBox subject3;
	
	private Button allCheckedSubjectsBtn;
	private List<CheckBox> checkBoxes;
	
	private Button imageSizeBtn;
	private Button largerBtn;
	private Button smallerBtn;
	private Button rotateBtn;
	
	private DatePicker datePicker;
	private TimePicker timePicker;
	private TextView dateTimeTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		htmlTxt = (TextView) findViewById(R.id.html_txt);
		linkTxt = (TextView) findViewById(R.id.link_txt);
		imageTxt = (TextView) findViewById(R.id.imageTxt);
		iconEdit = (EditText) findViewById(R.id.iconEdit);
		autoEdit = (AutoCompleteTextView) findViewById(R.id.autoEdit);
		
		imgBtn1 = (Button) findViewById(R.id.imgBtn1);
		
		gender = (RadioGroup) findViewById(R.id.gender);
		
		subject1 = (CheckBox) findViewById(R.id.subject1);
		subject2 = (CheckBox) findViewById(R.id.subject2);
		subject3 = (CheckBox) findViewById(R.id.subject3);
		allCheckedSubjectsBtn = (Button) findViewById(R.id.allCheckedSubjects);
		checkBoxes = new ArrayList<CheckBox>();
		checkBoxes.add(subject1);
		checkBoxes.add(subject2);
		checkBoxes.add(subject3);
		
		imageSizeBtn = (Button) findViewById(R.id.imageSizeBtn);
		largerBtn = (Button) findViewById(R.id.largerBtn);
		smallerBtn = (Button) findViewById(R.id.smallerBtn);
		rotateBtn = (Button) findViewById(R.id.rotateBtn);
		
		datePicker = (DatePicker) findViewById(R.id.datePicker1);
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		dateTimeTxt = (TextView) findViewById(R.id.dateTimeTxt);
		
		final Resources res = getResources();
		htmlTxt.setText(Html.fromHtml(res.getString(R.string.html_string)));
		htmlTxt.setMovementMethod(LinkMovementMethod.getInstance());
		
		String linkString = res.getString(R.string.link_string);
		SpannableString spannableString = new SpannableString(linkString);
		spannableString.setSpan(new ClickableSpan() {

			@Override
			public void onClick(View widget) {
				Toast.makeText(getApplicationContext(), "可以跳转到另外一个activity", Toast.LENGTH_SHORT).show();
			}
			
		}, 0, linkString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		linkTxt.setText(spannableString);
		linkTxt.setMovementMethod(LinkMovementMethod.getInstance());
		
		String html = "图文：" + "<img src='xiaowanzi' />";
		CharSequence charSequence = Html.fromHtml(html, new ImageGetter() {

			@Override
			public Drawable getDrawable(String source) {
				Drawable drawable = res.getDrawable(getResourceId(source));
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
				return drawable;
			}
			
		}, null);
		imageTxt.setText(charSequence);
		
		String[] hints = res.getStringArray(R.array.hints);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_dropdown_item_1line, hints);
		autoEdit.setAdapter(arrayAdapter);
		
		SpannableString btnStringLeft = new SpannableString("image");
		SpannableString btnStringRight = new SpannableString("image");
		Bitmap bitmapLeft = BitmapFactory.decodeResource(res, R.drawable.button);
		Bitmap bitmapRight = BitmapFactory.decodeResource(res, R.drawable.button1);
		ImageSpan imgSpanLeft = new ImageSpan(this, bitmapLeft);
		ImageSpan imgSpanRight = new ImageSpan(this, bitmapRight);
		btnStringLeft.setSpan(imgSpanLeft, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		btnStringRight.setSpan(imgSpanRight, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// imgBtn1.append(btnStringLeft);
		// imgBtn1.append("图文按钮");
		// imgBtn1.append(btnStringRight);
		
		gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton gender = (RadioButton) findViewById(checkedId);
				Toast.makeText(getApplicationContext(), "您选择的是： " + gender.getText(), Toast.LENGTH_SHORT).show();
			}
			
		});
		
		SubjectCheckedChangeListener checkedListener = new SubjectCheckedChangeListener();
		subject1.setOnCheckedChangeListener(checkedListener);
		subject2.setOnCheckedChangeListener(checkedListener);
		subject3.setOnCheckedChangeListener(checkedListener);
		
		allCheckedSubjectsBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String msg = "您的性别：";
				int radioButtons = gender.getChildCount();
				// int checkedRadioButtonId = gender.getCheckedRadioButtonId();
				// RadioButton checkedRadioButton = (RadioButton) findViewById(checkedRadioButtonId);
				for (int i = 0; i < radioButtons; i++) {
					RadioButton radioButton = (RadioButton) gender.getChildAt(i);
					if (radioButton.isChecked()) {
						msg += radioButton.getText();
						break;
					}
				}
				msg += "\n选中的科目有：";
				for (CheckBox checkBox : checkBoxes) {
					if (checkBox.isChecked()) {
						msg += checkBox.getText() + "  ";
					}
				}
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
			}
		});
		
		imageSizeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
				ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
				BitmapDrawable img1 = (BitmapDrawable) imageView1.getDrawable();
				BitmapDrawable img2 = (BitmapDrawable) imageView2.getDrawable();
				int actualWidth1 = img1.getBitmap().getWidth();
				int actualHeight1 = img1.getBitmap().getHeight();
				int width1 = imageView1.getMeasuredWidth();
				int height1 = imageView1.getMeasuredHeight();
				int actualWidth2 = img2.getBitmap().getWidth();
				int actualHeight2 = img2.getBitmap().getHeight();
				int width2 = imageView2.getMeasuredWidth();
				int height2 = imageView2.getMeasuredHeight();
				String msg = "width1: " + width1 + ", height1: " + height1
						+ "; width2: " + width2 + ", height2: " + height2
						+ "; actual width1: " + actualWidth1 + ", actual height1: " + actualHeight1
						+ "; actual width2: " + actualWidth2 + ", actual height2: " + actualHeight2;
				new AlertDialog.Builder(MainActivity.this).setMessage(msg)
				.setPositiveButton("确定", null).show();
			}
			
		});
		
		OnClickListener imageBtnClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageView imageView = (ImageView) findViewById(R.id.imageView1);
				int width = imageView.getMeasuredWidth();
				int height = imageView.getMeasuredHeight();
				int toAdd = 0;
				int btnId = v.getId();
				if (btnId == R.id.largerBtn) {
					toAdd = 20;
				} else if (btnId == R.id.smallerBtn) {
					toAdd = -20;
				}
				imageView.setLayoutParams(new LinearLayout.LayoutParams(width + toAdd, height + toAdd));
			}
		};
		largerBtn.setOnClickListener(imageBtnClick);
		smallerBtn.setOnClickListener(imageBtnClick);
		rotateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageView imageView = (ImageView) findViewById(R.id.imageView1);
				Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
				Matrix matrix = new Matrix();
				matrix.setRotate(90);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				imageView.setImageBitmap(bitmap);
			}
		});
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, month, dayOfMonth, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				showDateTime(year, monthOfYear, dayOfMonth, 0, 0);
			}
		});
		
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				showDateTime(0, 0, 0, hourOfDay, minute);
			}
			
		});
		
		
	}
	
	public void onClickInsertBtn(View view) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xiaowanzi);
		ImageSpan imageSpan = new ImageSpan(getApplicationContext(), bitmap);
		SpannableString spannableString = new SpannableString("icon");
		spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		iconEdit.append(spannableString);
	}
	
	private int getResourceId(String resourceName) {
		try {
			Field resourceField = R.drawable.class.getField(resourceName);
			return Integer.parseInt(resourceField.get(null).toString());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private class SubjectCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			CheckBox checkBox = (CheckBox) findViewById(buttonView.getId());
			if (isChecked) {
				Log.d("widget", "checkbox checked: " + checkBox.getText());
			} else {
				Log.d("widget", "checkbox unchecked: " + checkBox.getText());
			}
		}
		
	}
	
	private void showDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
		DateFormat dft = new SimpleDateFormat("yyyy年MM月dd日，HH:mm");
		Calendar calendar = Calendar.getInstance();
		if (year == 0) {
			year = datePicker.getYear();
			month = datePicker.getMonth();
			dayOfMonth = datePicker.getDayOfMonth();
		}
		if (hour == 0) {
			hour = timePicker.getCurrentHour();
			minute = timePicker.getCurrentMinute();
		}
		calendar.set(year, month, dayOfMonth, hour, minute);
		String dateTimeStr = dft.format(calendar.getTime());
		dateTimeTxt.setText(dateTimeStr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
