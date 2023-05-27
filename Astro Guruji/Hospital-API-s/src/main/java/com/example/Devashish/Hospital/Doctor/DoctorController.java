package com.example.Devashish.Hospital.Doctor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DoctorController
{
   // API to register doctors
	
	@PostMapping("/doctor/register")
	public String register(String email, String password)
	{
		try {
			//implementation of jdbc
			
			Class.forName("com.mysql.jdbc.Driver");
		Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Mysql@21");
		Statement stmt=con.createStatement();
		String query="insert into doctors values('"+email+"','"+password+"')";
		int i=stmt.executeUpdate(query);
		System.out.println("Number of rows inserted "+i);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Resgistraion is successful for    "+email+" ";
	}
	
	
	
	//API for doctors login
	
	@PostMapping("/doctor/login")
	public String login(String email,String password)
	{
		try {
			
			//jdbc
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Mysql@21");
			Statement stmt=con.createStatement();
			String query="select password from doctors where email ='"+email+"'";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
			{
				String pwd=rs.getString("password");
				if(pwd.equals(password))
				{
					return "You are valid user";
				}
				else
				{
					return "incorrect password";
				}
			}
			else
			{
				return "you are not registered";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "this is Devashish";
	}
	
	// creating report of patients
	
	
	@PostMapping("/patient/report")
	public String report(String email, String report,String patient_name)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Mysql@21");
		Statement stmt=con.createStatement();
		String query="update patients set report='"+report+"'where patient_name='"+patient_name+"'";
		int i=stmt.executeUpdate(query);
		System.out.println("Number of rows inserted "+i);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "report is stored for    "+patient_name+" ";
	}
	
	
	
	//fetching  reports
	
	@GetMapping("/report")

	public Map  report_all()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Mysql@21");
			PreparedStatement stmt=con.prepareStatement("select * from patients");
			ResultSet rs =stmt.executeQuery();
			List l= new ArrayList();
			Map map =new HashMap();
			
			while(rs.next())
			{
				
				map.put(rs.getString(2),rs.getString(4));
			
			
				
			}
			return map;
			
		}
		catch(Exception ex)
		{

			
		}
		
		
		return null;
	}
	
}
