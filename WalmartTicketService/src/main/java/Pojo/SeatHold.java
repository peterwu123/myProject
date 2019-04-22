package Pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeatHold {

    private int seatHoldId;

    private List<Seat> holdSeats=new ArrayList<Seat>();

    public int getSeatHoldId() {
        return seatHoldId;
    }

    public void setSeatHoldId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public List<Seat> getHoldSeats() {
        return holdSeats;
    }

    public void setHoldSeats(List<Seat> holdSeats) {
        this.holdSeats = holdSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatHold seatHold = (SeatHold) o;
        return seatHoldId == seatHold.seatHoldId &&
                Objects.equals(holdSeats, seatHold.holdSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatHoldId, holdSeats);
    }
}
