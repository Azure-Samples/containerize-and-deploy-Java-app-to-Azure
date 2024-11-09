package admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import models.Features;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MuhammadHarris
 */
public class ChangeFeatures extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var featuresObj = getServletContext().getAttribute("features");
        if (featuresObj instanceof List<?> list) {
            @SuppressWarnings("unchecked")
            var f = (List<Features>) list;

            char[] s = {'e','b','f'};
            IntStream.range(0, 3).forEach(i -> {
                var feature = f.get(i);
                
                // Saving old values
                feature.setNewSeatPitch(feature.getSeatPitch());
                feature.setNewSeatWidth(feature.getSeatWidth());
                feature.setNewVideoType(feature.getVideoType());
                feature.setNewPowerType(feature.getPowerType());
                feature.setNewSeatType(feature.getSeatType());
                feature.setNewPrice(feature.getPrice());
                
                // Setting new values temporarily
                feature.setSeatPitch(Double.parseDouble(request.getParameter("seat_pitch_" + s[i])));
                feature.setSeatWidth(Double.parseDouble(request.getParameter("seat_width_" + s[i])));
                feature.setVideoType(request.getParameter("video_" + s[i]));
                feature.setPowerType(request.getParameter("power_" + s[i]));
                feature.setSeatType(request.getParameter("seat_type_" + s[i]));
                feature.setPrice(Integer.parseInt(request.getParameter("price_" + s[i])));
            });
        
            f.get(1).setNewWifi( f.get(1).getWifi());
            f.get(2).setNewWifi( f.get(2).getWifi());
        
            f.get(1).setWifi( request.getParameter("wifi_b"));
            f.get(2).setWifi( request.getParameter("wifi_f"));
            
            f.get(2).setNewSpecialFood(f.get(2).getSpecialFood());
            f.get(2).setSpecialFood( request.getParameter("special_food_f"));       
            
            Features.isChanged = true;
        }        
        response.sendRedirect("ChangeFeatures.jsp");
    }


}
