# User Requirements Document

**Project:** MusicPlayer  
**Course:** COMP 2800 – Software Development  
**University of Windsor, School of Computer Science**  
**Term:** 2026W

---

## 1. Introduction

This document defines the user requirements for MusicPlayer, a free and open source
local music player built in Java. It describes who the application is intended for,
what problems it addresses, the use cases it must support, and the functional and
non-functional requirements that define its behaviour.

---

## 2. Problem Statement

The dominant music streaming platforms have introduced a set of systemic problems that
affect both listeners and artists.

**Artist compensation** is calculated using a pro rata model, where a platform's total
revenue is pooled and distributed among rights holders based on their share of total
streams. This model heavily favours mainstream artists while smaller artists with
dedicated fanbases receive negligible compensation regardless of listener loyalty. This
stands in contrast to traditional music sales such as physical media, where a purchase
directly benefits the artist. Research and reporting by PBS NewsHour and others has
documented widespread musician pushback against this payment structure.

**Algorithmic manipulation** has led to a growing distrust of streaming platform
recommendations. AI-generated music has been found flooding platform libraries and
being inserted into user-generated playlists such as Spotify's Discover Weekly without
listener knowledge. This artificially inflates total stream counts, diluting artist
royalty percentages while increasing platform revenue. Artists including King Gizzard
and the Lizard Wizard have publicly quit Spotify in protest of AI, and they were quickly
replaced by AI-generated doppelgangers of their work and brand.

**Audio quality** remains a largely unresolved issue. While several platforms have
marketed lossless or high-fidelity tiers, independent testing has revealed that these
offerings frequently do not deliver true lossless audio and are primarily marketing
exercises. Local FLAC files remain the most reliable way for a listener to guarantee
they are hearing music at the quality the artist intended.

**User hostility** is a structural feature of the freemium model. Free tier interfaces
are deliberately degraded to pressure users into purchasing subscriptions, removing
features that are considered basic functionality in any other context such as the
ability to choose which song to play.

MusicPlayer addresses all of these concerns by providing a DRM-free, FOSS alternative
that plays locally owned music files. Listeners who purchase music directly from artists
or platforms like Bandcamp can play their files at full quality, support artists fairly,
and do so using software that is transparent, freely available, and licensed under
GPL v3.

---

## 3. Target Users

MusicPlayer is designed for two overlapping user groups:

**The ethically motivated listener** owns a local music library built through direct
purchases, downloads, or rips from physical media. They are aware of the exploitative
nature of streaming platforms and prefer to support artists through direct transactions.
They value software that is open source, privacy respecting, and free of algorithmic
interference in their listening experience.

**The audiophile listener** prioritises audio quality above convenience. They store
their library in lossless formats such as FLAC and WAV and are unwilling to accept the
lossy compression that streaming platforms deliver even on their highest quality tiers.
They want a player that supports these formats natively without transcoding or quality
loss.

These two groups frequently overlap. MusicPlayer is designed to serve both without
compromise — it is simultaneously ethically sound, accessible as free and open source
software, and capable of playing higher quality audio than any major streaming platform
currently offers.

---

## 4. Use Cases

### UC-01 — Add a Music Directory

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | The application is open and the Settings panel is visible |
| **Steps** | 1. Click the + button next to the Directories header. 2. Navigate to and select a folder containing music files. 3. Click the Refresh button to scan the directory |
| **Postcondition** | All supported audio files in the selected folder and its subfolders are added to the library |

### UC-02 — Remove a Music Directory

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one directory is registered in Settings |
| **Steps** | 1. Open Settings. 2. Click the trash icon next to the directory to remove |
| **Postcondition** | The directory and all songs scanned from it are removed from the library and any playlists they appeared in |

### UC-03 — Refresh the Library

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one directory is registered |
| **Steps** | 1. Open Settings. 2. Click the Refresh button |
| **Postcondition** | All registered directories are rescanned and the song list is updated to reflect any added or changed files |

### UC-04 — Play a Song

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one song is visible in the song table |
| **Steps** | 1. Double-click any row in the song table |
| **Postcondition** | The selected song begins playing. The bottom bar updates to show the song title, artist, album, artwork, and total length |

