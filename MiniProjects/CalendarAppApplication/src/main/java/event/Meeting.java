package event;

import java.time.ZonedDateTime;
import java.util.Set;
public class Meeting extends AbstractEvent {

    // 참가자 : 중복되지 않기 때문에 Set으로 설정
    private Set<String> participants;
    private String meetingRoom;
    private String agenda;

    public Meeting(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt,
                   Set<String> participants, String meetingRoom, String agenda) {
        // AbstractEvent에 전달할 변수들 받아줌
        super(id, title, startAt, endAt);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    @Override
    public void print() {
        System.out.printf("[회의] %s : %s%n", getTitle(), agenda);
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.MEETING;
    }

}
