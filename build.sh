#!/bin/bash

# compile
javac -cp "lib/*" -sourcepath src -d bin src/app/MusicPlayer.java

# extract dependencies
mkdir -p fatjar
for jar in lib/*.jar; do
    jar xf "$jar" -C fatjar 2>/dev/null || unzip -qo "$jar" -d fatjar
done

# merge SPI files
mkdir -p fatjar/META-INF/services

echo "org.jflac.sound.spi.FlacAudioFileReader
javazoom.spi.mpeg.sampled.file.MpegAudioFileReader" > fatjar/META-INF/services/javax.sound.sampled.spi.AudioFileReader

echo "org.jflac.sound.spi.FlacFormatConversionProvider
javazoom.spi.mpeg.sampled.convert.MpegFormatConversionProvider" > fatjar/META-INF/services/javax.sound.sampled.spi.FormatConversionProvider

# package
jar cfe MusicPlayer.jar app.MusicPlayer -C bin . -C res . -C fatjar .

# cleanup
rm -rf fatjar
echo "Done! MusicPlayer.jar is ready."