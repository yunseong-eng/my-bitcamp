package guestbook.bean; 

import java.util.Date; // 날짜를 다루기 위해 필요한 코드에요.

import lombok.Getter; //Lombok
import lombok.Setter; //Lombok

@Getter 
@Setter 
public class GuestbookDTO { // 방명록의 데이터를 담는 그릇
    private int seq; // 글 번호
    private String name; // 이름
    private String email; // 이메일
    private String homepage; // 홈페이지
    private String subject; // 글 제목
    private String content; // 글 내용
    private Date logtime; // 글을 작성한 시간
    
    @Override // 이 클래스가 기본적으로 가지고 있는 동작을 수정할 때 사용
    public String toString() { // 이 객체를 텍스트로 보여줄 때 어떻게 보일지 정해줌
        return "GuestbookDTO [seq=" + seq + ", name=" + name + ", email=" + email + ", homepage=" + homepage
                + ", subject=" + subject + ", content=" + content + ", logtime=" + logtime + "]";
        // 저장된 정보들을 한 줄로 만들어서 보여줌
    }
}
