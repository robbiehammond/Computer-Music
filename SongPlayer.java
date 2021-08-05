import org.jfugue.player.Player;

public class SongPlayer {

    static String fullSong = "";
    static String readableSong = "";
    static MelodyGenerator gen = null;

    //put everything together and have it ready for the GUI
    public static void play() {
        Player player = new Player();
        player.play(gen.song + "0s"); // "0s" to hear the last note and a clean release
    }

    //standard constructor
    public static void generate(int numMeasures, Key key, boolean drumsOn, boolean ChordsOn) throws Exception {
        gen = new MelodyGenerator(numMeasures, key, drumsOn, ChordsOn);

        LilypondWriter w = new LilypondWriter(gen, "test");
        w.writeToDesktop();

        //Save the song in both formats so they can be retrieved
        fullSong = gen.getFullSong();
        readableSong = gen.getReadableSong();
    }

    //overload for abridged constructor
    public static void generate(String song, Key key, boolean drumsOn, boolean ChordsOn) throws Exception {
        gen = new MelodyGenerator(song, key, drumsOn, ChordsOn);

        //Save the song in both formats so they can be retrieved
        fullSong = gen.getFullSong();
        readableSong = gen.getReadableSong();
    }
}
