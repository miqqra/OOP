package ru.nsu.krasnikov;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.management.ObjectName;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JsonReaderWriter {
    private static final String source = "Task_1_5_2/src/main/resources/notebook.json";
    private static final File file = new File(source);

    public List<Note> read() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<>() {
        });
    }

    public List<Note> read(Date timeFrom, Date timeTo, List<String> keywords) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Note> notes = objectMapper.readValue(file, new TypeReference<>() {
        });
        return notes
                .stream()
                .filter(
                        note -> note.creationTime().after(timeFrom) &&
                                note.creationTime().before(timeTo) &&
                                keywords
                                        .stream()
                                        .allMatch(keyword -> note.topic().contains(keyword))
                )
                .toList();
    }

    public boolean write(Note note) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, note);
        return true;
    }

    public boolean remove(String topic) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodes = objectMapper.readTree(file);
        List<Note> notes = objectMapper.readValue(file, new TypeReference<>() {
        });
        return false;
    }
}
