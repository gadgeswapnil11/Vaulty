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

public class DeleteOne extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fname = req.getParameter("name");
		
		
		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admissionprocess_db", "root",
					"root");

			PreparedStatement pst = con
					.prepareStatement("delete from admissionprocess_db.student_info where FirstName=?");
			pst.setString(1, fname);

			int a = pst.executeUpdate();

			if (a > 0) {
				RequestDispatcher rd = req.getRequestDispatcher("GetAllData.jsp");
				rd.forward(req, resp);
			}


			
		}catch (Exception e) {
		}

	}

}
