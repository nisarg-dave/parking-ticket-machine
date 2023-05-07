package com.parking.ticket;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;

public class TicketMachine {

    /**
     * The city car park
     */
    private CityCarPark cityCarPark = new CityCarPark();

    /**
     * The foreshore car park
     */
    private ForeshoreCarPark foreshoreCarPark = new ForeshoreCarPark();

    /**
     * The hotel car park
     */
    private HotelCarPark hotelCarPark = new HotelCarPark();

    /**
     * The western car park
     */
    private WesternCarPark westernCarPark = new WesternCarPark();

    /**
     * The day time formatter
     */
    private DateTimeFormatter dayTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");


    public static void main(String[] args) {

        // Create a ticket machine object
        TicketMachine ticketMachine = new TicketMachine();

        // initialise Scanner
        Scanner sc = new Scanner(System.in);

        while (true) {
            ticketMachine.printUserMenu(sc);
        }
    }

    /**
     * Prints the user menu
     *
     * @param sc the scanner object
     */
    private void printUserMenu(Scanner sc) {
        int carParkSelection;

        // Prompts the user to select which car park they would like to go to
        do {
            System.out.println("\nPlease select which car park you would like to go to.");
            System.out.println("1) City Car Park");
            System.out.println("2) Foreshore Park");
            System.out.println("3) Hotel Car Park");
            System.out.println("4) Western Car Park");
            System.out.println("5) Quit");
            System.out.print("Enter choice: ");
            carParkSelection = sc.nextInt();

            if (carParkSelection < 1 || carParkSelection > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (carParkSelection < 1 || carParkSelection > 5);

        // Displays the car park menu of the car park selected by the user
        switch (carParkSelection) {
            case 1:
                cityCarParkMenu(sc);
                break;
            case 2:
                foreshoreCarParkMenu(sc);
                break;
            case 3:
                hotelCarParkMenu(sc);
                break;
            case 4:
                westernCarParkMenu(sc);
                break;
            case 5:
                System.exit(0);
        }

        // Redisplay menu unless the user wants to quit
        if (carParkSelection != 5) {
            printUserMenu(sc);
        }
    }

    /**
     * Calculates the cost
     *
     * @param entryTime  the entry time
     * @param exitTime   the exit time
     * @param hourlyCost the hourly cost of the car park
     * @return the cost
     */
    private double calculateCost(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyCost) {
        double cost;
        Duration timeElapsed = Duration.between(entryTime, exitTime);
        long timeElapsedInMinutes = timeElapsed.toMinutes();

        // If the time elapsed is less than 30 minutes, the cost is $0
        if (timeElapsedInMinutes <= 30) {
            cost = 0;
        }
        // Else if the entrance and exit days are just a day apart and the entrance if after 10pm and exit is before 5am the next day, the cost is $0
        else if (entryTime.toLocalDate().equals(exitTime.minusDays(1).toLocalDate()) && entryTime.toLocalTime().isAfter(LocalTime.parse("22:00")) && exitTime.toLocalTime().isBefore(LocalTime.parse("05:00"))) {
            cost = 0;
        } else {
            // Calculates the cost
            cost = ((double) timeElapsedInMinutes / 60) * hourlyCost;
        }

        return cost;
    }

    /**
     * The city car park menu
     *
     * @param sc the scanner object
     */
    private void cityCarParkMenu(Scanner sc) {
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

        // Prompts the user to enter the car park or pay and exit if the there are bays available
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
            // The user enters the car park and a ticket is printed
            case 1:
                Ticket t = new Ticket();
                this.cityCarPark.bayTaken(t);
                newEntryTime = t.getEntryTime();
                newTicketNumber = t.getTicketNumber();
                System.out.println("\n### Printing Ticket ###");
                System.out.printf("Entrance Time: %s\n", this.dayTimeFormatter.format(newEntryTime));
                System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", newTicketNumber);
                break;
            // The user is prompted to enter their ticket number when exiting
            case 2:
                System.out.print("\nPlease enter your ticket number: ");
                ticketNumberOfUser = sc.next();
                ticketOfUser = this.cityCarPark.getTicketOfUser(ticketNumberOfUser);
                // If it isn't null, a receipt is printed and a bay is freed
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
                }
                // Else, prompts the user to try again as the ticket isn't found
                else {
                    System.out.println("Cannot find ticket number. Please try again.");
                }
        }

        // Redisplay the menu unless the user wants to quit
        if (userSelection != 3) {
            cityCarParkMenu(sc);
        }
    }

