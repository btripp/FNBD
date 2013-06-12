package com.firstnationalbank.closingcostsfnbd;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends SherlockActivity {
	
	//Parameters
	int LoanAmount;
	double downPayment;
	double sellerCosts;
	int downPaymentSpinner;
	int sellerCostsSpinner;	
	
	double iRate30 = 0.04;
	double iRate20 = 0.0375;
	double iRate15 = 0.0325;
	double iRate10 = 0.03;
	
	
	//To be calculated
	double mortgage30 = 0;
	double mortgage20 = 0;
	double mortgage15 = 0;
	double mortgage10 = 0;
	
	double closingCosts30 = 0;
	double closingCosts20 = 0;
	double closingCosts15 = 0;
	double closingCosts10 = 0;
	
	double ownersTitleInsurance = 0;
	double propertyTaxes = 0;

	double oddDay30 = 0;
	double oddDay20 = 0;
	double oddDay15 = 0;
	double oddDay10 = 0;
	
	double escrow = 0;	
	
	//Fees
	int loanOrigination = 250;
	int adminFee = 470;
	int underwritingFee = 265;
	int appraisalFee = 350;
	int creditReportFee = 30;
	int floodCertification = 14;
	int abstractTitle = 125;
	int titleBinder = 25;
	int titlePolicy = 125;
	int settlementFee = 80;
	int titleInsurance = 100;
	int recordingFeeDeed = 36;
	int recordingFeeMortgage = 132;
	int stampFee = 1;
	int surveyFee = 175;
	int aggregateAccountAdj = 60;
	int hazardInsurance = 60;
	
	
	
	TextView thirtyYearText;
	TextView twentyYearText;
	TextView fifteenYearText;
	TextView tenYearText;
	
	TextView thirtyMonthlyPaymentValue;
	TextView twentyMonthlyPaymentValue;
	TextView fifteenMonthlyPaymentValue;
	TextView tenMonthlyPaymentValue;
	
	TextView thirtyMonthlyClosingValue;
	TextView twentyMonthlyClosingValue;
	TextView fifteenMonthlyClosingValue;
	TextView tenMonthlyClosingValue;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);		
		init();	
		doMonthlyPayments();
		doClosingCosts();
		
		 thirtyYearText.setText("30 year - " + String.valueOf(iRate30 * 100) + "%");
		 twentyYearText.setText("20 year - " + String.valueOf(iRate20 * 100) + "%");
		 fifteenYearText.setText("15 year - " + String.valueOf(iRate15 * 100) + "%");
		 tenYearText.setText("10 year - " + String.valueOf(iRate10 * 100) + "%");
		
		 thirtyMonthlyPaymentValue.setText("$" + String.valueOf(mortgage30));
		 twentyMonthlyPaymentValue.setText("$" + String.valueOf(mortgage20));
		 fifteenMonthlyPaymentValue.setText("$" + String.valueOf(mortgage15));
		 tenMonthlyPaymentValue.setText("$" + String.valueOf(mortgage10));
		
		 thirtyMonthlyClosingValue.setText("$" + String.valueOf(closingCosts30));
		 twentyMonthlyClosingValue.setText("$" + String.valueOf(closingCosts20));
		 fifteenMonthlyClosingValue.setText("$" + String.valueOf(closingCosts15));
		 tenMonthlyClosingValue.setText("$" + String.valueOf(closingCosts10));		
	}

	private void init() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    LoanAmount = Integer.parseInt(extras.getString("purchasePrice"));
		    downPayment = extras.getDouble("downPayment");
		    sellerCosts = extras.getDouble("sellerCosts");
		    downPaymentSpinner = extras.getInt("downPaymentSpinner");
		    sellerCostsSpinner = extras.getInt("sellerCostsSpinner");
		}
		thirtyYearText = (TextView)findViewById(R.id.thirtyYearText);
		twentyYearText = (TextView)findViewById(R.id.twentyYearText);
		fifteenYearText = (TextView)findViewById(R.id.fifteenYearText);
		tenYearText = (TextView)findViewById(R.id.tenYearText);
		
		thirtyMonthlyPaymentValue = (TextView)findViewById(R.id.thirtyMonthlyPaymentValue);
		twentyMonthlyPaymentValue = (TextView)findViewById(R.id.twentyMonthlyPaymentValue);
		fifteenMonthlyPaymentValue = (TextView)findViewById(R.id.fifteenMonthlyPaymentValue);
		tenMonthlyPaymentValue = (TextView)findViewById(R.id.tenMonthlyPaymentValue);
		
		thirtyMonthlyClosingValue = (TextView)findViewById(R.id.thirtyMonthlyClosingValue);
		twentyMonthlyClosingValue = (TextView)findViewById(R.id.twentyMonthlyClosingValue);
		fifteenMonthlyClosingValue = (TextView)findViewById(R.id.fifteenMonthlyClosingValue);
		tenMonthlyClosingValue = (TextView)findViewById(R.id.tenMonthlyClosingValue);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
	   inflater.inflate(R.menu.main, (com.actionbarsherlock.view.Menu) menu);
	   return super.onCreateOptionsMenu(menu);
	}
	private void doMonthlyPayments()
	{
		propertyTaxes = ((LoanAmount * 0.011)/12) * 2;	
		
		mortgage30 = monthlyCalc(iRate30, 360, LoanAmount) + propertyTaxes + hazardInsurance;
		mortgage20 = monthlyCalc(iRate20, 240, LoanAmount) + propertyTaxes + hazardInsurance;
		mortgage15 = monthlyCalc(iRate15, 180, LoanAmount) + propertyTaxes + hazardInsurance;
		mortgage10 = monthlyCalc(iRate10, 120, LoanAmount) + propertyTaxes + hazardInsurance;
	}
	
	
	private void doClosingCosts()
	{
		oddDay30 = oddDaysInterest(iRate30, 360, LoanAmount);
		oddDay20 = oddDaysInterest(iRate20, 240, LoanAmount);
		oddDay15 = oddDaysInterest(iRate15, 180, LoanAmount);
		oddDay10 = oddDaysInterest(iRate10, 120, LoanAmount);
		
		escrow = calcEscrow(LoanAmount);
		ownersTitleInsurance = calcTitleInsurance(LoanAmount);
		
		closingCosts30 = closingCosts20 = closingCosts15 = closingCosts10 = (loanOrigination +
				adminFee + underwritingFee + appraisalFee + creditReportFee +
				floodCertification + abstractTitle +abstractTitle + titleBinder +
				titlePolicy + settlementFee + titleInsurance + recordingFeeDeed +
				recordingFeeMortgage + stampFee + surveyFee + aggregateAccountAdj + 
				escrow + ownersTitleInsurance
				); 
		
		closingCosts30 += oddDay30;
		closingCosts20 += oddDay20;
		closingCosts15 += oddDay15;
		closingCosts10 += oddDay10;
	}
	private double monthlyCalc(double interestRate, int monthsInLoan, int amount)
	{
		double term = Math.pow((1.0 + (interestRate/12.0)), monthsInLoan);		
		return (amount * ((interestRate * term)/(term - 1)));		
	}
	private double oddDaysInterest(double interestRate, int term, int amount)
	{
		return (((amount * interestRate)/term) * 15);
	}
	private double calcEscrow(int amount)
	{
		if(amount <= 70000){
			
			escrow = 700.00;
		}
		else if(amount <= 120000){
			escrow = 910.00;
		}
		else{
			escrow = 1120.00;
		}
		return escrow;
	}
	private double calcTitleInsurance(int amount){
		return (((amount / 1000.0) * 5.75)/2);
	}
	
	   
	
	
}
