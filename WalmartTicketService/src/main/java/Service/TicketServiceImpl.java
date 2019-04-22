package Service;

import Dao.TicketDao;
import Dao.TicketDaoImpl;
import MyException.MyException;
import Pojo.Seat;
import Pojo.SeatHold;
import Pojo.UserInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao=new TicketDaoImpl();

    public int numSeatsAvailable() {

        int count=0;
        long holdtime=30000;  // hold 30 seconds
        Date currentTime=new Date();
        List<Seat> allSeats=ticketDao.findAllSeats();
        for (int i = 0; i < allSeats.size(); i++) {
            if (allSeats.get(i).isReserved()){
                continue;
            }
            if((currentTime.getTime()-allSeats.get(i).getHoldDate().getTime())<holdtime){
                continue;
            }
            count++;
        }
        return count;
    }

    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws MyException {
        UserInfo userInfo=emailValidation(customerEmail); // valid email. Return UserInfo if email correct!
        if (userInfo.getSeatHoldId()!=0){
            throw new MyException("You have already held your seats. Please reserve your seats.");
        }
        SeatHold seatHold=findandHoldSeatsHelper(userInfo,numSeats);
        seatHold.setSeatHoldId((int)(1+Math.random()*10000));   // generate a id for Seats hold
        userInfo.setSeatHoldId(seatHold.getSeatHoldId());       // put hold id into userinfo
        ticketDao.saveSeatsHold(seatHold.getSeatHoldId(),seatHold.getHoldSeats());
        return seatHold;
    }

    public String reserveSeats(int seatHoldId, String customerEmail) throws MyException{

        List<Seat> list=ticketDao.findHoldSeatsById(seatHoldId);
        UserInfo userInfo=emailValidation(customerEmail); // valid email. Return UserInfo if email correct!
        if(list==null){ //  seat hold Id NOT exist!
            throw new MyException("Invalid seatHoldId");
        }
        Seat seat=list.get(0);
        Date date=new Date();
        if ((date.getTime()-seat.getHoldDate().getTime())>30000){
            userInfo.setSeatHoldId(0);
            ticketDao.deletSeatsHoldById(seatHoldId); // delete this expired hold seats
            throw new MyException("Your held seats are expired! please find and hold seats again!");
        }
        if (seatHoldId!=userInfo.getSeatHoldId()){
            throw new MyException("you tpye wrong email or seat hold ID");
        }


        list.forEach(p->p.setReserved(true));    // set ticket reserved

        //add all reserved ticket to user's ticket history
        List<Seat> userHistory=ticketDao.findUserInfoByEmail(customerEmail).getTicketHistory();
        Iterator<Seat> iterator=list.iterator();
        while (iterator.hasNext()){
            userHistory.add(iterator.next());
        }
        // remove hold status from userInfo
        ticketDao.deletSeatsHoldById(userInfo.getSeatHoldId());
        userInfo.setSeatHoldId(0);
        //generate confirmationCode
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        int i = (int)(Math.random()*900 + 100);
        return df.format(new Date())+Integer.toString(i);  // return unique confirmation code using current time+random number
    }

    private SeatHold findandHoldSeatsHelper(UserInfo userInfo,int numSeats){
        List<Seat> seats=userInfo.getTicketHistory();
        int num=seats.size();
        if(num==0){
            num=1;
        }
        int rolSum=0;
        int colSum=0;
        Iterator<Seat> iterator=seats.iterator();
        while (iterator.hasNext()){

            Seat seat=iterator.next();
            rolSum+=seat.getRow();
            colSum+=seat.getCol();
        }

        //find average seat id from user's ticket history
        int average=(rolSum*10+colSum)/num;
        if (average==0){
            average=55;
        }
        //find and hold seats for user
        int n=0;
        SeatHold seatHold=new SeatHold();
        for (int i=1;i<=numSeats;i++){
            if(n%2==0){
                average+=n;
                if (!ifSeatAvailable(average)){
                    i--;
                    n++;
                }else {
                    Seat seat=ticketDao.findSeatById(average);
                    seat.setHoldDate(new Date());                 // update seatHold time
                    seatHold.getHoldSeats().add(seat);
                    n++;
                }
            }else {
                average-=n;
                if (!ifSeatAvailable(average)){
                    i--;
                    n++;
                }else {
                    Seat seat=ticketDao.findSeatById(average);
                    seat.setHoldDate(new Date());
                    seatHold.getHoldSeats().add(seat);
                    n++;
                }
            }
        }
        return seatHold;
    }

    private boolean ifSeatAvailable(int seatId){
        Date currentTime=new Date();
        if (seatId<0||seatId>=100){   //out of seats range
            return false;
        }
        if (ticketDao.findSeatById(seatId).isReserved()){    // if this seat has bean resevered, return false
            return false;
        }else if ((currentTime.getTime()-ticketDao.findSeatById(seatId).getHoldDate().getTime())<30000){  // 30 seconds hold time
            return false;
        }else {
            return true;
        }
    }

    public UserInfo emailValidation(String email) throws MyException{
        UserInfo userInfo=ticketDao.findUserInfoByEmail(email);
        if (userInfo==null) {
            throw new MyException("Invalid email");
        }
        return userInfo;
    }

    public void seatNumberValidation(int seatsNum) throws MyException{
        if(seatsNum>numSeatsAvailable()){
            throw new MyException("sorry insufficient tickets");
        }else if(seatsNum<=0){
            throw new MyException("sorry tickets number should be more than 1");
        }
    }

    public int verifyInputIsNum(String str) throws MyException{
        if(!str.matches("\\d+")){  //  Verify that the input is a number
            throw new MyException("please type correct number.");
        }else {
            return Integer.parseInt(str);
        }
    }
}