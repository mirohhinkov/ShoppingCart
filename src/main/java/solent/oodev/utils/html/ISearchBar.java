package solent.oodev.utils.html;

public interface ISearchBar {
    String SEARCH_BAR =
            "<section id=\"search\">\n" +
                    "    <form action=\"/search\">\n" +
                    "      <label for=\"productSearch\">Search by product by category or name:</label>\n" +
                    "      <input type=\"search\" id=\"productSearch\" name=\"productSearch\" value= \"%1%\">\n" +
                    "      <input type=\"submit\" value=\"Search\">\n" +
                    "    </form>\n" +
                    "</section>";
}
