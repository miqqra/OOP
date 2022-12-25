package ru.nsu.krasnikov;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notebook {
    public static void main(String[] args) throws IllegalArgumentException, IOException, ParseException {
        JsonReaderWriter json = new JsonReaderWriter();
        switch (args[0]) {
            case "-add" -> {
                //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                Date now = new Date();
                //String creationTime = dtf.format(now);
                json.write(new Note(args[1], args[2], now));
            }
            case "-rm" -> {

            }
            case "-show" -> {
                if (args.length == 1){
                    json.read();
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    List<String> keywords = new ArrayList<>();
                    Date timeFrom = dateFormat.parse(args[1]);
                    Date timeTo = dateFormat.parse(args[2]);
                    for (int i = 3; i < args.length; i++){
                        keywords.add(args[i]);
                    }
                    json.read(timeFrom, timeTo, keywords);
                }
            }
        }
    }
}
