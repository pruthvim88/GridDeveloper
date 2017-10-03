import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	
	//Initializing a grid with ranges of X from [-10,10] and Y from [-10,10]. We can also vary the size of the grid as per our need.
	protected int gridLength = 21;
	protected int gridWidth = 21;
	
	//A 2-D array of all the events with their locations offset to (0,0) in the grid.
	protected Event[][] grid = new Event[gridWidth][gridLength];
	
	//A 2-D array to keep track of the locations which has an event
	protected boolean[][] hasEvent = new boolean[gridWidth][gridLength];
	
	//Total number of events that can be arranged in the grid with the constraint of maximum one per coordinate
	protected int maxEvents = gridLength*gridWidth;
	
	//To track the the number of events in the grid and assign an event ID
	protected int eventCounter = 0;
	
	//To track events which are allocated in the grid
	protected int totalEvents = 0;
	
	
	//Method to generate event data at multiple locations in the grid randomly
	public void generateRandomSeeds(int count){
		Random rand = new Random();
		for(int i=0;i<count;i++){
			
			// Generates a coordinate with x in [-10,10] and y in [-10,10]
			while(true){
				
				int x = rand.nextInt(gridLength);
				int y = rand.nextInt(gridWidth);
				
				// Event creation stops when the number of events cross the grid threshold
				if(this.maxEvents<=0){
					System.out.println("Maximum event threshold reached. Cannot organize anymore events in this grid");
					break;
				}
				
				//Check if there is an event already present in the generated coordinates
				if(!hasEvent[x][y]){
					this.eventCounter+=1;
					Event event = new Event(eventCounter);
					
					//Transforming the grid to coordinates such that the grid is centered at origin
					int xCoord = x-gridLength/2;
					int yCoord = y-gridWidth/2;
					
					//Set the location of the event
					event.setLocation(xCoord, yCoord);
					
					int finalTickets = 0;
					
					for(int j=0;j<event.ticketPrices.length;j++){
						
						//Ticket count is randomly taken in a range of 0 to 100
						int minTickets = 0;
						int maxTickets = 100;
						int ticketCount = rand.nextInt(maxTickets-minTickets)+minTickets;
					
						//Ticket prices are randomly chosen in a range of $1 to $100 and are added to the ticket array in Event
						float minPrice = 1.0f;
						float maxPrice = 100.0f;
						float finalPrice = rand.nextFloat()*(maxPrice-minPrice)+minPrice;
						finalPrice = (float) (Math.round(finalPrice*100.0)/100.0);
						
						//Add the ticket price and the ticket count to the ticket arrays in Event object
						event.ticketPrices[j] = finalPrice;
						event.ticketCounts[j] = ticketCount;
						
						//Sum all the ticket counts of each class to get the final ticket count
						finalTickets+=ticketCount;
					}
					
					event.setTicketCount(finalTickets);
					
					//Assigning the event created to the grid at the coordinates generated and marking the grid position as occupied and increment the event count
					this.grid[x][y] = event;	
					this.hasEvent[x][y]=true;
					this.maxEvents-=1;
					incrementEventsSize();
					break;
					
				}
				
			}
			
		}
		
	}
	
	
	//Assumptions - Grid is always centered at (0,0) and is symmetric
	public List<Event> getClosestLocations(Location input){
		
		List<Event> closestEvents = new ArrayList<Event>();
		int xLim = gridLength/2;
		int yLim = gridWidth/2;
		
		int x = input.getXCoordinate()+xLim;
		int y = input.getYCoordinate()+yLim;
		
		//Tracks the number of closest events
		int count = 0;
		
		//maxRadius is used as a limit to see if we are done checking for the events in the grid
		int maxRadius = Math.max(Math.max(x, gridLength-x), Math.max(y, gridWidth-y));
		
		//If there exists an event in the user location, add the event to the closest event list
		if(hasEvent[x][y]){
			closestEvents.add(grid[x][y]);
			count++;
		}
		
		int radius = 1;
		
		//Once the closest event count reaches 5, we exit from the loop
		while(count<5 && maxRadius>0){
			
			//If the event coordinates are present only on x-axis
			if(x+radius<gridLength){
				
				if(hasEvent[x+radius][y]){
					closestEvents.add(grid[x+radius][y]);
					count++;
				}
				
				if(count>=5) break;
			}
			
			int j=1;
			
			//If there exists a y-coordinate for the event
			for(int i=x+radius-1;i>x;i--){

				if(i<gridLength && y+j<gridWidth){
					if(hasEvent[i][y+j]){
						closestEvents.add(grid[i][y+j]);
						count++;
					}
				}
				if(count>=5) break;
				
				if(i<gridLength && y-j>=0){
					if(hasEvent[i][y-j]){
						closestEvents.add(grid[i][y-j]);
						count++;
					}
				}
				
				if(count>=5) break;
				j++;
			}
			
			for(int i=x;i>x-radius;i--){
			
				if(i>0 && y+j<gridWidth){
					if(hasEvent[i][y+j]){
						closestEvents.add(grid[i][y+j]);
						count++;
					}
				}
				
				if(count>=5) break;
				
				if(i>0 && y-j>=0){
					if(hasEvent[i][y-j]){
						closestEvents.add(grid[i][y-j]);
						count++;
					}
				}
				
				if(count>=5) break;
				j--;
			}
			
			//If the event coordinates are present only on x-axis
			if(x-radius>0){

				if(hasEvent[x-radius][y]){
					closestEvents.add(grid[x-radius][y]);
					count++;
				}
				
				if(count>=5) break;
				
			}
			
			maxRadius--;
			radius++;
		}
		
		return closestEvents;
	}
	
	//Return the total events in the grid
	public int getTotalEvents(){
		return this.totalEvents;
	}
	
	//Used as a sub routine to increment the count of the events in case of a event addition
	protected void incrementEventsSize(){
		this.totalEvents++;
	}
	
}
