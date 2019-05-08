package com.bytedance.questions;

/**
 * 1 找零
 * Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。
 * 现在小Y使用1024元的纸币购买了一件价值为N(0<N<=1024) 的商品，请问最少他会收到多少硬币？
 *
 * 输入描述:
 *
 * 一行，包含一个数N。
 *
 * 输出描述:
 *
 * 一行，包含一个数，表示最少收到的硬币数。
 *
 * 输入：
 *
 * 200
 *
 * 输出：
 *
 * 17
 *
 * 说明：
 *
 * 花200，需要找零824块，找12个64元硬币，3个16元硬币，2个4元硬币即可。
 *
 * 备注：对于100%的数据，N(0<N<=1024)
 */
public class Change01 {

    public static final int COIN_1 = 1;
    public static final int COIN_4 = 4;
    public static final int COIN_16 = 16;
    public static final int COIN_64 = 64;


    private int amountCoin1 = 0;
    private int amountCoin4 = 0;
    private int amountCoin16 = 0;
    private int amountCoin64 = 0;

    private int amountCoin;

    public int getAmountCoin1() {
        return amountCoin1;
    }

    public void setAmountCoin1(int amountCoin1) {
        this.amountCoin1 = amountCoin1;
    }

    public int getAmountCoin4() {
        return amountCoin4;
    }

    public void setAmountCoin4(int amountCoin4) {
        this.amountCoin4 = amountCoin4;
    }

    public int getAmountCoin16() {
        return amountCoin16;
    }

    public void setAmountCoin16(int amountCoin16) {
        this.amountCoin16 = amountCoin16;
    }

    public int getAmountCoin64() {
        return amountCoin64;
    }

    public void setAmountCoin64(int amountCoin64) {
        this.amountCoin64 = amountCoin64;
    }

    public int getAmountCoin() {
        return amountCoin1+amountCoin4+amountCoin16+amountCoin64;
    }


    public int  countCoinAmount(int money){
        if (0 > money || money > 1024) {
            throw new RuntimeException("商品价值不能大于1024或者小于0");
        }
        money = 1024-money;

        setAmountCoin64(money/Change01.COIN_64);
        System.out.println("64的硬币数量:"+getAmountCoin64());
        int remainder64 = money % Change01.COIN_64;
        setAmountCoin16((remainder64 / Change01.COIN_16));
        System.out.println("16的硬币数量:"+getAmountCoin16());
        int remainder16 = remainder64 % Change01.COIN_16;
        setAmountCoin4(remainder16 / Change01.COIN_4);
        System.out.println("4的硬币数量:"+getAmountCoin4());
        int remainder4 = remainder16 % Change01.COIN_4;
        setAmountCoin1(remainder4);
        System.out.println("1的硬币数量:"+getAmountCoin1());

        return getAmountCoin();
    }

    public static void main(String[] args) {

        Change01 change = new Change01();
        int num = change.countCoinAmount(200);
        System.out.println(num);
    }

}
