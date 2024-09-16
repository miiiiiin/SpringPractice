package com.site.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// RuntimeException 커스텀 클래스 (데이터베이스에서 특정 엔티티 또는 데이터를 찾을 수 없을 때 발생시 키는 예외 클래스)
// 404 에러 반환
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }
}
