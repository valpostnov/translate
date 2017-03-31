package com.postnov.android.translate.domain;

import java.util.Map;

/**
 * @author Valentin Postnov
 */
public interface QueryMapFactory {

    Map<String, String> createForTrans(String text);

    Map<String, String> createForDict(String text);
}
