package ru.arlechin.life;

import org.codehaus.jackson.map.ObjectMapper;
import ru.arlechin.life.model.Pattern;

import java.io.InputStream;

/**
 * @author arlechin
 */
public class Resource {

    private InputStream input;
    private Pattern pattern;

    public Resource() {
        input = getClass().getResourceAsStream("/glider_gun.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            pattern = mapper.readValue(input, Pattern.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public Pattern getPattern() {
        return pattern;
    }
}
