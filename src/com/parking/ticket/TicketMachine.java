package com.parking.ticket;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;

public class TicketMachine {

    private CityCarPark cityCarPark = new CityCarPark();
    private DateTimeFormatter dayTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");


    public static void main(String args[]) {

        TicketMachine ticketMachine = new TicketMachine();

        // initialise Scanner
        Scanner sc = new Scanner(System.in);

        while (true) {
            ticketMachine.printUserMenu(sc);
        }
    }

    public void printUserMenu(Scanner sc) {
        int carParkSelection;

        do {
            System.out.println("\nPlease select which car park you would like to go to.");
            System.out.println("1) City Car Park");
            System.out.println("2) Foreshore Park");
            System.out.println("3) Hotel Car Park");
            System.out.println("4) Underground Car Park");
            System.out.println("5) Western Car Park");
            System.out.println("6) Quit");
            System.out.print("Enter choice: ");
            carParkSelection = sc.nextInt();

            if (carParkSelection < 1 || carParkSelection > 6) {
                System.out.println("Invalid choice. Please choose 1-6");
            }
        } while (carParkSelection < 1 || carParkSelection > 6);

        switch (carParkSelection) {
            case 1:
                cityCarParkMenu(sc);
        }

        if (carParkSelection != 6) {
            printUserMenu(sc);
        }
    }

    public double calculateCost(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyCost) {
        double cost;
        Duration timeElapsed = Duration.between(entryTime, exitTime);
        long timeElapsedInMinutes = timeElapsed.toMinutes();

        if (timeElapsedInMinutes <= 30) {
            cost = 0;
        }
        // Else if the entrance and exit days are just a day apart and the entrance if after 10pm and exit is before 5am the next day
        else if (entryTime.toLocalDate().equals(exitTime.minusDays(1).toLocalDate()) && entryTime.toLocalTime().isAfter(LocalTime.parse("22:00")) && exitTime.toLocalTime().isBefore(LocalTime.parse("05:00"))) {
            cost = 0;
        } else {
            cost = ((double) timeElapsedInMinutes / 60) * hourlyCost;
        }

        return cost;
    }

    public void cityCarParkMenu(Scanner sc) {
        // User selection
        int userSelection;

        // New Ticket init
        LocalDateTime newEntryTime;
        String newTicketNumber;

        // Existing Ticket inits
        String ticketNumberOfUser;
        Ticket ticketOfUser;
        LocalDateTime entryTime;
        LocalDateTime exitTime;
        Duration timeElapsed;
        double cost;

        do {
            System.out.printf("\nWelcome to City Car Park. Bays available: %d.", this.cityCarPark.getBaysAvailable());
            if (this.cityCarPark.getBaysAvailable() == 0) {
                System.out.println("\nSorry, this car park has no bays available. Please go to another one.");
                printUserMenu(sc);
            }
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) Enter Car Park");
            System.out.println("2) Pay and Exit");
            System.out.println("3) Quit");
            System.out.print("Enter choice: ");
            userSelection = sc.nextInt();
            if (userSelection < 1 || userSelection > 3) {
                System.out.println("Invalid choice. Please choose 1-3");
            }
        } while (userSelection < 1 || userSelection > 3);


        switch (userSelection) {
            case 1:
                Ticket t = new Ticket();
                this.cityCarPark.bayTaken(t);
                newEntryTime = t.getEntryTime();
                newTicketNumber = t.getTicketNumber();
                System.out.println("\n### Printing Ticket ###");
                System.out.printf("Entrance Time: %s\n", this.dayTimeFormatter.format(newEntryTime));
                System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", newTicketNumber);
                break;
            case 2:
                System.out.print("\nPlease enter your ticket number: ");
                ticketNumberOfUser = sc.next();
                ticketOfUser = this.cityCarPark.getTicketOfUser(ticketNumberOfUser);
                if (ticketOfUser != null) {
                    this.cityCarPark.bayFreed();
                    ticketOfUser.setExitTime();
                    entryTime = ticketOfUser.getEntryTime();
                    exitTime = ticketOfUser.getExitTime();
                    timeElapsed = Duration.between(entryTime, exitTime);
//                    timeElapsed = Duration.between(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T17:00:00"));
                    cost = calculateCost(ticketOfUser.getEntryTime(), ticketOfUser.getExitTime(), this.cityCarPark.getHourlyCost());
//                    cost = calculateCost(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T16:30:00"), this.cityCarPark.getHourlyCost());
                    System.out.println("\n### Printing Receipt ###");
                    System.out.println("Location: City Car Park");
                    System.out.printf("Entry Time: %s\n", this.dayTimeFormatter.format(entryTime));
                    System.out.printf("Exit Time: %s\n", this.dayTimeFormatter.format(exitTime));
                    System.out.printf("Duration: %sd. %sh. %sm. \n", timeElapsed.toDays(), timeElapsed.toHours() % 24, timeElapsed.toMinutes() % 60);
                    System.out.printf("Ticket Number: %s\n", ticketNumberOfUser);
                    System.out.printf("Cost: $%.2f\n", cost);
                } else {
                    System.out.println("Cannot find ticket number. Please try again.");
                }
        }

        if (userSelection != 3) {
            cityCarParkMenu(sc);
        }
    }


}
