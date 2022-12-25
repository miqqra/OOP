package ru.nsu.krasnikov;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.util.Date;

public record Note(@JsonGetter("topic") String topic, @JsonGetter("content") String content,
                   @JsonGetter("creationTime") Date creationTime) {
}
