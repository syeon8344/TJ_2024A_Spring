package example.day04;

import java.util.ArrayList;

public class Step3 {
    public static void main(String[] args) {

        String money = "123123123";
        // 문제 : money 변수에 존재하는 문자열 금액에 천단위 쉼표를 넣기
        ArrayList<String> moneyArr = new ArrayList<>();
        for (int i = 0; i < money.length(); i++){
            if (i%3 == 0) {
                moneyArr.add(money.substring(i, i + 3));
            }
        }
        String commaMoney = moneyArr.get(0) + "," + moneyArr.get(1) + "," + moneyArr.get(2);
        System.out.println(commaMoney);
    }
}
