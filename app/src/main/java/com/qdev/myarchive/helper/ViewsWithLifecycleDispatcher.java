package com.qdev.myarchive.helper;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.WeakHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import static androidx.lifecycle.Lifecycle.State.STARTED;

//MIT License
//
//Copyright (c) Vladimir Kudashov
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.
//
//E-mail: wn1soft@gmail.com
//

public class ViewsWithLifecycleDispatcher<T> implements LifecycleEventObserver {

    public interface ViewRunnable<T> {
        void onRun(T view);
    }

    private final PriorityQueue<ViewRunnable<T>> runnableList =
            new PriorityQueue<>();
    private final WeakHashMap<T, LifecycleOwner> viewLifecycleMap = new WeakHashMap<>();
    private final WeakHashMap<T, Object> viewMap = new WeakHashMap<>();

    private final Object present = new Object();

    public ViewsWithLifecycleDispatcher() {

    }

    public void addView(T view, LifecycleOwner lifecycleOwner) { //Подключение View
        if (lifecycleOwner != null) {
            synchronized(viewLifecycleMap) {
                viewLifecycleMap.put(view, lifecycleOwner);
                lifecycleOwner.getLifecycle().addObserver(this);
            }
        } else {
            synchronized(viewMap) {
                viewMap.put(view, present);
            }
        }
        dispatchStored();
    }

    public void addView(T view) { //Подключение View
        if (view instanceof LifecycleOwner) {
            addView(view , (LifecycleOwner) view);
        } else {
            addView(view, null);
        }
    }

    public void removeView(T view) { //Удаление View
        if (view instanceof LifecycleOwner) {
            synchronized(viewLifecycleMap) {
                viewLifecycleMap.get(view).getLifecycle().removeObserver(this);
                viewLifecycleMap.remove(view);
            }
        } else {
            synchronized(viewMap) {
                viewMap.remove(view);
            }
        }
    }

    public void removeAllView() { //Удаление View
        synchronized(viewLifecycleMap) {
            viewLifecycleMap.clear();
        }
        synchronized(viewMap) {
            viewMap.clear();
        }
    }

    private void run (ViewRunnable<T> runnable, T view) {
        runnable.onRun(view);
    }

    @UiThread
    private void dispatchStored() {
        synchronized(runnableList) {
            while (!runnableList.isEmpty()) {
                boolean isViewInPresenterStateFinded = false;
                ViewRunnable<T> runnable = runnableList.peek();
                synchronized (viewLifecycleMap) {
                    for (Map.Entry<T, LifecycleOwner> entry : viewLifecycleMap.entrySet()) {
                        try {
                            if (entry.getValue().getLifecycle().getCurrentState().isAtLeast(STARTED)) {
                                run(runnable, entry.getKey());
                                isViewInPresenterStateFinded = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (viewMap) {
                    for (Map.Entry<T, Object> entry : viewMap.entrySet()) {
                        try {
                            run(runnable, entry.getKey());
                            isViewInPresenterStateFinded = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (runnableList) {
                    if (isViewInPresenterStateFinded) {
                        runnableList.poll();
                    }
                }
            }
        }
    }

    @UiThread
    public void dispatch(ViewRunnable<T> runnable) {
        boolean isViewInPresenterStateFinded = false;
        synchronized(viewLifecycleMap) {
            for (Map.Entry<T, LifecycleOwner> entry : viewLifecycleMap.entrySet()) {
                try {
                    if (entry.getValue().getLifecycle().getCurrentState().isAtLeast(STARTED)) {
                        run(runnable, entry.getKey());
                        isViewInPresenterStateFinded = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized(viewMap) {
            for (Map.Entry<T, Object> entry : viewMap.entrySet()) {
                isViewInPresenterStateFinded = true;
                try {
                    run(runnable, entry.getKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (!isViewInPresenterStateFinded) {
            synchronized(runnableList) {
                runnableList.add(runnable);
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        synchronized(viewLifecycleMap) {
            for (Map.Entry<T, LifecycleOwner> entry : viewLifecycleMap.entrySet()) {
                entry.getValue().getLifecycle().removeObserver(this);
            }
        }
        super.finalize();
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_START || event == Lifecycle.Event.ON_RESUME) {
            dispatchStored();
        }
    }
}
