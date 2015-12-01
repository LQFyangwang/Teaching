package com.ht.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Notification.Action;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ContactsActivity extends ActionBarActivity {

	private ListView contactList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		contactList = (ListView) findViewById(R.id.contactList);
		
		ContentResolver resover = getContentResolver();
		Cursor contactCursor = resover.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		List<Map<String, String>> contacts = new ArrayList<Map<String, String>>();
		for (contactCursor.moveToFirst(); !contactCursor.isAfterLast(); contactCursor.moveToNext()) {
			Map<String, String> contact = new HashMap<String, String>();
			contact.put("name", contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			String contactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
			int phoneCount = contactCursor.getInt(contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
			if (phoneCount > 0) {
				Cursor phoneCursor = resover.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, 
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
				phoneCursor.moveToFirst();
				contact.put("phone", phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
			}
			contacts.add(contact);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, contacts, R.layout.contact_item,
				new String[]{"name", "phone"}, new int[]{R.id.name, R.id.phone});
		contactList.setAdapter(adapter);
		
		contactList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String phone = ((TextView) view.findViewById(R.id.phone)).getText().toString();
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
				startActivity(intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contacts, menu);
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
