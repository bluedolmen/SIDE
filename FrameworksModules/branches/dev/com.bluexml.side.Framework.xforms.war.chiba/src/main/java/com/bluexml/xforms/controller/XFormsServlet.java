package com.bluexml.xforms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluexml.xforms.controller.navigation.NavigationManager;

/**
 * The Class XFormsServlet.
 */
public class XFormsServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7339716424047085875L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NavigationManager.getInstance().sendXForms(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("BlueXML XForms - XFormsServlet initialized.");
	}

	
}
