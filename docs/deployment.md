# Deployment Document

**Project:** MusicPlayer  
**Course:** COMP 2800 – Software Development  
**University of Windsor, School of Computer Science**  
**Term:** 2026W

---

## 1. System Requirements

MusicPlayer is a cross-platform Java desktop application. It runs on any operating system
that supports Java 17 or higher with no additional installation or configuration required.

| Requirement | Minimum | Recommended |
|---|---|---|
| Java Version | Java 17 | OpenJDK 25 |
| Operating System | Windows 10, macOS 11, or Ubuntu 20.04 | Any current version |
| RAM | 256 MB available | 512 MB or more |
| Disk Space | 50 MB for the application | Additional space for music files |
| Internet Connection | Not required | Not required |

The application stores its database in the user's home directory under
`JavaMusicPlayer-Data/`. No administrator privileges are required.

---

## 2. Installation

### 2.1 Option A — Precompiled JAR (Recommended)

This is the simplest method and requires no build tools beyond Java itself.

**Step 1 — Verify Java is installed**

Open a terminal and run:
```bash
java -version
```
The output should show version 17 or higher. If Java is not installed, download and install
[OpenJDK 25](https://jdk.java.net/25/) for your operating system.

**Step 2 — Download the latest release**

Download `MusicPlayer.jar` from the
[latest release](https://github.com/Mai-19/comp-2800-project/releases/latest)
on the GitHub repository.

**Step 3 — Run the application**

Open a terminal in the folder containing the JAR and run:
```bash
java -jar MusicPlayer.jar
```

Alternatively, you can double-click the JAR file directly,
or right-click and select "Open with Java".

---

### 2.2 Option B — Build from Source

This method requires Git and Java 17+ to be installed.

**Step 1 — Clone the repository**
```bash
git clone https://github.com/Mai-19/java-music-player.git
cd java-music-player
```

**Step 2 — Run the build script**

On Linux or macOS:
```bash
chmod +x build.sh
./build.sh
```

On Windows:
```bat
build.bat
```

The script compiles the source code, bundles all dependencies from `lib/`, and produces
`MusicPlayer.jar` in the project root directory.

**Step 3 — Run the application**
```bash
java -jar MusicPlayer.jar
```

---

## 3. Database Setup

No manual database setup is required. On first launch the application automatically:

1. Creates the folder `~/JavaMusicPlayer-Data/` in the user's home directory
2. Creates the SQLite database file `MusicPlayerDB.sqlite` inside that folder
3. Runs `CREATE TABLE IF NOT EXISTS` for all tables

The database stores the music library, playlists, play history, and application settings.
It persists between sessions and does not need to be recreated unless deleted manually.

**Database location by operating system:**

| OS | Path |
|---|---|
| Windows | `C:\Users\<username>\JavaMusicPlayer-Data\MusicPlayerDB.sqlite` |
| macOS | `/Users/<username>/JavaMusicPlayer-Data/MusicPlayerDB.sqlite` |
| Linux | `/home/<username>/JavaMusicPlayer-Data/MusicPlayerDB.sqlite` |

For the full database schema see [`docs/schema.sql`](./schema.sql).

---

## 4. First Launch

When the application is launched for the first time the song table will be empty because
no music directories have been added yet.

**Step 1 — Open Settings**  
Click the settings icon in the top right corner of the window.

**Step 2 — Add a music directory**  
Click the **+** button next to the Directories header. A file chooser dialog will open.
Navigate to and select a folder containing your music files. The application supports
MP3, FLAC, and WAV files.

**Step 3 — Return to the player**  
Click the back arrow to return to the main player. Your songs will now appear in the
All Songs tab.

---

## 6. Known Limitations

| Issue | Description | Workaround |
|---|---|---|
| 24-bit audio files | Files with a bit depth greater than 16 are skipped during scanning due to a limitation of the audio playback library | Use 16-bit versions of affected files |
| Opening a second playlist | If a playlist is opened while another is already open the queue is automatically replaced with the new playlist's songs even without interacting with it | Close the current playlist using the back arrow before opening another |