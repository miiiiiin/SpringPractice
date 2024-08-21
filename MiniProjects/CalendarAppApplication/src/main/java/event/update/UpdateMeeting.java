package event.update;

import java.time.ZonedDateTime;
import java.util.Set;

public class UpdateMeeting extends AbstractAuditableEvent {

    // 참가자 : 중복되지 않기 때문에 Set으로 설정
    private Set<String> participants;
    private String meetingRoom;
    private String agenda;

    // 생성자
    public UpdateMeeting(String title, ZonedDateTime startAt, ZonedDateTime endAt,
                            Set<String> participants, String meetingRoom, String agenda) {
        super(title, startAt, endAt);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public String getAgenda() {
        return agenda;
    }
}
