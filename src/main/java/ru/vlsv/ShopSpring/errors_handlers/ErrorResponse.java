package ru.vlsv.ShopSpring.errors_handlers;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.11.2019
 * @link https://github.com/Centnerman
 */

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String msg;
    private long timeStamp;

}
