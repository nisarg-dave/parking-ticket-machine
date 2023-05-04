package com.parking.ticket;

import java.time.LocalDateTime;


class Ticket {

    private String ticketNumber;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;


    Ticket() {
        // inits
        GenerateTicketNumber generateTicketNumber = new GenerateTicketNumber();
        this.ticketNumber = generateTicketNumber.generateNumber();
        generateTicketNumber.addTicketNumber(ticketNumber);
        this.entryTime = LocalDateTime.now();
    }

    String getTicketNumber() {
        return this.ticketNumber;
    }

    LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }

    LocalDateTime getExitTime() {
        return this.exitTime;
    }
}
