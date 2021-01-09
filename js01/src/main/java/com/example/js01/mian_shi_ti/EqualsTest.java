package com.example.js01.mian_shi_ti;

import java.util.Objects;

public class EqualsTest {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsTest that = (EqualsTest) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static void main(String[] args) {

       EqualsTest t1 = new EqualsTest();
       EqualsTest t2 = new EqualsTest();
       t1.setId(1);
       t1.setName("1");
       t2.setId(1);
       t2.setName("1");
       t1.equals(t2);
    }
}
