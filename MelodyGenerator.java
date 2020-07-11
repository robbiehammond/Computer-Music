import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MelodyGenerator {

    //checks for which constructor has been used
    private boolean abridgedConstructorUsed;

    //input key
    Key key;

    //list of each measure generated
    private ArrayList<String> measures = new ArrayList<>();

    //the possible choices for notes that can still be put in a given measure
    private ArrayList<Integer> durations = new ArrayList<>();

    //the song in JFugue format - is constantly modified
    String song;

    //the song in JFugue format, minus the drums and chords
    private String readableSong;

    //the full song in JFugue format with drums and chords - not modified once made
    private String fullSong;

    //flags to add drums or chords selected by user
    private boolean drumsOn;
    private boolean chordsOn;

    //standard constructor that generates the song, translates it into JFugue, and then adds JFugue drums/chords
    public MelodyGenerator(int numMeasures, Key key, boolean drumsOn, boolean chordsOn) throws Exception {
        this.key = key;
        this.drumsOn = drumsOn;
        this.chordsOn = chordsOn;
        abridgedConstructorUsed = false;

        updateDurations(16);
        addMeasures(numMeasures);
        song = concatenate();
    }

    //constructor for already generated and translated songs - still adds drums/chords
    public MelodyGenerator(String song, Key key, boolean drumsOn, boolean chordsOn) throws Exception {
        this.song = song;
        this.key = key;
        this.drumsOn = drumsOn;
        this.chordsOn = chordsOn;
        abridgedConstructorUsed = true;

        this.song = concatenate();
    }

    //update options based on how many beats remaining
    private void updateDurations(double remaining) {
        //numbers are added multiple times to offset the chances from equal between each duration
        //(quarters and 8ths are most common, as they are in actual music)

        //reset the arraylist, because the number of beats left in the measure has definitely changed since the method was last called
        durations.clear();

        //always can put at least 1 16th note, otherwise this function wouldn't be called
        durations.add(1);

        //if there is less than a beat remaining, add chances for eighth notes to be selected
        if (remaining >= 2) {
            for (int i = 0; i < 4; i++) { //add multiple so that they have a higher chance of being called
                durations.add(2);
            }
        }

        //if there is less than 2 beats remaining, add chances for quarter notes to be selected
        if (remaining >= 4) {
            for (int i = 0; i < 4; i++) {
                durations.add(4);
            }
        }

        //if there is less than 4 beats (ie the whole measure) remaining, add chances for half notes to be selected
        //these should be relatively rare
        if (remaining >= 8) {
            durations.add(8);
        }

        //if the whole measure is remaining, add chance for a whole note to be selected
        //also should be rare
        if (remaining == 16) {
            durations.add(16);
        }
    }

    //generates individual measures
    private String generateMeasure() throws Exception {
        Random random = new Random();

        //beats left in measure
        double beatsremaining = 16;

        //String to construct the measure
        StringBuilder measureStream = new StringBuilder();

        while (beatsremaining > 0) {
            //update the amount of remaining beats before doing any operations
            updateDurations(beatsremaining);
            //get a random scale degree, 1/8 chance for everything but scale degree 1, which has 1/4 (to emphasize tonic)
            int degree = random.nextInt(8) + 1;

            //chose an appropriate duration based on the number of beats left in the measure
            int duration = durations.get(random.nextInt(durations.size()));

            //update the amount of beats remaining
            beatsremaining = beatsremaining - duration;

            //if scale degree 8 is chosen, play root an octave higher, else do things normally
            if (degree == 8)
                measureStream.append(correctSharps(key.degreeToNote(degree)) + "6" + durationToString(duration) + " ");
            else
                measureStream.append(correctSharps(key.degreeToNote(degree)) + "5" + durationToString(duration) + " ");

        }
        //put bar line
        measureStream.append(" | ");
        return measureStream.toString();
    }

    //changes the duration to JFugue text
    private char durationToString(int duration) throws Exception {
        switch (duration) {
            case 16:
                return 'w';
            case 8:
                return 'h';
            case 4:
                return 'q';
            case 2:
                return 'i';
            case 1:
                return 's';
            //if something unexpected is put in (which shouldn't happen without messing with the code), throw an error
            default:
                throw new Exception("Invalid note duration thrown in");
        }
    }

    //generate as many measures as the user asked for
    private void addMeasures(int numMeasures) throws Exception {
        for (int i = 0; i < numMeasures; i++) {
            measures.add(generateMeasure());
        }
    }

    //put measures together into a single string
    private String concatenate() throws Exception {

        //the final song
        StringBuilder concat = new StringBuilder();

        //if we're using the constructor that must generate the song
        if (!abridgedConstructorUsed) {
            concat.append("V0 I[Piano] ");

            //add each measure to the final song
            for (String s : measures) {
                concat.append(s);
            }
            //save the string without the drums and chords, as it is used for output in the UserInterface
            readableSong = concat.toString();
        }

        //for the abridged (or any other) constructor
        else {
            //append the song
            concat.append(song);

            //since the number of measures was never assigned in the constructor, find out how long it is, as the number of measures is used for the drums
            for (int i = 0; i < concat.length(); i++) {
                if (concat.charAt(i) == '|') {
                    measures.add("placeholder");
                }
            }
        }

        //for both constructors
        if (chordsOn) {
            addChords(concat);
        }

        if (drumsOn) {
            //for each measure, add 1 bar of a generic drum beat
            for (int i = 0; i < measures.size(); i++) {
                concat.append("V9 [BASS_DRUM]i [CLOSED_HI_HAT]i  [ELECTRIC_SNARE]i [CLOSED_HI_HAT]i [BASS_DRUM]i [CLOSED_HI_HAT]i  [ELECTRIC_SNARE]i [CLOSED_HI_HAT]i | ");
            }
        }
        return concat.toString();
    }

    //fixes sharps because you can't add "#" in Java plain text
    private String correctSharps(Key.Notes n) {
        String s = n.toString();
        if (s.contains("sharp")) {
            s = s.replace("sharp", "#");
        }
        return s;
    }


    //Currently plays the chord of the scale degree of the first note of a given measure
    private void addChords(StringBuilder song) throws Exception {

        //used to save where the first note of each measure is
        ArrayList<Integer> list = new ArrayList<>();

        //gets the note of the first chord
        String firstChord = String.valueOf(song.charAt(song.indexOf("]") + 2));

        //converts that letter to the appropriate scale degree
        //takes advantage of the fact that all scales only have distinct letters, so we can just ignore accidentals via substring
        int scaleDegree = 0;
        for (int i = 1; i < 9; i++) {

            //just the letter, no accidentals - accidentals taken care of by the known key
            String temp = key.ScaleDegree.get(i).toString().substring(0,1);

            if (temp.equals(firstChord))
                scaleDegree = i;
        }

        //the chord in readable JFugue
        String chord = correctSharps(key.ScaleDegree.get(scaleDegree));

        //add the appropriate chord quality to the first
        chord = addChordQuality(scaleDegree, chord);
        song.insert(song.indexOf("]") + 2, chord);


        //find out where notes that must be "chorded" are
        for (int i = 0; i < song.length(); i++) {
            if (song.charAt(i) == '|')
                list.add(i + 2);
        }


        //primarily repeat process from before, but do this with every single note position that has been found
        for (int i = 0; i < list.size() - 1; i++) {

            //get the index of the current chord to analyze
            String curChord = String.valueOf(song.charAt(list.get(i)));

            //translate just as before
            scaleDegree = 0;
            for (int j = 1; j < 9; j++) {
                //just the letter, no accidentals
                String temp = key.ScaleDegree.get(j).toString().substring(0,1);

                if (temp.equals(curChord))
                    scaleDegree = j;
            }


            //make the chord in JFugue
            chord = correctSharps(key.ScaleDegree.get(scaleDegree));
            chord = addChordQuality(scaleDegree, chord);
            song.insert(list.get(i), chord);

            //clear the list and update indexes because the indexes shifted backwards with the new chord addition (ie the string got longer)
            list.clear();
            for (int k = 0; k < song.length(); k++) {
                if (song.charAt(k) == '|')
                    list.add(k + 2);
            }
        }
        //save the full song in a variable to access later
        fullSong = song.toString();
    }

    //find the correct quality of the chord
    private String addChordQuality(int scaleDegree, String chord) throws Exception {
        //choose the quality based on major/minor key and the scale degree - just simple music theory
        if (key.getKey().toLowerCase().contains("maj")) {
            switch (scaleDegree) {
                case 0:
                    throw new Exception("Invalid scale degree went through, that's a problem");
                case 1:
                case 4:
                case 5:
                case 8:
                    chord += "4majh+";
                    break;
                case 2:
                case 3:
                case 6:
                    chord += "4minh+";
                    break;
                case 7:
                    chord += "4dimh+";
                    break;
                default:
                    throw new Exception("Problem!");
            }
        }
        if (key.getKey().toLowerCase().contains("min")) {
            switch (scaleDegree) {
                case 0:
                    throw new Exception("Invalid scale degree went through, that's a problem");
                case 3:
                case 6:
                case 5:
                case 7:
                    chord += "4majh+";
                    break;
                case 1:
                case 4:
                case 8:
                    chord += "4minh+";
                    break;
                case 2:
                    chord += "4dimh+";
                    break;
                default:
                    throw new Exception("Problem!");
            }
        }
        return chord;
    }

    //get the readable song
    public String getReadableSong() {
        return readableSong;
    }

    //get the full song (with drums and/or chords)
    public String getFullSong() {
        return fullSong;
    }


}
