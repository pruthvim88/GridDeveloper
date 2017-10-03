import java.util.List;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[]args){
		
		//Creating an object grid which initializes a grid of size 21X21
		Grid grid = new Grid();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to give a seed count for fixing the number of events - Y/N");
		String response = sc.nextLine();
		
		Scanner scInt = new Scanner(System.in);
		if(response.toUpperCase().equals("Y")){
			System.out.println("Enter the seed count for a grid of size 21X21");
			int count = scInt.nextInt();
			grid.generateRandomSeeds(count);
			
		}
		else // generating seeds of count 50 randomly 
			grid.generateRandomSeeds(50);
		
		System.out.println();
		System.out.println("Events are all seeded");
		System.out.println();
		System.out.println("Welcome user!!! Before you proceed further, please enter your location as comma seperated coordinates");
		String[] coordinates = sc.nextLine().split(",");
		
		//Check if the input entered for coordinates are valid or not
		while(coordinates.length!=2 || Math.abs(Integer.parseInt(coordinates[0]))>grid.grid[0].length/2 || Math.abs(Integer.parseInt(coordinates[1]))>grid.grid.length/2){
			if(coordinates.length!=2)
				System.out.println("You have entered invalid coordinates. Please enter your valid coordinates again");
			else{
				System.out.println("Your coordinates are outside the realm of the grid. Please enter a coordinate inside the grid");
			}
			coordinates = sc.nextLine().split(",");
		}
		
		//Storing the location of the user
		User user = new User();
		user.setLocation(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
		
		//Gather the top 5 closest events and display them
		List<Event> closestEventList = grid.getClosestLocations(user.getLocation());
		System.out.println();
		System.out.println("Displaying your closest events");
		System.out.println();
		for(int i=0;i<Math.min(6, closestEventList.size());i++){
			Event event = closestEventList.get(i);
			event.displayEventDetails();
			System.out.println("Distance of the event from user location - "+user.getLocation().manhattanDistance(event.getLocation()));
			System.out.println();
		}
		
		scInt.close();
		sc.close();
	}
}
