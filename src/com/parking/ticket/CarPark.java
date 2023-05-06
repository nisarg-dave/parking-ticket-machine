package com.parking.ticket;

class CarPark {
    private Ticket[] tickets;

    private int totalParkingBays;

    private int parkingBaysAvailable;

    private double hourlyCost;

    void setNumberOfBays(int numberOfBays) {
        this.tickets = new Ticket[numberOfBays];
        this.totalParkingBays = numberOfBays;
        this.parkingBaysAvailable = numberOfBays;
    }

    int getBaysAvailable() {
        return this.parkingBaysAvailable;
    }

    void bayTaken(Ticket t) {
        for (int i = 0; i < this.tickets.length; i++) {
            if (this.tickets[i] == null) {
                this.tickets[i] = t;
                break;
            }
        }
        this.parkingBaysAvailable--;
    }

    void bayFreed() {
        if (this.parkingBaysAvailable < this.totalParkingBays) {
            this.parkingBaysAvailable++;
        }
    }

    void setHourlyCost(double hourlyCost) {
        this.hourlyCost = hourlyCost;
    }

    double getHourlyCost() {
        return this.hourlyCost;
    }

    Ticket getTicketOfUser(String enteredTicketNumber) {

        Ticket ticketOfUser = null;

        for (Ticket t : this.tickets) {
            if (t != null && enteredTicketNumber.compareTo(t.getTicketNumber()) == 0) {
                ticketOfUser = t;
                break;
            }
        }

        return ticketOfUser;
    }
}

class CityCarPark extends CarPark {

    CityCarPark() {
        // init
        this.setNumberOfBays(50);
        this.setHourlyCost(2.5);
    }
}

class ForeshoreCarPark extends CarPark {
    ForeshoreCarPark() {
        // init
        this.setNumberOfBays(35);
        this.setHourlyCost(1.75);
    }
}

class HotelCarPark extends CarPark {
    HotelCarPark() {
        // init
        this.setNumberOfBays(25);
        this.setHourlyCost(4.5);
    }
}

class WesternCarPark extends CarPark {
    WesternCarPark() {
        // init
        this.setNumberOfBays(35);
        this.setHourlyCost(1.25);
    }
}


