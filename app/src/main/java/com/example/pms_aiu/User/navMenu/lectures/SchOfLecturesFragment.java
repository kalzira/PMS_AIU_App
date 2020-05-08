package com.example.pms_aiu.User.navMenu.lectures;

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

import com.example.pms_aiu.Models.User;
import com.example.pms_aiu.R;
import com.example.pms_aiu.User.HomePageUsersActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SchOfLecturesFragment extends Fragment {


    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_web_view, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar_webView); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar
        TextView title_toolbar = root.findViewById(R.id.title_toolbar_webview);
        title_toolbar.setText("Schedule of Lectures");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomePageUsersActivity.class));
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        //Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        webView = root.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        table_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).getValue(User.class);

                //Faculty of Engineering and Informatics
                if(user.getDepartment().contains("COM")){
                    webView.loadUrl("http://com.iaau.edu.kg/calendar/schedule-of-lectures.html");

                }
                if(user.getDepartment().contains("MAT")){
                    webView.loadUrl("https://docs.google.com/spreadsheets/d/1bUVQTZo9ecq7GHSVKKsqKctbwGkfD9CFYGJnQY2eyVg/edit?ts=5c4195b3#gid=757678406");
                }
                if (user.getDepartment().contains("EN")){
                    webView.loadUrl("http://electronic.iaau.edu.kg/?q=en/article/schedule-lecture");
                }
                if (user.getDepartment().contains("IE")){
                    //Чтобы открыть этот опубликованный документ, необходимо иметь разрешение.
                    webView.loadUrl("http://ie.iaau.edu.kg/");
                }

                //need to implement other departments
                else{

                    webView.loadUrl("http://www.iaau.edu.kg/view/public/pages/page.xhtml?id=78");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
                        Intent intent = new Intent(getActivity(), HomePageUsersActivity.class);
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
