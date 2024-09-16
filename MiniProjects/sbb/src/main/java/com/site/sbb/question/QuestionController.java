package com.site.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String list(Model model) {
        // 매개변수로 Model을 지정하면 객체가 자동으로 생성됨
        // Model 객체는 자바 클래스 (Java class) 와 템플릿 (template) 간의 연결 고리 역할
        // Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있음.

        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    // 변하는 id 값 을 얻을 때에는 @PathVariable 애너테이션을 사용
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }



}
