package com.secondhand.presentationadvertapi.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdvertResponse {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
//    private Category category;

//    @Getter
//    @AllArgsConstructor
//    public static class Category {
//        private Long id;
//        private String name;
//    }
}
