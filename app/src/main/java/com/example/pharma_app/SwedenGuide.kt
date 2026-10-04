package com.example.pharma_app

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class GuideLink(val label: String, val url: String)
data class GuideSection(val title: String, val text: String, val links: List<GuideLink>)
data class GuidePage(val title: String, val sections: List<GuideSection>)

object SwedenGuideContent {
    private val migration = GuideLink("Migrationsverket — الإقامة والعمل",
        "https://www.migrationsverket.se/en/you-want-to-apply/work.html")
    private val police = GuideLink("Polisen — الشرطة",
        "https://polisen.se/en/contacting-the-police")
    private val registration = GuideLink("Skatteverket — الانتقال والتسجيل",
        "https://www.skatteverket.se/servicelankar/otherlanguages/englishengelska/individualsandemployees/movingtosweden")
    private val care = GuideLink("1177 — الرعاية الصحية",
        "https://www.1177.se/en/other-languages/other-languages/soka-vard")
    private val parents = GuideLink("Försäkringskassan — خدمات الأسرة",
        "https://www.forsakringskassan.se/english/parents")
    private val employment = GuideLink("Arbetsförmedlingen — العمل في السويد",
        "https://arbetsformedlingen.se/for-arbetssokande/arbeta-i-sverige")

    val pages = mapOf(
        "laws" to GuidePage("Laws and rights / القوانين والحقوق", listOf(
            GuideSection("Residence and work / الإقامة والعمل",
                "Rules depend on your nationality and situation. Check the current requirements before applying or starting work.\nتختلف القواعد بحسب الجنسية والحالة. تحقق من المتطلبات الحالية قبل التقديم أو بدء العمل.", listOf(migration)),
            GuideSection("Police and reporting / الشرطة والإبلاغ",
                "Use the official police contact page for reporting and non-emergency contact. In immediate danger, call 112.\nراجع صفحة الشرطة الرسمية للإبلاغ والتواصل غير الطارئ. عند خطر فوري اتصل بالرقم 112.", listOf(police)),
            GuideSection("Official laws / القوانين الرسمية",
                "Start with official information; this directory cannot decide your legal rights or replace individual legal advice.\nابدأ بالمعلومات الرسمية؛ هذا الدليل لا يحدد حقوقك القانونية ولا يغني عن الاستشارة القانونية الفردية.",
                listOf(GuideLink("Polisen — Laws and regulations / القوانين",
                    "https://polisen.se/en/laws-and-regulations")))
        )),
        "job" to GuidePage("Work in Sweden / العمل في السويد", listOf(
            GuideSection("Finding work / البحث عن عمل",
                "Explore job-search support, employment types and working in Sweden. Read the terms of a job offer before accepting.\nتعرف على خدمات البحث عن عمل وأنواع التوظيف والعمل في السويد. اقرأ شروط عرض العمل قبل القبول.",
                listOf(employment, GuideLink("Job search / البحث عن وظيفة",
                    "https://arbetsformedlingen.se/for-arbetssokande/sa-hittar-du-jobbet"))),
            GuideSection("Permits and job offers / التصاريح وعروض العمل",
                "Not every job offer gives a right to work or residence. Check your permit category and beware of fraudulent offers.\nلا يمنح كل عرض عمل حق العمل أو الإقامة. تحقق من فئة تصريحك واحذر عروض العمل الاحتيالية.", listOf(migration))
        )),
        "family" to GuidePage("Family and children / الأسرة والأطفال", listOf(
            GuideSection("Family services / خدمات الأسرة",
                "Official information covers parental benefit, child allowance and care of a sick child. Eligibility and amounts depend on your circumstances; apply through the authority.\nتشمل المعلومات الرسمية إجازة الوالدين وإعانة الطفل ورعاية الطفل المريض. يعتمد الاستحقاق والمبلغ على ظروفك؛ قدم طلبك لدى الجهة الرسمية.", listOf(parents)),
            GuideSection("Parental benefit / إعانة الوالدين",
                "Check the current application steps and requirements directly with Försäkringskassan.\nتحقق من خطوات التقديم والشروط الحالية مباشرة لدى فورسيكرينغسكاسان.",
                listOf(GuideLink("Parental benefit / إعانة الوالدين",
                    "https://www.forsakringskassan.se/english/parents/when-the-child-is-born/parental-benefit")))
        )),
        "health" to GuidePage("Healthcare / الرعاية الصحية", listOf(
            GuideSection("Emergencies / الطوارئ",
                "Call 112 for an immediate emergency. For healthcare advice, call 1177. This app does not diagnose symptoms or recommend personal treatment.\nاتصل بالرقم 112 للطوارئ الفورية. للاستشارة الصحية اتصل بالرقم 1177. لا يشخص هذا التطبيق الأعراض ولا يوصي بعلاج شخصي.", listOf(care)),
            GuideSection("Finding care / الوصول إلى الرعاية",
                "1177 explains how to seek care and use healthcare services in Sweden. Ask your provider about costs and your situation.\nيوضح موقع 1177 كيفية طلب الرعاية واستخدام الخدمات الصحية في السويد. اسأل مقدم الرعاية عن التكاليف وما ينطبق على حالتك.", listOf(care))
        )),
        "government" to GuidePage("Authorities / الجهات الحكومية", listOf(
            GuideSection("Registration and tax / التسجيل والضرائب",
                "Check population registration and moving-to-Sweden guidance. Requirements differ by situation.\nراجع إرشادات التسجيل السكاني والانتقال إلى السويد. تختلف المتطلبات بحسب الحالة.", listOf(registration)),
            GuideSection("Residence / الإقامة",
                "Migrationsverket handles migration matters. Follow the official instructions for your application category.\nتختص مصلحة الهجرة بشؤون الهجرة. اتبع التعليمات الرسمية لفئة طلبك.", listOf(migration)),
            GuideSection("Social insurance and employment / التأمين الاجتماعي والعمل",
                "Use the appropriate authority for benefits or employment support; this app cannot submit applications.\nراجع الجهة المختصة للمساعدات أو دعم التوظيف؛ لا يقدم هذا التطبيق الطلبات نيابة عنك.", listOf(parents, employment)),
            GuideSection("Government / الحكومة",
                "Read information from the Government Offices of Sweden.\nاقرأ معلومات مكاتب الحكومة السويدية.",
                listOf(GuideLink("Government Offices / مكاتب الحكومة", "https://www.government.se/")))
        )),
        "mistakes" to GuidePage("Common pitfalls / أخطاء شائعة", listOf(
            GuideSection("Unofficial advice / معلومات غير رسمية",
                "Do not assume someone else's permit or benefit decision applies to you. Use current official information and keep copies of decisions.\nلا تفترض أن قرار تصريح أو إعانة لشخص آخر ينطبق عليك. استخدم المعلومات الرسمية الحالية واحتفظ بنسخ القرارات.", listOf(migration, parents)),
            GuideSection("Unverified job offers / عروض العمل غير الموثوقة",
                "Check an employer and the permit rules before paying anyone or accepting promises about residence.\nتحقق من صاحب العمل وقواعد التصريح قبل دفع المال أو قبول وعود الإقامة.", listOf(migration, employment)),
            GuideSection("Registration and healthcare / التسجيل والرعاية",
                "Check registration steps rather than assuming they happen automatically. Use 112 for emergencies, not routine questions; use 1177 for healthcare advice.\nراجع خطوات التسجيل بدل افتراض حدوثه تلقائيًا. استخدم 112 للطوارئ لا للأسئلة الروتينية، و1177 للاستشارة الصحية.", listOf(registration, care))
        ))
    )
}

