package com.project.ihearyou.fragment

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.ihearyou.R
import java.util.Locale

/*class RecordingVoiceFragment : Fragment() {

    private lateinit var recognizer: SpeechRecognizer
    private lateinit var tts: TextToSpeech
    private lateinit var recordingButton: Button
    private val REQUEST_RECORD_AUDIO_PERMISSION = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recording_voice, container, false)
        recordingButton = view.findViewById(R.id.recordingButton)

        // Initialize SpeechRecognizer and TextToSpeech
        recognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        recognizer.setRecognitionListener(recognitionListener)

        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
            }
        }

        // Set up button to request permission and start listening
        recordingButton.setOnClickListener {
            checkAudioPermission()
        }

        return view
    }

    private fun checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION)
        } else {
            startListening()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening()
        } else {
            Toast.makeText(requireContext(), "Audio permission is required", Toast.LENGTH_SHORT).show()
        }
    }



    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500)
        }
        recognizer.startListening(intent)
    }


    private val recognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            Toast.makeText(requireContext(), "Listening...", Toast.LENGTH_SHORT).show()
        }

        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(p0: Float) {}
        override fun onBufferReceived(p0: ByteArray?) {}
        override fun onEndOfSpeech() {}

        override fun onError(error: Int) {
            val errorMessage = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                SpeechRecognizer.ERROR_CLIENT -> "Client-side error"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                SpeechRecognizer.ERROR_NETWORK -> "Network error"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                SpeechRecognizer.ERROR_NO_MATCH -> "No match found"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognition service busy"
                SpeechRecognizer.ERROR_SERVER -> "Server error"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                else -> "Unknown error"
            }
            Toast.makeText(requireContext(), "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }

       /* override fun onResults(results: Bundle) {
            val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            matches?.let {
                if ("blue" in it) showColorScreen("blue")
                else if ("red" in it) showColorScreen("red")
            }
        }*/
       override fun onResults(results: Bundle) {
           val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
           matches?.let {
               for (match in it) {
                   Log.d("Recognizer", "Match: $match")  // Log each recognized phrase
               }
               if (it.any { result -> result.contains("blue", ignoreCase = true) }) {
                   showColorScreen("blue")
               } else if (it.any { result -> result.contains("red", ignoreCase = true) }) {
                   showColorScreen("red")
               } else {
                   Toast.makeText(requireContext(), "No recognized color found", Toast.LENGTH_SHORT).show()
               }
           }
       }

        override fun onPartialResults(p0: Bundle?) {}
        override fun onEvent(p0: Int, p1: Bundle?) {}
    }

    private fun showColorScreen(color: String) {
      /*  val mainView = view ?: return*/
        when (color) {
            "blue" -> {
               /* mainView.setBackgroundColor(Color.BLUE)
                respondToUser("Here is a blue screen")*/
                findNavController().navigate(R.id.action_recordingVoiceFragment_to_blueFragment)
            }
            "red" -> {
               /* mainView.setBackgroundColor(Color.RED)
                respondToUser("Here is the red screen")*/
                findNavController().navigate(R.id.action_recordingVoiceFragment_to_blueFragment)
            }
        }
    }

    private fun respondToUser(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroyView() {
        tts.stop()
        tts.shutdown()
        recognizer.destroy()
        super.onDestroyView()
    }
}*/



class RecordingVoiceFragment : Fragment() {
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recording_voice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textToSpeech = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.US
            }
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())

        view.findViewById<Button>(R.id.recordingButton).setOnClickListener {
            startListening()
        }
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val spokenText = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()
                handleVoiceCommand(spokenText)
            }

            override fun onPartialResults(p0: Bundle?) {

            }

            override fun onEvent(p0: Int, p1: Bundle?) {

            }

            override fun onReadyForSpeech(p0: Bundle?) {
                Toast.makeText(requireContext(), "Listening...", Toast.LENGTH_SHORT).show()
            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(p0: Float) {
                Log.d("RecordingVoiceFragment", "Sound level:  dB")
            }

            override fun onBufferReceived(p0: ByteArray?) {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(error: Int) {
                val errorMessage = when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                    SpeechRecognizer.ERROR_CLIENT -> "Client-side error"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                    SpeechRecognizer.ERROR_NETWORK -> "Network error"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                    SpeechRecognizer.ERROR_NO_MATCH -> "No match found"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognition service busy"
                    SpeechRecognizer.ERROR_SERVER -> "Server error"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                    else -> "Unknown error"
                }
                Toast.makeText(requireContext(), "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }

            // Other overrides can be left empty
        })
        speechRecognizer.startListening(intent)
    }

    private fun handleVoiceCommand(spokenText: String?) {
        val navController = findNavController()

        when {
            spokenText?.contains("blue", ignoreCase = true) == true -> {
                textToSpeech.speak("This is the blue color", TextToSpeech.QUEUE_FLUSH, null, null)
                navController.navigate(R.id.blueFragment)
            }
            spokenText?.contains("red", ignoreCase = true) == true -> {
                textToSpeech.speak("This is the red color", TextToSpeech.QUEUE_FLUSH, null, null)
                navController.navigate(R.id.redFragment)
            }
            else -> {
                textToSpeech.speak("Color not recognized", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        speechRecognizer.destroy()
        textToSpeech.shutdown()
    }
}

