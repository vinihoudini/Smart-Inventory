package com.example.inventory_management_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RecipeDTO {
    private Long id;
    private String name;
    private String instructions;
    private List<ItemDTO> ingredients;

}

