package com.Cucumber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class PostgresqlDB {

	static ResultSet rs = null;
	static Statement stm = null;
	static String testKey = "endDate";
	static String testKey2 = "year";
	static String testKey3 = "month";
	static String defDate = "2013-12-31 00:00:00";
	static String defYear = "2013";
	static String defMonth = "12";

	static String endDate = ReadPropertiesFile.ReadProperties(testKey);
	static String year = ReadPropertiesFile.ReadProperties(testKey2);
	static String month = ReadPropertiesFile.ReadProperties(testKey3);

	public static Connection connect(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://nbs-putnamvpc-cluster1.cchmdkhfkmdq.us-east-1.redshift.amazonaws.com:5439/putnamdev", "etl_user",
					"Etl123pass");
			System.out.println("Connection to Postgresql is successfull");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found, please add it to your project");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("Connection to Postgresql failed");
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("An error occured while closing the connection.");
			e.printStackTrace();
		}
	}
	public static List<String> ParentInv_AssetClass_Code_Name_Dim(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select h.parentinvasstclass,h.parentinvasstclassnm, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamacctinvhierarchy h  on f.lippersymbolvalue = h.lippersymbol where f.enddate = '%s' group by h.parentinvasstclass,h.parentinvasstclassnm, f.enddate order by h.parentinvasstclass asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ParentInvDivision(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select h.parentinvdiv, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamacctinvhierarchy h  on f.lippersymbolvalue = h.lippersymbol where f.enddate = '%s' group by h.parentinvdiv, f.enddate order by h.parentinvdiv asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> AccountCodeName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.accountcode, r.accountname, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamacctinvhierarchy r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.accountcode, r.accountname, f.enddate order by r.accountcode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ParentInvGrpCodeName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select h.parentinvgrp,h.parentinvgrpnm, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamacctinvhierarchy h  on f.lippersymbolvalue = h.lippersymbol where f.enddate = '%s' group by h.parentinvgrp,h.parentinvgrpnm, f.enddate order by h.parentinvgrp asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ParentInvFocusCodeName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select h.parentinvfocus,h.parentinvfocusnm, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamacctinvhierarchy h  on f.lippersymbolvalue = h.lippersymbol where f.enddate = '%s' group by h.parentinvfocus,h.parentinvfocusnm, f.enddate order by h.parentinvfocus asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ManagementCompanyCodeName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.mgtcompanycode,r.mgtcompanyname, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.mgtcompanycode,r.mgtcompanyname, f.enddate order by r.mgtcompanycode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> FundFamilyName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.fundfamilyname, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.fundfamilyname, f.enddate order by r.fundfamilyname asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> FundClassCode(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.fundclasscode, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.fundclasscode, f.enddate order by r.fundclasscode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date:"+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> InvestmentObjectiveCode(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.investmentobjcode, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.investmentobjcode, f.enddate order by r.investmentobjcode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> LoadCode(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.loadcode, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.loadcode, f.enddate order by r.loadcode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> DistributionChannel(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.distributionchannel, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.distributionchannel, f.enddate order by r.distributionchannel asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ClosedToNewInv(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.closedtonewinvestors, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.closedtonewinvestors, f.enddate order by r.closedtonewinvestors asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> PortfolioNumber(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.portfoliono, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.portfoliono, f.enddate order by r.portfoliono asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> FundClubID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.fundclubid, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.fundclubid, f.enddate order by r.fundclubid asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> DataFrequencyCode(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.datafrequencycode, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%S' group by r.datafrequencycode, f.enddate order by r.datafrequencycode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> LipperFundName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.lipperfundname, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.lipperfundname, f.enddate order by r.lipperfundname asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> FullFundName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select r.fullfundname, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r  on f.lippersymbolvalue = r.lippersymbol where f.enddate = '%s' group by r.fullfundname, f.enddate order by r.fullfundname asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> FundUniverseName(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select  flat_uni_map.funduniversename, round(sum(flat_qa_fact.fundvalue)) as flow_value, flat_qa_fact.enddate from qa_testing_flatlgfundsflowinc_copy AS flat_qa_fact inner join FlatPutnamFundRefData_table1 AS flat_refdata on (flat_qa_fact.lippersymbolvalue = flat_refdata.lippersymbol) inner join FlatLipperUniverseInvObjMap_table1 AS flat_uni_map on (flat_refdata.fundclasscode = flat_uni_map.fundclsinvobjcode) where flat_qa_fact.enddate = '%s' Group by flat_qa_fact.enddate, flat_uni_map.funduniversename order by flat_uni_map.funduniversename asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> AssetID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select assetid, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by assetid, enddate order by assetid asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> LipperSymbolID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select lippersymbolid, lippersymbolvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by lippersymbolid, lippersymbolvalue, enddate order by lippersymbolid asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> NasdaqTickerID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select nasdaqid, nasdaqvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by nasdaqid, nasdaqvalue, enddate order by nasdaqid asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> CUSIPID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select cusipid, cusipvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by cusipid, cusipvalue, enddate order by cusipid, cusipvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ISINCodeID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select cusipid, cusipvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by cusipid, cusipvalue, enddate order by cusipid, cusipvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ISINCurrencyClassID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select isincurrencyclassid, isincurrencyclassvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by isincurrencyclassid, isincurrencyclassvalue, enddate order by isincurrencyclassid, isincurrencyclassvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> MFTHOMSONCodeID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select mfidthomsoncodeid, mfidthomsoncodevalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by mfidthomsoncodeid, mfidthomsoncodevalue, enddate order by mfidthomsoncodeid, mfidthomsoncodevalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> Quarter(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select  round(sum(f.fundvalue)) as flow_value from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r on f.lippersymbolvalue = r.lippersymbol left join flatputnamacctinvhierarchy h on f.lippersymbolvalue = h.lippersymbol where f.enddate = '%s' and f.lippersymbolvalue != 'N/A'";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> Year(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select  round(sum(f.fundvalue)) as flow_value from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r on f.lippersymbolvalue = r.lippersymbol left join flatputnamacctinvhierarchy h on f.lippersymbolvalue = h.lippersymbol where extract(year from f.enddate) = '%s' and f.lippersymbolvalue != 'N/A'";

			if(year.isEmpty()){
				System.out.println("The DB Query is running on Default Year : "+defYear);
				sql = String.format(sql, defYear);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on Year : "+year);
				sql = String.format(sql, year);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> Month(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select  round(sum(f.fundvalue)) as flow_value from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r on f.lippersymbolvalue = r.lippersymbol left join flatputnamacctinvhierarchy h on f.lippersymbolvalue = h.lippersymbol where extract(year from f.enddate) = '%s' and extract(month from f.enddate) = '%s' and f.lippersymbolvalue != 'N/A'";

			if(month.isEmpty() && year.isEmpty()){
				System.out.println("The DB Query is running on Default Year : "+defYear+" And Default Month : "+defMonth);
				sql = String.format(sql, defYear, defMonth);
				rs = stm.executeQuery(sql);	
			}else if(month.isEmpty() && !(year.isEmpty())){
				System.out.println("The DB Query is running on Default Year : "+defYear+" And Entered Month : "+month);
				sql = String.format(sql, defYear, month);
				rs = stm.executeQuery(sql);	
			}else if(!(month.isEmpty()) && year.isEmpty()){
				System.out.println("The DB Query is running on Entered Year : "+year+" And Default Month : "+defMonth);
				sql = String.format(sql, year, defMonth);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on Year : "+year+" And Month : "+month);
				sql = String.format(sql, year, month);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> Date(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select  round(sum(f.fundvalue)) as flow_value from qa_testing_flatlgfundsflowinc_copy f left join flatputnamfundrefdata r on f.lippersymbolvalue = r.lippersymbol left join flatputnamacctinvhierarchy h on f.lippersymbolvalue = h.lippersymbol where f.enddate = '%s' and f.lippersymbolvalue != 'N/A'";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> Currency(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select f.currency, r.accountcode, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy f left join flatputnamacctinvhierarchy r on f.lippersymbolvalue = r.lippersymbol where enddate = '%s' and r.accountcode = '2HF' group by f.currency, r.accountcode, enddate order by r.accountcode asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> PermID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select permid, permvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by permid, permvalue, enddate order by permid, permvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> RICID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select ricid, ricvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by ricid, ricvalue, enddate order by ricid, ricvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> USCIKID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select uscikid, uscikvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by uscikid, uscikvalue, enddate order by uscikid, uscikvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> USFundID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select usfundnoid, usfundnovalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by usfundnoid, usfundnovalue, enddate order by usfundnoid, usfundnovalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> USSECSeriesID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select ussecseriesid, ussecseriesvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by ussecseriesid, ussecseriesvalue, enddate order by ussecseriesid, ussecseriesvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}

			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> USSECClassID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select ussecclassid, ussecclassvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by ussecclassid, ussecclassvalue, enddate order by ussecclassid, ussecclassvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> FundTypeLevel(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select a.fundtypelevel, round(sum(f.fundvalue)) as flow_value, f.enddate from qa_testing_flatlgfundsflowinc_copy f left join (select FlatPutnamAcctInvHierarchy_table1.lippersymbol,FlatLipperSymbolAcctMap_table1.fundtypelevel from FlatPutnamAcctInvHierarchy_table1 left join FlatLipperSymbolAcctMap_table1 on FlatPutnamAcctInvHierarchy_table1.lippersymbol = FlatLipperSymbolAcctMap_table1.lippersymbol and FlatPutnamAcctInvHierarchy_table1.accountcode = FlatLipperSymbolAcctMap_table1.acctcode) a  on f.lippersymbolvalue = a.lippersymbol where f.enddate = '%s' and f.lippersymbolvalue != 'N/A' group by a.fundtypelevel, f.enddate";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> ValorID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select valorid, valorvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by valorid, valorvalue, enddate order by valorid, valorvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> WKNGermanID(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select wkngermanid, wkngermanvalue, round(sum(fundvalue)) as flow_value, enddate from qa_testing_flatlgfundsflowinc_copy where enddate = '%s' group by wkngermanid, wkngermanvalue, enddate order by wkngermanid, wkngermanvalue asc";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("flow_value"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> LipperSymbolCount(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select count(distinct lippersymbolvalue) as count from qa_testing_flatlgfundsflowinc_copy where enddate = '%s'";

			if(endDate.isEmpty()){
				System.out.println("The DB Query is running on Default End Date : "+defDate);
				sql = String.format(sql, defDate);
				rs = stm.executeQuery(sql);	
			}else{
				System.out.println("The DB Query is running on End Date : "+endDate);
				sql = String.format(sql, endDate);
				rs = stm.executeQuery(sql);	
			}
			while (rs.next()) {
				getVal.add(rs.getString("count"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
	public static List<String> TotalLipperSymbol(){
		Connection con = connect();
		List<String> getVal = new ArrayList<String>();
		try{
			stm = con.createStatement();
			String sql = "select count(lippersymbolvalue) as count from dimlippersymbol";

			rs = stm.executeQuery(sql);	

			while (rs.next()) {
				getVal.add(rs.getString("count"));
			}
			System.out.println("Results fetched successfully");
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
			try{
				if(stm != null){
					stm.close();
				}
				if(con != null){
					close(con);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return getVal;
	}
}