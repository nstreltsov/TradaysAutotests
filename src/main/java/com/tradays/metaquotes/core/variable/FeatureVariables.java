package com.tradays.metaquotes.core.variable;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
@Value
public class FeatureVariables {
    private static Map<String, Object> variables = new HashMap<>();
}
