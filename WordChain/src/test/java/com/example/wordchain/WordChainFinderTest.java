package com.example.wordchain;

import com.example.wordchain.WordChainFinder.WordChainFinderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordChainFinderTest {

    private WordChainFinderImpl wordChainFinder = new WordChainFinderImpl();

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

//    @Test
//    public void fromCatToDogShouldReturnResult() {
//        List<String> result = Stream.of("cat", "cot", "cog", "dog").collect(Collectors.toList());
//        assertEquals(wordChainFinder.findWordChain("cat", "dog"), result);
//    }
//
//    @Test
//    public void alchemistShouldReturnResult() {
//        List<String> result = Stream.of("lead", "load", "goad", "gold").collect(Collectors.toList());
//        assertEquals(wordChainFinder.findWordChain("lead", "gold"), result);
//    }
//
//    @Test
//    public void programmerShouldReturnResult() {
//        List<String> result = Stream.of("ruby", "roby", "robe", "rode", "code").collect(Collectors.toList());
//        assertEquals(wordChainFinder.findWordChain("ruby", "code"), result);
//    }
//
//    @Test
//    public void UpperCaseShouldReturnResult() {
//        List<String> result = Stream.of("cat", "cot", "cog", "dog").collect(Collectors.toList());
//        assertEquals(wordChainFinder.findWordChain("CAT", "DOG"), result);
//    }
//
//    @Test
//    public void TwoEqualWordsShouldReturnResult() {
//        List<String> result = Stream.of("cat").collect(Collectors.toList());
//        assertEquals(wordChainFinder.findWordChain("cat", "cat"), result);
//    }
//
//    @Test
//    public void OneMoveShouldReturnResult() {
//        List<String> result = Stream.of("cat", "cot").collect(Collectors.toList());
//        assertEquals(wordChainFinder.findWordChain("cat", "cot"), result);
//    }
}
