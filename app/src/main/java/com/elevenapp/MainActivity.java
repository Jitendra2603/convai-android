package com.elevenapp;

import android.app.Activity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

public class MainActivity extends Activity {
    private static final int PERMISSION_REQUEST_CODE = 1234;
    private WebView webView;
    private String[] REQUIRED_PERMISSIONS = {
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar));
        
        webView = findViewById(R.id.webView);
        setupWebView();
        checkAndRequestPermissions();
    }

    private void setupWebView() {
        WebSettings webSettings = webView.getSettings();
        
        // Enable all modern web features and hardware acceleration
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        
        // Enable hardware acceleration and WebGL
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        
        // Security settings for WebGL content
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        
        // Set high performance mode
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(true);

        webView.setBackgroundColor(Color.TRANSPARENT);
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return null;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                runOnUiThread(() -> request.grant(request.getResources()));
            }
        });

        // Load the iframe HTML with proper viewport settings
        String iframeHtml = "<!DOCTYPE html><html><head>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no'>" +
                "<style>" +
                "* { margin: 0; padding: 0; box-sizing: border-box; }" +
                "body { background-color: transparent; overflow: hidden; display: flex; justify-content: center; align-items: center; height: 100vh; }" +
                "elevenlabs-convai {" +
                "  width: 100%;" +
                "  max-width: 450px;" +
                "  min-height: 150px;" +
                "  display: block;" +
                "  border-radius: 12px;" +
                "  overflow: hidden;" +
                "  background-color: transparent;" +
                "  margin: 16px;" +
                "}" +
                "</style>" +
                "</head><body>" +
                "<elevenlabs-convai agent-id=\"your-agent-id\"></elevenlabs-convai>" +
                "<script src=\"https://elevenlabs.io/convai-widget/index.js\" async type=\"text/javascript\"></script>" +
                "</body></html>";

        webView.loadDataWithBaseURL("https://elevenlabs.io", iframeHtml, "text/html", "UTF-8", null);
    }

    private void checkAndRequestPermissions() {
        if (!hasPermissions()) {
            requestPermissions(REQUIRED_PERMISSIONS, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean hasPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (hasPermissions()) {
                webView.reload();
            }
        }
    }
} 