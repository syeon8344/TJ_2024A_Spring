package example.day08.todo;

public class TodoDto {
    //매개변수
    private int tno;
    private String tcontent;
    private int tstate;

    //생성자
    public TodoDto(){}

    //toString()
    @Override
    public String toString() {
        return "TodoDto{" +
                "tno=" + tno +
                ", tcontent='" + tcontent + '\'' +
                ", tstate=" + tstate +
                '}';
    }

    //getter/setter

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public int getTstate() {
        return tstate;
    }

    public void setTstate(int tstate) {
        this.tstate = tstate;
    }
}
