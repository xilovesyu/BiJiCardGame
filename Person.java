package com.xi;

import java.util.ArrayList;

/**
 * Created by xijiaxiang on 2016/2/16.
 */
public class Person {
    private String name;
    private int Money;
    ArrayList<Card> cards=new ArrayList<>();
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
    public void addCard(Card card){
            this.cards.add(card);
    }
    public void printCard(){
        System.out.println();
        for (Card card:cards) {
            System.out.print(card.printCard()+",");
        }
        System.out.println();
    }
    public Card[] getCards() {
        Card[] temp=new Card[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            temp[i]=new Card(cards.get(i).getKind(),cards.get(i).getNum());
        }
        return temp;
    }
}
