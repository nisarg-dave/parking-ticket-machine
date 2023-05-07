package com.parking.ticket;

import java.util.Random;
import java.util.ArrayList;

class GenerateTicketNumber {

    /**
     * An array list that stores all the ticket numbers that are generated
     */
    private ArrayList<String> ticketNumbers = new ArrayList<String>();


    /**
     * Creates a new unique ticket number
     *
     * @return the ticket number
     */
    String generateNumber() {

        //init
        String ticketNumber = "";
        Random rng = new Random();
        int len = 6;
        boolean isNotUnique = true;

        while (isNotUnique) {
            // Generate the number
            for (int c = 0; c < len; c++) {
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                ticketNumber += ((Integer) rng.nextInt(10)).toString();
            }

            // If the array list is empty
            if (this.ticketNumbers.size() == 0) {
                // The ticket number is unique
                isNotUnique = false;
            }

            // For each ticket number
            for (String tn : this.ticketNumbers) {
                // If it is not the same as the existing ticket numbers
                if (tn.compareTo(ticketNumber) != 0) {
                    // The ticket number generated is unique
                    isNotUnique = false;
                }
            }
        }

        return ticketNumber;
    }

    /**
     * Adds the ticket number to the array list
     *
     * @param ticketNumber to add to the array list of ticket numbers
     */
    void addTicketNumber(String ticketNumber) {
        this.ticketNumbers.add(ticketNumber);
    }
}
