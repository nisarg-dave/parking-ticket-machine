package com.parking.ticket;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TicketMachine {

    private CityCarPark cityCarPark = new CityCarPark();


    public static void main (String args[]){

        TicketMachine ticketMachine = new TicketMachine();

        // initialise Scanner
        Scanner sc = new Scanner(System.in);

        while(true){
            ticketMachine.printUserMenu(sc);
        }
    }

    public void printUserMenu(Scanner sc){
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

          if (carParkSelection < 1 || carParkSelection > 6){
              System.out.println("Invalid choice. Please choose 1-6");
          }
      } while(carParkSelection < 1 || carParkSelection > 6);

        switch(carParkSelection){
            case 1:
                cityCarParkMenu(sc);
        }

        if(carParkSelection != 6){
            printUserMenu(sc);
        }
    }

    public void cityCarParkMenu(Scanner sc){
        // User selection
        int userSelection;
        String ticketNumberOfUser;
        Ticket ticketOfUser;

        // Ticket init
        LocalDate date;
        LocalTime entranceTime;
        String ticketNumber;

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");



            do{
                System.out.printf("\nWelcome to City Car Park. Bays available: %d.", this.cityCarPark.getBaysAvailable());
                if(this.cityCarPark.getBaysAvailable() == 0){
                    System.out.println("\nSorry, this car park has no bays available. Please go to another one.");
                    printUserMenu(sc);
                }
                System.out.println("\nWhat would you like to do?");
                System.out.println("1) Enter Car Park");
                System.out.println("2) Pay and Exit");
                System.out.println("3) Quit");
                System.out.print("Enter choice: ");
                userSelection = sc.nextInt();
                if (userSelection < 1 || userSelection > 3){
                    System.out.println("Invalid choice. Please choose 1-3");
                }
            } while(userSelection < 1 || userSelection > 3);


            switch(userSelection){
                case 1:
                    Ticket t = new Ticket();
                    this.cityCarPark.bayTaken(t);
                    System.out.println(this.cityCarPark.getBaysAvailable());
                    date = t.getDate();
                    entranceTime = t.getEntranceTime();
                    ticketNumber = t.getTicketNumber();
                    System.out.println("\n### Printing Ticket ###");
                    System.out.printf("Date: %s\n", dayFormatter.format(date));
                    System.out.printf("Entrance Time: %s\n", timeFormatter.format(entranceTime));
                    System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", ticketNumber);
                    break;
                case 2:
                    System.out.print("Please enter your ticket number: ");
                    ticketNumberOfUser = sc.next();
                    ticketOfUser = this.cityCarPark.getTicketOfUser(ticketNumberOfUser);
                    System.out.println(ticketOfUser);


            }
        if(userSelection != 3){
            cityCarParkMenu(sc);
        };
    }



}
