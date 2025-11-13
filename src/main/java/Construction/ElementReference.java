package Construction;
import java.util.Map;

public class ElementReference {
    public static Map<String, Integer> electrons = Map.of( // maps element symbols to how many bonds they have
            "O",2,
            "C",4,
            "H",1,
            "N",3,
            "Cl",1
    );

    public static Map<String, Double> atomicMass = Map.of( // maps element symbols to their mass
            "O", 16.0,
            "C", 12.0,
            "H", 1.0,
            "N",14.0,
            "Cl",35.5
    );
}
