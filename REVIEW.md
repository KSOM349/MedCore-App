# MedCore first-version review

This change keeps the original Kotlin/XML Android app and its application ID.

## Review paths

- Home → Undersköterska → Anatomy → each of the four lessons → matching lesson quiz.
- Home → Undersköterska → Pharmacy → existing educational pharmacy quiz, with shuffled answers.
- Quiz → answer → Next → results → Restart. Try rotating before/after an answer.
- Home → each of the six Sweden categories → official links → return to app.
- A detail screen with a description but no lesson URL must not crash.

The pharmacy question bank is inherited, not a newly validated medical curriculum.
Information and quizzes are educational only, not diagnosis, prescriptions, or
individual legal advice. The Sweden directory does not determine eligibility or
submit applications. Official pages may change and require an external browser.
Lessons load from the existing public MedCore-Content repository and need internet.

## Build and checks

Use JDK 21, Android SDK platform 36.1 and build tools 36.0.0, matching the existing
compileSdk. No replacement of the build system or SDK downgrade is required.

```sh
bash gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug --no-daemon
```

An Android review workflow template is supplied at
`verification/android-review-workflow.yml`. It is **not enabled**: the connected
GitHub authorization accepted application-code writes but refused workflow-file
writes. To enable it, an owner can copy it to
`.github/workflows/android-review.yml` on the review branch, then run it in
GitHub Actions. It builds, tests and uploads a debug APK and reports; it does not
publish, sign a release or merge. No GitHub Android build has run yet.

For an independent, SDK-free check of the actual quiz model, use Kotlin:

```sh
kotlinc app/src/main/java/com/example/pharma_app/QuizContent.kt \
  verification/QuizSmoke.kt -include-runtime -d /tmp/medcore-quiz-tests.jar
java -jar /tmp/medcore-quiz-tests.jar
```

This exercises 404 quiz sessions. It does **not** verify Android rendering.

## Local verification limitation

The Replit workspace had no Android SDK. A real Gradle Android build was attempted
and stopped at “SDK location not found”. The standalone Kotlin quiz smoke check
passed. Read the GitHub workflow result separately; a local smoke check is not
evidence of a successful APK build or device test.