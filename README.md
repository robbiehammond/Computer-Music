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
  -  Note: As stated earlier, it is required to have LilyPond to view the sheet music for the melodies.
  However, LilyPond is not at all necessary to generate and play back songs.
  -  Another Note: To replay a song, copy the first line of text that is displayed in the output
  into the Input Song, making sure to not copy the second line of Extra Info. However, the extra info is still important,
  as it contains the key signature of the song when it was generated. If you attempt to replay a song with the wrong
  key signature inputted in the Key field, it will likely sound bad and you may encounter errors.
  -  Final Note: Even if a melody was generated with the drums and/or chords off, they can still be turned 
  on for the same song at a later point. Vice Versa also applies. The drums and chords are generated and put into the 
  JFugue text after the melody itself was already generated, so they are toggleable at any point.
