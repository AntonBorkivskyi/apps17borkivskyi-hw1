package json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    List<JsonPair> pairs = new ArrayList();

    public JsonObject(JsonPair... jsonPairs) {
        for(JsonPair pair : jsonPairs){
            pairs.add(pair);
        }
    }

    @Override
    public String toJson() {
        if(pairs.size() == 0){
            return "{}";
        }
        String return_string = "{";
        for(JsonPair pair : pairs){
            return_string = return_string + pair.key.toString() + ": " + pair.value.toJson() + ", ";
        }
        return_string = return_string.substring(0, return_string.length() - 2);
        return_string += "}";
        return return_string;
    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);
    }

    public Json find(String name) {
        for(JsonPair pair : pairs){
            if (pair.key.equals(name)){
                return pair.value;
            }
        }
        return null;
    }

    public boolean contains(String name){
        for(JsonPair pair : pairs){
            if (pair.key.equals(name)){
                return true;
            }
        }
        return false;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for(String name : names) {
            Json value = this.find(name);
            if (value != null) {
                jsonObject.add(new JsonPair(name, value));
            }
        }
        return jsonObject;
    }
}
