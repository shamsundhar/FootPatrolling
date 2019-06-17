package com.school.foot_patroling.reports;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.school.foot_patroling.R;

import butterknife.BindView;

public class DialogPDFViewer extends Dialog {
    @BindView(R.id.pdfView)
    PDFView pdfView;

    byte[] decodedString;

    public interface OnDialogPdfViewerListener {
        void onAgreeClick(DialogPDFViewer dialogFullEula);

        void onCloseClick(DialogPDFViewer dialogFullEula);
    }

    public DialogPDFViewer(Context context, String base64, final DialogPDFViewer.OnDialogPdfViewerListener onDialogPdfViewerListener) {
        super(context);

        setContentView(R.layout.dialog_pdf_viewer);
//        findViewById(R.id.dialog_pdf_viewer_close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onDialogPdfViewerListener.onCloseClick(DialogPDFViewer.this);
//            }
//        });
//
//        findViewById(R.id.dialog_pdf_viewer_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onDialogPdfViewerListener.onAgreeClick(DialogPdfViewer.this);
//            }
//        });

        decodedString = Base64.decode(base64.toString(), Base64.NO_WRAP);

        pdfView = ((PDFView) findViewById(R.id.pdfView));
        pdfView.fromBytes(decodedString).enableSwipe(true)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                        Log.d("","------onLayerDrawn-----");
                    }
                })
                // allows to draw something on all pages, separately for every page. Called only for visible pages
                .onDrawAll(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                        Log.d("","------onLayerDrawn-----");
                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Log.d("","------loadComplete-----");
                    }
                }) // called after document is loaded and starts to be rendered
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        Log.d("","------onPageChanged-----");
                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {
                        Log.d("","------onPageScrolled-----");
                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Log.d("","------onError-----");
                    }
                })
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Log.d("","------onPageError-----");
                    }
                })
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages) {
                        Log.d("","------onInitiallyRendered-----");
                    }
                })
                .onTap(new OnTapListener() {
                    @Override
                    public boolean onTap(MotionEvent e) {
                        return false;
                    }
                })
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .load();

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onDialogPdfViewerListener.onCloseClick(DialogPDFViewer.this);
                }
                return true;
            }
        });

    }
}