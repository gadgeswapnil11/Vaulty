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

public class UpdateOne extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lName = req.getParameter("lastName");
		String lName1 = req.getParameter("lastName1");

		System.out.println(lName);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admissionprocess_db", "root",
					"root");

			PreparedStatement pst = con
					.prepareStatement("update admissionprocess_db.student_info set LastName=? where LastName=?");
			pst.setString(1, lName1);
			pst.setString(2, lName);

			int a = pst.executeUpdate();

			if (a > 0) {
				RequestDispatcher rd = req.getRequestDispatcher("UpdateSuccessFully.jsp");
				rd.forward(req, resp);
			}

		} catch (Exception e) {
			System.out.println("Exception is Here UpdateOneJava " + e.getMessage());
		}

	}

}
