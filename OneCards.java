package com.xi;

/**一副牌的简单类，包括三张牌，类型（kind）,积分
 * Created by xijiaxiang on 2016/2/22.
 */
public class OneCards {
    private Card[] Onecards;
    private int kind;
    private int jifen;

    public OneCards(Card[] onecards) {
        Onecards = onecards;
    }

    public Card[] getOnecards() {
        return Onecards;
    }

    public void setOnecards(Card[] onecards) {
        Onecards = onecards;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getJifen() {
        return jifen;
    }

    public void setJifen(int jifen) {
        this.jifen = jifen;
    }
}
