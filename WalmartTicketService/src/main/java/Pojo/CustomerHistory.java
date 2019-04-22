package Pojo;

import java.util.ArrayList;
import java.util.List;

public class CustomerHistory {


    private String CustomerEmail;

    private List<Seat> ticketHistory=new ArrayList<Seat>();

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public List<Seat> getTicketHistory() {
        return ticketHistory;
    }

    public void setTicketHistory(List<Seat> ticketHistory) {
        this.ticketHistory = ticketHistory;
    }
}
