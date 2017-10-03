# GridDeveloper
Closest event location in a grid based on Manhattan distance

This program takes in input from the user to generate the events randomly in a grid of size of 21 X 21 by assigning coordinates to the events.
A user can give his/her location in the grid to get the list of 5 closest events to the user with the cheapest ticket prices and the ticket count based on Manhattan distance between the two coordinates.

--------------------------
Directions to run - 

Driver class is the class which contains the main method. Upon executing the program, a dialogue prompt pops up asking the user either to choose or not choose the number of seeds(events) to be generated in the grid.

If the answer is Y, then the user can input a size of <441(coordinates limit in the grid) and the events are randomly generated depending on the count.
If N, then a deault number of seeds(50 here) are created in the grid.

Once the events are created in the grid, a dialogue prompt pops up asking the user to enter his location coordinates as space seperated values.
If the user inputs valid coordinates, the program executes further otherwise a dialogue pops up asking the user to enter valid coordinates

With the entry of valid user coordinates, the program finds the list 5 nearest events to the user location and displays the event details(Event Id, Event coordinates, distance from the user coordinates, cheapest ticket price, Number of cheapest tickets available, Number of total tickets available)

After displaying the event details, the program exits

-----------------------------
A jar file has also been created for sthe same. It can be executed by running the below commmand from the location of the jar

java -jar Grid_Developer.jar
