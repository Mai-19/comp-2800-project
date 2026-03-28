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

## 5. Virtual Machine Deployment
 
### 5.1 Background and Challenges
 
The original course requirement asked for the application to be deployed on the
University of Windsor SCS MyWeb server so that graders could run it without any local
installation. However, MyWeb is a shared web hosting environment designed for PHP and
MySQL web applications. After investigation it was determined that MyWeb cannot host or
execute a Java Swing desktop GUI application, making it incompatible with this project.
We explored alternative hosting environments like AWS and AZURE, but ran into minor road
blocks that prevented us from deploying on these platforms, and ultimately decided to
host our own virtual machine.
 
### 5.2 Solution Overview
 
A browser-accessible virtual machine was set up on a personal home server and exposed
publicly via a custom domain. The VM runs a full Ubuntu KDE desktop environment inside
a Docker container, with OpenJDK 25 pre-installed and the application JAR placed on the
desktop for immediate use. Graders can access a live running instance of the application
directly in their browser with no installation, configuration, or credentials required.
 
**Access URL:** [https://mayar.dev](https://mayar.dev)
 
### 5.3 Infrastructure Stack
 
| Component | Technology | Purpose |
|---|---|---|
| Container runtime | Docker | Hosts the VM container |
| Desktop environment | LinuxServer Webtop (Ubuntu KDE) | Full graphical Linux desktop in a container |
| Remote display | KasmVNC (built into Webtop) | Streams the desktop to the browser over HTTPS |
| Java runtime | OpenJDK 25 (installed via Docker mod) | Runs the application JAR |
| Reverse proxy | Caddy | Handles HTTPS termination and routes traffic to the container |
| DNS and domain | Cloudflare + mayar.dev | Provides a stable public URL via router port forwarding |
 
### 5.4 Docker Compose Configuration
 
The VM container is defined with the following Docker Compose configuration:
 
```yaml
webtop:
  image: lscr.io/linuxserver/webtop:ubuntu-kde
  container_name: webtop
  security_opt:
    - seccomp:unconfined
  environment:
    - PUID=1000
    - PGID=1000
    - TZ=America/Toronto
    - HARDEN_DESKTOP=true
    - CUSTOM_USER=cs2800gr3
    - TITLE=ubuntu
    - DOCKER_MODS=linuxserver/mods:universal-package-install
    - INSTALL_PACKAGES=openjdk-25-jdk
  volumes:
    - /mnt/storage/webtop:/config
  ports:
    - "3000:3000"
    - "3001:3001"
  hostname: cs2800gr3
  shm_size: "1gb"
  restart: unless-stopped
  deploy:
    resources:
      limits:
        cpus: '4'
        memory: 6G
      reservations:
        cpus: '2'
        memory: 2G
```
 
Key configuration decisions:
 
- `HARDEN_DESKTOP=true` locks down the desktop environment so that casual visitors who
  stumble onto the URL cannot modify the system
- `INSTALL_PACKAGES=openjdk-25-jdk` installs Java automatically at container startup
  via the LinuxServer Docker mods system, requiring no manual Java installation
- `restart: unless-stopped` keeps the container running across server reboots for the
  duration of the grading period
 
### 5.5 Accessing the Application
 
1. Navigate to [https://mayar.dev](https://mayar.dev) in any modern browser
2. The Ubuntu KDE desktop will load directly — no login is required
3. Double-click the **MusicPlayer** shortcut on the desktop to launch the application
4. The application JAR is also available at `/home/cs2800gr3/java-music-player/MusicPlayer.jar`
   if preferred to launch from the terminal
 
A sample set of music files are pre-loaded in the VM for testing purposes.
The music library is pre-configured so the application loads with songs visible
immediately on first open.
 
---

## 6. Known Limitations

| Issue | Description | Workaround |
|---|---|---|
| 24-bit audio files | Files with a bit depth greater than 16 are skipped during scanning due to a limitation of the audio playback library | Use 16-bit versions of affected files |
| Opening a second playlist | If a playlist is opened while another is already open the queue is automatically replaced with the new playlist's songs even without interacting with it | Close the current playlist using the back arrow before opening another |