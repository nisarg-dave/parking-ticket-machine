package com.parking.ticket;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class TicketMachine {

    private CityCarPark cityCarPark = new CityCarPark();
    private DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


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

    public double calculateCost(LocalTime entranceTime, LocalTime exitTime, double hourlyCost){
        double hourDiff = exitTime.getHour() - entranceTime.getHour();
        double minuteDiff = exitTime.getMinute() - entranceTime.getMinute();
        double cost = (hourlyCost * hourDiff) + ((minuteDiff/60) * hourlyCost);
        System.out.println(cost);
        return 0;
    }

    public void cityCarParkMenu(Scanner sc){
        // User selection
        int userSelection;

        // New Ticket init
        LocalDate newDate;
        LocalTime newEntranceTime;
        String newTicketNumber;

        // Existing Ticket inits
        String ticketNumberOfUser;
        Ticket ticketOfUser;
        double cost;

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
                newDate = t.getDate();
                newEntranceTime = t.getEntranceTime();
                newTicketNumber = t.getTicketNumber();
                System.out.println("\n### Printing Ticket ###");
                System.out.printf("Date: %s\n", this.dayFormatter.format(newDate));
                System.out.printf("Entrance Time: %s\n", this.timeFormatter.format(newEntranceTime));
                System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", newTicketNumber);
                break;
            case 2:
                System.out.print("\nPlease enter your ticket number: ");
                ticketNumberOfUser = sc.next();
                ticketOfUser = this.cityCarPark.getTicketOfUser(ticketNumberOfUser);
                if(ticketOfUser != null){
                    ticketOfUser.setExitTime();
//                    cost = calculateCost(ticketOfUser.getEntranceTime(),ticketOfUser.getExitTime(), this.cityCarPark.getHourlyCost());
                    cost = calculateCost(LocalTime.parse("07:47"),LocalTime.parse("16:08"), this.cityCarPark.getHourlyCost());

                }
                else{
                    System.out.println("Cannot find ticket number. Please try again.");
                }
            }

        if(userSelection != 3){
            cityCarParkMenu(sc);
        };
    }



}
