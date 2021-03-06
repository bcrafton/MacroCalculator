package com.example.macrocalculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class AddItem extends ActionBarActivity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_item); // set the content view to activity_add_item, not fragement_add_item

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void AddFoodItem(View view)
	{
		String name = null;
		double proteinCount;
		double carbCount;
		double fatCount;
		
		TextView nameValue = (TextView)findViewById(R.id.Name);
		name = nameValue.getText().toString();
		
		// if any of these textboxes contain nothing then it fails. use try catch.
		try
		{
			TextView proteinValue = (TextView)findViewById(R.id.ProteinValue);
			proteinCount = Double.parseDouble(proteinValue.getText().toString());
		}
		catch(Exception e)
		{
			proteinCount = 0;
		}
		try
		{
			TextView carbValue = (TextView)findViewById(R.id.CarbValue);
			carbCount = Double.parseDouble(carbValue.getText().toString());
		}
		catch(Exception e)
		{
			carbCount = 0;
		}
		try
		{
			TextView fatValue = (TextView)findViewById(R.id.FatValue);
			fatCount = Double.parseDouble(fatValue.getText().toString());
		}
		catch(Exception e)
		{
			fatCount = 0;
		}
		
		int calorieCount = (int)(proteinCount*4 + carbCount*4 + fatCount*9);
		
		DBAdapter myDb;
		myDb = new DBAdapter(this);
		myDb.open();
		myDb.addRowToMenu(DBAdapter.FOOD_MENU, name, calorieCount, (int)proteinCount, (int)carbCount, (int)fatCount);
		myDb.close();
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_add_item,
					container, false);
			return rootView;
		}
	}
	
	
	

}
