package com.xi;

import java.util.ArrayList;

public class ZuHe {
    public static int M = 3;

    public static void main(String[] args) {
        //test 方块2:	梅花10:	小王:	方块8:	梅花K:	黑桃6:	方块5:	红心A:	红心Q:
        //梅花10,小王*,红心9
        Card[] cardstemp = new Card[3];
        cardstemp[0] = new Card(Card.MEIHUA, 10);
        cardstemp[1] = new Card(Card.XIAOWANG, Card.XIAOWANG_NUM);
        cardstemp[2] = new Card(Card.HONGXIN, 9);
        //PaiXuCards(cardstemp);
        //System.out.println(CardsKind(cardstemp));
        System.out.println(cardstemp[0].printCard());
        System.out.println(cardstemp[1].printCard());
        System.out.println(cardstemp[2].printCard());
        System.out.println(getKindsAndTrueCards(cardstemp));
    }
    /**
     * 返回的数据是所有的排列情况以及每一种排列的积分情况
     * */
    public static ArrayList<OneCards[]> myzuhe(Card[] cards) {
        int count = 0;
        ArrayList<OneCards[]> cardsArrayList=new ArrayList<>();
        for (int i = 1; i <= cards.length - M + 1; i++) {
            for (int j = i + 1; j <= cards.length - M + 2; j++) {
                for (int k = j + 1; k <= cards.length - M + 3; k++) {
                    Card sub[] = {cards[i - 1], cards[j - 1], cards[k - 1]};
                    Card left[] = SubCard(cards, sub);
                    for (int a = 1; a <= left.length - M + 1; a++) {
                        for (int b = a + 1; b <= left.length - M + 2; b++) {
                            for (int c = b + 1; c <= left.length - M + 3; c++) {
                                Card[] sub1 = {left[a - 1], left[b - 1], left[c - 1]};
                                Card left1[] = SubCard(left, sub1);
                                //判断是否是个合法的排列
                                //判断的时候只发送对象数组的副本，保留原来的对象数组
                                //可以利用card类的clone方法
                                Card[] jundge = {new Card(cards[i - 1]), new Card(cards[j - 1]), new Card(cards[k - 1]),
                                        new Card(left[a - 1]), new Card(left[b - 1]), new Card(left[c - 1]),
                                        new Card(left1[0]), new Card(left1[1]), new Card(left1[2])};

                                int atemp[]=Jundge(jundge);
                                //符合大小关系并且不是三个都是普通牌
                                if(atemp[0]==1&&(atemp[1]!=0&&atemp[1]!=2)) {
                                    System.out.println("排列" + (count + 1) + ":" +
                                            cards[i - 1].printCard() + "," + cards[j - 1].printCard() + "," +
                                            cards[k - 1].printCard() + "," + left[a - 1].printCard() + "," +
                                            left[b - 1].printCard() + "," + left[c - 1].printCard() + "," +
                                            left1[0].printCard() + "," + left1[1].printCard() + "," +
                                            left1[2].printCard()+"->score:"+atemp[1]);
                                    System.out.println("积分：\t\t\t"+atemp[2]+",\t\t\t"+atemp[3]+",\t\t\t"+atemp[4]);
                                    OneCards[] oneCardses=new OneCards[3];
                                    oneCardses[0]=new OneCards(new Card[]{jundge[0],jundge[1],jundge[2]});
                                    oneCardses[0].setJifen(atemp[2]);
                                    oneCardses[1]=new OneCards(new Card[]{jundge[3],jundge[4],jundge[5]});
                                    oneCardses[1].setJifen(atemp[3]);
                                    oneCardses[2]=new OneCards(new Card[]{jundge[6],jundge[7],jundge[8]});
                                    oneCardses[2].setJifen(atemp[4]);
                                    cardsArrayList.add(oneCardses);
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return cardsArrayList;
    }
    /**得到一副牌中没有包含在另外一副牌中的牌
     * */
    private static Card[] SubCard(Card[] num, Card[] num1) {
        Card[] temp = new Card[num.length - num1.length];
        boolean[] flag = new boolean[num.length];
        int k = 0;
        for (int i = 0; i < flag.length; i++) {
            flag[i] = false;
        }
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num1.length; j++) {
                if (num[i].equals(num1[j])) {
                    flag[i] = true;
                }
            }
        }
        for (int i = 0; i < num.length; i++) {
            if (!flag[i]) {
                temp[k] = num[i];
                k++;
            }
        }
        return temp;
    }

    public static int[] Jundge(Card[] temp) {
        boolean flag = false;
        int [] atemp=new int[5];
        if (temp.length != 9){atemp[0]=0; return atemp;}
        ///得到三副牌
        Card[] firstCards = {temp[0], temp[1], temp[2]};
        Card[] secondCards = {temp[3], temp[4], temp[5]};
        Card[] thirdCards = {temp[6], temp[7], temp[8]};
        //判断三副牌是什么类型,如果有大小王替换为真实的牌比如大王，小王，5-》5,6,7
        int KindFirst = getKindsAndTrueCards(firstCards);
        int KindSecond = getKindsAndTrueCards(secondCards);
        int KindThird = getKindsAndTrueCards(thirdCards);
       // System.out.println(KindFirst + "" + KindSecond + "" + KindThird);
        if (KindThird > KindSecond && KindSecond > KindFirst) {
            flag = true;
            atemp[0]=1;
        } else if (KindThird == KindSecond && KindSecond > KindFirst) {
            //1,2,2
            //在判断2,3点数
            flag=CompareTwoCards(thirdCards,secondCards,KindThird);
            if(flag)
                atemp[0]=1;
            else atemp[0]=0;
        } else if (KindThird > KindSecond && KindSecond == KindFirst) {
            //2,2,3
            //在判断1,2点数
            flag=CompareTwoCards(secondCards,firstCards,KindSecond);
            if(flag)
                atemp[0]=1;
            else atemp[0]=0;
        } else if (KindThird == KindSecond && KindSecond == KindFirst) {
            //1,1,1
            //先判断2,3点数，在判断1,2点数
            if(CompareTwoCards(thirdCards,secondCards,KindThird)){
                flag=CompareTwoCards(secondCards,firstCards,KindSecond);
                if(flag)
                    atemp[0]=1;
                else atemp[0]=0;
            }else{
                flag=false;
                atemp[0]=0;
            }
        } else {
            //不符合
            flag = false;
            atemp[0]=0;
        }
       // System.out.println(flag);
        atemp[1]=KindFirst*2+KindSecond*2+KindThird*2;
        if(flag){
            /*System.out.println("积分："+JiFen(firstCards,KindFirst)+","+
            JiFen(secondCards,KindSecond)+","+JiFen(thirdCards,KindThird));*/
            atemp[2]=JiFen(firstCards,KindFirst);
            atemp[3]=JiFen(secondCards,KindSecond);
            atemp[4]=JiFen(thirdCards,KindThird);
        }
        return atemp;
    }

    private static int getKindsAndTrueCards(Card[] temp) {
        if (temp.length != 3) return -1;
        PaiXuCards(temp);
        int numa = temp[0].getNum();
        int numb = temp[1].getNum();
        int numc = temp[2].getNum();
        int kinda = temp[0].getKind();
        int kindb = temp[1].getKind();
        int kindc = temp[2].getKind();
        //System.out.println(numa+""+kinda);
        if (kinda == Card.XIAOWANG || kinda == Card.DAWANG) {
            //至少有一个小王或者大王
            if (kinda == Card.XIAOWANG && kindb == Card.DAWANG) {
                //大小王 都有
                if (numc == 12)//小王，大王，Q,
                {
                    temp[0].set(Card.HEITAO, 13);
                    temp[1].set(Card.HONGXIN, Card.A);
                } else if (numc == 13) {//小王，大王，K
                    temp[0].set(Card.HEITAO, 12);
                    temp[1].set(Card.HONGXIN, Card.A);
                } else if (numc == 1) {//小王，大王，1
                    temp[0].set(Card.HEITAO, 12);
                    temp[1].set(Card.HONGXIN, 13);
                    temp[2].set(kindc,Card.A);
                } else {//小王，大王，非(Q||K||1)
                    temp[0].set(Card.HEITAO, numc + 1);
                    temp[1].set(Card.HONGXIN, numc + 2);
                }
                return 2;
            } else {
                //只有小王或者只有大王
                //判断是否为亲或者顺亲
                if ((kindb == kindc && (kinda == -1) && (kindb == Card.HEITAO || kindb == Card.MEIHUA)) ||
                        (kindb == kindc && (kinda == 0) && (kindb == Card.HONGXIN || kindb == Card.FANGKUAI))) {
                    //亲或者顺亲，，，小王，黑桃，黑桃 或者 小王，梅花，梅花
                    //               大王，红心，红心，或者大王，方块，方块
                    //判断是否顺亲，跟下面拖拉机代码一样
                    if (numb == 12 && numc == 13) {
                        //顺亲（小王||大王），Q，K
                        temp[0].set(kindb, Card.A);
                        return 4;
                    } else if (numb == 1 && numc == 12) {
                        //顺亲，这里（小王||大王），Q，A
                        temp[0].set(kindb, 13);
                        temp[1].set(kindb, Card.A);
                        return 4;
                    } else if (numb == 1 && numc == 13) {
                        //顺亲 这里（小王||大王），K，A
                        temp[0].set(kindb, 12);
                        temp[1].set(kindb, Card.A);
                        return 4;
                    } else if (numb + 1 == numc) {
                        //顺亲 （小王||大王），5,6,
                        temp[0].set(kindb, numc + 1);
                        return 4;
                    } else if (numb + 2 == numc) {
                        //顺亲  （小王||大王），5,7,
                        temp[0].set(kindb, numb + 1);
                        return 4;
                    } else {
                        //亲,如果有A，那么肯定是K，如果没有A，肯定是A
                        if (numb == 1) {//temp[1]=A,temp[0]=K,temp[2]
                            temp[0].set(kindb, 13);
                            temp[1].set(kindb, Card.A);
                            return 3;
                        } else {//temp[0]=A,temp[1]=numb,temp[2]=numc
                            temp[0].set(kindb, Card.A);
                            return 3;
                        }
                    }
                } else if (numb == 12 && numc == 13) {
                    //不是亲的，判断是否为拖拉机，这里判断是否为（小王||大王），Q，K
                    temp[0].set(kindc, Card.A);
                    return 2;
                } else if (numb == 1 && numc == 12) {
                    //拖拉机 这里（小王||大王），Q，A
                    temp[0].set(kindb, 13);
                    temp[1].set(kindb, Card.A);
                    return 2;
                } else if (numb == 1 && numc == 13) {
                    //拖拉机 这里（小王||大王），K，A
                    temp[0].set(kindb, 12);
                    temp[1].set(kindb, Card.A);
                    return 2;
                } else if (numb + 1 == numc) {
                    //拖拉机, （小王||大王），5,6,
                    temp[0].set(kindc, numc + 1);
                    return 2;
                } else if (numb + 2 == numc) {
                    //拖拉机  （小王||大王），5,7,
                    temp[0].set(kindc, numb + 1);
                    return 2;
                } else {
                    //一对的情况了，如果有A，则为一对A，否则是最大数的那对，比如，5,9-》一对9
                    //还有一种情况，本身后面两张牌就是一对怎么办
                    if (numb == numc) {
                        //本身是一对
                        if (numb == 1) {
                            temp[0].set(kindb, 13);
                            temp[1].set(kindb, Card.A);
                            temp[2].set(kindc, Card.A);
                            return 1;
                        } else {
                            temp[0].set(kindb, Card.A);
                            return 1;
                        }
                    } else if (numb == 1) {
                        //一对A，temp[0],1，numc
                        temp[0].set(kindc, Card.A);
                        temp[1].set(kindb, Card.A);
                        return 1;
                    } else {
                        //6,12,->一对Q
                        temp[0].set(kindb, numc);
                        return 1;
                    }
                }
            }
        } else {
            //没有大小王的情况下
            if (numa == numb && numb == numc) {
                //炸弹
                if(numa==1){
                    //A,A,A
                    temp[0].set(kinda,Card.A);
                    temp[1].set(kinda,Card.A);
                    temp[2].set(kinda,Card.A);
                }
                return 5;
            } else if (kinda == kindb && kindb == kindc) {
                //亲或者顺亲
                if (numa == 1 && numb == 12 && numc == 13) {
                    temp[0].set(kinda, Card.A);
                    return 4;
                } else if (numa + 1 == numb && numb + 1 == numc) {
                    return 4;
                } else {
                    if (numa == 1) {
                        //A亲
                        temp[0].set(kinda, Card.A);
                        return 3;
                    } else return 3;
                }
            } else if (numa == 1 && numb == 12 && numc == 13) {
                //Q,K,A拖拉机
                temp[0].set(kinda, Card.A);
                return 2;
            } else if (numa + 1 == numb && numb + 1 == numc) {
                //拖拉机，JQK
                return 2;
            } else if (numa == numb) {
                //一对，x,x,y
                if (numa == 1) {
                    //一对A
                    temp[0].set(kinda, Card.A);
                    temp[1].set(kindb, Card.A);
                    return 1;
                } else {
                    return 1;
                }
            } else if (numb == numc) {
                //一对，x,y,y
                if (numb == 1) {
                    //-1||0，A，A实际上，没有这种可能
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (numa == 1) {
                    //有A的一般牌
                    temp[0].set(kinda, Card.A);
                    return 0;
                } else {
                    //一般牌
                    return 0;
                }
            }
        }
    }

    public static void PaiXuCards(Card[] temp) {
        if (temp.length != 3) {
            return;
        }
        if (temp[0].getNum() > temp[1].getNum()) {
            Card linshi = temp[0];
            temp[0] = temp[1];
            temp[1] = linshi;
        }
        if (temp[0].getNum() > temp[2].getNum()) {
            Card linshi = temp[0];
            temp[0] = temp[2];
            temp[2] = linshi;
        }
        if (temp[1].getNum() > temp[2].getNum()) {
            Card linshi = temp[1];
            temp[1] = temp[2];
            temp[2] = linshi;
        }
    }
    /**
     * 比较两副牌的方法，这里比较的两副牌必须是相同类型
     * */
    private static boolean CompareTwoCards(Card[] laterCards, Card[] preCards, int kind) {
        boolean flag = false;
        PaiXuCards(laterCards);
        PaiXuCards(preCards);
        switch (kind) {
            case 5:
                if (laterCards[0].getNum() >= preCards[0].getNum()) {
                    flag = true;
                } else {
                    flag = false;
                }
                break;
            case 4:
                flag = laterCards[2].getNum() >= preCards[2].getNum();
                break;
            case 3:
            case 0: {
                if (laterCards[2].getNum() >= preCards[2].getNum()) {
                    if (laterCards[1].getNum() >= preCards[1].getNum()) {
                        flag = laterCards[0].getNum() >= preCards[0].getNum();
                    } else {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
            break;
            case 2:
                flag = laterCards[2].getNum() >= preCards[2].getNum();
                break;
            case 1: {//对
                int duizia = DuiZiAndKaoBei(laterCards)[0];
                int kaobeia =DuiZiAndKaoBei(laterCards)[1];
                int duizib = DuiZiAndKaoBei(preCards)[0];
                int kaobeib =DuiZiAndKaoBei(preCards)[1];
                if (duizia > duizib) {
                    flag = true;
                } else if (duizia == duizib) {
                    flag = kaobeia >= kaobeib;
                } else {
                    flag = false;
                }
            }
            break;
            default:
                flag = false;
                break;
        }
        return flag;
    }
    /**得到是对子牌的对子和靠背
     * */
    private static int[] DuiZiAndKaoBei(Card[] temp){
        if(temp.length!=3)return new int[]{-1,-1};
        PaiXuCards(temp);
        int duizi = -1;
        int kaobei = -1;
        if (temp[0].getNum() == temp[1].getNum()) {
            duizi = temp[0].getNum();
            kaobei = temp[2].getNum();
        } else if (temp[1].getNum() == temp[2].getNum()) {
            duizi = temp[1].getNum();
            kaobei = temp[0].getNum();
        }
        return new int[]{duizi,kaobei};
    }
        ///考虑积分系统
    //初步打算分类积分系统，三副牌各不相干，只计算每一副牌的得分
    //先按照种类计算，然后根据种类判断每一个种类里面的小分数
    //kind0(普通牌)第一张牌+第二张牌*14+第三张牌*14*14-》最大是J，K，A-》2937分
    //kind1(对子)2937分+对子牌*14+靠背牌-》最大的是A,A,K->2937+209=3146分
    //kind2（拖拉机）3146分+最大的牌-》最大的拖拉机Q，K，A-》3146+14=3160分
    //kind3（亲）3160分+第一张牌+第二张牌*14+第三张牌*14*14-》最大是J，K，A->3160+2937=6097分
    //kind4(顺亲)6097分+最大的牌-》最大顺亲Q，K，A->6097+14=6111分
    //kind5（炸弹）6111分+其中一张牌-》最大的炸弹A，A，A-》6111+14=6125分
    private static int JiFen(Card[] temp,int kind){
        if(temp.length!=3) return -1;
        PaiXuCards(temp);
        int numa=temp[0].getNum();
        int numb=temp[1].getNum();
        int numc=temp[2].getNum();
        int fenshu=0,Maxkind0=2937,Maxkind1=3146;
        int Maxkind2=3160,Maxkind3=6097,Maxkind4=6111;
        switch (kind){
            case 0:{
                fenshu=numa+numb*14+numc*14*14;
            }break;
            case 1:{
                fenshu=Maxkind0+DuiZiAndKaoBei(temp)[0]*14+DuiZiAndKaoBei(temp)[1];
            }break;
            case 2:{
                fenshu=Maxkind1+numc;
            }break;
            case 3:{
                fenshu=Maxkind2+numa+numb*14+numc*14*14;
            }break;
            case 4:{
                fenshu=Maxkind3+numc;
            }break;
            case 5:{
                fenshu=Maxkind4+numa;
            }break;
        }
        return fenshu;
    }
}