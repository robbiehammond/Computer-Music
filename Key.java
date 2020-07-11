import java.util.HashMap;

public class Key {

    //Key in string format
    private String key;

    //Maps Notes to their respective scale degrees
    HashMap<Integer, Notes> ScaleDegree = new HashMap<>();

    public Key(String inputkey) throws Exception {
        this.key = inputkey;
        addNotes();
    }


    //Notes available to be used
    //"sharpsharp" = double sharp (x)
    public enum Notes {
        Cb,
        C,
        Csharp,
        Csharpsharp,
        Db,
        D,
        Dsharp,
        Eb,
        Ebb,
        E,
        Esharp,
        Fb,
        F,
        Fsharp,
        Fsharpsharp,
        Gb,
        G,
        Gsharp,
        Gsharpsharp,
        Ab,
        A,
        Asharp,
        Bb,
        Bbb,
        B,
        Bsharp
    }

    //All (standard) keys
    /*Though this certainly could have been done algorithmically, generating notes for scales becomes problematic when
     dealing with double sharps/double flats and when using technically "invalid" key signatures (such as G# minor).
     Though this "brute force" method certainly is not as pretty, it allows for easy addition of keys and no issues for
     implementing standard, custom, and theoretical keys.
     */
    public void addNotes() throws Exception {
        switch (key) {
            case "Cmaj":
                ScaleDegree.put(1, Notes.C);
                ScaleDegree.put(2, Notes.D);
                ScaleDegree.put(3, Notes.E);
                ScaleDegree.put(4, Notes.F);
                ScaleDegree.put(5, Notes.G);
                ScaleDegree.put(6, Notes.A);
                ScaleDegree.put(7, Notes.B);
                ScaleDegree.put(8, Notes.C);
                break;

            case "Cmin":
                ScaleDegree.put(1, Notes.C);
                ScaleDegree.put(2, Notes.D);
                ScaleDegree.put(3, Notes.Eb);
                ScaleDegree.put(4, Notes.F);
                ScaleDegree.put(5, Notes.G);
                ScaleDegree.put(6, Notes.Ab);
                ScaleDegree.put(7, Notes.Bb);
                ScaleDegree.put(8, Notes.C);
                break;

            case "C#maj":
                ScaleDegree.put(1, Notes.Csharp);
                ScaleDegree.put(2, Notes.Dsharp);
                ScaleDegree.put(3, Notes.Esharp);
                ScaleDegree.put(4, Notes.Fsharp);
                ScaleDegree.put(5, Notes.Gsharp);
                ScaleDegree.put(6, Notes.Asharp);
                ScaleDegree.put(7, Notes.Bsharp);
                ScaleDegree.put(8, Notes.Csharp);
                break;

            case "C#min":
                ScaleDegree.put(1, Notes.Csharp);
                ScaleDegree.put(2, Notes.Dsharp);
                ScaleDegree.put(3, Notes.E);
                ScaleDegree.put(4, Notes.Fsharp);
                ScaleDegree.put(5, Notes.Gsharp);
                ScaleDegree.put(6, Notes.A);
                ScaleDegree.put(7, Notes.B);
                ScaleDegree.put(8, Notes.Csharp);
                break;

            case "Dbmaj":
                ScaleDegree.put(1, Notes.Db);
                ScaleDegree.put(2, Notes.Eb);
                ScaleDegree.put(3, Notes.F);
                ScaleDegree.put(4, Notes.Gb);
                ScaleDegree.put(5, Notes.Ab);
                ScaleDegree.put(6, Notes.Bb);
                ScaleDegree.put(7, Notes.C);
                ScaleDegree.put(8, Notes.Db);
                break;

            case "Dbmin":
                ScaleDegree.put(1, Notes.Db);
                ScaleDegree.put(2, Notes.Eb);
                ScaleDegree.put(3, Notes.Fb);
                ScaleDegree.put(4, Notes.Gb);
                ScaleDegree.put(5, Notes.Ab);
                ScaleDegree.put(6, Notes.Bbb);
                ScaleDegree.put(7, Notes.Cb);
                ScaleDegree.put(8, Notes.Db);
                break;

            case "Dmaj":
                ScaleDegree.put(1, Notes.D);
                ScaleDegree.put(2, Notes.E);
                ScaleDegree.put(3, Notes.Fsharp);
                ScaleDegree.put(4, Notes.G);
                ScaleDegree.put(5, Notes.A);
                ScaleDegree.put(6, Notes.B);
                ScaleDegree.put(7, Notes.Csharp);
                ScaleDegree.put(8, Notes.D);
                break;

            case "Dmin":
                ScaleDegree.put(1, Notes.D);
                ScaleDegree.put(2, Notes.E);
                ScaleDegree.put(3, Notes.F);
                ScaleDegree.put(4, Notes.G);
                ScaleDegree.put(5, Notes.A);
                ScaleDegree.put(6, Notes.Bb);
                ScaleDegree.put(7, Notes.C);
                ScaleDegree.put(8, Notes.D);
                break;

            case "D#maj":
                ScaleDegree.put(1, Notes.Dsharp);
                ScaleDegree.put(2, Notes.Esharp);
                ScaleDegree.put(3, Notes.Fsharpsharp);
                ScaleDegree.put(4, Notes.Gsharp);
                ScaleDegree.put(5, Notes.Asharp);
                ScaleDegree.put(6, Notes.Bsharp);
                ScaleDegree.put(7, Notes.Csharpsharp);
                ScaleDegree.put(8, Notes.Dsharp);
                break;

            case "D#min":
                ScaleDegree.put(1, Notes.Dsharp);
                ScaleDegree.put(2, Notes.Esharp);
                ScaleDegree.put(3, Notes.Fsharp);
                ScaleDegree.put(4, Notes.Gsharp);
                ScaleDegree.put(5, Notes.Asharp);
                ScaleDegree.put(6, Notes.B);
                ScaleDegree.put(7, Notes.Csharp);
                ScaleDegree.put(8, Notes.Dsharp);
                break;

            case "Ebmaj":
                ScaleDegree.put(1, Notes.Eb);
                ScaleDegree.put(2, Notes.F);
                ScaleDegree.put(3, Notes.G);
                ScaleDegree.put(4, Notes.Ab);
                ScaleDegree.put(5, Notes.Bb);
                ScaleDegree.put(6, Notes.C);
                ScaleDegree.put(7, Notes.D);
                ScaleDegree.put(8, Notes.Eb);
                break;

            case "Ebmin":
                ScaleDegree.put(1, Notes.Eb);
                ScaleDegree.put(2, Notes.F);
                ScaleDegree.put(3, Notes.Gb);
                ScaleDegree.put(4, Notes.Ab);
                ScaleDegree.put(5, Notes.Bb);
                ScaleDegree.put(6, Notes.Cb);
                ScaleDegree.put(7, Notes.Db);
                ScaleDegree.put(8, Notes.Eb);
                break;

            case "Emaj":
                ScaleDegree.put(1, Notes.E);
                ScaleDegree.put(2, Notes.Fsharp);
                ScaleDegree.put(3, Notes.Gsharp);
                ScaleDegree.put(4, Notes.A);
                ScaleDegree.put(5, Notes.B);
                ScaleDegree.put(6, Notes.Csharp);
                ScaleDegree.put(7, Notes.Dsharp);
                ScaleDegree.put(8, Notes.E);
                break;

            case "Emin":
                ScaleDegree.put(1, Notes.E);
                ScaleDegree.put(2, Notes.Fsharp);
                ScaleDegree.put(3, Notes.G);
                ScaleDegree.put(4, Notes.A);
                ScaleDegree.put(5, Notes.B);
                ScaleDegree.put(6, Notes.C);
                ScaleDegree.put(7, Notes.D);
                ScaleDegree.put(8, Notes.E);
                break;

            case "Fmaj":
                ScaleDegree.put(1, Notes.F);
                ScaleDegree.put(2, Notes.G);
                ScaleDegree.put(3, Notes.A);
                ScaleDegree.put(4, Notes.Bb);
                ScaleDegree.put(5, Notes.C);
                ScaleDegree.put(6, Notes.D);
                ScaleDegree.put(7, Notes.E);
                ScaleDegree.put(8, Notes.F);
                break;

            case "Fmin":
                ScaleDegree.put(1, Notes.F);
                ScaleDegree.put(2, Notes.G);
                ScaleDegree.put(3, Notes.Ab);
                ScaleDegree.put(4, Notes.Bb);
                ScaleDegree.put(5, Notes.C);
                ScaleDegree.put(6, Notes.Db);
                ScaleDegree.put(7, Notes.Eb);
                ScaleDegree.put(8, Notes.F);
                break;

            case "F#maj":
                ScaleDegree.put(1, Notes.Fsharp);
                ScaleDegree.put(2, Notes.Gsharp);
                ScaleDegree.put(3, Notes.Asharp);
                ScaleDegree.put(4, Notes.B);
                ScaleDegree.put(5, Notes.Csharp);
                ScaleDegree.put(6, Notes.Dsharp);
                ScaleDegree.put(7, Notes.Esharp);
                ScaleDegree.put(8, Notes.Fsharp);
                break;

            case "F#min":
                ScaleDegree.put(1, Notes.Fsharp);
                ScaleDegree.put(2, Notes.Gsharp);
                ScaleDegree.put(3, Notes.A);
                ScaleDegree.put(4, Notes.B);
                ScaleDegree.put(5, Notes.Csharp);
                ScaleDegree.put(6, Notes.D);
                ScaleDegree.put(7, Notes.E);
                ScaleDegree.put(8, Notes.Fsharp);
                break;

            case "Gbmaj":
                ScaleDegree.put(1, Notes.Gb);
                ScaleDegree.put(2, Notes.Ab);
                ScaleDegree.put(3, Notes.Bb);
                ScaleDegree.put(4, Notes.Cb);
                ScaleDegree.put(5, Notes.Db);
                ScaleDegree.put(6, Notes.Eb);
                ScaleDegree.put(7, Notes.F);
                ScaleDegree.put(8, Notes.Gb);
                break;

            case "Gbmin":
                ScaleDegree.put(1, Notes.Gb);
                ScaleDegree.put(2, Notes.Ab);
                ScaleDegree.put(3, Notes.Bbb);
                ScaleDegree.put(4, Notes.Cb);
                ScaleDegree.put(5, Notes.Db);
                ScaleDegree.put(6, Notes.Ebb);
                ScaleDegree.put(7, Notes.Fb);
                ScaleDegree.put(8, Notes.Gb);
                break;

            case "Gmaj":
                ScaleDegree.put(1, Notes.G);
                ScaleDegree.put(2, Notes.A);
                ScaleDegree.put(3, Notes.B);
                ScaleDegree.put(4, Notes.C);
                ScaleDegree.put(5, Notes.D);
                ScaleDegree.put(6, Notes.E);
                ScaleDegree.put(7, Notes.Fsharp);
                ScaleDegree.put(8, Notes.G);
                break;

            case "Gmin":
                ScaleDegree.put(1, Notes.G);
                ScaleDegree.put(2, Notes.A);
                ScaleDegree.put(3, Notes.Bb);
                ScaleDegree.put(4, Notes.C);
                ScaleDegree.put(5, Notes.D);
                ScaleDegree.put(6, Notes.Eb);
                ScaleDegree.put(7, Notes.F);
                ScaleDegree.put(8, Notes.G);
                break;

            case "G#maj":
                ScaleDegree.put(1, Notes.Gsharp);
                ScaleDegree.put(2, Notes.Asharp);
                ScaleDegree.put(3, Notes.Bsharp);
                ScaleDegree.put(4, Notes.Csharp);
                ScaleDegree.put(5, Notes.Dsharp);
                ScaleDegree.put(6, Notes.Esharp);
                ScaleDegree.put(7, Notes.Fsharpsharp);
                ScaleDegree.put(8, Notes.Gsharp);
                break;

            case "G#min":
                ScaleDegree.put(1, Notes.Gsharp);
                ScaleDegree.put(2, Notes.Asharp);
                ScaleDegree.put(3, Notes.B);
                ScaleDegree.put(4, Notes.Csharp);
                ScaleDegree.put(5, Notes.Dsharp);
                ScaleDegree.put(6, Notes.E);
                ScaleDegree.put(7, Notes.Fsharp);
                ScaleDegree.put(8, Notes.Gsharp);
                break;

            case "Abmaj":
                ScaleDegree.put(1, Notes.Ab);
                ScaleDegree.put(2, Notes.Bb);
                ScaleDegree.put(3, Notes.C);
                ScaleDegree.put(4, Notes.Db);
                ScaleDegree.put(5, Notes.Eb);
                ScaleDegree.put(6, Notes.F);
                ScaleDegree.put(7, Notes.G);
                ScaleDegree.put(8, Notes.Ab);
                break;

            case "Abmin":
                ScaleDegree.put(1, Notes.Ab);
                ScaleDegree.put(2, Notes.Bb);
                ScaleDegree.put(3, Notes.Cb);
                ScaleDegree.put(4, Notes.Db);
                ScaleDegree.put(5, Notes.Eb);
                ScaleDegree.put(6, Notes.Fb);
                ScaleDegree.put(7, Notes.Gb);
                ScaleDegree.put(8, Notes.Ab);
                break;

            case "Amaj":
                ScaleDegree.put(1, Notes.A);
                ScaleDegree.put(2, Notes.B);
                ScaleDegree.put(3, Notes.Csharp);
                ScaleDegree.put(4, Notes.D);
                ScaleDegree.put(5, Notes.E);
                ScaleDegree.put(6, Notes.Fsharp);
                ScaleDegree.put(7, Notes.Gsharp);
                ScaleDegree.put(8, Notes.A);
                break;

            case "Amin":
                ScaleDegree.put(1, Notes.A);
                ScaleDegree.put(2, Notes.B);
                ScaleDegree.put(3, Notes.C);
                ScaleDegree.put(4, Notes.D);
                ScaleDegree.put(5, Notes.E);
                ScaleDegree.put(6, Notes.F);
                ScaleDegree.put(7, Notes.G);
                ScaleDegree.put(8, Notes.A);
                break;

            case "A#maj":
                ScaleDegree.put(1, Notes.Asharp);
                ScaleDegree.put(2, Notes.Bsharp);
                ScaleDegree.put(3, Notes.Csharpsharp);
                ScaleDegree.put(4, Notes.Dsharp);
                ScaleDegree.put(5, Notes.Esharp);
                ScaleDegree.put(6, Notes.Fsharpsharp);
                ScaleDegree.put(7, Notes.Gsharpsharp);
                ScaleDegree.put(8, Notes.Asharp);
                break;

            case "A#min":
                ScaleDegree.put(1, Notes.Asharp);
                ScaleDegree.put(2, Notes.Bsharp);
                ScaleDegree.put(3, Notes.Csharp);
                ScaleDegree.put(4, Notes.Dsharp);
                ScaleDegree.put(5, Notes.Esharp);
                ScaleDegree.put(6, Notes.Fsharp);
                ScaleDegree.put(7, Notes.Gsharp);
                ScaleDegree.put(8, Notes.Asharp);
                break;

            case "Bbmaj":
                ScaleDegree.put(1, Notes.Bb);
                ScaleDegree.put(2, Notes.C);
                ScaleDegree.put(3, Notes.D);
                ScaleDegree.put(4, Notes.Eb);
                ScaleDegree.put(5, Notes.F);
                ScaleDegree.put(6, Notes.G);
                ScaleDegree.put(7, Notes.A);
                ScaleDegree.put(8, Notes.Bb);
                break;

            case "Bbmin":
                ScaleDegree.put(1, Notes.Bb);
                ScaleDegree.put(2, Notes.C);
                ScaleDegree.put(3, Notes.Db);
                ScaleDegree.put(4, Notes.Eb);
                ScaleDegree.put(5, Notes.F);
                ScaleDegree.put(6, Notes.Gb);
                ScaleDegree.put(7, Notes.Ab);
                ScaleDegree.put(8, Notes.Bb);
                break;

            case "Bmaj":
                ScaleDegree.put(1, Notes.B);
                ScaleDegree.put(2, Notes.Csharp);
                ScaleDegree.put(3, Notes.Dsharp);
                ScaleDegree.put(4, Notes.E);
                ScaleDegree.put(5, Notes.Fsharp);
                ScaleDegree.put(6, Notes.Gsharp);
                ScaleDegree.put(7, Notes.Asharp);
                ScaleDegree.put(8, Notes.B);
                break;

            case "Bmin":
                ScaleDegree.put(1, Notes.B);
                ScaleDegree.put(2, Notes.Csharp);
                ScaleDegree.put(3, Notes.D);
                ScaleDegree.put(4, Notes.E);
                ScaleDegree.put(5, Notes.Fsharp);
                ScaleDegree.put(6, Notes.G);
                ScaleDegree.put(7, Notes.A);
                ScaleDegree.put(8, Notes.B);
                break;

            default:
                throw new Exception("Enter a Valid Key.");
        }

    }

    //get the key
    public String getKey() {
        return this.key;
    }

    //get the corresponding note from a scale degree
    public Notes degreeToNote(int degree) {
        return ScaleDegree.get(degree);
    }
}
