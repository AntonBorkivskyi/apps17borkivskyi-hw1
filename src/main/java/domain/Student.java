package domain;

import json.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    String name;
    String surname;
    Integer year;
    List<Tuple> subjects = new ArrayList();

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        for(Tuple tuple : exams){
            this.subjects.add(tuple);
        }
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(new JsonPair("name", new JsonString(this.name)));
        jsonObject.add(new JsonPair("surname", new JsonString(this.surname)));
        jsonObject.add(new JsonPair("year", new JsonNumber(this.year)));
        JsonObject[] sub_exams = new JsonObject[subjects.size()];
        for (int i = 0; i < subjects.size(); i++){
            JsonObject sub_obj = new JsonObject();
            sub_obj.add(new JsonPair("course", new JsonString(subjects.get(i).key.toString())));
            sub_obj.add(new JsonPair("mark", new JsonNumber((int)subjects.get(i).value)));
            sub_obj.add(new JsonPair("passed", new JsonBoolean((int)subjects.get(i).value > 2)));
            sub_exams[i] = sub_obj;
        }
        JsonArray jarr = new JsonArray(sub_exams);
        jsonObject.add(new JsonPair("exams", jarr));
        return jsonObject;
    }
}
