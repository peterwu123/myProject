

****************************************
*                                      *
*   Walmart ticket service.            *
*         By Peter Wu                  *
****************************************

Assume We have 10*10 seats.
The program implements three basic functions.
****
   1.Find the number of available seats
****

   2.The system recommends the seat to the user based on the user's ticket reserve history.
   The user can hold the seats and can only hold it once, unless the user has reserved the seat before the next hold.
   After holding the seat, other users will find that these seats are not available.
   For the convenience of demo, the seat hold time is only 30 seconds.
****

   3.When the user holds the seat, the system will return a seatHoldId.
    The user enters this number and adds his own email to reserve the seat successfully.
    The seatHoldId and the user email must match, otherwise an error will return.
    The system will return a confirmation code after successful.
****

   4.If the number of seats the user wants to hold is greater than the available number, or if the user enters the wrong number, the system prompts the user to enter the wrong number.
   If the user's email input is incorrect or does not exist, the system will prompt the user to enter a wrong email.
****

   5.The system has strong scalability, such as storing user information and held seat information in the cache.
    If a distributed system needs to be established, each server can share the cache information.
    Store the data in the database.
     If the user is not satisfied with the seat recommended by the system, the user can make their own choice.
