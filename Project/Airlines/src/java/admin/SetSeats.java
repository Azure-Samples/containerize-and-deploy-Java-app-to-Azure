package admin;

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
public class SetSeats extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var flightsObj = getServletContext().getAttribute("flights");
        if (flightsObj instanceof List<?> list) {
            @SuppressWarnings("unchecked")
            var flights = (List<Flight>)list;

            flights.stream()
                .filter(f -> f.getFlightName().equals(request.getParameter("flight_name")))
                .findFirst()
                .ifPresent(f -> {
                    f.setOldESeats(f.getEconomySeats());
                    f.setOldBSeats(f.getBusinessSeats());
                    f.setOldFSeats(f.getFirstSeats());
                    f.setOldTSeats(f.getTotalSeats());

                    f.setEconomySeats(Integer.parseInt(request.getParameter("seats_e")));
                    f.setBusinessSeats(Integer.parseInt(request.getParameter("seats_b")));
                    f.setFirstSeats(Integer.parseInt(request.getParameter("seats_f")));
                    f.setTotalSeats(f.getEconomySeats() + f.getBusinessSeats() + f.getFirstSeats());

                    f.setCurrentSeats(f.getTotalSeats());
                    f.isChanged = true;
                });
        }
        response.sendRedirect("SetSeats.jsp");        
    }
}
