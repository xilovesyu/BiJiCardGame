package com.xi;

import java.util.ArrayList;
import java.util.Random;

/**处理一副牌
 * Created by xijiaxiang on 2016/2/16.
 */
public class CardHandler {
    private Card[] cards=null;
    public Card[] InitCards(){
        //限定56张牌
        cards=new Card[54];
        //先初始化（1-K）*4
        for(int i=0;i<13;i++){
            for(int j=0;j<4;j++){
                cards[i*4+j]=new Card(j+1,i+1);
            }
        }
        //然后是大王和小王
        cards[52]=new Card(Card.DAWANG,Card.DAWANG_NUM);
        cards[53]=new Card(Card.XIAOWANG, Card.XIAOWANG_NUM);
        return cards;
    }
    public void XiCards(){
        if(cards==null){
            return;
        }
        Random random=new Random();
        for(int i=0;i<cards.length;i++){
            int j=random.nextInt(cards.length);
            Card temp=cards[i];
            cards[i]=cards[j];
            cards[j]=temp;
        }
    }
    public void FaCards(Person[] persons){
        if(cards==null){
            return;
        }
        for (int j=0;j<9;j++){
            for(int i=0;i<persons.length;i++){
                //每个人发九张牌,并且第一张第一个人的
                //第二张第二个人的，依次类推
                persons[i].addCard(cards[j*persons.length+i]);
            }
        }
    }
    public ArrayList<OneCards[]> ListAllCount(Person person){
        return ZuHe.myzuhe(person.getCards());
    }
}
