package web.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberDto {
    private int no;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
}
