package com.wahidabd.shinigami.presentation.onboarding

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.ActivityLoginBinding
import com.wahidabd.shinigami.domain.user.User
import com.wahidabd.shinigami.presentation.MainActivity
import com.wahidabd.shinigami.utils.pref.PreferenceManager
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleClient: GoogleSignInClient

    private val pref: PreferenceManager by inject()

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        binding.login.onClick {
            val signIn = googleClient.signInIntent
            launchIntent.launch(signIn)
        }
    }

    override fun initProcess() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth
    }

    override fun initObservers() {}

    private val launchIntent = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthGoogle(account.idToken.toString())
            }catch (e: ApiException){
                showToast(e.message.toString())
            }
        }
    }

    private fun firebaseAuthGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    pref.login(true)
                    pref.setUser(
                        User(
                            name = user?.displayName,
                            email = user?.email,
                            avatar = user?.photoUrl.toString()
                        )
                    )

                    // navigate to main
                    MainActivity.start(this)
                    finish()
                } else {
                    showToast(task.exception?.message.toString())
                }
            }
    }


    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}