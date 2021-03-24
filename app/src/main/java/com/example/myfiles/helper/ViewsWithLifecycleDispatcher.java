package com.example.myfiles.helper;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.WeakHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import static androidx.lifecycle.Lifecycle.State.STARTED;

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
