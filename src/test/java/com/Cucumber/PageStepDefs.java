package com.Cucumber;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;


public class PageStepDefs {
	String curDir = System.getProperty("user.dir");
	public String ChromeDriverPath = curDir+ File.separator + "src" +File.separator + "test" + File.separator + "resources" + File.separator + "chromedriver.exe";
	public WebDriver webdriver;
	public ResultSet rs;
	String localhost="http://google.com";
	private String MicrosoftStrategyURL = "http://10.0.0.55";

	public PageStepDefs (){
		System.setProperty("webdriver.chrome.driver",ChromeDriverPath);
		webdriver = new ChromeDriver();	
	}

	@Given("^I browse to the (.+) page$")
	public void open_page(String u)
	{
		webdriver.get(localhost+u);
	}

	@Given("^I open the login (.+) page$")
	public void I_open_the_login_page(String postfixURL) {
		webdriver.get(MicrosoftStrategyURL + postfixURL);
	}

	@And("^I click on the button (.+)$")
	public void click_On_Menu(String Id)
	{
		webdriver.findElement(By.id(Id)).click();
	}

	@When("^I enter (.+) in the (.+) field$")
	public void I_enter_a_value_in_a_field(String value, String fieldId) {
		webdriver.findElement(By.id(fieldId)).sendKeys(value);
	}

	@And("^I click on the Link by className (.+)$")
	public void click_On_PutnamLink(String className)
	{
		webdriver.findElement(By.className(className)).click();
	}

	@And("^I click on the Link by arraylist (.+)$")
	public void click_On_arraylist(String classValue)
	{
		List<WebElement> linkarray = webdriver.findElements(By.className(classValue));
		System.out.println(linkarray.get(1).findElement(By.tagName("a")).getText());
		linkarray.get(1).findElement(By.tagName("a")).click();
	}

	@When("^I click on the element using Xpath (.+)$")
	public void I_click_on_the_element_using_Xpath(String xpathExp){
		webdriver.findElement(By.xpath(xpathExp)).click();
	}

	@When("^I click on the button using cssSelector (.+)$")
	public void I_click_on_the_element_using_cssSelector(String cssSelector){
		webdriver.findElement(By.cssSelector(cssSelector)).click();
	}

