package com.site.sbb;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
//@Setter
@RequiredArgsConstructor
public class HelloLombok {
    private final String hello;
    private final int lombok;

    // @RequiredArgsConstructor 애너테이션을 적용하면 해당 속성 (hello와 lombok) 을
    // 필요로 하는 생성자가 롬복에 의해 자동으로 생성
//    public HelloLombok(String hello, int lombok) {
//        this.hello = hello;
//        this.lombok = lombok;
//    }
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("hello", 5);
//        helloLombok.setHello("df");

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());

    }
}
