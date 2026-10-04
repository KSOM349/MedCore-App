package com.example.pharma_app

import kotlin.random.Random

data class Question(val question: String, val options: List<String>, val correctAnswer: Int)

/** Educational practice based on the four existing MedCore-Content anatomy lessons. */
object QuizContent {
    fun anatomy(lesson: Int): List<Question>? = when (lesson) {
        1 -> listOf(
            Question("When does prenatal development begin? / متى يبدأ التطور قبل الولادة؟",
                listOf("At birth / عند الولادة", "At puberty / عند البلوغ", "At fertilisation / عند الإخصاب", "In adulthood / في البلوغ الكامل"), 2),
            Question("Approximately how long is a pregnancy? / ما مدة الحمل تقريبًا؟",
                listOf("40 weeks / أسبوعًا", "10 weeks / أسابيع", "100 weeks / أسبوع", "5 weeks / أسابيع"), 0),
            Question("What is puberty associated with? / بماذا يرتبط البلوغ؟",
                listOf("No body changes / لا تغيرات", "Hormonal changes / تغيرات هرمونية", "Only ageing / الشيخوخة فقط", "Loss of all organs / فقدان الأعضاء"), 1),
            Question("Which can happen naturally with ageing? / أي تغير قد يحدث طبيعيًا مع التقدم في العمر؟",
                listOf("Bones disappear / تختفي العظام", "Organs stop cooperating / يتوقف تعاون الأعضاء", "No physical changes / لا تغيرات جسدية", "Reduced muscle mass / انخفاض الكتلة العضلية"), 3)
        )
        2 -> listOf(
            Question("What forms an organ? / مم يتكون العضو؟",
                listOf("Several tissues / عدة أنسجة", "Only air / الهواء فقط", "Only water / الماء فقط", "A single organ system / جهاز واحد"), 0),
            Question("Why do organ systems cooperate? / لماذا تتعاون أجهزة الجسم؟",
                listOf("To stop growth / لإيقاف النمو", "For normal body function / لعمل الجسم بصورة طبيعية", "To remove all cells / لإزالة الخلايا", "They do not cooperate / لا تتعاون"), 1),
            Question("Which is an organ system? / أي مما يلي جهاز في الجسم؟",
                listOf("A single cell / خلية واحدة", "Calcium / الكالسيوم", "The nervous system / الجهاز العصبي", "A vitamin / فيتامين"), 2),
            Question("What are tissues made of? / مم تتكون الأنسجة؟",
                listOf("Whole people / أشخاص", "Organ systems / أجهزة", "Only minerals / معادن فقط", "Cells / خلايا"), 3)
        )
        3 -> listOf(
            Question("What is the smallest living building block? / ما أصغر وحدة حية في الجسم؟",
                listOf("An organ / عضو", "A cell / خلية", "A tissue / نسيج", "An organ system / جهاز"), 1),
            Question("What do similar cells form? / ماذا تشكل الخلايا المتشابهة؟",
                listOf("A tissue / نسيج", "A whole person / إنسان كامل", "A skeleton / هيكل", "A joint / مفصل"), 0),
            Question("What do cooperating organs form? / ماذا تشكل الأعضاء المتعاونة؟",
                listOf("One cell / خلية", "One mineral / معدن", "An organ system / جهاز", "Only skin / الجلد فقط"), 2),
            Question("Which order describes body organisation? / ما ترتيب بناء الجسم؟",
                listOf("Organ → Cell → Tissue", "Tissue → Cell → Organ", "System → Tissue → Cell", "Cell → Tissue → Organ → System"), 3)
        )
        4 -> listOf(
            Question("Approximately how many bones does an adult have? / كم عظمة لدى البالغ تقريبًا؟",
                listOf("150", "206", "300", "30"), 1),
            Question("Which organ does the skull protect? / أي عضو تحميه الجمجمة؟",
                listOf("Heart / القلب", "Lungs / الرئتان", "Brain / الدماغ", "Stomach / المعدة"), 2),
            Question("What is a joint? / ما المفصل؟",
                listOf("Where bones meet / مكان التقاء العظام", "A blood vessel / وعاء دموي", "A muscle / عضلة", "A vitamin / فيتامين"), 0),
            Question("Where are blood cells produced? / أين تنتج خلايا الدم؟",
                listOf("Hair / الشعر", "Teeth / الأسنان", "Nails / الأظافر", "Bone marrow / نخاع العظم"), 3),
            Question("Which mineral is stored in the skeleton? / أي معدن يخزن في الهيكل العظمي؟",
                listOf("Calcium / الكالسيوم", "Only iron / الحديد فقط", "Only potassium / البوتاسيوم فقط", "No minerals / لا معادن"), 0)
        )
        else -> null
    }
}

/** A deterministic option order keeps the correct answer stable across screen rotation. */
class QuizSession(
    val questions: List<Question>,
    val seed: Int,
    var index: Int = 0,
    var score: Int = 0,
    var selected: Int = -1
) {
    init {
        require(questions.isNotEmpty())
        require(questions.all { it.options.size == 4 && it.options.distinct().size == 4 && it.correctAnswer in 0..3 })
        require(index in 0..questions.size && score in 0..index + 1 && selected in -1..3)
    }
    val finished: Boolean get() = index >= questions.size
    val order: List<Int> get() = (0..3).shuffled(Random(seed + index))
    val correctPosition: Int get() = order.indexOf(questions[index].correctAnswer)
    fun answer(position: Int) {
        if (finished || selected != -1 || position !in 0..3) return
        selected = position
        if (position == correctPosition) score++
    }
    fun next() {
        if (!finished && selected != -1) { index++; selected = -1 }
    }
}