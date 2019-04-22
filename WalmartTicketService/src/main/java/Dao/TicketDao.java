package Dao;

import Pojo.Seat;
import Pojo.UserInfo;

import java.util.List;

public interface TicketDao {

    List<Seat> findAllSeats();
    Seat findSeatById(int id);

    UserInfo findUserInfoByEmail(String email);

    void saveSeatsHold(int id,List<Seat> seats);

    List<Seat> findHoldSeatsById(int id);

    void deletSeatsHoldById(int id);
}
