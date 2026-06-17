package com.example.pharma_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var scoreText: TextView
    private lateinit var restartBtn: Button

    private var currentIndex = 0
    private var score = 0

    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        scoreText = findViewById(R.id.scoreText)
        restartBtn = findViewById(R.id.restartBtn)

        questions = getQuestions()

        option1.setOnClickListener { checkAnswer(0) }
        option2.setOnClickListener { checkAnswer(1) }
        option3.setOnClickListener { checkAnswer(2) }
        option4.setOnClickListener { checkAnswer(3) }

        restartBtn.setOnClickListener {
            currentIndex = 0
            score = 0
            restartBtn.visibility = View.GONE
            showQuestion()
        }

        showQuestion()
    }

    private fun showQuestion() {
        if (currentIndex >= questions.size) {
            questionText.text = "Finished! Score: $score / ${questions.size}"
            option1.visibility = View.GONE
            option2.visibility = View.GONE
            option3.visibility = View.GONE
            option4.visibility = View.GONE
            restartBtn.visibility = View.VISIBLE
            return
        }

        val q = questions[currentIndex]
        questionText.text = q.question
        option1.text = q.options[0]
        option2.text = q.options[1]
        option3.text = q.options[2]
        option4.text = q.options[3]
    }

    private fun checkAnswer(selected: Int) {

        val correct = questions[currentIndex].correctAnswer

        // تعطيل الأزرار مؤقتاً
        option1.isEnabled = false
        option2.isEnabled = false
        option3.isEnabled = false
        option4.isEnabled = false

        // تلوين الإجابات
        val buttons = listOf(option1, option2, option3, option4)

        if (selected == correct) {
            score++
            buttons[selected].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        } else {
            buttons[selected].setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            buttons[correct].setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        }

        // انتظار ثانية ثم الانتقال للسؤال التالي
        questionText.postDelayed({

            // إعادة الألوان
            for (btn in buttons) {
                btn.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                btn.isEnabled = true
            }

            currentIndex++
            showQuestion()

        }, 1000)
    }

    private fun getQuestions(): List<Question> {
        return listOf(

            Question("Paracetamol is used for? / يستخدم الباراسيتامول ل؟", listOf("Pain","Infection","Cancer","Allergy"),0),
            Question("Ibuprofen belongs to? / الايبوبروفين ينتمي ل؟", listOf("NSAID","Antibiotic","Vitamin","Hormone"),0),
            Question("Aspirin reduces? / الأسبرين يقلل؟", listOf("Clotting","Vision","Hair","Sleep"),0),
            Question("Insulin lowers? / الأنسولين يخفض؟", listOf("Blood sugar","Pressure","Temperature","Calcium"),0),
            Question("Morphine acts on? / المورفين يعمل على؟", listOf("Opioid receptors","Kidney","Skin","Eye"),0),

            Question("Antibiotics treat? / المضادات الحيوية تعالج؟", listOf("Bacteria","Virus","Pain","Fever"),0),
            Question("Penicillin is? / البنسلين هو؟", listOf("Antibiotic","Vitamin","Hormone","Enzyme"),0),
            Question("Vitamin D helps? / فيتامين د يساعد؟", listOf("Bones","Brain","Skin","Hair"),0),
            Question("Adrenaline increases? / الأدرينالين يزيد؟", listOf("Heart rate","Sleep","Weight","Vision"),0),
            Question("Liver function? / وظيفة الكبد؟", listOf("Detox","Breathing","Thinking","Hearing"),0),

            Question("Kidney filters? / الكلية ترشح؟", listOf("Blood","Air","Food","Hormones"),0),
            Question("ECG checks? / تخطيط القلب يفحص؟", listOf("Heart","Brain","Lungs","Kidney"),0),
            Question("Amoxicillin treats? / الأموكسيسيلين يعالج؟", listOf("Infection","Pain","Cancer","Allergy"),0),
            Question("Insulin type? / الأنسولين نوع من؟", listOf("Hormone","Vitamin","Protein","Mineral"),0),
            Question("Paracetamol overdose affects? / جرعة زائدة تؤثر على؟", listOf("Liver","Heart","Brain","Eye"),0),

            Question("Pain killer is? / مسكن الألم؟", listOf("Analgesic","Antibiotic","Vitamin","Hormone"),0),
            Question("Antihistamine used for? / مضاد الهيستامين؟", listOf("Allergy","Pain","Infection","Cancer"),0),
            Question("Insulin produced in? / ينتج في؟", listOf("Pancreas","Heart","Liver","Brain"),0),
            Question("Blood pressure drug? / ضغط الدم؟", listOf("Amlodipine","Vitamin C","Paracetamol","Insulin"),0),
            Question("Vitamin C helps? / فيتامين سي؟", listOf("Immunity","Sleep","Vision","Hearing"),0),

            Question("Fever drug? / دواء الحمى؟", listOf("Paracetamol","Insulin","Antibiotic","Vitamin"),0),
            Question("Antibiotic kills? / يقتل؟", listOf("Bacteria","Virus","Pain","Sugar"),0),
            Question("Brain controls? / الدماغ؟", listOf("Body","Food","Water","Air"),0),
            Question("Heart pumps? / القلب؟", listOf("Blood","Air","Food","Hormones"),0),
            Question("Lungs function? / الرئة؟", listOf("Breathing","Thinking","Filtering","Pumping"),0),

            Question("Vitamin A helps? / فيتامين A؟", listOf("Vision","Hearing","Smell","Touch"),0),
            Question("Insulin used for? / يستخدم ل؟", listOf("Diabetes","Pain","Infection","Cancer"),0),
            Question("Skin is largest? / الجلد؟", listOf("Organ","Cell","Bone","Muscle"),0),
            Question("Kidney number? / عدد الكلى؟", listOf("2","1","3","4"),0),
            Question("Normal temp? / الحرارة؟", listOf("37","35","40","30"),0),

            Question("Blood color? / لون الدم؟", listOf("Red","Blue","Green","Black"),0),
            Question("Oxygen carried by? / الأكسجين؟", listOf("Blood","Food","Skin","Bone"),0),
            Question("Injection route? / الحقن؟", listOf("Body","Air","Food","Eye"),0),
            Question("Antibiotic misuse causes? / سوء الاستخدام؟", listOf("Resistance","Sleep","Vision","Hair"),0),
            Question("Pain scale max? / أقصى ألم؟", listOf("10","5","3","1"),0),

            Question("Tablet form? / الحبوب؟", listOf("Medicine","Chair","Table","Door"),0),
            Question("Capsule is? / الكبسولة؟", listOf("Medicine","Food","Water","Air"),0),
            Question("Syrup is? / الشراب؟", listOf("Liquid","Solid","Gas","Powder"),0),
            Question("Eye drops for? / العين؟", listOf("Eyes","Heart","Skin","Brain"),0),
            Question("Ear drops for? / الأذن؟", listOf("Ear","Eye","Nose","Skin"),0),

            Question("Nasal spray? / الأنف؟", listOf("Nose","Eye","Ear","Skin"),0),
            Question("Inhaler used for? / البخاخ؟", listOf("Asthma","Pain","Cancer","Fever"),0),
            Question("Glucose is? / الجلوكوز؟", listOf("Sugar","Protein","Fat","Vitamin"),0),
            Question("Vitamin K helps? / فيتامين K؟", listOf("Clotting","Vision","Hearing","Breathing"),0),
            Question("Tablet taken by? / تؤخذ؟", listOf("Mouth","Eye","Ear","Skin"),0),

            Question("Injection risk? / خطر؟", listOf("Infection","Sleep","Hair","Vision"),0),
            Question("Antibiotic duration? / مدة؟", listOf("Full course","1 day","Stop early","Random"),0),
            Question("Overdose means? / الجرعة الزائدة؟", listOf("Too much","Little","None","Food"),0),
            Question("Pharmacology studies? / يدرس؟", listOf("Drugs","Food","Air","Water"),0),
            Question("Dose means? / الجرعة؟", listOf("Amount","Time","Place","Color"),0),

            Question("Side effect? / الأثر الجانبي؟", listOf("Unwanted","Main","Food","Water"),0),
            Question("Generic name? / الاسم العلمي؟", listOf("Drug name","Color","Shape","Taste"),0),
            Question("Brand name? / التجاري؟", listOf("Company","Doctor","Patient","Food"),0),
            Question("Storage? / التخزين؟", listOf("Cool","Heat","Sun","Water"),0),
            Question("Expiry? / الانتهاء؟", listOf("Not usable","Fresh","New","Safe"),0),

            Question("Water with meds? / الماء؟", listOf("Yes","No","Maybe","Sometimes"),0),
            Question("Doctor gives? / الطبيب؟", listOf("Medicine","Food","Water","Air"),0)

        )
    }
}