### UC-05 — Pause and Resume Playback

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A song is currently playing |
| **Steps** | 1. Click the Play/Pause button in the bottom bar |
| **Postcondition** | Playback pauses. Clicking again resumes from the same position |

### UC-06 — Seek to a Position

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A song is currently loaded |
| **Steps** | 1. Click or drag the progress bar to the desired position |
| **Postcondition** | Playback jumps to the selected position. The current time label updates accordingly |

### UC-07 — Skip Forward or Backward

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A song is currently playing |
| **Steps** | 1. Click the Forward button to skip ahead 5 seconds, or the Rewind button to go back 5 seconds |
| **Postcondition** | Playback position adjusts by 5 seconds in the selected direction. If near the end of the song, Forward skips to the next track. If near the start, Rewind returns to position 0 |

### UC-08 — Skip to Next or Previous Song

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A song is currently playing and the queue contains more than one song |
| **Steps** | 1. Click the Next or Previous button in the bottom bar |
| **Postcondition** | Playback advances to the next song or returns to the previous one. If more than 5 seconds of the current song have played, Previous restarts the current song instead |

### UC-09 — Adjust Volume

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | The application is open |
| **Steps** | 1. Drag the volume slider in the bottom bar left to decrease or right to increase volume |
| **Postcondition** | Playback volume changes in real time |

### UC-10 — Mute and Unmute

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | The application is open |
| **Steps** | 1. Click the volume button in the bottom bar |
| **Postcondition** | Audio is muted and the button icon changes to indicate muted state. Clicking again restores the previous volume level |

### UC-11 — Shuffle the Queue

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | The song table contains at least two songs |
| **Steps** | 1. Click the Shuffle toggle button in the bottom bar |
| **Postcondition** | The queue is randomly reordered. Clicking again restores the original alphabetical order |

### UC-12 — Repeat a Song

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A song is currently playing |
| **Steps** | 1. Click the Repeat toggle button in the bottom bar |
| **Postcondition** | The current song loops indefinitely. Clicking again returns to normal sequential playback |

### UC-13 — Search the Library

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | The song table contains at least one song |
| **Steps** | 1. Click the search bar at the top of the window. 2. Type any search term |
| **Postcondition** | The song table filters in real time to show only rows matching the search term across all columns. Clearing the field restores the full list |

### UC-14 — Create a Playlist

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | The Playlists tab is visible |
| **Steps** | 1. Click the + icon in the Playlists tab. 2. Enter a name in the dialog. 3. Click OK |
| **Postcondition** | A new empty playlist is created and appears in the playlists list |

### UC-15 — Add Songs to a Playlist

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one playlist exists and at least one song is visible in the table |
| **Steps** | 1. Select one or more songs in the table. 2. Right-click the selection. 3. Hover over Add to playlist. 4. Click the target playlist name |
| **Postcondition** | The selected songs are added to the chosen playlist |

### UC-16 — Open and Browse a Playlist

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one playlist exists |
| **Steps** | 1. Click the Playlists tab. 2. Click any playlist row |
| **Postcondition** | The song table switches to showing only the songs in that playlist. The playback queue is replaced with the playlist contents |

### UC-17 — Remove Songs from a Playlist

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A playlist is currently open |
| **Steps** | 1. Select one or more songs. 2. Right-click the selection. 3. Click Remove from playlist |
| **Postcondition** | The selected songs are removed from the playlist. They remain in the library |

### UC-18 — Delete a Playlist

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one playlist exists |
| **Steps** | 1. In the Playlists tab list view, click the trash icon next to the target playlist. 2. Confirm the deletion in the dialog |
| **Postcondition** | The playlist is permanently deleted |

### UC-19 — View Synced Lyrics

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | A song is playing and a matching LRC file exists in the same directory as the audio file |
| **Steps** | 1. Click the Lyrics tab |
| **Postcondition** | Lyrics are displayed and scroll automatically to keep the active line centred. The active line is highlighted at a larger size |

### UC-20 — Download Weekly Stats Image

