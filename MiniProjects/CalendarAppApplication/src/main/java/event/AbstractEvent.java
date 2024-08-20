package event;

import exception.InvalidEventException;

import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class AbstractEvent implements Event {
    // 공통 메서드, 변수 정의
    private final int id;
    private String title;

    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Duration duration; // 시간 차이

    private final ZonedDateTime createdAt; // 생성된 이후로 바뀔 일이 없기 때문에 final
    private ZonedDateTime modifiedAt;

    private boolean deletedYn; // 이벤트가 삭제되어서 조회가 불가능한지 여부 판단

    // 생성자
    protected AbstractEvent(int id, String title, ZonedDateTime startAt,
                            ZonedDateTime endAt) {

        // 예외처리 (start와 end 시간이 뒤바뀌면 객체 만들 수 없게끔)

        if (startAt.isAfter(endAt)) {
            throw new InvalidEventException(String.format("시작일은 종료일보다 이전이어야 합니다. 시작일=%s, 종료일=%s", startAt, endAt));
        }

        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = Duration.between(startAt, endAt);

        ZonedDateTime now = ZonedDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
        this.deletedYn = false; // 삭제가 안된 상태가 기본값 (유효한 상태)
    }

    public String getTitle() {
        return this.title;
    }

    public ZonedDateTime getStartAt() {
        return startAt;
    }

    public ZonedDateTime getEndAt() {
        return endAt;
    }
}
