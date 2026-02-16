package Construction;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class ElementReference {
    public static final Map<String, Integer> electrons = Map.of( // maps element symbols to how many bonds they have
            "O",2,
            "C",4,
            "H",1,
            "N",3,
            "F",1,
            "Cl",1,
            "Br",1,
            "I",1
    );

    public static final Map<String, Double> atomicMass = Map.of( // maps element symbols to their mass
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

    public static final Map<Set<String>, String> groupCombos = Map.of(
        Set.of("carbonyl","hydroxy"),"carboxylic acid",
            Set.of("carbonyl","amine"),"amide",
            Set.of("carbonyl","ester link"),"ester",
            Set.of("carbonyl","halogen"),"acid halide"

    );

    public static final Map<String, Integer> atomicMasses = Map.ofEntries(
          Map.entry("carboxylic acid",1),
            Map.entry("ester",2),
            Map.entry("acid halide",3),
            Map.entry("amide",4),
            Map.entry("nitrile",5),
            Map.entry("aldehyde",6),
            Map.entry("ketone",7),
            Map.entry("hydroxy",8),
            Map.entry("alkene",9),
            Map.entry("amine",10),
            Map.entry("halogen",11)

    );

}
