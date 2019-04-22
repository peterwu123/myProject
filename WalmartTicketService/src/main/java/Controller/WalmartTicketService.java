package Controller;

import Dao.TicketDaoImpl;
import MyException.MyException;
import Pojo.SeatHold;
import Service.TicketService;
import Service.TicketServiceImpl;

import java.util.Scanner;

public class WalmartTicketService {



    public static void main(String[] args){

        //start here
        walmartTicketSystem();
    }





    public static void walmartTicketSystem(){

        TicketService ticketService=new TicketServiceImpl();
        TicketDaoImpl.initial();  //initial my dataBase
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("***************************************");
            System.out.println("*  welcome to walmart ticket service  *");
            System.out.println("***************************************");
            System.out.println("intput 1--->Num Seats Available");
            System.out.println("intput 2--->Find And HoldSeats");
            System.out.println("intput 3--->Reserve Seats");
            String str = sc.nextLine();
            if (str.equals("1")) {
                System.out.println("The Available seats number:" + ticketService.numSeatsAvailable());
            } else if (str.equals("2")) {
                try {
                    System.out.println("type your email:");
                    String email = sc.nextLine();
                    System.out.println("type numbers of ticket:");
                    int num=((TicketServiceImpl) ticketService).verifyInputIsNum(sc.nextLine());
                    ((TicketServiceImpl) ticketService).seatNumberValidation(num);
                    SeatHold seatHold=ticketService.findAndHoldSeats(num,email);
                    System.out.println("your seatHoldId:"+seatHold.getSeatHoldId());
                    System.out.print("you hold:");
                    seatHold.getHoldSeats().forEach(p->System.out.print("  [ROW:"+p.getRow()+"  COL:"+p.getCol()+"]"));
                    System.out.println("");
                    System.out.println("you have 30 Seconds to check out. Or it will expire");
                } catch (MyException e) {
                    System.out.println(e.getMyExceptionMessage());
                }
            } else if (str.equals("3")) {
                try {
                    System.out.print("Type your email:");
                    String email=sc.nextLine();
                    System.out.print("Type your seatHoldId:");
                    int seatHoldId=((TicketServiceImpl) ticketService).verifyInputIsNum(sc.nextLine());
                    String confirmationCode=ticketService.reserveSeats(seatHoldId,email);
                    System.out.println("Thank you for using walmart ticket service!Your confirmation Code:"+confirmationCode);
                    System.out.println("Enjoy your movie:Avengers: Endgame");
                }catch (MyException e){
                    System.out.println(e.getMyExceptionMessage());
                }

            } else {
                System.out.println("Invalid input! please try again.");
            }
        }
    }

}
