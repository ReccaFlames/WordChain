package com.example.wordchain;

import com.example.wordchain.WordChainFinder.WordChainFinderImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayDeque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordChainFinderTest {

    private WordChainFinderImpl wordChainFinder;
    private ArrayDeque<String> expected;

    @Before
    public void setUp()
    {
        wordChainFinder = new WordChainFinderImpl();
        expected = new ArrayDeque<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void NumberAsStartWordShouldReturnException() {
        wordChainFinder.findWordChain("123", "cog");
    }

    @Test(expected = IllegalArgumentException.class)
    public void NumberAsEndWordShouldReturnException() {
        wordChainFinder.findWordChain("cat", "123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void NumbersAsInputShouldReturnException() {
        wordChainFinder.findWordChain("231", "123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullAsStartWordShouldReturnException() {
        wordChainFinder.findWordChain(null, "cog");
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullAsEndWordShouldReturnException() {
        wordChainFinder.findWordChain("cat", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullAsInputShouldReturnException() {
        wordChainFinder.findWordChain(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyInputShouldReturnException() {
        wordChainFinder.findWordChain("", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyStartWordShouldReturnException() {
        wordChainFinder.findWordChain("", "cog");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyEndWordShouldReturnException() {
        wordChainFinder.findWordChain("cat", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void DifferentWordsLengthShouldReturnException() {
        wordChainFinder.findWordChain("cat", "horse");
    }

    @Test(expected = IllegalArgumentException.class)
    public void SpecialSignsShouldReturnException() {
        wordChainFinder.findWordChain("//cat", "h!or2");
    }

    @Test
    public void fromCatToDogShouldReturnResult() {
        expected.add("cat");
        expected.add("cot");
        expected.add("cog");
        expected.add("dog");

        assertTrue(wordChainFinder.findWordChain("cat", "dog").stream().allMatch(e->expected.contains(e)));
    }

    @Test
    public void alchemistShouldReturnResult() {
        expected.add("lead");
        expected.add("load");
        expected.add("goad");
        expected.add("gold");

        assertTrue(wordChainFinder.findWordChain("lead", "gold").stream().allMatch(e->expected.contains(e)));
    }

    @Test
    public void programmerShouldReturnResult() {
        expected.add("ruby");
        expected.add("roby");
        expected.add("robe");
        expected.add("rode");
        expected.add("code");

        assertTrue(wordChainFinder.findWordChain("ruby", "code").stream().allMatch(e->expected.contains(e)));
    }

    @Test
    public void UpperCaseShouldReturnResult() {
        expected.add("cat");
        expected.add("cot");
        expected.add("cog");
        expected.add("dog");

        assertTrue(wordChainFinder.findWordChain("CAT", "DOG").stream().allMatch(e->expected.contains(e)));
    }

    @Test
    public void TwoEqualWordsShouldReturnResult() {
        assertEquals(1, wordChainFinder.findWordChain("cat", "cat").size());
    }

    @Test
    public void OneMoveShouldReturnResult() {
        assertEquals(2, wordChainFinder.findWordChain("cat", "cot").size());
    }
}
