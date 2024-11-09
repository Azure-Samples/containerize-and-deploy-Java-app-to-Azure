package manager;

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
public class DisapproveFeatures extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ArrayList<Features> f = (ArrayList<Features>) (getServletContext().getAttribute("features"));

        var featuresObj = getServletContext().getAttribute("features");
        if (featuresObj instanceof List<?> list) {
            @SuppressWarnings("unchecked")
            var f = (List<Features>) list;

            IntStream.range(0, 3).forEach(i -> {
                var feature = f.get(i);            

                (feature).setSeatPitch(feature.getNewSeatPitch() );
                (feature).setSeatWidth(feature.getNewSeatWidth() );            
                (feature).setVideoType(feature.getNewVideoType() );            
                (feature).setPowerType(feature.getNewPowerType() );            
                (feature).setSeatType(feature.getNewSeatType() );            
                (feature).setPrice(feature.getNewPrice() );
                (feature).setWifi(feature.getNewWifi() );
                (feature).setSpecialFood(feature.getNewSpecialFood());

                (feature).setNewSeatPitch( 0);
                (feature).setNewSeatWidth( 0);            
                (feature).setNewVideoType( null);            
                (feature).setNewPowerType( null);            
                (feature).setNewSeatType( null);            
                (feature).setNewPrice( 0);
                (feature).setNewWifi( null);
                (feature).setNewSpecialFood( null);
                Features.isChanged = false;
            });
        }
        response.sendRedirect("ApproveFeatures.jsp");
    }
}
