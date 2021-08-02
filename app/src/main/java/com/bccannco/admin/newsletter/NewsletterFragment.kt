package com.bccannco.admin.newsletter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.bccannco.admin.BaseFragment
import com.bccannco.admin.R
import com.bccannco.admin.comman.BEARER_TOKEN
import com.bccannco.admin.comman.INTERNET_CONNECTIVITY_MSG
import com.bccannco.admin.comman.NewslatterapiIdentifier
import kotlinx.android.synthetic.main.activity_login.*

class NewsletterFragment : BaseFragment(), NewsletterInterface, View.OnClickListener {

    lateinit var _context: Context
    lateinit var model: NewslatterModel
    lateinit var Tv_SendNoew: TextView
    lateinit var Et_SubjectLine: EditText
    lateinit var Et_MessageBody: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_newsletter, container, false)
        _context = container?.context!!
        model = NewslatterModel(_context, this)
        Init(view)
        prefInit(_context)
        loaderInit(view)

        return view
    }

    private fun Init(view: View) {
        Tv_SendNoew = view.findViewById(R.id.Tv_SendNoew)
        Et_SubjectLine = view.findViewById(R.id.Et_SubjectLine)
        Et_MessageBody = view.findViewById(R.id.Et_MessageBody)
        Tv_SendNoew.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.Tv_SendNoew -> {
                if (IsValid()) {
                    if (isNetworkAvailable()) {
                        showDialog()
                        model.NewslatterApiCalling(
                            get_StringPref(BEARER_TOKEN, ""),
                            Et_SubjectLine.text.toString(),
                            Et_MessageBody.text.toString()
                        )
                    } else {
                        toast(INTERNET_CONNECTIVITY_MSG)
                    }
                }
            }
        }
    }

    private fun IsValid(): Boolean {
        if (!EmptyFielValidation(Et_SubjectLine, "Subject line")) {
            YoYo.with(Techniques.StandUp)
                .duration(200)
                .repeat(1)
                .playOn(Et_SubjectLine);
            return false
        }
        if (!EmptyFielValidation(Et_MessageBody, "Message body")) {
            YoYo.with(Techniques.StandUp)
                .duration(200)
                .repeat(1)
                .playOn(Et_MessageBody);
            return false
        }
        return true
    }

    override fun <T> OnSuccess(result: T, identifier: Int) {

        when (identifier) {
            NewslatterapiIdentifier -> {
                hideDialog()
                Et_MessageBody.text = null
                Et_SubjectLine.text = null
                toast("Sent.")
            }
        }
    }

    override fun <T> OnError(result: T) {
        hideDialog()
        toast(result)
    }
}