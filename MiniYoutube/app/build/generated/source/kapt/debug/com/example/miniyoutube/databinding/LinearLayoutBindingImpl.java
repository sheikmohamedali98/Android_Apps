package com.example.miniyoutube.databinding;
import com.example.miniyoutube.R;
import com.example.miniyoutube.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LinearLayoutBindingImpl extends LinearLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LinearLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private LinearLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.thumbnail.setTag(null);
        this.videoTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.videoModel == variableId) {
            setVideoModel((com.example.miniyoutube.domain.VideoModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVideoModel(@Nullable com.example.miniyoutube.domain.VideoModel VideoModel) {
        this.mVideoModel = VideoModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.videoModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String videoModelName = null;
        com.example.miniyoutube.domain.VideoModel videoModel = mVideoModel;
        java.lang.String videoModelTnImg = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (videoModel != null) {
                    // read videoModel.name
                    videoModelName = videoModel.getName();
                    // read videoModel.tn_img
                    videoModelTnImg = videoModel.getTn_img();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.example.miniyoutube.adapter.AdapterKt.getImage(this.thumbnail, videoModelTnImg);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.videoTitle, videoModelName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): videoModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}