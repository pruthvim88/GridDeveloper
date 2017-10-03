
public class Event {
	
	// A unique event id is assigned to each event
	protected int eventId;
	
	protected int totalTicketCount;
	
	//Lists for all ticket prices and their count for the event. I have arbitrarily fixed the categories as 10. If needed we can increment/decrement this category count
	protected float[] ticketPrices = new float[10];
	
	protected int[] ticketCounts = new int[10];
	
	protected Location location;
	
	public Event(int eventId){
		this.eventId = eventId;
	}
	
	//Set a location for the event
	public void setLocation(int x, int y){
		this.location = new Location(x, y);
	}
	
	//Get the location details of the event
	public Location getLocation(){
		return this.location;
	}
	
	//Get the event Id of the event
	public String getEventId(){
		if(this.eventId<10) return "00"+this.eventId;
		else if(this.eventId<100) return "0"+this.eventId;
		else return Integer.toString(this.eventId);
	}
	
	//Set the total ticket count for the event
	public void setTicketCount(int count){
		this.totalTicketCount=count;
	}
	
	//Get the total available tickets
	public int getAvailableTickets(){
		return this.totalTicketCount;
	}
	
	//Get the cheapest ticket price of all the available tickets	
	public int getCheapestTicketIndex(){
		int cheapestTicketInd = 0;
		float cheapestTicket = 100.0f;
		for(int i=0;i<this.ticketPrices.length;i++){
			if(this.ticketPrices[i]<cheapestTicket){
				cheapestTicket = this.ticketPrices[i];
				cheapestTicketInd = i;
			}			
		}
		return cheapestTicketInd;
	}
	
	//Display all the details of the event
	public void displayEventDetails(){
		
		System.out.println("EventId - "+getEventId());
		System.out.print("Location of the event - ");
		getLocation().printLocationDetails();
		System.out.println("Total tickets Available - "+getAvailableTickets());
		System.out.println("Price of the cheapest ticket - $"+this.ticketPrices[getCheapestTicketIndex()]);
		System.out.println("Number of cheapest tickets available - "+this.ticketCounts[getCheapestTicketIndex()]);
	}
}
