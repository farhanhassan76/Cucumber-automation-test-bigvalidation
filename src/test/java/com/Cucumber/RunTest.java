package com.Cucumber;

import java.lang.annotation.Annotation;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.pagefactory.Annotations;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags={"@myTest"},
//		        features = "target/test-classes/com/Cucumber/")
		features = {//"classpath:com/Cucumber/ParentInvestmentDivision.feature",
//				"classpath:com/Cucumber/LipperSymbolCount.feature",
//				"classpath:com/Cucumber/TotalLipperSymbol.feature",
				"classpath:com/Cucumber/AccountCodeName.feature",
//				"classpath:com/Cucumber/AssetID.feature",
//				"classpath:com/Cucumber/ClosedToNewInv.feature",
				"classpath:com/Cucumber/Currency.feature",
//				"classpath:com/Cucumber/CUSIPID.feature",
//				"classpath:com/Cucumber/DataFrequencyCode.feature",
				"classpath:com/Cucumber/Date.feature",
				"classpath:com/Cucumber/DistributionChannel.feature",
//				"classpath:com/Cucumber/FullFundName.feature",
//				"classpath:com/Cucumber/FundClassCode.feature",
//				"classpath:com/Cucumber/FundClubID.feature",
//				"classpath:com/Cucumber/FundFamilyName.feature",
//				"classpath:com/Cucumber/FundTypeLevel.feature",
//				"classpath:com/Cucumber/FundUniverseName.feature",
//				"classpath:com/Cucumber/InvestmentObjectiveCode.feature",
//				"classpath:com/Cucumber/ISINCodeID.feature",
//				"classpath:com/Cucumber/ISINCurrencyClassID.feature",
//				"classpath:com/Cucumber/LipperFundName.feature",
//				"classpath:com/Cucumber/LipperSymbolID.feature",
//				"classpath:com/Cucumber/LoadCode.feature",
//				"classpath:com/Cucumber/ManagementCompanyCodeName.feature",
//				"classpath:com/Cucumber/MFTHOMSONCodeID.feature",
				"classpath:com/Cucumber/Month.feature",
//				"classpath:com/Cucumber/NasdaqTickerID.feature",
//				"classpath:com/Cucumber/ParentInvAsstClassCodeName.feature",
//				"classpath:com/Cucumber/ParentInvFocusCodeName.feature",
//				"classpath:com/Cucumber/ParentInvGrpCodeName.feature",
//				"classpath:com/Cucumber/PermID.feature",
//				"classpath:com/Cucumber/PortfolioNumber.feature",
				"classpath:com/Cucumber/Quarter.feature",
//				"classpath:com/Cucumber/RICID.feature",
//				"classpath:com/Cucumber/USCIKID.feature",
//				"classpath:com/Cucumber/USFundID.feature",
//				"classpath:com/Cucumber/USSECClassID.feature",
//				"classpath:com/Cucumber/USSECSeriesID.feature",
//				"classpath:com/Cucumber/ValorID.feature",
//				"classpath:com/Cucumber/WKNGermanID.feature",
		"classpath:com/Cucumber/Year.feature"})
		public class RunTest {
			

//		@BeforeClass
//		public static void loadFeatureList(){
//			
//			//RunIT.class.getAnnotations()[0].
//			for (Annotation an : RunIT.class.getAnnotations()){
//				
//				System.out.println(an.getClass().getDeclaredFields()[0].toString());
//			}
//		}
	}
