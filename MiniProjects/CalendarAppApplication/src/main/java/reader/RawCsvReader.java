package reader;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RawCsvReader {
    public List<String[]> readAll(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        return csvReader.readAll(); // 이 경로로 입력된 데이터에서 csv파일로 변환이 된 다음, 결과값을 리스트 형식으로 반환
    }
}
