package com.site.sbb.question;

import com.site.sbb.answer.AnswerForm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RequiredArgsConstructor 애너테이션의 생성자 방식으로 questionRepository 객체를 주입
 * 롬복이 제공하는 애너테이션으로, final 이 붙은 속성을 포함하는 생성자를 자동으로 만들어 주는 역할
 * 스프링부트가 내부적으로 QuestionController를 생성할 때
 * 롬복으로 만들어진 생성자에 의해 questionRepository 객체가 자동으로 주입됨.
 */
@RequiredArgsConstructor
@RequestMapping("/question")
@Controller
public class QuestionController {

//    private final QuestionRepository questionRepository;

    //  questionService 객체도 @RequiredArgsConstructor에 의해 생성자 방식으로 주입됨.
    private final QuestionService questionService;

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        // 매개변수로 Model을 지정하면 객체가 자동으로 생성됨
        // Model 객체는 자바 클래스 (Java class) 와 템플릿 (template) 간의 연결 고리 역할
        // Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있음.

        Page<Question> paging = this.questionService.getList(page);
//        List<Question> questionList = this.questionService.getList();
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    // 변하는 id 값 을 얻을 때에는 @PathVariable 애너테이션을 사용
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    /**
     * subject, content 항목을 지닌 폼이 전송되면 QuestionForm의 subject, content 속성이 자동으로 바인딩
     * @Valid 애너테이션을 적용 하면 QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작
     * BindingResult 매개변수는 @Valid 애너테이션으로 검증이 수행된 결과를 의미하는 객체
     * BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 함.
     * 만약 두 매개변 수의 위치가 정확하지 않다면 @Valid만 적용되어 입력값 검증 실패 시 400 오류가 발생
     * @param questionForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        // 서비스에 질문 데이터 저장
//        this.questionService.create(subject, content);
//        return "redirect:/question/list";
        // 질문 저장 후 질문 목록으로 이동

        if (bindingResult.hasErrors()) {
            // 오류가 있는 경우에 는 다시 제목과 내용을 작성하는 화면으로 리다이렉팅
            return "question_form";
        }
        // 오류 없을 경우 질문 등록
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }


}
