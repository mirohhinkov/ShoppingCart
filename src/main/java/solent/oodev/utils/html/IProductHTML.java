/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.utils.html;

public interface IProductHTML {

    public static String SONG_PAGE_TITLE = "<h2>Songs List</h2>";

    public static String MESSAGE ="<p id=\"message\">%1</p>";

    public static String FLEX_CONTAINER="<div class=\"flex-container\">";

    public static String DIV_END = "</div>";

    public static String CARD =
            "<div class=\"card\">\n" +
            "    <img\n" +
            "      class=\"product-img\"\n" +
            "      src=\"%1\"\n" +
            "      alt=\"%2\"\n" +
            "    />\n" +
            "    <div class=\"card_right\">\n" +
            "      <p class=\"item\">%2</p>\n" +
            "      <p class=\"category_title\">Category:</p>\n" +
            "      <p class=\"category\">%4</p>\n" +
            "      <a href=\"%5\" >\n" +
            "        <button class=\"btn_card abs\">\n" +
            "          <span class=\"btn-txt\">%6</span>\n" +
            "        </button></a\n" +
            "      >\n" +
            "    </div>\n" +
            "  </div>"
;

}


