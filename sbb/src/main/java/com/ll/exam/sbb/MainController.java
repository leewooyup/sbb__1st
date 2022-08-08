package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    int i = 0;
    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {
        // 서버에서 출력
        System.out.println("Hello");
        // 먼 미래에 브라우저에서 보여짐
        return "zzzz안녕하세요.";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @GetMapping("/form")
    @ResponseBody
    public String showForm() {
        return """
                <form method="POST" action="/plus">
                    <input type="number" name="a" placeholder="숫자1" />
                    <input type="number" name="b" placeholder="숫자2" />
                    <input type="submit" value="plus로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public String Plus(@RequestParam(defaultValue = "0") int a, int b) {
        return """
                <h1>%d</h1>
                <h1> +연산 결과</h1>
                
                """.formatted(a+b);

    }

    @GetMapping("/minus")
    @ResponseBody
    public String Minus(@RequestParam(defaultValue = "0") int a, int b) {
        return """
                <h1>%d</h1>
                <h1> -연산 결과 </h1>
                
                """.formatted(a-b);

    }

    @GetMapping("/increase")
    @ResponseBody
    public String Increase() {

        return """
                <h1>%d</h1>
                <h1> incease</h1>
                
                """.formatted(i++);

    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit) {
        if(dan == null) {
            dan = 9;
        }

        if(limit == null) {
            limit = 9;
        }

        final Integer finalDan = dan;
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan*i))
                .collect(Collectors.joining("<br>"));

    }
}
