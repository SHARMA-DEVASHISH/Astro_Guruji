package com.example.Devashish.Hospital.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//registration of patients

@RestController
public class PatientController
{

	@PostMapping("/patient/register")
	public String patient_register(String patient_name, String phone_number)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Mysql@21");
		Statement stmt=con.createStatement();
		String query="insert into patients(patient_name,phone_number) values('"+patient_name+"','"+phone_number+"')";
		int i=stmt.executeUpdate(query);
		System.out.println("Number of rows inserted "+i);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Resgistraion of patient is successful for "+patient_name+" ";
	}
}
