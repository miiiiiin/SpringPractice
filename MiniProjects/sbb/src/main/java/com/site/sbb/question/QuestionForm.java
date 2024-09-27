package com.site.sbb.question;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max=200) // 최대 길이가 200 바이트 (byte) 를 넘으면 안 된다
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
