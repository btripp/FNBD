package com.firstnationalbank.closingcostsfnbd;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends SherlockActivity {

	EditText purchasePrice;
	Spinner downPaymentSpinner;
	Spinner sellerSpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		init();		
	}
	
	
	public void calculateLowerCost(View view) {
		Intent intent = new Intent(this, ResultsActivity.class);
		
		intent.putExtra("purchasePrice", ((EditText)findViewById(R.id.purchasePrice)).getText().toString());
		intent.putExtra("downPayment", ((EditText)findViewById(R.id.downPayment)).getText().toString());
		intent.putExtra("sellerCosts", ((EditText)findViewById(R.id.sellerCostsEditText)).getText().toString());
		intent.putExtra("downPaymentSpinner", ((Spinner)findViewById(R.id.inputTypeSpinner)).getSelectedItem().toString());
		intent.putExtra("sellerCostsSpinner", ((Spinner)findViewById(R.id.inputTypeSpinnerSeller)).getSelectedItem().toString());
		
		startActivity(intent);
	}
	public void calculateLowerRate(View view) {
		Intent intent = new Intent(this, ResultsActivity.class);
		startActivity(intent);
	}
	private void init(){
		//ActionBar actionBar = getActionBar();
		//actionBar.hide();
		
		purchasePrice = (EditText)findViewById(R.id.purchasePrice);
		purchasePrice.setFilters(new InputFilter[] { new CurrencyFormatInputFilter()});
		
		downPaymentSpinner = (Spinner)findViewById(R.id.inputTypeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.inputTypes, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		downPaymentSpinner.setAdapter(adapter);
		
		sellerSpinner = (Spinner)findViewById(R.id.inputTypeSpinnerSeller);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
				R.array.inputTypes, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sellerSpinner.setAdapter(adapter2);	
	}
	
	

}

