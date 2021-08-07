# story 1
[x] Case1  
Given a parking lot, and a car,  
When park the car,  
Then return a parking ticket. 

[x] Case2  
Given a parking lot with a parked car, and a parking ticket,  
When fetch the car,  
Then return the parked car.

[x] Case3  
Given a parking lot with two parked cars, and two parking tickets,  
When fetch the car twice,  
Then return the right car with each ticket.

[x] Case4  
Given a parking lot, and a wrong parking ticket,  
When fetch the car,  
Then return no car.

[x] Case5
Given a parking lot, and a parking ticket that has been fetched,  
When fetch the car,  
Then return no car.

[x] Case6
Given a full parking lot, and car,  
When park the car,  
Then return no parking ticket.


# story 2
[x] Case1  
Given a parking lot, and an unrecognized ticket,  
When fetch the car,  
Then return nothing with error message "Unrecognized parking ticket.”

[x] Case2  
Given a parking lot, and a used ticket,  
When fetch the car,  
Then return nothing with error message "Unrecognized parking ticket."

[x] Case3  
Given a parking lot without any position, and a car,  
When park the car,  
Then return nothing with error message "No available position."


# story 3
[x] Case1  
Given a parking lot, a standard parking boy, and a car,  
When park the car,  
Then return a parking ticket.

[x] Case2  
Given a parking lot with a parked car, a standard parking boy, and a parking ticket,
When fetch the car,  
Then return the parked car.

[x] Case3  
Given a parking lot with two parked cars, a standard parking boy, and two parking
tickets,  
When fetch the car twice,  
Then return the right car with each ticket 

[x] Case4  
Given a parking lot, a standard parking boy, and a wrong parking ticket,  
When fetch the car,  
Then return nothing with error message "Unrecognized parking ticket.”

[x] Case5  
Given a parking lot, a standard parking boy, and a used parking ticket,  
When fetch the car,  
Then return nothing with error message "Unrecognized parking ticket."

[x] Case6  
Given a parking lot without any position, a standard parking boy, and a car,  
When park the car,  
Then return nothing with error message "No available position."


# story 4
[x] Case1  
Given a standard parking boy, who manage two parking lots, both with available position, and a car,  
When park the car,   
Then the car will be parked to the first parking lot  

[x] Case2  
Given a standard parking boy, who manage two parking lots, first is full and second with available position, and a car,  
When park the car,  
Then the car will be parked to the second parking lot  

[x] Case3  
Given a standard parking boy, who manage two parking lots, both with a parked car, and two parking ticket,  
When fetch the car twice,  
Then return the right car with each ticket

[x] Case4    
Given a standard parking boy, who manage two parking lots, and an unrecognized ticket,  
When fetch the car,  
Then return nothing with error message "Unrecognized parking ticket.”

[] Case5  
Given a standard parking boy, who manage two parking lots, and a used ticket,  
When fetch the car,  
Then return nothing with error message "Unrecognized parking ticket."

[] Case6  
Given a standard parking boy, who manage two parking lots, both without any position, and a car,  
When park the car,  
Then return nothing with error message "No available position."