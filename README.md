# MusicPlayer
A lightweight all-java local music player!

## Installation

### Prerequisites
- [OpenJDK 25](https://jdk.java.net/25/) <br> (recommended, will work for Java 17 and up)

### Precompiled Steps
1. Download the latest release [here](https://github.com/Mai-19/comp-2800-project/releases/latest)
2. Run with `java -jar MusicPlayer.jar` OR double click OR right click and select run with java

### Build Steps
1. **Clone the repository**
```bash
git clone https://github.com/Mai-19/comp-2800-project.git
cd comp-2800-project
```
2. **Compile the source**
All dependencies are included in `lib/`. Run the following from the project root:
```bash
javac -cp "lib/*" -sourcepath src -d out src/app/App.java
```
3. **Package into JAR**
```bash
jar cfe MusicPlayer.jar app.App -C out . -C res .
```
4. **Run**
```bash
java -jar MusicPlayer.jar
```

## Usage

Add a directory in the settings and press refresh!

![Demo](.github/assets/demo.gif)

## License
[LGPL-3.0](./LICENSE)
