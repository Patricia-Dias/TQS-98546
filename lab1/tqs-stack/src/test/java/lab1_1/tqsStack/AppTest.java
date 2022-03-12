package lab1_1.tqsStack;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static int CAPACITY = 10;
    private TqsStack stack;
    private TqsStack boundedStack;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @BeforeEach
    private void innit(){
        stack = new TqsStack();
    }

    @Test
    @DisplayName("A stack is empty on construction.")
    public void testA(){
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("A stack has size 0 on construction.")
    public void testB(){
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    public void testC(){
        int n=3;
        for (int i=0; i<n; i++)
            stack.push(i);
        assertEquals(stack.size(), n);
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x.")
    public void testD(){
        stack.push(1);
        int popped = stack.pop();
        assertEquals(1, popped);
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    public void testE(){
        stack.push(1);
        int size1 = stack.size();
        int returned = stack.peek();
        assertEquals(1, returned);
        assertEquals(size1, stack.size());
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    public void testF(){
        int size = 3;
        for (int i=0; i<size;i++)
            stack.push(i);
        for (int i=0; i<size;i++)
            stack.pop();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    public void testG(){
        assertEquals(NoSuchElementException.class, stack.pop());
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    public void testH(){
        assertEquals(NoSuchElementException.class, stack.peek());
    }

    @Before
    public void boundedStack(){
        boundedStack = new TqsStack(CAPACITY);
    }
    @Test(expected = IllegalStateException.class)
    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    public void testI(){
        for (int i=0; i<CAPACITY; i++){
            boundedStack.push(i);
        }
        boundedStack.push(CAPACITY);
    }
}
