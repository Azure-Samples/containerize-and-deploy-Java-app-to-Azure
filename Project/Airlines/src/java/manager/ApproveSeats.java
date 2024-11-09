package manager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import models.Flight;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MuhammadHarris
 */
public class ApproveSeats extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ArrayList<Flight> flights = (ArrayList<Flight>) (getServletContext().getAttribute("flights"));
        var flightsObj = getServletContext().getAttribute("flights");
        if (flightsObj instanceof List<?> list) {
            @SuppressWarnings("unchecked")
            var flights = (List<Flight>)list;

            flights.stream()
            .filter(flight -> flight.getFlightName().equals(request.getParameter("flight_name")))
            .findFirst()
            .ifPresent(flight -> {
                flight.setOldESeats(0);
                flight.setOldBSeats(0);
                flight.setOldFSeats(0);
                flight.setOldTSeats(0);
                flight.isChanged = false;            
            });
        }        
        response.sendRedirect("ApproveSeats.jsp");
    }
}
