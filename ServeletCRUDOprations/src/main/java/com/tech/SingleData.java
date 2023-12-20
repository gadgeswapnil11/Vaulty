package com.tech;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingleData extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fname = req.getParameter("fname");
		List<StudentData> a2 = new ArrayList();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admissionprocess_db", "root",
					"root");

			PreparedStatement pst = con.prepareStatement("SELECT * FROM admissionprocess_db.student_info where FirstName=?");
			pst.setString(1, fname);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				StudentData s = new StudentData();
				s.setFname(rs.getString(1));

				s.setLname(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setCourse(rs.getString(4));
				s.setCity(rs.getString(5));
				s.setUid(rs.getString(6));
				s.setPass(rs.getString(7));
				a2.add(s);
			}
			
			
			System.out.println(a2);

			req.setAttribute("getsingle", a2);
			
			
			RequestDispatcher rd= req.getRequestDispatcher("GetSingleData.jsp");
			rd.forward(req, resp);
	
			
			
			

		} catch (Exception e) {
			System.out.println("JDBC EXCEPTION at Single Data Class " + e.getMessage());
		}

	}

}