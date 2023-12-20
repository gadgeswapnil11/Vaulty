package com.tech;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegestrationPage extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String FristName = req.getParameter("fname");
		String LastNAme = req.getParameter("lname");
		String email = req.getParameter("email");
		String course = req.getParameter("course");
		String city = req.getParameter("city");
		String Uid = req.getParameter("uid");
		String pass = req.getParameter("pass");

		System.out.println(FristName + "  - " + LastNAme + "  - " + email + "  - " + course + "  - " + city + "  - "
				+ Uid + "  - " + pass);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admissionprocess_db", "root",
					"root");

			PreparedStatement pst = con.prepareStatement("insert into student_info values(?,?,?,?,?,?,?)");
			pst.setString(1, FristName);
			pst.setString(2, LastNAme);
			pst.setString(3, email);
			pst.setString(4, course);
			pst.setString(5, city);
			pst.setString(6, Uid);
			pst.setString(7, pass);
			int count = pst.executeUpdate();
			
			if(count>0) {

				RequestDispatcher rd= req.getRequestDispatcher("LoginPage.jsp");
				rd.forward(req, resp);
			}
			else {
				RequestDispatcher rd= req.getRequestDispatcher("failure.jsp");
				rd.forward(req, resp);			}

		} catch (Exception e) {
			System.out.println("JDBC EXCEPtion at RegestrationPage class = " + e.getMessage());
		}

	}

}
