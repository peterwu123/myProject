package Dao;

import Pojo.Seat;
import Pojo.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TicketDaoImpl implements TicketDao{


    //assume this static block is my database
    private static Map<Integer, Seat> allSeats=new ConcurrentHashMap<Integer, Seat>();
    private static Map<String, UserInfo> allUser=new ConcurrentHashMap<String, UserInfo>();
    private static Map<Integer, List<Seat>> seatsHold=new ConcurrentHashMap<Integer, List<Seat>>();
    public static void initial(){  //initial my DB
        int seatId=0;
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                allSeats.put(seatId,new Seat(i,j));
                seatId++;
            }
        }
        allUser.put("aaa@gmail.com",new UserInfo("aaa@gmail.com"));
        allUser.put("bbb@gmail.com",new UserInfo("bbb@gmail.com"));
        allUser.put("ccc@gmail.com",new UserInfo("ccc@gmail.com"));
    }

    @Override
    public List<Seat> findAllSeats() {
        List<Seat> seats=new ArrayList<>();
        Set<Map.Entry<Integer,Seat>> set=allSeats.entrySet();
        for (Map.Entry<Integer,Seat> entry:set){
            seats.add(entry.getValue());
        }
        return seats;
    }

    @Override
    public Seat findSeatById(int id) {
        return allSeats.get(id);
    }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        return allUser.get(email);
    }

    @Override
    public void saveSeatsHold(int id, List<Seat> seats) {
        seatsHold.put(id,seats);
    }

    @Override
    public List<Seat> findHoldSeatsById(int id) {
        return seatsHold.get(id);
    }

    @Override
    public void deletSeatsHoldById(int id) {
        seatsHold.remove(id);
    }
}
