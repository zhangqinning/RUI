package com.music.lib_image_loader.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.music.lib_image_loader.Image.CornerTransform;
import com.music.lib_image_loader.Image.CustomRequestListener;
import com.music.lib_image_loader.Image.ImageUtils;
import com.music.lib_image_loader.R;

import java.io.File;
import java.util.concurrent.ExecutionException;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;


public class ImageLoaderManager {

    private ImageLoaderManager() {
    }

    public static ImageLoaderManager getInstance() {
        return ImageLoaderManager.SingletonHolder.instance;
    }


    //静态内部类
    private static class SingletonHolder {
        private static ImageLoaderManager instance = new ImageLoaderManager();
    }

    /**
     * 加载ImageView的图片
     */
    public void displayImageForView(ImageView view, String url) {
        Glide.with(view.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(withCrossFade())
                .into(view);

    }

    public File getImageFile(Context context, String url) throws ExecutionException, InterruptedException {
        return Glide.with(context)
                .downloadOnly()
                .load(url)
                .submit()
                .get();

    }

    /**
     * 加载圆形图片
     *
     * @param imageView
     * @param url
     */
    public void displayImageForCircle(final ImageView imageView, final String url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(final Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }


    /**
     * 加载圆角图片
     *
     * @param imageView
     * @param url
     */
    public void displayImageForCorner(final ImageView imageView, String url, int corner) {
        CornerTransform transformation = new CornerTransform(imageView.getContext(), ImageUtils.dip2px(imageView.getContext(), corner));
        transformation.setExceptCorner(false, false, false, false);
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transform(transformation)
                .into(imageView);
    }

    /**
     * 加载圆角图片 圆角5dp
     *
     * @param imageView
     * @param url
     */
    public void displayImageForCorner(final ImageView imageView, String url) {
        displayImageForCorner(imageView, url, 5);
    }

    public void displayImageForViewGroup(final ViewGroup group, String url, final int radius) {
        Glide.with(group.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new SimpleTarget<Bitmap>() {//设置宽高
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        final Bitmap res = resource;
                        Disposable subscribe = Observable.just(resource)
                                .map(new Function<Bitmap, Drawable>() {
                                    @Override
                                    public Drawable apply(Bitmap bitmap) {
                                        return new BitmapDrawable(
                                                //毛玻璃效果
                                                ImageUtils.doBlur(res, radius, true)
                                        );
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Drawable>() {
                                    @Override
                                    public void accept(Drawable drawable) {
                                        group.setBackground(drawable);
                                    }
                                });
                    }
                });
    }

    public void displayImageForViewGroup(final ImageView view, String url, final int radius) {
        Glide.with(view.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new SimpleTarget<Bitmap>() {//设置宽高
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        final Bitmap res = resource;
                        Disposable subscribe = Observable.just(resource)
                                .map(new Function<Bitmap, Drawable>() {
                                    @Override
                                    public Drawable apply(Bitmap bitmap) {
                                        //毛玻璃效果
                                        return new BitmapDrawable(ImageUtils.doBlur(res, radius, true));
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Drawable>() {
                                    @Override
                                    public void accept(Drawable drawable) {
                                        view.setBackground(drawable);
                                    }
                                });
                    }
                });
    }

    public void displayImageForNotification(Context context, RemoteViews rv, int id,
                                            Notification notification, int NOTIFICATION_ID, String url) {
        this.displayImageForTarget(context,
                initNotificationTarget(context, id, rv, notification, NOTIFICATION_ID), url);
    }

    /*
     * 初始化Notification Target
     */
    private NotificationTarget initNotificationTarget(Context context, int id, RemoteViews rv,
                                                      Notification notification, int NOTIFICATION_ID) {
        return new NotificationTarget(context, id, rv, notification, NOTIFICATION_ID);
    }

    private void displayImageForTarget(Context context, Target target, String url) {
        this.displayImageForTarget(context, target, url, null);
    }

    /**
     * 为非view加载图片
     */
    private void displayImageForTarget(Context context, Target target, String url,
                                       CustomRequestListener requestListener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(withCrossFade())
                .fitCenter()
                .listener(requestListener)
                .into(target);
    }

    @SuppressLint("CheckResult")
    private RequestOptions initCommonRequestOption() {

        return new RequestOptions()
                //.placeholder(R.mipmap.ic_album_demo)//loading时显示的图片
                .error(R.mipmap.ic_album_demo)//load失败时显示的图片
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//缓存策略
                .skipMemoryCache(false)//使用内存缓存
                .priority(Priority.NORMAL);
    }
}
