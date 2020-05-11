package com.barnamechi.ayandehsazan.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.barnamechi.ayandehsazan.R;

public class WebViewFragment extends Fragment {
    private String mainURL;
    private Context context;
    private WebView webView;
    private TextView pleaseWaitTV;

    public WebViewFragment(Context context, String mainURL) {
        this.context = context;
        this.mainURL = mainURL;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        init(rootView);
        showPleaseWait();
        return rootView;
    }

    private void showPleaseWait() {
        pleaseWaitTV.setVisibility(View.VISIBLE);
    }

    private void hidePleaseWait() {
        pleaseWaitTV.setVisibility(View.GONE);
    }

    private void init(View rootView) {
        webView = rootView.findViewById(R.id.webview_fragment_web_view_wv);
        pleaseWaitTV = rootView.findViewById(R.id.webview_fragment_plaese_wait_tv);
    }

    @Override
    public void onResume() {
        loadURL(mainURL);
        super.onResume();
    }

    private void loadURL(String mainURL) {
        hidePleaseWait();
        webView.loadUrl(mainURL);
    }
}
