/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.util.StringUtils;

/**
 *
 * @author aruns
 */
public class EntityNotMatchPathException extends RuntimeException {

    public EntityNotMatchPathException(String clazz, String... searchParamsMap) {
        super(EntityNotMatchPathException.generateMessage(clazz, toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity)
                + " does not match the path "
                + searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}