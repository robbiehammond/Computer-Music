# Computer-Music
Uses JFugue instruments and notation to generate, play back, and then transcribe a completely computer-generated song.
Only needs Java installed to run and generate music you can listen to, but also requires having LilyPond installed 
(can be found at https://lilypond.org/) to view the transcribed melodies.

## Some Songs Generated
- A couple Examples 
  - https://youtu.be/4PLGNAwlTfk

## How to Use
- Clone the repo.
- Go to the directory in terminal.
- run `java -cp JFugue/jfugue-5.0.9.jar; Main` and then follow the GUI prompts.
- If you wish to generate a new song, choose settings from the Drums On, Chords On, Key, and Major/Minor boxes,
and then fill in a number of measures in integer form. The characteristics of the generated song will be based on these choices.
- If you wish to replay a melody, place your JFugue text (which is the first line of text in the output after a song was generated) in 
the Input Song field and make sure you have the key field set to the same note that the song was generated in, in addition to the Major/Minor field set to 
the same scale that the song was generated in. This information is displayed in the second line of the output once a song is generated, following
the text "Extra Info." If these settings do not match the settings displayed when the song was first generated, the result will likely sound bad and
you may encounter problems.
  -  Make sure to not copy the second line containing Extra Info into the Input Song field. Even so, the extra info is still important,
  because as stated earlier, playing a song with the wrong Key and/or Major/Minor settings will not produce the desired result.
-  As discussed above, it is required to have LilyPond to view the sheet music for the melodies.
  However, LilyPond is not at all necessary to generate and play back songs.
- Even if a melody was generated with the drums and/or chords off, they can still be turned 
  on for the same song at a later point. Vice Versa also applies. The drums and chords are generated and put into the 
  JFugue text after the melody itself was already generated, so they are toggleable at any point.