/** Native, scrollable directory shared by the existing Sweden activities. */
object SwedenGuide {
    fun show(activity: AppCompatActivity, key: String) {
        val page = requireNotNull(SwedenGuideContent.pages[key])
        val padding = (20 * activity.resources.displayMetrics.density).toInt()
        val content = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(padding, padding, padding, padding)
            setBackgroundColor(Color.rgb(247, 250, 252))
            layoutDirection = View.LAYOUT_DIRECTION_LOCALE
        }
        fun text(value: String, size: Float) = TextView(activity).apply {
            text = value
            textSize = size
            setTextColor(Color.rgb(23, 45, 64))
            setPadding(0, padding / 2, 0, padding / 2)
        }
        content.addView(Button(activity).apply {
            text = "Back / رجوع"
            setOnClickListener { activity.finish() }
        })
        content.addView(text(page.title, 25f))
        content.addView(text("Official-resource directory — general information only; not medical or legal advice. Rules may change. Links open outside the app.\nدليل مصادر رسمية — معلومات عامة فقط، وليست نصيحة طبية أو قانونية. قد تتغير القواعد. تفتح الروابط خارج التطبيق.", 15f))
        page.sections.forEach { section ->
            content.addView(text(section.title, 20f))
            content.addView(text(section.text, 17f))
            section.links.forEach { link ->
                content.addView(Button(activity).apply {
                    text = "${link.label}\n${Uri.parse(link.url).host}"
                    isAllCaps = false
                    setOnClickListener {
                        try {
                            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link.url)))
                        } catch (_: ActivityNotFoundException) {
                            Toast.makeText(activity, "No browser available / لا يوجد متصفح متاح", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
        val scroll = ScrollView(activity).apply { addView(content) }
        ViewCompat.setOnApplyWindowInsetsListener(scroll) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        activity.setContentView(scroll)
        ViewCompat.requestApplyInsets(scroll)
    }
}