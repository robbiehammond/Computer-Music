import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInterface extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    //input text
    private TextField text = new TextField("--Put JFugue text here--");
    //output text
    private TextArea output = new TextArea("--Output will be displayed here--");
    //# measures
    private TextField measureText = new TextField("--Put Number of Measures Here--");
    //Generate song
    private Button generateNewSongButton = new Button("Generate Random Song!");
    //Play from input
    private Button generateFromInput = new Button("Generate From Input");

    //choose to put drums on
    ComboBox DrumsBox = new ComboBox();
    //choose to put chords on
    ComboBox ChordsBox = new ComboBox();
    //select key
    ComboBox KeyBox = new ComboBox();
    //select major/minor
    ComboBox ModeBox = new ComboBox();

    //get the selected key
    String getFromKeyBox() {
        String key = (String) KeyBox.getValue();
        if (key.equals("--Choose--")) {
            output.appendText("Please fill in all of the choices!");
            return null;
        }

        if (ModeBox.getValue() == "Major")
            key += "maj";
        else if (ModeBox.getValue() == "Minor")
            key += "min";
        else {
            output.appendText("Please fill in all of the choices!");
            return null;
        }
        return key;
    }

    //get the number of measures from the box, making sure it is valid
    Integer getNumMeasures() {
        int numberMeasures = 0;
        try {
            numberMeasures = Integer.parseInt(measureText.getCharacters().toString());
            if (numberMeasures < 1) {
                output.appendText("Please enter a valid number of measures, in numeric form (ie \"16\")");
                return null;
            }
        }
        catch (NumberFormatException e) {
            output.appendText("Please enter a valid number of measures, in numeric form (ie \"16\")");
            return null;
        }
        return numberMeasures;
    }

    //check if chords should be on
    Boolean areChordsOn() {
        if (ChordsBox.getValue().equals("Yes"))
            return true;
        else if (ChordsBox.getValue().equals("No"))
            return false;
        else {
            output.appendText("Please fill in all of the choices!");
            return null;
        }
    }

    //check if drums should be on
    Boolean areDrumsOn() {
        if (DrumsBox.getValue().equals("Yes"))
            return true;
        else if (DrumsBox.getValue().equals("No"))
            return false;
        else {
            output.appendText("Please fill in all of the choices!");
            return null;
        }
    }

    @Override public void start(Stage stage) {
        stage.setTitle("Random Music Generator");
        Scene scene = new Scene(new Group(), 530, 375);

        //drums selection
        DrumsBox.getItems().addAll(
                "Yes",
                "No"
        );
        DrumsBox.setValue("--Choose--");


        //chords selection
        ChordsBox.getItems().addAll(
                "Yes",
                "No"
        );
        ChordsBox.setValue("--Choose--");


        //key selection
        KeyBox.getItems().addAll(
                "C",
                "C#",
                "Db",
                "D",
                "D#",
                "Eb",
                "E",
                "F",
                "F#",
                "Gb",
                "G",
                "G#",
                "Ab",
                "A",
                "A#",
                "Bb",
                "B"
        );
        KeyBox.setValue("--Choose--");


        //major/minor selection
        ModeBox.getItems().addAll(
                "Major",
                "Minor"
        );
        ModeBox.setValue("--Choose--");

        //when clicked, generate and play the new song on a different thread, so that the window can still be used
        generateNewSongButton.setOnAction(event -> {
            Thread play = new Thread(new GenerateSongThread("From Scratch"));
            play.start();
        });

        //when clicked, play the inputted song on a different thread
        generateFromInput.setOnAction(event -> {
            Thread play = new Thread(new GenerateSongThread("From Input"));
            play.start();
        });

        //so that all threads are closed on exit
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        //set up visuals
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Drums On? "), 0, 0);
        grid.add(DrumsBox, 1, 0);
        grid.add(new Label("Chords On? "), 2, 0);
        grid.add(ChordsBox, 3, 0);
        grid.add(new Label("Key?"), 0, 1);
        grid.add(KeyBox, 1, 1, 3, 1);
        grid.add(new Label("Major or Minor?"), 2, 1);
        grid.add(ModeBox, 3, 1);
        grid.add(new Label("Number of Measures:"), 0, 2);
        grid.add(measureText, 1, 2, 3, 1);
        grid.add(new Label("(optional) Input Song"), 0, 3);
        grid.add(text, 1, 3, 3, 1);
        grid.add(new Label("Output"), 0, 4);
        grid.add(output, 0, 5, 4, 1);
        grid.add(generateNewSongButton, 1, 6, 1, 1);
        grid.add(generateFromInput, 2, 6, 1, 1);
        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);

        //display everything
        stage.setScene(scene);
        stage.show();
    }

    //nested class for new thread creation
    class GenerateSongThread extends Thread {

        //stores whether we are generating this song from scratch, or reading from input
        String type;

        public GenerateSongThread(String type) {
            this.type = type;
        }

        public void run() {
            if (type.equals("From Scratch")) {
                output.clear();

                //get all of the necessary materials from the boxes
                String key = getFromKeyBox();
                if (key == null)
                    return;

                Integer measureNum = getNumMeasures();
                if (measureNum == null)
                    return;

                Boolean chordsOn = areChordsOn();
                if (chordsOn == null)
                    return;

                Boolean drumsOn = areDrumsOn();
                if (drumsOn == null)
                    return;

                //play the song
                try {
                    SongPlayer.generate(measureNum, new Key(key), drumsOn, chordsOn);
                    //readable song is outputted, so it can be directly fed back into the input
                    Platform.runLater(() -> {
                        output.appendText(SongPlayer.readableSong + "\n");
                        output.appendText("Extra Info (don't copy this into input, but make sure the settings match these " +
                                "for playback): Key = " + key);
                            });
                    SongPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> output.appendText("Uh oh, something went wrong."));
                }
            }
            if (type.equals("From Input")) {

                output.clear();

                //get variables from boxes
                String key = getFromKeyBox();
                if (key == null)
                    return;

                //get this one directly
                String song = text.getCharacters().toString();
                if (song.equals("--Put JFugue text here--")) {
                    output.appendText("To generate from input, please enter a song in the correct format (ie the format" +
                            " that can be seen from generated songs)");
                    return;
                }

                Boolean chordsOn = areChordsOn();
                if (chordsOn ==  null)
                    return;

                Boolean drumsOn = areDrumsOn();
                if (drumsOn == null)
                    return;

                //play the song
                try {
                    SongPlayer.generate(song, new Key(key), drumsOn, chordsOn);
                    SongPlayer.play();
                } catch (Exception e) {
                    //if it is not working, it is most likely due to an improper key. Alert the user
                    Platform.runLater(() -> output.appendText("There was a problem in generating the chords. Did you make sure you put in the " +
                                    "appropriate key in the key field?\nAre you sure the formatting is correct?")
                            );
                }
            }
        }
    }
}
