package customer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import models.Flight;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MuhammadHarris
 */
public class SearchFlights extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var flightsObj = getServletContext().getAttribute("flights");
        if (flightsObj instanceof List<?> list) {
            @SuppressWarnings("unchecked")
            var flights = (List<Flight>)list;
            ArrayList<Flight> results = new ArrayList<>();
            results.add(flights.get(1));
            request.setAttribute("results", results);
        }
        request.getRequestDispatcher("ShowFlights.jsp").forward(request,response);
    }
}
