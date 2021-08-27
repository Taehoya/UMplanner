package com.cth.myapplication.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TermTest {
    @Test
    public void testTerm(){
        System.out.println("\nStarting testTerm");
        assertTrue(Term.Fall.isFall());
        assertTrue(Term.FallWinter.isFall());
        assertTrue(Term.FallWinterSummer.isFall());

        assertFalse(Term.Winter.isFall());
        assertFalse(Term.WinterSummer.isFall());

        assertTrue(Term.Winter.isWinter());
        assertTrue(Term.WinterSummer.isWinter());
        assertTrue(Term.FallWinterSummer.isWinter());

        assertFalse(Term.Fall.isWinter());
        assertFalse(Term.FallSummer.isWinter());

        assertTrue(Term.Summer.isSummer());
        assertTrue(Term.FallSummer.isSummer());
        assertTrue(Term.FallWinterSummer.isSummer());

        assertFalse(Term.FallWinter.isSummer());
        assertFalse(Term.Winter.isSummer());

        assertTrue(Term.Winter.checkTerm(Term.Winter));
        assertTrue(Term.WinterSummer.checkTerm(Term.Winter));
        assertTrue(Term.FallWinterSummer.checkTerm(Term.Winter));

        assertFalse(Term.FallSummer.checkTerm(Term.Winter));
        assertFalse(Term.Fall.checkTerm(Term.Summer));

        assertEquals("Fall", Term.Fall.toString());
        assertEquals("Winter", Term.Winter.toString());

        assertSame(Term.getTerm(1), Term.Fall);
        assertSame(Term.getTerm(2), Term.Winter);
        assertSame(Term.getTerm(4), Term.Summer);

        System.out.println("Finished testTerm");
    }//testCourse


}
