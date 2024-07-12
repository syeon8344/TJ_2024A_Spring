package example.day07.todo;

public class TodoDto {
    //매개변수
    private int listCode;
    private String listName;
    private String listDate;
    private boolean isDone;

    //생성자
    public TodoDto(){}

    public TodoDto(String listName) {
        this.listName = listName;
    }
    //getter/setter
    public int getListCode() {
        return listCode;
    }

    public void setListCode(int listCode) {
        this.listCode = listCode;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
