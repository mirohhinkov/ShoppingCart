package solent.oodev.utils.html;

public interface IUserForms {
    String LOGIN = "    <div class=\"login-form\">\n" +
            "      <form:form class=\"form\" method=\"POST\" modelAttribute=\"user\">\n" +
            "        <div class=\"form__group\">\n" +
            "          <label class=\"form__label\" for=\"email\">\n" +
            "            Email address\n" +
            "          </label>\n" +
            "          <form:input\n" +
            "            path=\"userEmail\"\n" +
            "            class=\"form__input\"\n" +
            "            placeholder=\"you@example.com\"\n" +
            "            type=\"email\"\n" +
            "            name=\"email\"\n" +
            "            required=\"true\"\n" +
            "          />\n" +
            "        </div>\n" +
            "        <div class=\"form__group.ma-bt-md\">\n" +
            "          <label class=\"form__label\" for=\"password\">\n" +
            "            Password\n" +
            "          </label>\n" +
            "          <form:input\n" +
            "            path=\"password\"\n" +
            "            class=\"form__input\"\n" +
            "            type=\"password\"\n" +
            "            name=\"password\"\n" +
            "            placeholder=\"••••••••\"\n" +
            "            required=\"true\"\n" +
            "            minLength=\"5\"\n" +
            "          />\n" +
            "        </div>\n" +
            "        <p>To register an account please send a message via contact form.</p>\n" +
            "        <div class=\"form__group\">\n" +
            "          <input type=\"submit\" class=\"btn btn--blue\" value=\"Login\" />\n" +
            "        </div>\n" +
            "      </form:form>\n" +
            "    </div>";
}
