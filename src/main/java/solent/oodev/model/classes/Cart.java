/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.model.classes;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Cart {
    
    private final HashMap<Long, ProductOrder> productOrders = new HashMap<>();
    

}
