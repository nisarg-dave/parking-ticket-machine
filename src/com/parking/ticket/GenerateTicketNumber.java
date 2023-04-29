package com.parking.ticket;

import java.util.Random;

class GenerateTicketNumber {
    private String carParkName;

    GenerateTicketNumber(String carParkName){
        this.carParkName = carParkName;
    }

    String generateNumber(){

        //init
        String ticketNumber;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        do {
            ticketNumber = "";
            // generate the number
            for (int c= 0; c < len; c++){
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                ticketNumber += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            // Check the number to make sure it's unique
            switch(this.carParkName) {
                case "City Car Park":
                    CityCarPark cityCarPark = new CityCarPark();
                    for (Ticket t : cityCarPark.getTickets()){
                        if(t != null){
                            if(ticketNumber.compareTo(t.getTicketNumber()) == 0){
                                nonUnique = true;
                            }
                        }
                    }
                    break;
            }

        }
        while(nonUnique);

        return ticketNumber;
    }
}
