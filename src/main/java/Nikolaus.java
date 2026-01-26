public class Nikolaus {
    public static void main(String[] args) {
        greet();
        System.out.println();
        bidFarewell();
    }

    public static void greet() {
        String logo = "       ::::    :::   :::::::::::   :::    :::    ::::::::     :::              :::        :::    :::     :::::::: \n" +
            "      :+:+:   :+:       :+:       :+:   :+:    :+:    :+:    :+:            :+: :+:      :+:    :+:    :+:    :+: \n" +
            "     :+:+:+  +:+       +:+       +:+  +:+     +:+    +:+    +:+           +:+   +:+     +:+    +:+    +:+         \n" +
            "    +#+ +:+ +#+       +#+       +#++:++      +#+    +:+    +#+          +#++:++#++:    +#+    +:+    +#++:++#++   \n" +
            "   +#+  +#+#+#       +#+       +#+  +#+     +#+    +#+    +#+          +#+     +#+    +#+    +#+           +#+    \n" +
            "  #+#   #+#+#       #+#       #+#   #+#    #+#    #+#    #+#          #+#     #+#    #+#    #+#    #+#    #+#     \n" +
            " ###    ####   ###########   ###    ###    ########     ##########   ###     ###     ########      ########       ";
        for (int i = 0; i < 114; i++) {
            System.out.print('\u2500');
        }
        System.out.println();
        System.out.println(logo);
        for (int i = 0; i < 114; i++) {
            System.out.print('\u2500');
        }
        System.out.println();
        System.out.println("Greetings!!! I'm Nikolaus, your personal chatbot!!!");
        System.out.println("What can I do for you today???");
    }

    public static void bidFarewell() {
        System.out.println("Goodbye!!! Hope to see you again soon!!!");
    }
}


