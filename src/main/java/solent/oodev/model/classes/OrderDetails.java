/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.model.classes;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetails {
    private long orderId;
    private Date orderDate;
    private long ProductId;
    private String prodDescription;
    private int quantity;
}
