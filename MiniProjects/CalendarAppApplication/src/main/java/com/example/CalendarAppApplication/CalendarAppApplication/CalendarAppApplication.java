package com.example.CalendarAppApplication.CalendarAppApplication;

import event.*;
import event.update.UpdateMeeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reader.EventCsvReader;
import reader.RawCsvReader;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class CalendarAppApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(CalendarAppApplication.class, args);
//	}

	public static void main(String[] args) throws IOException {
//		List<AbstractEvent> list = new ArrayList<>();
		Schedule schedule = new Schedule();
		HashSet<String> participants = new HashSet<String>();
		participants.add("sk.min");

//		Meeting meeting1 = new Meeting(1, "meeting1", ZonedDateTime.now(), ZonedDateTime.now().plusHours(1), participants, "meetingRoomA", "study");
//		schedule.add(meeting1);
//		Todo todo1 = new Todo(2, "todo2", ZonedDateTime.now().plusHours(3), ZonedDateTime.now().plusHours(4), "할 일 적기2");
//		schedule.add(todo1);

		// 예외 발생 사례
//		Todo todo2 = new Todo(3, "todo3", ZonedDateTime.now().plusHours(5), ZonedDateTime.now().plusHours(4), "할 일 적기3");
//		schedule.add(todo2);
//		schedule.forEach(Event::print);
//		schedule.printAll();

		// Meeting 클래스인 것만 가져와서 필터링
//		list.stream().filter(each -> each instanceof Meeting)
//				.collect(Collectors.toList());

//		list.stream()
//				.filter(each -> each.support(EventType.MEETING)) // Meeting 타입 필터한 결과
//				.forEach(Event::print); // MEETING에 대한 결과만 print
//		schedule.printBy(EventType.TO_DO);


		// 데이터 csv 파일로 만들어 대량으로 등록하기
		EventCsvReader csvReader = new EventCsvReader(new RawCsvReader());
		String meetingCsvPath = "/data/meeting.csv";
		String noDisturbanceCsvPath = "/data/no_disturbance.csv";
		String outofOfficeCsvPath = "/data/out_of_office.csv";
		String toDoCsvPath = "/data/todo.csv";

		List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
		meetings.forEach(schedule::add);

		List<NoDisturbance> noDisturbances = csvReader.readNoDisturbance(noDisturbanceCsvPath);
		noDisturbances.forEach(schedule::add);

		List<OutOfOffice> outOfOffices = csvReader.readOutOfOffice(outofOfficeCsvPath);
		outOfOffices.forEach(schedule::add);

		List<Todo> todos = csvReader.readTodo(toDoCsvPath);
		todos.forEach(schedule::add);


		// Meeting 첫번째 데이터 가져옴
		Meeting meeting = meetings.get(0);
		meeting.print();

		System.out.println("수정 후...");

		meetings.get(0).validateAndUpdate(
				new UpdateMeeting(
						"new title",
						ZonedDateTime.  now(),
						ZonedDateTime.now().plusHours(1),
						null,
						"A",
						"new agenda"
				)
		); // 수정 (Meeting의 첫 번째 데이터에 대해서 이 값들로 업데이트를 하겠다는 의미)

		schedule.printAll();

		meeting.delete(true);

//		System.out.println("삭제 후 수정 시도...");
//
//		meetings.get(0).validateAndUpdate(
//				new UpdateMeeting(
//						"new title",
//						ZonedDateTime.now(),
//						ZonedDateTime.now().plusHours(1),
//						null,
//						"B",
//						"Deleted agenda"
//				)
//		); // 수정 (Meeting의 첫 번째 데이터에 대해서 이 값들로 업데이트를 하겠다는 의미)


		meeting.print();
	}
}
