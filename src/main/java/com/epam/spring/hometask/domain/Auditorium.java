package com.epam.spring.hometask.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class Auditorium extends DomainId {
    private String name;
    private long numberOfSeats;
    private Set<Integer> vipSeats;
    private double vipSeatsMultiplier;

    public Auditorium() {
    }

    public Auditorium(String name, Integer numberOfSeats, Set<Integer> vipSeats, Double vipSeatsMultiplier) {
        this.name = name;
        this.numberOfSeats = (long) numberOfSeats;
        this.vipSeats = vipSeats;
        this.vipSeatsMultiplier = vipSeatsMultiplier;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setVipSeats(String seats) {
        String [] ints = seats.replace("[", "").replace("]", "").split("[, ]+");
        Set<Integer> set = Arrays.stream(ints).map(Integer::parseInt).collect(Collectors.toSet());
        this.vipSeats = set;
    }

    public void setVipSeatsMultiplier(double vipSeatsMultiplier) {
        this.vipSeatsMultiplier = vipSeatsMultiplier;
    }

    public long getNumberOfSeats() {
        return this.numberOfSeats;
    }

    public Set<Integer> getVipSeats() {
        return this.vipSeats;
    }

    public double getVipSeatsMultiplier() {
        return this.vipSeatsMultiplier;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Auditorium)) {
            return false;
        } else {
            Auditorium that = (Auditorium) o;
            return this.getNumberOfSeats() == that.getNumberOfSeats() &&
                    Double.compare(that.getVipSeatsMultiplier(), this.getVipSeatsMultiplier()) == 0 &&
                    Objects.equals(this.getName(), that.getName()) &&
                    Objects.equals(this.getVipSeats(), that.getVipSeats());
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getNumberOfSeats(), this.getVipSeats(), this.getVipSeatsMultiplier());
    }
    @Override
    public String toString() {
        return "Auditorium{id=" + this.getId() + ", name='" + this.name + '\'' + ", numberOfSeats=" + this.numberOfSeats + ", vipSeats=" + this.vipSeats + '}';
    }
}