    /**
     * The foreshore car park menu
     *
     * @param sc the scanner object
     */
    private void foreshoreCarParkMenu(Scanner sc) {
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

        // Prompts the user to enter the car park or pay and exit if the there are bays available
        do {
            System.out.printf("\nWelcome to Foreshore Car Park. Bays available: %d.", this.foreshoreCarPark.getBaysAvailable());
            if (this.foreshoreCarPark.getBaysAvailable() == 0) {
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
            // The user enters the car park and a ticket is printed
            case 1:
                Ticket t = new Ticket();
                this.foreshoreCarPark.bayTaken(t);
                newEntryTime = t.getEntryTime();
                newTicketNumber = t.getTicketNumber();
                System.out.println("\n### Printing Ticket ###");
                System.out.printf("Entrance Time: %s\n", this.dayTimeFormatter.format(newEntryTime));
                System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", newTicketNumber);
                break;
            // The user is prompted to enter their ticket number when exiting
            case 2:
                System.out.print("\nPlease enter your ticket number: ");
                ticketNumberOfUser = sc.next();
                ticketOfUser = this.foreshoreCarPark.getTicketOfUser(ticketNumberOfUser);
                // If it isn't null, a receipt is printed and a bay is freed
                if (ticketOfUser != null) {
                    this.foreshoreCarPark.bayFreed();
                    ticketOfUser.setExitTime();
                    entryTime = ticketOfUser.getEntryTime();
                    exitTime = ticketOfUser.getExitTime();
                    timeElapsed = Duration.between(entryTime, exitTime);
//                    timeElapsed = Duration.between(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T17:00:00"));
                    cost = calculateCost(ticketOfUser.getEntryTime(), ticketOfUser.getExitTime(), this.foreshoreCarPark.getHourlyCost());
//                    cost = calculateCost(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T16:30:00"), this.cityCarPark.getHourlyCost());
                    System.out.println("\n### Printing Receipt ###");
                    System.out.println("Location: Foreshore Car Park");
                    System.out.printf("Entry Time: %s\n", this.dayTimeFormatter.format(entryTime));
                    System.out.printf("Exit Time: %s\n", this.dayTimeFormatter.format(exitTime));
                    System.out.printf("Duration: %sd. %sh. %sm. \n", timeElapsed.toDays(), timeElapsed.toHours() % 24, timeElapsed.toMinutes() % 60);
                    System.out.printf("Ticket Number: %s\n", ticketNumberOfUser);
                    System.out.printf("Cost: $%.2f\n", cost);
                }
                // Else, prompts the user to try again as the ticket isn't found
                else {
                    System.out.println("Cannot find ticket number. Please try again.");
                }
        }

        // Redisplay the menu unless the user wants to quit
        if (userSelection != 3) {
            foreshoreCarParkMenu(sc);
        }
    }

    /**
     * The hotel car park menu
     *
     * @param sc the scanner object
     */
    private void hotelCarParkMenu(Scanner sc) {
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

        // Prompts the user to enter the car park or pay and exit if the there are bays available
        do {
            System.out.printf("\nWelcome to Hotel Car Park. Bays available: %d.", this.hotelCarPark.getBaysAvailable());
            if (this.hotelCarPark.getBaysAvailable() == 0) {
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
            // The user enters the car park and a ticket is printed
            case 1:
                Ticket t = new Ticket();
                this.hotelCarPark.bayTaken(t);
                newEntryTime = t.getEntryTime();
                newTicketNumber = t.getTicketNumber();
                System.out.println("\n### Printing Ticket ###");
                System.out.printf("Entrance Time: %s\n", this.dayTimeFormatter.format(newEntryTime));
                System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", newTicketNumber);
                break;
            // The user is prompted to enter their ticket number when exiting
            case 2:
                System.out.print("\nPlease enter your ticket number: ");
                ticketNumberOfUser = sc.next();
                ticketOfUser = this.hotelCarPark.getTicketOfUser(ticketNumberOfUser);
                // If it isn't null, a receipt is printed and a bay is freed
                if (ticketOfUser != null) {
                    this.hotelCarPark.bayFreed();
                    ticketOfUser.setExitTime();
                    entryTime = ticketOfUser.getEntryTime();
                    exitTime = ticketOfUser.getExitTime();
                    timeElapsed = Duration.between(entryTime, exitTime);
//                    timeElapsed = Duration.between(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T17:00:00"));
                    cost = calculateCost(ticketOfUser.getEntryTime(), ticketOfUser.getExitTime(), this.hotelCarPark.getHourlyCost());
//                    cost = calculateCost(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T16:30:00"), this.cityCarPark.getHourlyCost());
                    System.out.println("\n### Printing Receipt ###");
                    System.out.println("Location: Hotel Park");
                    System.out.printf("Entry Time: %s\n", this.dayTimeFormatter.format(entryTime));
                    System.out.printf("Exit Time: %s\n", this.dayTimeFormatter.format(exitTime));
                    System.out.printf("Duration: %sd. %sh. %sm. \n", timeElapsed.toDays(), timeElapsed.toHours() % 24, timeElapsed.toMinutes() % 60);
                    System.out.printf("Ticket Number: %s\n", ticketNumberOfUser);
                    System.out.printf("Cost: $%.2f\n", cost);
                }
                // Else, prompts the user to try again as the ticket isn't found
                else {
                    System.out.println("Cannot find ticket number. Please try again.");
                }
        }

        // Redisplay the menu unless the user wants to quit
        if (userSelection != 3) {
            hotelCarParkMenu(sc);
        }
    }

    /**
     * The western car park menu
     *
     * @param sc the scanner object
     */
    private void westernCarParkMenu(Scanner sc) {
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

        // Prompts the user to enter the car park or pay and exit if the there are bays available
        do {
            System.out.printf("\nWelcome to Western Car Park. Bays available: %d.", this.westernCarPark.getBaysAvailable());
            if (this.westernCarPark.getBaysAvailable() == 0) {
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
            // The user enters the car park and a ticket is printed
            case 1:
                Ticket t = new Ticket();
                this.westernCarPark.bayTaken(t);
                newEntryTime = t.getEntryTime();
                newTicketNumber = t.getTicketNumber();
                System.out.println("\n### Printing Ticket ###");
                System.out.printf("Entrance Time: %s\n", this.dayTimeFormatter.format(newEntryTime));
                System.out.printf("Ticket Number: %s. Please make sure to remember this.\n", newTicketNumber);
                break;
            // The user is prompted to enter their ticket number when exiting
            case 2:
                System.out.print("\nPlease enter your ticket number: ");
                ticketNumberOfUser = sc.next();
                ticketOfUser = this.westernCarPark.getTicketOfUser(ticketNumberOfUser);
                // If it isn't null, a receipt is printed and a bay is freed
                if (ticketOfUser != null) {
                    this.westernCarPark.bayFreed();
                    ticketOfUser.setExitTime();
                    entryTime = ticketOfUser.getEntryTime();
                    exitTime = ticketOfUser.getExitTime();
                    timeElapsed = Duration.between(entryTime, exitTime);
//                    timeElapsed = Duration.between(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T17:00:00"));
                    cost = calculateCost(ticketOfUser.getEntryTime(), ticketOfUser.getExitTime(), this.westernCarPark.getHourlyCost());
//                    cost = calculateCost(LocalDateTime.parse("2023-05-06T08:30:00"), LocalDateTime.parse("2023-05-06T16:30:00"), this.cityCarPark.getHourlyCost());
                    System.out.println("\n### Printing Receipt ###");
                    System.out.println("Location: Western Car Park");
                    System.out.printf("Entry Time: %s\n", this.dayTimeFormatter.format(entryTime));
                    System.out.printf("Exit Time: %s\n", this.dayTimeFormatter.format(exitTime));
                    System.out.printf("Duration: %sd. %sh. %sm. \n", timeElapsed.toDays(), timeElapsed.toHours() % 24, timeElapsed.toMinutes() % 60);
                    System.out.printf("Ticket Number: %s\n", ticketNumberOfUser);
                    System.out.printf("Cost: $%.2f\n", cost);
                }
                // Else, prompts the user to try again as the ticket isn't found
                else {
                    System.out.println("Cannot find ticket number. Please try again.");
                }
        }

        // Redisplay the menu unless the user wants to quit
        if (userSelection != 3) {
            westernCarParkMenu(sc);
        }
    }

}
