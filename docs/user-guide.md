# User Guide

**Project:** MusicPlayer  
**Course:** COMP 2800 – Software Development  
**University of Windsor, School of Computer Science**  
**Term:** 2026W

---

## 1. Overview

MusicPlayer is a lightweight local music player for MP3, FLAC, and WAV files. It runs
entirely offline and requires no account or internet connection. This guide covers all
features available in the current release.

---

## 2. Getting Started

### 2.1 Adding a Music Directory

On first launch the song list will be empty. To populate your library:

1. Click the **settings icon** (sliders) in the top right corner
2. Click the **+** button next to the Directories header
3. Select a folder containing your music files in the file chooser dialog
4. Click the **back arrow** to return to the player — your songs will now appear in the All Songs tab

You can add as many directories as you like. The application scans each folder
recursively, so subfolders are included automatically.

### 2.2 Removing a Music Directory

In the Settings panel, click the **trash icon** next to any directory to remove it.
Removing a directory also removes all of its songs from the library and any playlists
they appeared in.

---

## 3. Playing Music

### 3.1 Starting Playback

Double-click any song in the song table to begin playing it. The bottom bar will update
to show the song title, album, artist, and album artwork if available.

### 3.2 Playback Controls

All playback controls are located in the bottom bar:

| Control | Description |
|---|---|
| **Shuffle** | Randomly reorders the queue. Click again to restore the original order |
| **Previous** | Goes back to the previous song. If more than 5 seconds have played, restarts the current song instead |
| **Rewind** | Skips back 5 seconds. If within the first 5 seconds, jumps to the start |
| **Play / Pause** | Toggles playback |
| **Forward** | Skips ahead 5 seconds. If within the last 5 seconds, skips to the next song |
| **Next** | Skips to the next song in the queue |
| **Repeat** | Loops the current song indefinitely. Click again to turn off |
| **Volume slider** | Drag left or right to adjust the volume |
| **Mute button** | Mutes the audio. Click again to restore the previous volume level |

### 3.3 Seeking

Click or drag the **progress bar** at the bottom of the window to jump to any position
in the current song. Playback pauses while dragging and resumes when you release.

---

## 4. Search

The search bar at the top of the window filters the song table in real time as you type.
The search is case-insensitive and matches against all columns including title, artist,
album, and year. Clearing the search field restores the full list.

Search works in all views including inside playlists.

---

## 5. Playlists

### 5.1 Creating a Playlist

1. Click the **Playlists** tab on the left side panel
2. Click the **+** icon at the top right of the playlists list
3. Enter a name in the dialog that appears and click OK

### 5.2 Adding Songs to a Playlist

1. In any song view, right-click one or more selected songs
2. Hover over **Add to playlist**
3. Click the name of the playlist to add the selected songs to

### 5.3 Opening a Playlist

Click any playlist row in the Playlists tab to open it. The song table will switch to
showing only the songs in that playlist. The search bar continues to work normally
while a playlist is open.

> **Note:** Opening a playlist automatically replaces the current playback queue with
> that playlist's songs.

### 5.4 Removing Songs from a Playlist

While a playlist is open, right-click one or more selected songs and choose
**Remove from playlist**. This removes the songs from the playlist only — they remain
in your library.

### 5.5 Deleting a Playlist

In the Playlists tab list view, click the **trash icon** next to any playlist to delete it.
A confirmation dialog will appear before the playlist is removed.

---

## 6. Lyrics

MusicPlayer can display lyrics for any song that has an accompanying LRC file.

### 6.1 Setting Up Lyrics

Place a `.lrc` file in the same folder as the audio file. The LRC file must have the
exact same name as the audio file with the extension changed to `.lrc`:

```
Beach Life-in-Death.flac  -->  Beach Life-in-Death.lrc
```

### 6.2 Synced vs Unsynced Lyrics

The application supports two LRC formats:

- **Synced LRC** — contains timestamps like `[03:21.50]` before each line. The active
  line is highlighted and the panel scrolls automatically to follow the song position
- **Unsynced LRC** — plain text with no timestamps. All lines are displayed at once
  with no automatic scrolling

If no LRC file is found for the current song the Lyrics tab displays "No lyrics available".

For more information on the LRC format see the
[LRC Wikipedia article](https://en.wikipedia.org/wiki/LRC_(file_format)).

---

## 7. Weekly Stats

MusicPlayer tracks how many times each song has been played and generates a shareable
stats image showing your top 5 most played songs for the current week.

### 7.1 Downloading the Stats Image

1. Open **Settings**
2. Click the **Download Stats** button at the bottom of the panel
3. Choose a save location and filename in the file chooser dialog
4. Click Save — the image will be exported as a PNG file

### 7.2 Automatic Weekly Reset

Play counts reset automatically at the start of each new week. The reset date is tracked
internally and requires no action from the user.

---

## 8. Settings Reference

| Control | Description |
|---|---|
| **Back arrow** | Returns to the main player view |
| **Refresh icon** | Rescans all registered directories and updates the library |
| **+ (Directories)** | Opens a file chooser to add a new music directory |
| **Trash icon (directory row)** | Removes that directory and all its songs from the library |
| **Download Stats** | Exports the weekly top 5 stats as a PNG image |

---

## 9. Supported File Formats

| Format | Notes |
|---|---|
| MP3 | Fully supported |
| FLAC | Fully supported. **24-bit FLAC files are not supported and will be skipped during scanning** |
| WAV | Fully supported |

Lyrics are loaded from `.lrc` files placed alongside the audio file. No other lyric
formats are currently supported.