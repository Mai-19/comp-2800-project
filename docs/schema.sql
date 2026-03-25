-- MusicPlayer Database Schema
-- SQLite database auto-created at ~/JavaMusicPlayer-Data/MusicPlayerDB.sqlite
-- This file is for documentation purposes only
-- The database is created automatically by DatabaseManager.java on first launch

-- Foreign key enforcement is enabled at runtime via:
-- PRAGMA foreign_keys = ON;


-- Stores the absolute paths of music folders registered by the user
-- Deleting a directory cascades to SONGS
CREATE TABLE IF NOT EXISTS DIRECTORIES (
    path TEXT PRIMARY KEY
);


-- Stores every scanned song and its metadata
-- Linked to the directory it was scanned from
-- Deleting a directory cascades here via directory_path
-- Deleting a song cascades to PLAYLIST_SONGS and STATS
CREATE TABLE IF NOT EXISTS SONGS (
    song_id        INTEGER PRIMARY KEY AUTOINCREMENT,
    title          TEXT,
    artist         TEXT,
    album          TEXT,
    release_year   TEXT,
    seconds        INTEGER,
    length         TEXT,
    path           TEXT NOT NULL UNIQUE,
    directory_path TEXT NOT NULL,
    last_modified  INTEGER,
    FOREIGN KEY (directory_path) REFERENCES DIRECTORIES(path) ON DELETE CASCADE
);


-- Tracks play count and last played time per song
-- One row per song, created on first play
-- Deleting a song cascades here
CREATE TABLE IF NOT EXISTS STATS (
    song_id    INTEGER PRIMARY KEY,
    play_count INTEGER NOT NULL DEFAULT 0,
    last_played TEXT,
    FOREIGN KEY (song_id) REFERENCES SONGS(song_id) ON DELETE CASCADE
);


-- Key-value store for application-level settings
-- Currently used to store the last weekly stats reset date under the key "last_reset"
CREATE TABLE IF NOT EXISTS META (
    key   TEXT PRIMARY KEY,
    value TEXT
);


-- Stores user-created playlists
-- Name must be unique
-- Deleting a playlist cascades to PLAYLIST_SONGS
CREATE TABLE IF NOT EXISTS PLAYLISTS (
    playlist_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT NOT NULL UNIQUE
);


-- Junction table linking songs to playlists
-- Composite primary key prevents a song appearing in the same playlist twice
-- Cascades on both sides so removing either a song or a playlist cleans this up
CREATE TABLE IF NOT EXISTS PLAYLIST_SONGS (
    playlist_id INTEGER,
    song_id     INTEGER,
    PRIMARY KEY (playlist_id, song_id),
    FOREIGN KEY (playlist_id) REFERENCES PLAYLISTS(playlist_id) ON DELETE CASCADE,
    FOREIGN KEY (song_id)     REFERENCES SONGS(song_id)     ON DELETE CASCADE
);
