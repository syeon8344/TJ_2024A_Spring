package example.day02.consolemvc;

import example.day02.consolemvc.view.PhoneView;

public class AppStart {
    public static void main(String[] args) {
        PhoneView.getInstance().run();
    }
}
