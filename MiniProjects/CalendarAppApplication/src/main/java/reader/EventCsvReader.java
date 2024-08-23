package reader;

import com.opencsv.CSVReader;
import event.Meeting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventCsvReader {

    // 하나의 mocking class로 만드는 것이 목적 (단위 Test 시)
    private final RawCsvReader rawCsvReader;

    // 생성자 호출될 때 주입시켜줌
    public EventCsvReader(RawCsvReader rawCsvReader) {
        this.rawCsvReader = rawCsvReader;
    }
    public List<Meeting> readMeetings(String path) throws IOException { // csv 경로
        List<Meeting> result = new ArrayList<>();

        // 데이터 읽는 부분
        // List는 String 배열을 가지고 있는 데이터
        List<String[]> read = rawCsvReader.readAll(path); // 테스트에서 mocking 처리 예정 (List<String[]>에 해당하는 부분)

        for (int i=0; i<read.size(); i++) {
            if (skipHeader(i)) continue; // header skip

            String[] each = read.get(i);

            // Meeting으로 변환 부분
            result.add(
                    new Meeting(Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(LocalDateTime.parse(each[6], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")),
                            ZonedDateTime.of(LocalDateTime.parse(each[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")),
                            new HashSet<>(Arrays.asList(each[3].split(","))),
                            each[4],
                            each[5]
                            )
            );
        }

        // Meeting으로 변환하는 부분

        return result;
    }

    private static boolean skipHeader(int i) {
        return i == 0;
        // method로 분리 (option + command + m)
    }

//    private List<String[]> readAll(String path) throws IOException {
//        InputStream in = getClass().getResourceAsStream(path);
//        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
//        CSVReader csvReader = new CSVReader(reader);
//        return csvReader.readAll(); // 이 경로로 입력된 데이터에서 csv파일로 변환이 된 다음, 결과값을 리스트 형식으로 반환
//    }
}
