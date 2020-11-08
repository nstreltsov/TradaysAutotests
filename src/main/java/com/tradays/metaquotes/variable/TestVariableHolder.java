package com.tradays.metaquotes.variable;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nikolay Streltsov on 08.11.2020
 */
public class TestVariableHolder {
    private final static String VAR_PATTERN = "\\#\\{(?<var>[^{^(^}^)]*)\\}";
    private final static Pattern VAR_PATTERN_COMPILED = Pattern.compile(VAR_PATTERN);

    @Getter
    private static Map<String, Object> variables = new HashMap<>();

    public static <T> T evalVariable(String param) {
        if (param.trim().matches(".*" + VAR_PATTERN + ".*")) {
            Matcher varMatcher = VAR_PATTERN_COMPILED.matcher(param);
            while (varMatcher.find()) {
                return (T) variables.get(varMatcher.group("var"));
            }
        }
        return (T) param;
    }
}
