package com.bridgelabz.firstservletproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Login Servlet Testing", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "Amrrish"), @WebInitParam(name = "password", value = "Roshan") })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get request parameters for username and password
		String username = request.getParameter("user");
		String password = request.getParameter("pwd");

		// Get servlet config init parameters
		String userID = getServletConfig().getInitParameter("user");
		String userPassword = getServletConfig().getInitParameter("password");

		// Validate the username
		boolean isUsernameValid = isValidUsername(username);

		if (isUsernameValid && userID.equals(username) && userPassword.equals(password)) {
			request.setAttribute("user", username);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			if (!isUsernameValid) {
				out.println(
						"<font color=red>Invalid username. Name should start with a capital letter and have a minimum of 3 characters.<font>");
			} else {
				out.println("<font color=red>Incorrect username or password.<font>");
			}
			rd.include(request, response);
		}
	}

	// Method to validate the username
	private boolean isValidUsername(String username) {
		if (username.length() >= 3 && Character.isUpperCase(username.charAt(0))) {
			return true;
		}
		return false;
	}
}
