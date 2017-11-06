package com.ebi.genome;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class TestConstants {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    public static final String ERROR_CODE_TODO_ENTRY_NOT_FOUND = "NOT_FOUND";
    public static final String ERROR_CODE_VALIDATION_FAILED = "BAD_REQUEST";

    public static final String FIELD_NAME_DESCRIPTION = "description";

    public static final int MAX_LENGTH_DESCRIPTION = 500;
    public static final int MAX_LENGTH_TITLE = 100;

    public static final String REQUEST_PARAM_PAGE_NUMBER = "page";
    public static final String REQUEST_PARAM_PAGE_SIZE = "size";
    public static final String REQUEST_PARAM_SEARCH_TERM = "searchTerm";
    public static final String REQUEST_PARAM_SORT = "sort";

    public static final String SORT_DIRECTION_ASC = "ASC";

    /**
     * Prevents instantiation.
     */
    private TestConstants() {}
}
