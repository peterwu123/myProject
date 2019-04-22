package Pojo;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {


    private String userEmail;
    private List<Seat> ticketHistory=new ArrayList<Seat>();

    private int seatHoldId=0;

    public int getSeatHoldId() {
        return seatHoldId;
    }

    public void setSeatHoldId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public UserInfo(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Seat> getTicketHistory() {
        return ticketHistory;
    }

    public void setTicketHistory(List<Seat> ticketHistory) {
        this.ticketHistory = ticketHistory;
    }
}
