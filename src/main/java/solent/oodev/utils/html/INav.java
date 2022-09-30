/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.utils.html;


public interface INav {

    String NAV =
                "<header id=\"main-navigation\">" +
                    "<nav>" +
                            "<a href=\"songs.jsp\">Songs</a>" +
                            "<a href=\"cart.jsp\">Cart</a>" +
                            "%ORDERS%" +
                            "<a href=\"%JSP%.jsp\">%LOG%</a>" +
                            "<a href=\"admin.jsp\">Admin</a>" +
                    "</nav>" +
                "</header>";

    String ORDERS = "<a href=\"orders.jsp\">Orders</a>";
    String MAIN = "<main class=\"main\">";
    String END_MAIN = "</main>";
    String WELCOME =
            "<section id=\"welcome\">" +
                    "  " +
                    "</section>";

    String WELCOME_START =
               "<section id=\"welcome\">";
    String WELCOME_END =
               "</section>";
    String BODY = "<body>";
    String BODY_END = "</body> </html>";
}

