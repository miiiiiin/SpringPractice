package event;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<AbstractEvent> events = new ArrayList<>();
    public void add(AbstractEvent event) {
        if (hasScheduleConflictWith(event)) {
            // conflict이 발생하면 추가하지 않고 종료
            return;
        }
        this.events.add(event);
    }

    public void printAll() {
        events.forEach(Event::print);
    }
    public void printBy(EventType type) {
        events.stream()
                .filter(event -> event.support(type)) // Meeting 타입 필터한 결과
                .forEach(Event::print); // MEETING에 대한 결과만 print
    }

    // 시간 겹치는지 확인
    public boolean hasScheduleConflictWith(AbstractEvent event) {
        // 이벤트의 시작 시간과 종료 시간을 비교 (이벤트 중 시작/종료 시간이 겹치는 것이 하나라도 있으면 Conflict 발생)
        return events.stream()
                .anyMatch(each ->
                        event.getStartAt().isBefore(each.getEndAt()) && event.getStartAt().isAfter(each
                                .getStartAt()) // 시작 시간이 각각의 endAt보다 전이거나 각각의 startAt이 해당 이벤트의 startAt 뒤에 있거나 (시간이 겹침)
                            || (event.getEndAt().isAfter(each.getStartAt())) && event.getEndAt().isBefore(each.getEndAt()));
    }
}
