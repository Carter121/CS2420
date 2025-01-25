package assign02.timing;

import assign02.UHealthID;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

@SuppressWarnings("DuplicatedCode")
public class GenerateID {

    private final static int numOfIDs = 250_000;
    public final static String outputDirectory = "src/main/java/com/cartercarling/cs2420/assignments/assign02/src/main/java/assign02/timing";
    private final static Random rng = new Random();

    public static void main(String[] args) {
        GenerateID generateID = new GenerateID();
        generateID.generateIDs();
    }

    private void generateIDs() {
        ArrayList<UHealthID> generatedIDs = this.generateUHIDs(numOfIDs);

        try (Writer writer = new FileWriter(outputDirectory + "/output.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(generatedIDs, writer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Generate unique UHealthIDs.
     *
     * @param howMany - IDs to make
     * @return a list of UHealthIDs
     */
    private ArrayList<UHealthID> generateUHIDs(int howMany) {
        ArrayList<UHealthID> ids = new ArrayList<UHealthID>(howMany);
        HashSet<UHealthID> idSet = new HashSet<UHealthID>(howMany);
        char[] prefix = new char[4];
        while (idSet.size() < howMany) {
            prefix[0] = (char) ('A' + rng.nextInt(26));
            prefix[1] = (char) ('A' + rng.nextInt(26));
            prefix[2] = (char) ('A' + rng.nextInt(26));
            prefix[3] = (char) ('A' + rng.nextInt(26));
            idSet.add(new UHealthID(new String(prefix) + "-" + String.format("%04d", rng.nextInt(10000))));
        }
        ids.addAll(idSet);
        return ids;
    }

}
