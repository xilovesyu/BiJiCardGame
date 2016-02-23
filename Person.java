package com.xi;

import java.util.ArrayList;

/**人的简单类,包括姓名，钱数，以及三副牌
 * Created by xijiaxiang on 2016/2/16.
 */
public class Person {
    private String name;
    private int Money;
    ArrayList<OneCards> onecards=new ArrayList<>();
    public Person(String name, int money) {
        this.name = name;
        Money = money;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }
    public void addOneCards(OneCards oneCards){
            this.onecards.add(oneCards);
    }

    public OneCards[] getOneCards() {
        OneCards[] temp=new OneCards[onecards.size()];
        for (int i = 0; i < onecards.size(); i++) {
            temp[i]=onecards.get(i);
        }
        return temp;
    }
}
