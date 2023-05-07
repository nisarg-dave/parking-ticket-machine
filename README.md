# Parking Ticket Machine

A parking ticket machine project written in Java.

The parking ticket machine allows the user to pick the following car parks to enter:

- City Car Park (Total bays = 50, Hourly cost = $2.50)
- Foreshore Car Park (Total bays = 35, Hourly cost = $1.75)
- Hotel Car Park (Total bays = 25, Hourly cost = $4.50)
- Western Car Park (Total bays = 35, Hourly cost = $1.25)

The ticket generated from the machine contains the following properties:

- Unique ticket number (The user has to remember and enter this when exiting)
- Entry time
- Exit time (This is set when the user exits the car park)

The cost of the ticket is determined by the hourly cost of the car park selected and the total duration. If the duration
is less than 30 minutes, parking is free. Additionally, if the user has entered the car park after 10pm and exits the
car park before 5am the next day, parking is also free. 
