package com.parking.ticket;

class CarPark {

    /**
     * An array of tickets
     */
    private Ticket[] tickets;

    /**
     * The total number of parking bays
     */
    private int totalParkingBays;

    /**
     * The number of parking bays available
     */
    private int parkingBaysAvailable;

    /**
     * The hourly cost
     */
    private double hourlyCost;

    /**
     * Sets the number of tickets, total parking bays and parking bays available
     *
     * @param numberOfBays the number of bays
     */
    void setNumberOfBays(int numberOfBays) {
        this.tickets = new Ticket[numberOfBays];
        this.totalParkingBays = numberOfBays;
        this.parkingBaysAvailable = numberOfBays;
    }

    /**
     * Return the number of bays available
     *
     * @return the number of bays available
     */
    int getBaysAvailable() {
        return this.parkingBaysAvailable;
    }

    /**
     * A bay has been taken
     *
     * @param t The ticket printed when the bay was taken
     */
    void bayTaken(Ticket t) {
        // Iterate through the ticket array
        for (int i = 0; i < this.tickets.length; i++) {
            // If the ticket at this index is null
            if (this.tickets[i] == null) {
                // Place the ticket passed as a parameter at this ticket
                this.tickets[i] = t;
                break;
            }
        }
        // Decrement the number of bays available
        this.parkingBaysAvailable--;
    }

    /**
     * A bay has been freed
     */
    void bayFreed() {
        // If the number of bays available is less than the total parking bays
        if (this.parkingBaysAvailable < this.totalParkingBays) {
            // Increment the number of bays available
            this.parkingBaysAvailable++;
        }
    }

    /**
     * Sets the hourly cost
     *
     * @param hourlyCost the hourly cost
     */
    void setHourlyCost(double hourlyCost) {
        this.hourlyCost = hourlyCost;
    }

    /**
     * Gets the hourly cost
     *
     * @return the hourly cost
     */
    double getHourlyCost() {
        return this.hourlyCost;
    }

    /**
     * Gets the ticket of the user
     *
     * @param enteredTicketNumber the entered ticket number by the user
     * @return the ticket of the user
     */
    Ticket getTicketOfUser(String enteredTicketNumber) {

        Ticket ticketOfUser = null;

        // For each ticket in the tickets array
        for (Ticket t : this.tickets) {
            // If the ticket is not null and ticket numbers matches
            if (t != null && enteredTicketNumber.compareTo(t.getTicketNumber()) == 0) {
                // Set the ticket of user to the ticket found
                ticketOfUser = t;
                break;
            }
        }

        return ticketOfUser;
    }
}

class CityCarPark extends CarPark {

    /**
     * Creates the City car park
     */
    CityCarPark() {
        // init
        this.setNumberOfBays(50);
        this.setHourlyCost(2.5);
    }
}

class ForeshoreCarPark extends CarPark {

    /**
     * Creates the Foreshore car park
     */
    ForeshoreCarPark() {
        // init
        this.setNumberOfBays(35);
        this.setHourlyCost(1.75);
    }
}

class HotelCarPark extends CarPark {

    /**
     * Creates the Hotel car park
     */
    HotelCarPark() {
        // init
        this.setNumberOfBays(25);
        this.setHourlyCost(4.5);
    }
}

class WesternCarPark extends CarPark {

    /**
     * Creates the Western car park
     */
    WesternCarPark() {
        // init
        this.setNumberOfBays(35);
        this.setHourlyCost(1.25);
    }
}


