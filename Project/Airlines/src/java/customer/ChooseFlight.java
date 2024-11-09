package customer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import models.Customer;
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
public class ChooseFlight extends HttpServlet {


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
                    f.setCustomer((Customer)(request.getSession().getAttribute("customer")));
                });
            }
        
        /*
        //CODE TO SEND EMAIL
        
        
        final String username = "your email";
        final String password = "your email account's password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
          });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("your email"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(((Customer)(request.getSession().getAttribute("customer"))).getEmail() ));
                message.setSubject("Your Itinery");
                message.setText("Departure City:" + f.getDepartureCity() + " Arrival City:" + f.getArrivalCity() + " Departure Date:" + f.getDepartureDate() + " Arrival Date:" + f.getArrivalDate());

                Transport.send(message);


        } catch (MessagingException e) {
        }       
        */
        
        request.getRequestDispatcher("CurrentBooking.do").forward(request, response);
    }

}
