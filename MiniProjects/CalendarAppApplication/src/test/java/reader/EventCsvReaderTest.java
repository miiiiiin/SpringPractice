package reader;

import event.Meeting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// 정상 작동위해서는  MockitoExtension 어노테이션 추가해야함
@ExtendWith(MockitoExtension.class)
class EventCsvReaderTest {

    private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    // Mockito라는 라이브러리에서 RawCsvReader를 임의의 mocking class로 만들어서 테스트에 활용
    @Mock
    private RawCsvReader rawCsvReader;

    // EventCsvReader 객체가 생성될 때, 생성자에다가 필요한 정의된 mock 데이터들을 넣어줌
    @InjectMocks
    private EventCsvReader sut;
    @Test
    public void reader() throws IOException {
        // given
        String path = "";
        // EventCsvReader 객체가 생성될 때 mocking 처리가 되어있는 rawCsvReader를 호출
//        EventCsvReader sut = new EventCsvReader(rawCsvReader);

        // MockData
        List<String[]> mockData = new ArrayList<>();
        mockData.add(new String[8]); // header 스킵하기 때문에 임의의 값 넣어줌

        int mockSize = 5;
        for (int i = 0; i<mockSize; i++) {
            mockData.add(generateMock(i)); // id를 i로 지정
        }

        /** mocking 처리를 한 rawCsvReader가 호출되었을 때, 어떤 데이터를 리턴할건지 정의해주어야 함 **/
        // readall 메서드를 실행되었을 때, 어떤 mock 데이터를 리턴하게금 하는 코드를 작성해주어야함
        // readAll이 호출되었을 때, mockData를 리턴하라. (mocking 처리)
        when(rawCsvReader.readAll(path)).thenReturn(mockData);

        // when
        List<Meeting> meetings = sut.readMeetings(path);

        // then
        // 검증

        // 둘이 같은 값인지 체크
        assertEquals(mockSize, meetings.size());
        assertEquals("TITLE0", meetings.get(0).getTitle());
    }

    private String[] generateMock(int id) {
        String[] mock = new String[8]; // 배열 정의 시 사이즈를 넣어주어야 함
        mock[0] = String.valueOf(id);
        mock[1] = "Meeting"+id;
        mock[2] = "TITLE"+id;
        mock[3] = "A,B,C";
        mock[4] = "A1"+id;
        mock[5] = "TEST"+id;
        mock[6] = of(ZonedDateTime.now().plusHours(id));
        mock[7] = of(ZonedDateTime.now().plusHours(id+1));

        return mock;
    }

    private static String of(ZonedDateTime dateTime) {
        return  dateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }
}