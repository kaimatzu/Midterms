package com.kt.midterms;

public class Bill {
    int previous;
    int current;
    Pipe type;
    int pack;
    int month;

    public Bill(int previous, int current, Pipe type, int pack, int month) {
        this.previous = previous;
        this.current = current;
        this.type = type;
        this.pack = pack;
        this.month = month;
    }

    // TODO Milestone 3: Calculate bill.
    public double get_bill() {
        double consumption = (current - previous) * type.diameter;
        switch(pack){
            case 0: //premium
                if(consumption < 100)
                    return 850;
                else if(consumption >= 100 && consumption <= 250)
                    return 1500;
                return 2750;
            case 1: //regular
                if(consumption < 20)
                    return consumption * 15.75;
                else if(consumption >= 20 && consumption < 50)
                    return 500 + (12 * (consumption - 20));
                return 800 + (10 * (consumption - 50));
            case 2: //basic
                return consumption * 22.50;
        }
        return 0;
    }
}