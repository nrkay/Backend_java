package com.khairiyah.revisichallange4.dto.merchant;

import lombok.*;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRequest {
    private String name_merchant;
    private String location;
    private Boolean open;
}