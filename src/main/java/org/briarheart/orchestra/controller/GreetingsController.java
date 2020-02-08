package org.briarheart.orchestra.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * @author Roman Chigvintsev
 */
@RestController
public class GreetingsController {
    @RequestMapping(method = RequestMethod.GET, value = "/hi/{name}")
    public Map<String, String> sayHi(@PathVariable String name,
                                     @RequestHeader(value = "X-CNJ-Name", required = false) Optional<String> cn) {
        String resolvedName = cn.orElse(name);
        return Map.of("greeting", "Hello, " + resolvedName + "!");
    }
}
