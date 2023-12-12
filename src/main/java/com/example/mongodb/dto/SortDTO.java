package com.example.mongodb.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Data
public class SortDTO {
    private String by;
    private Sort.Direction direction;

    public boolean isEmpty() {
        if (StringUtils.containsWhitespace(by) || direction == null) {
            return true;
        }
        return false;
    }
}
