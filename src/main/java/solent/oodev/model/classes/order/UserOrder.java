package solent.oodev.model.classes.order;

import lombok.Data;

import java.util.Date;

@Data
public class UserOrder {
    Long orderId;
    Long userId;
    Date date;
}
