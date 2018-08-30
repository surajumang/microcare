package com.care.controller.seeker;

import com.care.dto.Sitter;
import com.care.dto.form.SitterFilter;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowSitters extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Check the requested person is logged in.
        /*
        Fetch all the sitters which match the criteria specified in the request parameter(email, zipCode).

         */
        FormBean formBean = FormPopulator.populate(req, SitterFilter.class);
        // put this in a utility class if possible.

        List<Sitter> sitters = null;
        /*
        Need to run a query on the DB
        SeekerService.showSitters(SitterFilter)--> List<Sitter DTO>
        Need a way to map Sitter Bean to Sitter DTo
        SitterService.getSitters(SitterFilter)---> List<Sitter Bean>

         */
        req.setAttribute("sitters", sitters);
        RequestDispatcher rd = req.getRequestDispatcher("ShowSitters.jsp");
        rd.forward(req, resp);

    }
}
