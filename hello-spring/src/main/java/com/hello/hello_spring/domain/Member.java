package com.hello.hello_spring.domain;

import jakarta.persistence.*;

// jpa가 관리하는 entity가 됨
@Entity
public class Member {

    // pk는 db에서 값을 생성해주고 있음.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
