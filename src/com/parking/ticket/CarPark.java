package com.parking.ticket;

abstract class CarPark {

    abstract int getTotalBays();

    abstract int getBaysAvailable();

    abstract void bayTaken(Ticket t);

    abstract void bayFreed();

}

class CityCarPark extends CarPark {

    private final Ticket[] tickets;

    private final int totalParkingBays;

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

    void bayTaken(Ticket t) {
        for(int i = 0; i < this.tickets.length; i++){
            if(this.tickets[i] == null){
                this.tickets[i] = t;
                break;
            }
        }
        this.parkingBaysAvailable =- 1;
    }

    void bayFreed() {
        if(this.parkingBaysAvailable < this.totalParkingBays){
            this.parkingBaysAvailable += 1;
        }
    }
}


