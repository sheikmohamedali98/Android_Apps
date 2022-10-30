package com.example.miniyoutube.databinding;
import com.example.miniyoutube.R;
import com.example.miniyoutube.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPlayBindingImpl extends FragmentPlayBinding  {

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
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPlayBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }
    private FragmentPlayBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.viewModel == variableId) {
            setViewModel((com.example.miniyoutube.viewmodel.VideoViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.miniyoutube.viewmodel.VideoViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelVideoProperty((androidx.lifecycle.LiveData<com.example.miniyoutube.domain.VideoModel>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelVideoProperty(androidx.lifecycle.LiveData<com.example.miniyoutube.domain.VideoModel> ViewModelVideoProperty, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        java.lang.String viewModelVideoPropertyTnImg = null;
        com.example.miniyoutube.viewmodel.VideoViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<com.example.miniyoutube.domain.VideoModel> viewModelVideoProperty = null;
        com.example.miniyoutube.domain.VideoModel viewModelVideoPropertyGetValue = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.videoProperty
                    viewModelVideoProperty = viewModel.getVideoProperty();
                }
                updateLiveDataRegistration(0, viewModelVideoProperty);


                if (viewModelVideoProperty != null) {
                    // read viewModel.videoProperty.getValue()
                    viewModelVideoPropertyGetValue = viewModelVideoProperty.getValue();
                }


                if (viewModelVideoPropertyGetValue != null) {
                    // read viewModel.videoProperty.getValue().tn_img
                    viewModelVideoPropertyTnImg = viewModelVideoPropertyGetValue.getTn_img();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            com.example.miniyoutube.adapter.AdapterKt.getImage(this.mboundView1, viewModelVideoPropertyTnImg);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.videoProperty
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}