package board.bean;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class BoardDTO {
    private int seq;         // 게시글 시퀀스
    private String id;       // 작성자 ID
    private String name;     // 작성자 이름
    private String email;    // 작성자 이메일
    private String subject;  // 게시글 제목
    private String content;  // 게시글 내용
    private int ref;    
    private int lev;
    private int step;
    private int pseq;
    private int reply;
    private int hit;
    private Date logtime;    // 작성 시간
}
