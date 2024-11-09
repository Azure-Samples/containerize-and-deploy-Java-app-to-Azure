/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import models.Customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author MuhammadHarris
 */
public class CustomerManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("customer") == null) {

            HttpSession s = request.getSession();
            String customerEmail = request.getRemoteUser();

            var customersObj = getServletContext().getAttribute("customers");
            if (customersObj instanceof List<?> list) {
                @SuppressWarnings("unchecked")
                var c = (List<Customer>) list;

                c.stream()
                        .filter(customer -> customer.getEmail().equals(customerEmail))
                        .findFirst()
                        .ifPresent(customer -> s.setAttribute("customer", customer));

            }
        }
        String uri = request.getRequestURI();
        String page = uri.split("/")[2];
        page = page.split(".jsp")[0] + ".jsp";

        request.getRequestDispatcher(page).forward(request, response);
    }
}
