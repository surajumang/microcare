package com.care.controller.seeker;

import com.care.dto.Sitter;
import com.care.dto.form.SitterFilterDTO;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
        String page = "/member/ErrorPage.jsp";
        FormBean formBean = FormPopulator.populate(req, SitterFilterDTO.class);
        // put this in a utility class if possible.

        List<Sitter> sitters = Collections.emptyList();
        /*
        Need to run a query on the DB
        SeekerService.showSitters(SitterFilterDTO)--> List<Sitter DTO>
        Need a way to map Sitter Bean to Sitter DTo
        SitterService.getSitters(SitterFilterDTO)---> List<Sitter Bean>

         */
        page = "/member/seeker/ShowSitters.jsp";
        req.setAttribute("sitters", sitters);
        getServletContext().getRequestDispatcher(page).forward(req, resp);

    }
}
