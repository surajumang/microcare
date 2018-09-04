package com.care.controller.seeker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowJobToEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        Get parameter from the request to refer to the job to be edited.
         */

        /*
        Extract the job object from the session object(it was set by the requsting JSP).
        Or bypass the Servlet and let the Jsp sent this request deirectly to the confirmation page.
        The confirmation page will then requst the EditJob servlet with the updated params.
         */

    }
}
