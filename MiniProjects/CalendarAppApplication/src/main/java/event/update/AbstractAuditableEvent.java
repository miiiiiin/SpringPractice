package event.update;

import java.time.ZonedDateTime;

public abstract class AbstractAuditableEvent {  // abstract 클래로 만듬.
    private final String title;
    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;

    // 생성자 (추상 클래스 만듬)
    protected AbstractAuditableEvent(String title, ZonedDateTime startAt, ZonedDateTime endAt) {
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public String getTitle() {
        return  title;
    }

    public ZonedDateTime getStartAt() {
        return startAt;
    }

    public ZonedDateTime getEndAt() {
        return endAt;
    }

}
