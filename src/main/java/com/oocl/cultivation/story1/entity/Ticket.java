package com.oocl.cultivation.story1.entity;

import java.util.Objects;

public class Ticket {

    private String number;

    private Ticket(String number) {
        this.number = number;
    }

    public static Ticket generateTicket(Car car){
        return new Ticket(car.getCardId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return number.equals(ticket.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
