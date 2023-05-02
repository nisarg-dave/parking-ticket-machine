package com.parking.ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Ticket {

    private String ticketNumber;

    private LocalDate date;

    private LocalTime entranceTime;

    private LocalTime exitTime;


    Ticket(){
        // inits
        GenerateTicketNumber generateTicketNumber = new GenerateTicketNumber();
        this.ticketNumber = generateTicketNumber.generateNumber();
        generateTicketNumber.addTicketNumber(ticketNumber);
        this.date = LocalDate.now();
        this.entranceTime = LocalTime.now();
    }

    String getTicketNumber(){
        return this.ticketNumber;
    }

    LocalDate getDate(){
        return this.date;
    }

    LocalTime getEntranceTime(){
        return this.entranceTime;
    }

    void setExitTime(){
        this.exitTime = LocalTime.now();
    }

    LocalTime getExitTime(){
        return this.exitTime;
    }
}
