package com.zs.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
        dictionary.insert("apple" ,"APPLE");
    }

    @Test
    void insert() {

        assertEquals("APPLE",dictionary.meaning("apple"));
    }

    @Test
    void search() {
        assertEquals(true,dictionary.search("apple"));
        assertEquals(false, dictionary.search("dog"));
    }

    @Test
    void meaning() {
        assertEquals("APPLE",dictionary.meaning("apple"));
    }

    @Test
    void correctWord() {
        ArrayList<String> correct = new ArrayList<String>();
        correct.add("apple");

        assertEquals(correct,dictionary.correctWord("epple"));
    }

    @Test
    void matchWord() {
        ArrayList<String> word = new ArrayList<String>();

        dictionary.insert("application", "APPLICATION");

        word.add("apple");
        word.add("application");

        assertEquals(word,dictionary.matchWord("app"));

    }
}