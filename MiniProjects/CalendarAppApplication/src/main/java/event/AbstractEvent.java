package event;

import event.update.AbstractAuditableEvent;
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

    // AbstractAuditableEvent 전달
    public void validateAndUpdate(AbstractAuditableEvent update) {
        if (deletedYn == true) throw new RuntimeException("이미 삭제된 이벤트는 수정할 수 없음.");

        defaultUpdate(update);
        update(update);
    }

     // 공통 업데이트
    public void defaultUpdate(AbstractAuditableEvent update) {
        this.title = update.getTitle();
        this.startAt = update.getStartAt();
        this.endAt = update.getEndAt();
        this.duration = Duration.between(this.startAt, this.endAt);
        this.modifiedAt = ZonedDateTime.now();
    }

    // 개별 업데이트 (각각의 이벤트 타입에 맞게 구현해줘야 함. AbstractEvent 를 상속받은 각각의 구현체에서 구현 해줘야 함.)
    // protected abstract 메서드로 만들어야 함.
    protected abstract void update(AbstractAuditableEvent update);

    public void delete(boolean deletedYn) {
        this.deletedYn = deletedYn;
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
