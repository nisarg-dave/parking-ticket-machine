package com.parking.ticket;

import java.util.Random;
import java.util.ArrayList;

class GenerateTicketNumber {

    private ArrayList<String> ticketNumbers = new ArrayList<String>();


    String generateNumber(){

        //init
        String ticketNumber = "";
        Random rng = new Random();
        int len = 6;
        boolean isNotUnique = true;

        while(isNotUnique){
            // Generate the number
            for (int c= 0; c < len; c++){
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                ticketNumber += ((Integer)rng.nextInt(10)).toString();
            }

            if(this.ticketNumbers.size() == 0){
                isNotUnique = false;
            }

            for(String tn: this.ticketNumbers){
                if(tn.compareTo(ticketNumber) != 0){
                    isNotUnique = false;
                }
            }
        }

        return ticketNumber;
    }

    void addTicketNumber(String ticketNumber){
        this.ticketNumbers.add(ticketNumber);
    }
}
