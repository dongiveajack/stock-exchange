package com.navi.stock_exchange.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String name;
    private String code;
}
