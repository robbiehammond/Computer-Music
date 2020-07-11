import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LilypondWriter {

    MelodyGenerator generatedMelody;

    String songName;

    //song in JFugue format
    String song;

    //Key of song in string format
    String key;

    public LilypondWriter(MelodyGenerator generatedMelody, String songName) {
        this.songName = songName;
        this.generatedMelody = generatedMelody;
        song = generatedMelody.getReadableSong();
        key = generatedMelody.key.getKey();
    }

    //print to console - can be used for debugging purposes
    public void printGeneratedMel() {
        System.out.println(generatedMelody.getReadableSong());
    }



    //write translated text to lilypond file
    public String writeLYFile() {
        return "\\version \"2.18.2\"\n\n\\header { }\n\n\\score {\n{\n" + "\n\\clef bass\n" + translateToLilyPond(song)
                + "\n\n}" + "\n\\layout { }\n}";
    }

    //put file with lilypond text on user's desktop
    public void writeToDesktop() throws IOException {
        String userHome = System.getProperty("user.home");
        File result = new File(userHome + "\\Desktop\\" + "YourGeneratedSong.ly");
        BufferedWriter out = new BufferedWriter(new FileWriter(result));
        out.write(writeLYFile());
        out.close();
    }

    //perform all translations
    public String translateToLilyPond(String original) {
        original = translateDurationAndPitch(original);
        StringBuilder build = new StringBuilder();

        //String is split into an array
        String[] notes = original.split(" ");


        //Append array of notes to a corrected lilypond string
        //skip the first 2 indexes, they're just the channel and instrument for JFugue. Not needed for LilyPond.
        for (int i = 2; i < notes.length; i++) {
            notes[i] = translateString(notes[i]);
            build.append(notes[i] + " ");
        }

        return build.toString();
    }

    //replace the JFugue syntax with lilypond syntax
    public String translateDurationAndPitch(String original) {
        original = original.replaceAll("w", "1");
        original = original.replaceAll("h", "2");
        original = original.replaceAll("q", "4");
        original = original.replaceAll("i", "8");
        original = original.replaceAll("5", ""); //octave 5 = nothing, 6 = '
        original = original.replaceAll("6", "'");
        original = original.replaceAll("s", "16"); //must be last so the 6 for the octave isn't replaced
        return original;
    }

    //translates flats and sharps from JFugue to lilypond
    public String translateString(String s) {
        s = s.replaceAll("#", "is");
        s = s.replaceAll("b", "es");
        s = s.toLowerCase();
        return s;
    }
}
