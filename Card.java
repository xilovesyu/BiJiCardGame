package com.xi;

/**simple class;card class
 * Created by xijiaxiang on 2016/2/16.
 */
public class Card {
    private int Kind;
    private int Num;
    //黑4，梅3，红2，方1，小大王0
    public static final int FANGKUAI=1;
    public static final int HONGXIN=2;
    public static final int MEIHUA=3;
    public static final int HEITAO=4;
    public static final int XIAOWANG=-1;
    public static final int DAWANG=0;
    //小王点数-1，大王点数0
    public  static  final int DAWANG_NUM=0;
    public  static  final int XIAOWANG_NUM=-1;
    //如果1变成A
    public static  final int A=14;
    public Card(int kind, int num) {
        Kind = kind;
        Num = num;
    }
    public Card(Card card){
        this.Kind=card.getKind();
        this.Num=card.getNum();
    }
    public int getKind() {
        return Kind;
    }
    public void setKind(int kind) {
        Kind = kind;
    }
    public int getNum() {
        return Num;
    }
    public void setNum(int num) {
        Num = num;
    }
    public void set(int kind,int num){
        this.Kind=kind;this.Num=num;
    }
    public String printCard(){
        return KindtoString()+NumtoString();
    }
    private String KindtoString(){
        String temp="error";
        int kind=Kind;
        if(kind==Card.HEITAO)temp="黑桃";
        if(kind==Card.HONGXIN) temp= "红心";
        if(kind==Card.FANGKUAI) temp= "方块";
        if(kind==Card.MEIHUA) temp= "梅花";
        if(kind==Card.DAWANG) temp="大王";
        if(kind==Card.XIAOWANG)temp="小王";
        return temp;
    }
    private String NumtoString(){
        String temp;
        int num=Num;
        switch (num){
            case 1:case 2:case 3:case 4:case 5:case 6:case 7:
            case 8:case 9:case 10:
                temp=num+"";break;
            case -1:case 0:temp="*";break;
            case 11:temp="J";break;
            case 12:temp="Q";break;
            case 13:temp="K";break;
            case 14:temp="A";break;
                default:temp="error";break;
        }
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        if (Kind != card.Kind) return false;
        return Num == card.Num;
    }
    @Override
    public int hashCode() {
        int result = Kind;
        result = 31 * result + Num;
        return result;
    }
}
