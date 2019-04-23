

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


                                      Instructions

Open the command line(CMD). Or open the terminal of Intellij or eclipse.
execute WalmartTicketService\target>java -jar WalmartTicketService-1.0-SNAPSHOT.jar

to start this project.Then you will see:


***************************************
*  welcome to walmart ticket service  *
***************************************
intput 1--->Num Seats Available
intput 2--->Find And HoldSeats
intput 3--->Reserve Seats


if you tpye 1, you can see how many seat available:
***********************************
**"The Available seats number:100"*
***********************************


if you tpye 2, you will see information below:
then yon can type your email
**********************************
**"type your email:aaa@gmail.com"*
**********************************



then you can type how many seats you want to hold.
*****************************
**"type numbers of ticket:2"*
*****************************


(if you type wrong email or number of seats greater than availabe seats number,
System will return a friendly wrong informaiton)
**************************************************
**"Invalid email" or "sorry insufficient tickets"*
**************************************************



if successful, you will see information below:
*********************************************************
**"your seatHoldId:8710                                **
** you hold:  [ROW:5  COL:5]  [ROW:5  COL:4]           **
** you have 30 Seconds to check out. Or it will expire"**
*********************************************************


then if you type 1, you will see:
********************************
**The Available seats number:98*
********************************
(because you held 2seats)

Then you type 3, input your seatHoldId and email.
(seatHoldId and Email must be consistent, otherwise system will return error information)
if you wait more than 30s,your held seats are expired.
********************************************************************
**Type your email:aaa@gmail.com                                    *
**Type your seatHoldId:8710                                        *
**Your held seats are expired! please find and hold seats again!   *
********************************************************************

if successful, you will see the info below:
**************************************************************************************
**Thank you for using walmart ticket service!Your confirmation Code:20190422125438399*
**Enjoy your movie:Avengers: Endgame                                                 *
**************************************************************************************

System will return a confirmation Code for you.