| Field | Description |
|---|---|
| **Actor** | User |
| **Precondition** | At least one song has been played during the current week |
| **Steps** | 1. Open Settings. 2. Click Download Stats. 3. Choose a save location and filename. 4. Click Save |
| **Postcondition** | A PNG image is saved showing the top 5 most played songs of the current week |

---

## 5. Functional Requirements

| ID | Requirement |
|---|---|
| FR-01 | The system shall support playback of MP3, FLAC, and WAV audio files |
| FR-02 | The system shall support lossless FLAC files up to 16-bit depth |
| FR-03 | The system shall scan registered directories recursively for supported audio files |
| FR-04 | The system shall read and display song metadata including title, artist, album, and release year from embedded audio tags |
| FR-05 | The system shall persist the music library, playlists, play history, and directory list across sessions using a local SQLite database |
| FR-06 | The system shall allow the user to add and remove music directories |
| FR-07 | The system shall allow the user to manually trigger a library rescan |
| FR-08 | The system shall support play, pause, resume, next, previous, forward 5s, and rewind 5s playback controls |
| FR-09 | The system shall allow the user to seek to any position in the current song using a progress slider |
| FR-10 | The system shall display the current playback position and total song length in real time |
| FR-11 | The system shall allow the user to adjust volume using a slider and mute using a button |
| FR-12 | The system shall support shuffle mode that randomly reorders the playback queue |
| FR-13 | The system shall support repeat mode that loops the current song |
| FR-14 | The system shall filter the song table in real time based on text entered in the search bar |
| FR-15 | The system shall allow the user to create, open, and delete playlists |
| FR-16 | The system shall allow the user to add songs to and remove songs from playlists |
| FR-17 | The system shall display synced lyrics from LRC files when available, scrolling automatically to follow playback position |
| FR-18 | The system shall display unsynced lyrics from LRC files when no timestamps are present |
| FR-19 | The system shall track play counts per song and reset them automatically on a weekly basis |
| FR-20 | The system shall generate and export a PNG image showing the top 5 most played songs of the current week |
| FR-21 | The system shall pre-cache the next song in the queue in a background thread to minimise the gap between tracks |
| FR-22 | The system shall display album artwork embedded in the audio file in the bottom bar during playback |

---

## 6. Non-Functional Requirements

| ID | Requirement |
|---|---|
| NFR-01 | The application shall run on any operating system that supports Java 17 or higher including Windows, macOS, and Linux |
| NFR-02 | The application shall require no internet connection for any core functionality |
| NFR-03 | The application shall require no administrator privileges to install or run |
| NFR-04 | The application shall create its database automatically on first launch with no manual setup required |
| NFR-05 | The progress bar shall update at a minimum of 4 times per second during playback |
| NFR-06 | The search filter shall apply within one timer tick of the user's keystroke with no perceptible delay |
| NFR-07 | The application shall be free and open source software licensed under GPL v3 |
| NFR-08 | The application shall not transmit any user data or listening history to any external server |
| NFR-09 | The user interface shall remain responsive during directory scanning by performing scan operations on a background thread |
| NFR-10 | The application shall be distributable as a single executable JAR file requiring no additional installation steps beyond having Java installed |

---

## 7. Constraints

| Constraint | Description |
|---|---|
| 16-bit audio only | Files with a bit depth greater than 16 bits are not supported by the audio playback library and are skipped during scanning |
| Local files only | The application plays locally stored files only. Streaming from remote sources is intentionally out of scope |
| LRC lyrics only | Lyrics are sourced exclusively from local LRC files. No lyrics API or online source is used, in keeping with the offline and privacy-first design |
| Single user | The application is designed for a single local user. There is no account system, sync, or multi-user support |

---

## 8. References

- PBS NewsHour — *Musicians push back on dwindling payments from streaming services*
- TechRadar — *AI music is flooding Spotify, and subscribers are furious*
- The Conversation — *King Gizzard and the Lizard Wizard quit Spotify in protest, only for an AI doppelganger to step in*
- Wikipedia — [LRC file format](https://en.wikipedia.org/wiki/LRC_(file_format))
- GNU GPL v3 — [https://www.gnu.org/licenses/gpl-3.0.en.html](https://www.gnu.org/licenses/gpl-3.0.en.html)