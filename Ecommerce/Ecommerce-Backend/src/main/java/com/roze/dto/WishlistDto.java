package com.roze.dto;

import lombok.Data;

@Data
public class WishlistDto {
    private Long id;
    private Long productId;
    private Long userId;
    private String productName;
    private String productDescription;
    private Long price;
    private byte[] returnedImg;

}
