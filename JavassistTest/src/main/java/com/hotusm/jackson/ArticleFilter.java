package com.hotusm.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true,value={"user"})
public interface ArticleFilter {
}
