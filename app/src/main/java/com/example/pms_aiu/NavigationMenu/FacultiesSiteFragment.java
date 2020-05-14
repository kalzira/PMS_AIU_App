package com.example.pms_aiu.NavigationMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pms_aiu.R;

public class FacultiesSiteFragment extends Fragment {


    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_web_view, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar_webView); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar
        final TextView title_toolbar = root.findViewById(R.id.title_toolbar_webview);
        title_toolbar.setText("Faculties");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomePageActivity.class));
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        webView = root.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://www.iaau.edu.kg/view/public/pages/page.xhtml?id=78");
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("Android WebView");
        webSettings.setJavaScriptEnabled(true);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //This is the filter
                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        Intent intent = new Intent(getActivity(), HomePageActivity.class);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
        return root;
    }
}
