/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.utils.html;

public interface ICart {
    String TITLE = "<h2>Your cart</h2>";
    String HEAD =
            "<table>" +
                    "<tr>" +
                      "<th class=\"col1\">Product</th>" +
                      "<th class=\"col2\">Category</th>" +
                      "<th class=\"col3\">Quantity</th>" +
                      "<th class=\"col4\">Price</th>" +
                      "<th class=\"col5\"> </th>" +
                    "</tr>";
    String ROW =
            "<tr>" +
                    "<td class=\"col1\">%1%</td>" +
                    "<td class=\"col2\">%2%</td>" +
                    "<td class=\"col3\">%3%</td>" +
                    "<td class=\"col4\">%4%</td>" +
                    "<td class=\"col5\">" +
                      "<ul>" +
                        "<li>" +
                          "<a href=\"#1\"><img class=\"action\" src=\"plus.png\" alt=\"Plus icon\" /></a>" +
                        "</li>" +
                        "<li>" +
                          "<a href=\"#2\"><img class=\"action\" src=\"minus.png\" alt=\"Minus icon\" /></a>" +
                        "</li>" +
                        "<li>" +
                          "<a href=\"#3\"><img class=\"action\" src=\"x.png\" alt=\"Delete icon\" /></a>" +
                        "</li>" +
                      "</ul>" +
                    "</td>" +
                  "</tr>";

    String PRICE_ROW = "<tr><td id=\"total\" colspan=\"3\">Total: </td><td class=\"col4\">%1</td><td></td></tr>";
    String END_TABLE = "</table>";
    String BUY = "<div class=\"flex-container\" id=\"buy\">%1%</div>";
    String BUY_LINK = "<a href=\"/buy\"><button>BUY</button></a>";
    String BUY_TOPUP = "<button class=\"red btn_card\">Please Top-up</button></a>";

}
