package com.xi;

import java.util.ArrayList;

/**test my software
 * Created by xijiaxiang on 2016/2/16.
 */
public class Test {
    public static void main(String[] args) {
       /// Card card=new Card(Card.MEIHUA,1);
        //System.out.println(card.printCard());
        CardHandler cardHandler=new CardHandler();
        Card[] cards=cardHandler.InitCards();
        ///////////////////////////
        Person[] p=new Person[6];
        for (int i=0;i<p.length;i++){
            p[i]=new Person("李"+(i+1)+"狗",100);
        }
        ///////////////////////////
        cardHandler.XiCards();
        cardHandler.FaCards(p);
        for (Person m:p) {
            m.printCard();
        }

        ///列出第一个人的牌的所有可能的组合
        ArrayList<Card[]> getAllCount=cardHandler.ListAllCount(p[0]);


    }
}
