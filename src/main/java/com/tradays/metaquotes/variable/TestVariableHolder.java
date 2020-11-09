package com.tradays.metaquotes.variable;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Хранилище переменных тестового сценария
 *
 * @author Nikolay Streltsov on 08.11.2020
 */
public class TestVariableHolder {
    private final static String VAR_PATTERN = "\\#\\{(?<var>[^{^(^}^)]*)\\}";
    private final static Pattern VAR_PATTERN_COMPILED = Pattern.compile(VAR_PATTERN);

    //TODO для многопоточного запуска переделать на ThreadLocal
    @Getter
    private static Map<String, Object> variables = new HashMap<>();

    /**
     * Извлекает значение переменной из коллеции переменных
     * Функционал можно расширять добавляя новые шаблоны переменных
     *
     * @param param - переменная в виде "#{variable}"
     * @return - значение переменной
     */
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
