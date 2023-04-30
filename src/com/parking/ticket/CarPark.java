package com.parking.ticket;

abstract class CarPark {

    abstract int getTotalBays();

    abstract int getBaysAvailable();

    abstract void bayTaken();

}

class CityCarPark extends CarPark {

    private final Ticket[] tickets;

    private int totalParkingBays;

    private int parkingBaysAvailable;

    private final double hourlyCost = 2.5;

    CityCarPark(){
        // init
        this.tickets = new Ticket[50];
        this.totalParkingBays = this.tickets.length;
        this.parkingBaysAvailable = this.tickets.length;
    }

    int getTotalBays(){
        return this.totalParkingBays;
    }

    int getBaysAvailable(){
        return this.parkingBaysAvailable;
    }

    void bayTaken() {
        this.parkingBaysAvailable =- 1;
    }

}