	@And("^I wait some seconds$")
	public void I_wait_a_few_seconds() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ie) {
		}
	}
	
	@When("^I wait for (.+) to visible$")
	public void i_wait_for_element_to_visible(final String elementId) {
		WebDriverWait myWait = new WebDriverWait(webdriver, 45);
		ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {		
			public Boolean apply(WebDriver driver) {
				return (driver.findElement(By.id(elementId)).isDisplayed());
			}
		};
		myWait.until(conditionToCheck);
	}

	@And("^I get data from requested table$")
	public List<String> getTableColumn(){
		List<String> tcolumn = new ArrayList<String>();
		boolean isFound = true;
		while(isFound){
			List<WebElement> uiElement = webdriver.findElements(By.cssSelector("table[id='table_UniqueReportID'] > tbody > tr"));
			System.out.println("Total Rows = "+uiElement.size());
			List<WebElement> tr = null;

			for(int i=1; i<uiElement.size(); i++){
				tr = uiElement.get(i).findElements(By.tagName("td"));
				tcolumn.add(tr.get(tr.size()-1).getText().toString().replace(",",""));
			}
			try{
				if(webdriver.findElement(By.id("rb_ReportStyleIncrementalFetch_2")).isDisplayed()){
					webdriver.findElement(By.cssSelector("a > img[class='mstrFetchIcon mstrFetchNext']")).click();
					Thread.sleep(30000);
				}else{
					isFound = false;
				}
			}catch(Exception e){
				isFound = false;
			}
		}
		System.out.println("Table Row Size : "+tcolumn.size());
		return tcolumn;
	}
	@And ("^I CompareData for ParentInvstAssetClassCodeName$")
	public void CompareParentInvstAssetClassCodeNameData(){
		List<String> db =  PostgresqlDB.ParentInv_AssetClass_Code_Name_Dim();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Parent Investment Asset Class Code Name was SUCCESSFULL");
	}
	@And ("^I CompareData for ParentInvestmentDivision$")
	public void CompareParentInvestmentDivisionData(){
		List<String> db =  PostgresqlDB.ParentInvDivision();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Parent Investment Division was SUCCESSFULL");
	}
	@And ("^I CompareData for AccountCodeName$")
	public void CompareAccountCodeNameData(){
		List<String> db =  PostgresqlDB.AccountCodeName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Account Code Name was SUCCESSFULL");
	}
	@And ("^I CompareData for ParentInvGrpCodeName$")
	public void CompareParentInvGrpCodeNameData(){
		List<String> db =  PostgresqlDB.ParentInvGrpCodeName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Parent Investment Group Code Name was SUCCESSFULL");
	}
	@And ("^I CompareData for ParentInvFocusCodeName$")
	public void CompareParentInvFocusCodeNameData(){
		List<String> db =  PostgresqlDB.ParentInvFocusCodeName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Parent Investment Focus Code Name was SUCCESSFULL");
	}
	@And ("^I CompareData for ManagementCompanyCodeName$")
	public void CompareManagementCompanyCodeNameData(){
		List<String> db =  PostgresqlDB.ManagementCompanyCodeName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Management Company Code Name was SUCCESSFULL");
	}
	@And ("^I CompareData for FundFamilyName$")
	public void CompareFundFamilyNameData(){
		List<String> db =  PostgresqlDB.FundFamilyName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Fund Family Name was SUCCESSFULL");
	}
	@And ("^I CompareData for FundClassCode$")
	public void CompareFundClassCodeData(){
		List<String> db =  PostgresqlDB.FundClassCode();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Fund Class Code was SUCCESSFULL");
	}
	@And ("^I CompareData for InvestmentObjectiveCode$")
	public void CompareInvestmentObjectiveCodeData(){
		List<String> db =  PostgresqlDB.InvestmentObjectiveCode();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Investment Objective Code was SUCCESSFULL");
	}
	@And ("^I CompareData for LoadCode$")
	public void CompareLoadCodeData(){
		List<String> db =  PostgresqlDB.LoadCode();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Load Code was SUCCESSFULL");
	}
	@And ("^I CompareData for DistributionChannel$")
	public void CompareDistributionChannelData(){
		List<String> db =  PostgresqlDB.DistributionChannel();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Distribution Channel was SUCCESSFULL");
	}
	@And ("^I CompareData for ClosedToNewInv$")
	public void CompareClosedToNewInvData(){
		List<String> db =  PostgresqlDB.ClosedToNewInv();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Closed To New Investment was SUCCESSFULL");
	}
	@And ("^I CompareData for PortfolioNumber$")
	public void ComparePortfolioNumberData(){
		List<String> db =  PostgresqlDB.PortfolioNumber();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Portfolio Number was SUCCESSFULL");
	}
	@And ("^I CompareData for FundClubID$")
	public void CompareFundClubIDData(){
		List<String> db =  PostgresqlDB.FundClubID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Fund Club ID was SUCCESSFULL");
	}
	@And ("^I CompareData for DataFrequencyCode$")
	public void CompareDataFrequencyCodeData(){
		List<String> db =  PostgresqlDB.DataFrequencyCode();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Data Frequency Code was SUCCESSFULL");
	}
	@And ("^I CompareData for LipperFundName$")
	public void CompareLipperFundNameData(){
		List<String> db =  PostgresqlDB.LipperFundName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Lipper Fund Name was SUCCESSFULL");
	}
	@And ("^I CompareData for FullFundName$")
	public void CompareFullFundNameData(){
		List<String> db =  PostgresqlDB.FullFundName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Full Fund Name was SUCCESSFULL");
	}
	@And ("^I CompareData for FundUniverseName$")
	public void CompareFundUniverseNameData(){
		List<String> db =  PostgresqlDB.FundUniverseName();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Fund Universe Name was SUCCESSFULL");
	}
	@And ("^I CompareData for AssetID$")
	public void CompareAssetIDData(){
		List<String> db =  PostgresqlDB.AssetID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Asset ID was SUCCESSFULL");
	}
	@And ("^I CompareData for LipperSymbolID$")
	public void CompareLipperSymbolIDData(){
		List<String> db =  PostgresqlDB.LipperSymbolID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Lipper Symbol ID was SUCCESSFULL");
	}
	@And ("^I CompareData for NasdaqTickerID$")
	public void CompareNasdaqTickerIDData(){
		List<String> db =  PostgresqlDB.NasdaqTickerID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Nasdaq Ticker ID was SUCCESSFULL");
	}
	@And ("^I CompareData for CUSIPID$")
	public void CompareCUSIPIDData(){
		List<String> db =  PostgresqlDB.CUSIPID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for CUSIP ID was SUCCESSFULL");
	}
	@And ("^I CompareData for ISINCodeID$")
	public void CompareISINCodeIDData(){
		List<String> db =  PostgresqlDB.ISINCodeID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for ISIN Code ID was SUCCESSFULL");
	}
	@And ("^I CompareData for ISINCurrencyClassID$")
	public void CompareISINCurrencyClassIDData(){
		List<String> db =  PostgresqlDB.ISINCurrencyClassID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for ISIN Currency Class ID was SUCCESSFULL");
	}
	@And ("^I CompareData for MFTHOMSONCodeID$")
	public void CompareMFTHOMSONCodeIDData(){
		List<String> db =  PostgresqlDB.MFTHOMSONCodeID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for MF THOMSON Code ID was SUCCESSFULL");
	}
	@And ("^I CompareData for Quarter$")
	public void CompareQuarterData(){
		List<String> db =  PostgresqlDB.Quarter();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Quarter Data was SUCCESSFULL");
	}
	@And ("^I CompareData for Year$")
	public void CompareYearData(){
		List<String> db =  PostgresqlDB.Year();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Year Data was SUCCESSFULL");
	}
	@And ("^I CompareData for Month$")
	public void CompareMonthData(){
		List<String> db =  PostgresqlDB.Month();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Month Data was SUCCESSFULL");
	}
	@And ("^I CompareData for Date$")
	public void CompareDateData(){
		List<String> db =  PostgresqlDB.Date();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Date Data was SUCCESSFULL");
	}
	@And ("^I CompareData for Currency$")
	public void CompareCurrencyData(){
		List<String> db =  PostgresqlDB.Currency();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Currency Data was SUCCESSFULL");
	}
	@And ("^I CompareData for PermID$")
	public void ComparePermIDData(){
		List<String> db =  PostgresqlDB.PermID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Perm ID was SUCCESSFULL");
	}
	@And ("^I CompareData for RICID$")
	public void CompareRICIDData(){
		List<String> db =  PostgresqlDB.RICID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for RIC ID was SUCCESSFULL");
	}
	@And ("^I CompareData for USCIKID$")
	public void CompareUSCIKIDData(){
		List<String> db =  PostgresqlDB.USCIKID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for US CIK ID was SUCCESSFULL");
	}
	@And ("^I CompareData for USFundID$")
	public void CompareUSFundIDData(){
		List<String> db =  PostgresqlDB.USFundID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for US Fund ID was SUCCESSFULL");
	}
	@And ("^I CompareData for USSECSeriesID$")
	public void CompareUSSECSeriesIDData(){
		List<String> db =  PostgresqlDB.USSECSeriesID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for US SEC Series ID was SUCCESSFULL");
	}
	@And ("^I CompareData for USSECClassID$")
	public void CompareUSSECClassIDData(){
		List<String> db =  PostgresqlDB.USSECClassID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for US SEC Class ID was SUCCESSFULL");
	}
	@And ("^I CompareData for FundTypeLevel$")
	public void CompareFundTypeLevelData(){
		List<String> db =  PostgresqlDB.FundTypeLevel();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Fund Type Level was SUCCESSFULL");
	}
	@And ("^I CompareData for ValorID$")
	public void CompareValorIDData(){
		List<String> db =  PostgresqlDB.ValorID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Valor ID was SUCCESSFULL");
	}
	@And ("^I CompareData for WKNGermanID$")
	public void CompareWKNGermanIDData(){
		List<String> db =  PostgresqlDB.WKNGermanID();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for WKN German ID was SUCCESSFULL");
	}
	@And ("^I CompareData for LipperSymbolCount$")
	public void CompareLipperSymbolCountData(){
		List<String> db =  PostgresqlDB.LipperSymbolCount();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Lipper Symbol Count was SUCCESSFULL");
	}
	@And ("^I CompareData for TotalLipperSymbol$")
	public void CompareTotalLipperSymbolData(){
		List<String> db =  PostgresqlDB.TotalLipperSymbol();
		List<String> tcolumn = getTableColumn();

		Collections.sort(db);
		Collections.sort(tcolumn);

		System.out.println("UI Values:"+tcolumn);
		System.out.println("DB Values:"+db);
		Assert.assertEquals(db, tcolumn);
		System.out.println("The Data Comparison for Total Lipper Symbol was SUCCESSFULL");
	}

	@After
	public void close_browser(){
		webdriver.quit();
	}
}

