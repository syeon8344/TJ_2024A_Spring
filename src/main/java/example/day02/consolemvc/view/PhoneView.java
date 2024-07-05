package example.day02.consolemvc.view;

import example.day02.consolemvc.controller.PhoneController;
import example.day02.consolemvc.model.dto.PhoneDto;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneView {
    // [1] 싱글톤 패턴
    private static PhoneView phoneView = new PhoneView();
    private PhoneView(){};

    public static PhoneView getInstance(){
        return phoneView;
    }

    Scanner scan = new Scanner(System.in);

    // 0. run()
    public void run() {
        while(true){
            System.out.println(">>1.[POST/CREATE/C] 2.[GET/SELECT/R] : ");
            int ch = scan.nextInt();
            switch (ch){
                case 1:
                    postPhone();
                    break;
                case 2:
                    getPhone();
                    break;
                default:
                    break;
            }
        }
    }

    // 1. 매개변수 : PhoneDTO (이름,전화번호) 객체, boolean값을 받는다
    public void postPhone(){
        System.out.println(">>name : ");
        String name = scan.next();
        System.out.println(">>phone :");
        String phone = scan.next();
        PhoneDto phoneDto = new PhoneDto(name, phone);
        boolean result = PhoneController.getInstance().postPhone(phoneDto);
        if (result){
            System.out.println(">>saved");
        } else {
            System.out.println(">>not saved");
        }
    }

    // 2. 매개변수 - X, PhoneDTO ArrayList 받아 출력
    public void getPhone(){
        ArrayList<PhoneDto> result = PhoneController.getInstance().getPhone();
        for (PhoneDto dto : result){
            System.out.printf(" %5d %20s %20s \n", dto.getId(), dto.getName(), dto.getPhone());
        };
    }
}
