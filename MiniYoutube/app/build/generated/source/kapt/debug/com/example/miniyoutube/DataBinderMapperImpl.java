package com.example.miniyoutube;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.miniyoutube.databinding.FragmentHomeBindingImpl;
import com.example.miniyoutube.databinding.FragmentPlayBindingImpl;
import com.example.miniyoutube.databinding.LinearLayoutBindingImpl;
import com.example.miniyoutube.databinding.VideoLayoutBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTHOME = 1;

  private static final int LAYOUT_FRAGMENTPLAY = 2;

  private static final int LAYOUT_LINEARLAYOUT = 3;

  private static final int LAYOUT_VIDEOLAYOUT = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.miniyoutube.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.miniyoutube.R.layout.fragment_play, LAYOUT_FRAGMENTPLAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.miniyoutube.R.layout.linear_layout, LAYOUT_LINEARLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.miniyoutube.R.layout.video_layout, LAYOUT_VIDEOLAYOUT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPLAY: {
          if ("layout/fragment_play_0".equals(tag)) {
            return new FragmentPlayBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_play is invalid. Received: " + tag);
        }
        case  LAYOUT_LINEARLAYOUT: {
          if ("layout/linear_layout_0".equals(tag)) {
            return new LinearLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for linear_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_VIDEOLAYOUT: {
          if ("layout/video_layout_0".equals(tag)) {
            return new VideoLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for video_layout is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "videoModel");
      sKeys.put(2, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/fragment_home_0", com.example.miniyoutube.R.layout.fragment_home);
      sKeys.put("layout/fragment_play_0", com.example.miniyoutube.R.layout.fragment_play);
      sKeys.put("layout/linear_layout_0", com.example.miniyoutube.R.layout.linear_layout);
      sKeys.put("layout/video_layout_0", com.example.miniyoutube.R.layout.video_layout);
    }
  }
}
