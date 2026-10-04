package com.example.pharma_app

import org.junit.Assert.*
import org.junit.Test

class QuizSessionTest {
    @Test fun everyLessonHasItsOwnQuestions() {
        val banks = (1..4).map { requireNotNull(QuizContent.anatomy(it)) }
        assertEquals(4, banks.distinct().size)
        assertNull(QuizContent.anatomy(0))
        banks.flatten().forEach {
            assertEquals(4, it.options.distinct().size)
            assertTrue(it.correctAnswer in 0..3)
        }
    }
    @Test fun shuffledAnswersRetainCorrectMapping() {
        for (seed in 0..100) {
            val session = QuizSession(QuizContent.anatomy(4)!!, seed)
            session.answer(session.correctPosition)
            assertEquals(1, session.score)
            session.answer(session.correctPosition)
            assertEquals("Repeated taps must not count twice", 1, session.score)
        }
    }
    @Test fun rotationRetainsOrderAndProgress() {
        val session = QuizSession(QuizContent.anatomy(1)!!, 42)
        session.answer(session.correctPosition)
        val restored = QuizSession(session.questions, session.seed, session.index, session.score, session.selected)
        assertEquals(session.order, restored.order)
        assertEquals(session.score, restored.score)
        restored.next()
        assertEquals(1, restored.index)
        assertEquals(-1, restored.selected)
    }
    @Test fun finishAndRestartAreSafe() {
        val session = QuizSession(QuizContent.anatomy(2)!!, 31)
        while (!session.finished) {
            session.answer(session.correctPosition)
            session.next()
        }
        assertEquals(session.questions.size, session.score)
        session.answer(0)
        session.next()
        val restarted = QuizSession(session.questions, 55)
        assertFalse(restarted.finished)
        assertEquals(0, restarted.score)
        assertEquals(-1, restarted.selected)
    }
    @Test fun wrongAnswerAndNextBeforeAnswerDoNotChangeScore() {
        val session = QuizSession(QuizContent.anatomy(3)!!, 5)
        session.next()
        assertEquals(0, session.index)
        session.answer((session.correctPosition + 1) % 4)
        assertEquals(0, session.score)
        session.next()
        assertEquals(1, session.index)
    }
}