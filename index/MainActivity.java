package oak.com.browser;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    EditText editText;
    Button button;
    private Button forword;
     private Button backword;
    private Button reload;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView webView=(WebView)findViewById(R.id.webview);

        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar);
        final EditText editText=(EditText)findViewById(R.id.edittext_search);
        Button button=(Button)findViewById(R.id.button_go);
        Button forword=(Button)findViewById(R.id.forword);
        Button backword=(Button)findViewById(R.id.Back) ;
        Button reload=(Button)findViewById(R.id.reload);
        Button home=(Button)findViewById(R.id.home_h);
        progressBar.setMax(100);
        progressBar.setVisibility(webView.GONE);
        forword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward()){
                    webView.goForward();
                }
            }
        });
        backword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()){
                    webView.goBack();
                }
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                webView.loadUrl("http://google.com");
                editText.setText("");

            }
        });


        if(savedInstanceState!=null){
     webView.restoreState(savedInstanceState);


        }else {

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);
            webView.setWebViewClient(new ourViewClient());
           webView.setWebChromeClient(new WebChromeClient(){
                                          @Override
                                          public void onProgressChanged(WebView view, int newProgress) {
                                              progressBar.setProgress(newProgress);

                                              if (newProgress <100 && progressBar.getVisibility()==progressBar.GONE){
                                                  progressBar.setVisibility(progressBar.VISIBLE);

                                              }
                                              if(newProgress==100){
                                                  progressBar.setVisibility(progressBar.GONE);
                                              }
                                          }
                                      }

           );







        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
          inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                webView.loadUrl("https://"+editText.getText().toString());
                editText.setText("");

            }
        });



    }
    public  class ourViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            CookieManager.getInstance().setAcceptCookie(true);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}


