package Construction;
import java.awt.*;
import java.util.Map;

public class ElementReference {
    public static Map<String, Integer> electrons = Map.of( // maps element symbols to how many bonds they have
            "O",2,
            "C",4,
            "H",1,
            "N",3,
            "F",1,
            "Cl",1,
            "Br",1,
            "I",1
    );

    public static Map<String, Double> atomicMass = Map.of( // maps element symbols to their mass
            "O", 16.0,
            "C", 12.0,
            "H", 1.0,
            "N", 14.0,
            "F",1.0,
            "Cl",35.5,
            "Br",1.0,
            "I",1.0
    );

    public static Color findColor(int id){

        Color[] list = new Color[]{
                Color.BLUE,
                Color.GREEN,
                Color.RED,
                Color.YELLOW,
                Color.CYAN,
                Color.MAGENTA,
                Color.ORANGE,
                Color.PINK,
                Color.GRAY,
        };

        return list[id];
    }

}
