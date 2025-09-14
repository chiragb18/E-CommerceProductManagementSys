package com.project.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotBlank(message = "customerName is required")
    private String customerName;

    @NotEmpty(message = "items cannot be empty")
    private List<OrderItemRequest> items;
}
