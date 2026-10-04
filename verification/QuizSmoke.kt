import com.example.pharma_app.QuizContent
import com.example.pharma_app.QuizSession

fun main() {
    check(QuizContent.anatomy(0) == null)
    for (lesson in 1..4) for (seed in 0..100) {
        val session = QuizSession(requireNotNull(QuizContent.anatomy(lesson)), seed)
        session.next()
        check(session.index == 0)
        while (!session.finished) {
            val position = session.correctPosition
            session.answer(position)
            val score = session.score
            session.answer(position)
            check(session.score == score)
            val restored = QuizSession(session.questions, session.seed, session.index, session.score, session.selected)
            check(restored.order == session.order && restored.selected == session.selected)
            session.next()
        }
        check(session.score == session.questions.size)
        session.answer(0)
        val restarted = QuizSession(session.questions, seed + 1)
        check(!restarted.finished && restarted.score == 0 && restarted.selected == -1)
        restarted.answer((restarted.correctPosition + 1) % 4)
        check(restarted.score == 0)
    }
    println("Passed: 404 quiz sessions, scoring, option mapping, duplicate taps, recreation, completion and restart.")
}