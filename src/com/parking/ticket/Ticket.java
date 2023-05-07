package com.parking.ticket;

import java.time.LocalDateTime;


class Ticket {

    /**
     * The unique ticket number
     */
    private String ticketNumber;

    /**
     * The entry time
     */
    private LocalDateTime entryTime;

    /**
     * The exit time
     */
    private LocalDateTime exitTime;


    /**
     * Creates a new ticket
     * Generates a new ticket number and sets the current time as the entry time
     */
    Ticket() {
        // inits
        GenerateTicketNumber generateTicketNumber = new GenerateTicketNumber();
        this.ticketNumber = generateTicketNumber.generateNumber();
        generateTicketNumber.addTicketNumber(ticketNumber);
        this.entryTime = LocalDateTime.now();
    }

    /**
     * Gets the unique ticket number
     *
     * @return the ticket number
     */
    String getTicketNumber() {
        return this.ticketNumber;
    }

    /**
     * Gets the entry time
     *
     * @return the entry time
     */
    LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    /**
     * Sets the exit time on the ticket
     */
    void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }

    /**
     * Gets the exit time
     *
     * @return the exit time
     */
    LocalDateTime getExitTime() {
        return this.exitTime;
    }
}
