package dev.mayank.aop.example.problem.model;

/**
 * Represents a song.
 */
public record Song(String name, String artist) {

    /**
     * Compact constructor for a new song.
     *
     * @param name   The name of the song.
     * @param artist The artist of the song.
     */
    public Song {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name of the song cannot be null or blank");
        }
        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException("Artist of the song cannot be null or blank");
        }
    }
}
