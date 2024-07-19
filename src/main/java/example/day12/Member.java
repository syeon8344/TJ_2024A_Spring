package example.day12;

import java.util.Objects;

public class Member {
    int memberId;
    String memberName;

    public Member(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member){
            Member member = (Member) obj;
            if(this.memberId == member.memberId){
                return true;
            } else {return false;}
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(memberId);
    }
}
