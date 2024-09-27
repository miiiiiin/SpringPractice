package com.site.sbb.answer;

import com.site.sbb.question.Question;
import com.site.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    @PostMapping("/create/{id}")
//    public String createAnswer(Model model, @PathVariable("id") Integer id,
//                               @RequestParam(value = "content") String content) {
//        Question question = this.questionService.getQuestion(id);
//        // 답변 저장
//        this.answerService.create(question, content);
//        return String.format("redirect:/question/detail/%s", id);
//    }
    /**
     * @Valid와 BindingResult를 사용하여 검증을 진행
     * 검증 실패하면 다시 답변 등록할수 있게끔 question_detail 템플릿 출력
     */
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,
                               BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            //  템플릿은 Question 객체가 필요하므로 model 객체에 Question 객체를 저장한 후에
            //  question_detail 템플릿 출력해야 함.
            model.addAttribute("question", question);
            return "question_detail";
        }
        // 답변 저장
        this.answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}
