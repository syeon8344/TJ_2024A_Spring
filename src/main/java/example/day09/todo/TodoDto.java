package example.day09.todo;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TodoDto {
    //매개변수
    private int tno;
    private String tcontent;
    private int tstate;

}
