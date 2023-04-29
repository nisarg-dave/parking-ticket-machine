package com.parking.ticket;

import java.time.LocalDate;
import java.time.LocalTime;

class Ticket {

    private String ticketNumber;

    private LocalDate date;

    private LocalTime entranceTime;

    private LocalTime exitTime;

    private int cost;

    Ticket(String carParkName){
        // inits
        GenerateTicketNumber generateTicketNumber = new GenerateTicketNumber(carParkName);
        this.ticketNumber = generateTicketNumber.generateNumber();
        this.date = LocalDate.now();
        this.entranceTime = LocalTime.now();
    }

    String getTicketNumber(){
        return this.ticketNumber;
    }


}
