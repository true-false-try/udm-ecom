package com.ecommerce.project.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
}
