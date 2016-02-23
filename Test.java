package com.xi;

import java.util.ArrayList;

/**
 * test my software
 * Created by xijiaxiang on 2016/2/16.
 */
public class Test {
    public static void main(String[] args) {
        /// Card card=new Card(Card.MEIHUA,1);
        //System.out.println(card.printCard());
        CardHandler cardHandler = new CardHandler();
        Card[] cards = cardHandler.InitCards();
        ///////////////////////////
        Person[] p = new Person[6];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Person("李" + (i + 1) + "狗", 100);
        }
        ///////////////////////////
        cardHandler.XiCards();
        cardHandler.FaCards(p);
        for (Person m : p) {
            m.printCard();
        }

        ///列出第一个人的牌的所有可能的组合
        ArrayList<OneCards[]> getAllCount = cardHandler.ListAllCount(p[0]);
        //下面是根据不同的规则筛选出来的比较可能赢得牌
        int maxFirstOneCards = 0;
        int indexOfmaxFirst = 0;
        for (int i = 0; i < getAllCount.size(); i++) {
            if (getAllCount.get(i)[0].getJifen() > maxFirstOneCards) {
                maxFirstOneCards = getAllCount.get(i)[0].getJifen();
                indexOfmaxFirst = i;
            }
        }
        OneCards[] firstcards = getAllCount.get(indexOfmaxFirst);
        System.out.println("第一组最大积分为" + maxFirstOneCards + "," + firstcards[0].toString()+firstcards[1].toString()+firstcards[2].toString());

        int maxSecondOneCards = 0;
        int indexOfmaxSecond = 0;
        for (int i = 0; i < getAllCount.size(); i++) {
            if (getAllCount.get(i)[1].getJifen() > maxSecondOneCards) {
                maxSecondOneCards = getAllCount.get(i)[1].getJifen();
                indexOfmaxSecond = i;
            }
        }
        OneCards[] secondcards = getAllCount.get(indexOfmaxSecond);
        System.out.println("第二组最大积分为" + maxSecondOneCards + "," + secondcards[0].toString()+secondcards[1].toString()+secondcards[2].toString());

        int maxThirdOneCards = 0;
        int indexOfmaxThird = 0;
        for (int i = 0; i < getAllCount.size(); i++) {
            if (getAllCount.get(i)[2].getJifen() > maxThirdOneCards) {
                maxThirdOneCards = getAllCount.get(i)[2].getJifen();
                indexOfmaxThird = i;
            }
        }
        OneCards[] thirdcards = getAllCount.get(indexOfmaxThird);
        System.out.println("第三组最大积分为" + maxThirdOneCards + "," + thirdcards[0].toString()+thirdcards[1].toString()+thirdcards[2].toString());

        //下面根据总分进行从大到小的排名
        //利用冒泡排序
        int maxJiFen1 = 0;
        int maxJiFen2 = 0;
        int i, j,  len = getAllCount.size();
        OneCards[] temp;
        for (i = 0; i < len - 1; i++) {
            for (j = 0; j < len - 1 - i; j++) {
                maxJiFen1=getAllCount.get(j)[0].getJifen()+getAllCount.get(j)[1].getJifen()+getAllCount.get(j)[2].getJifen();
                maxJiFen2=getAllCount.get(j+1)[0].getJifen()+getAllCount.get(j+1)[1].getJifen()+getAllCount.get(j+1)[2].getJifen();
                if (maxJiFen1 > maxJiFen2) {
                    temp = getAllCount.get(j);
                    getAllCount.set(j,getAllCount.get(j+1));
                    //arr[j] = arr[j + 1];
                    getAllCount.set(j+1,temp);
                    //arr[j + 1] = temp;
                }
            }
        }
        int maxindex=getAllCount.size()-1;
        OneCards[] oneCardses=getAllCount.get(maxindex);
        System.out.println(oneCardses[0].getJifen()+","+oneCardses[1].getJifen()+","+oneCardses[2].getJifen());
        System.out.println(oneCardses[0].toString()+oneCardses[1].toString()+oneCardses[2].toString());
    }
}
